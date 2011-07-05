package org.eclipse.b3.aggregator.engine;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.DateUtils;
import org.apache.tools.mail.MailMessage;
import org.eclipse.b3.aggregator.Aggregation;
import org.eclipse.b3.aggregator.AggregatorFactory;
import org.eclipse.b3.aggregator.ValidationSet;
import org.eclipse.b3.aggregator.Contact;
import org.eclipse.b3.aggregator.Contribution;
import org.eclipse.b3.aggregator.CustomCategory;
import org.eclipse.b3.aggregator.Feature;
import org.eclipse.b3.aggregator.MappedRepository;
import org.eclipse.b3.aggregator.MappedUnit;
import org.eclipse.b3.aggregator.MetadataRepositoryReference;
import org.eclipse.b3.aggregator.PackedStrategy;
import org.eclipse.b3.aggregator.impl.MetadataRepositoryReferenceImpl;
import org.eclipse.b3.p2.MetadataRepository;
import org.eclipse.b3.p2.util.P2ResourceImpl;
import org.eclipse.b3.p2.util.P2Utils;
import org.eclipse.b3.p2.util.ResourceSetWithAgent;
import org.eclipse.b3.util.ExceptionUtils;
import org.eclipse.b3.util.LogUtils;
import org.eclipse.b3.util.MonitorUtils;
import org.eclipse.b3.util.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.p2.repository.Transport;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

public class Builder extends ModelAbstractCommand {
	public enum ActionType {
		CLEAN, VERIFY, BUILD, CLEAN_BUILD
	}

	public static class PatternOptionHandler extends OptionHandler<Pattern> {
		public PatternOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super Pattern> setter) {
			super(parser, option, setter);
		}

		@Override
		public String getDefaultMetaVariable() {
			return "<regexp>";
		}

		@Override
		public int parseArguments(Parameters params) throws CmdLineException {
			String token = params.getParameter(0);
			try {
				Pattern value = Pattern.compile(token);
				setter.addValue(value);
			}
			catch(PatternSyntaxException ex) {
				throw new CmdLineException(owner, "");
			}
			return 1;
		}
	}

	private static class EmailAddress {
		private final String address;

		private final String personal;

		EmailAddress(String address, String personal) {
			this.address = address;
			this.personal = personal;
		}

		@Override
		public String toString() {
			if(personal == null)
				return address;

			return personal + " <" + address + ">";
		}
	}

	public static final String VERIFICATION_IU_PREFIX = "contributed.content."; //$NON-NLS-1$

	public static final String PDE_TARGET_PLATFORM_NAMESPACE = "A.PDE.Target.Platform";

	public static final String PDE_TARGET_PLATFORM_NAME = "Cannot be installed into the IDE";

	public static final Version ALL_CONTRIBUTED_CONTENT_VERSION = Version.createOSGi(1, 0, 0);

	public static final String COMPOSITE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID +
			".compositeRepository"; //$NON-NLS-1$

	public static final String COMPOSITE_METADATA_TYPE = org.eclipse.equinox.internal.p2.metadata.repository.Activator.ID +
			".compositeRepository"; //$NON-NLS-1$

	public static final String LINE_SEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final String NAMESPACE_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	public static final String PROFILE_ID = "b3AggregatorProfile"; //$NON-NLS-1$

	public static final String REPO_FOLDER_VERIFICATION = "verification"; //$NON-NLS-1$

	public static final String REPO_FOLDER_FINAL = "final"; //$NON-NLS-1$

	public static final String REPO_FOLDER_INTERIM = "interim"; //$NON-NLS-1$

	public static final String REPO_FOLDER_TEMP = "temp"; //$NON-NLS-1$

	public static final String REPO_FOLDER_AGGREGATE = "aggregate"; //$NON-NLS-1$

	public static final String SIMPLE_ARTIFACTS_TYPE = org.eclipse.equinox.internal.p2.artifact.repository.Activator.ID +
			".simpleRepository"; //$NON-NLS-1$

	public static final String SIMPLE_METADATA_TYPE = org.eclipse.equinox.internal.p2.metadata.repository.Activator.ID +
			".simpleRepository"; //$NON-NLS-1$

	public static final String INTERNAL_METADATA_TYPE = "org.eclipse.b3.aggregator.engine.internalRepository"; //$NON-NLS-1$

	public static final String PROP_AGGREGATOR_GENERATED_IU = "org.eclipse.b3.aggregator.generated.IU"; //$NON-NLS-1$

	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmm"); //$NON-NLS-1$

	static final String FEATURE_GROUP_SUFFIX = ".feature.group"; //$NON-NLS-1$

	static final IArtifactKey[] NO_ARTIFACT_KEYS = new IArtifactKey[0];

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	private static final Project PROPERTY_REPLACER = new Project();

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmm"); //$NON-NLS-1$

	static {
		TimeZone utc = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
		PROPERTY_REPLACER.initProperties();
		DATE_FORMAT.setTimeZone(utc);
		TIME_FORMAT.setTimeZone(utc);
		TIMESTAMP_FORMAT.setTimeZone(utc);
	}

	/**
	 * Creates a repository location without the trailing slash that will be added if the standard {@link java.io.File#toURI()} is used.
	 * 
	 * @param repoLocation
	 *            The location. Must be an absolute path.
	 * @return The created URI.
	 * @throws CoreException
	 *             if the argument is not an absolute path
	 */
	public static final URI createURI(File repoLocation) throws CoreException {
		if(repoLocation != null) {
			IPath path = Path.fromOSString(repoLocation.getPath());
			if(path.isAbsolute())
				try {
					String pathStr = path.removeTrailingSeparator().toPortableString();
					if(!pathStr.startsWith("/"))
						// Path starts with a drive letter
						pathStr = "/" + pathStr; //$NON-NLS-1$
					return new URI("file", null, pathStr, null); //$NON-NLS-1$
				}
				catch(URISyntaxException e) {
					throw ExceptionUtils.wrap(e);
				}
		}
		throw ExceptionUtils.fromMessage("File %s is not an absolute path", repoLocation);
	}

	public static String getValidationSetLabel(ValidationSet validationSet) {
		return validationSet == null
				? "<main>"
				: validationSet.getLabel();
	}

	public static String getExceptionMessages(Throwable e) {
		StringBuilder bld = new StringBuilder();
		getExceptionMessages(e, bld);
		return bld.toString();
	}

	public static IInstallableUnit getIU(IMetadataRepository mdr, String id, String version) {
		version = StringUtils.trimmedOrNull(version);
		IQuery<IInstallableUnit> query = version == null
				? QueryUtil.createIUQuery(id)
				: QueryUtil.createIUQuery(id, Version.create(version));
		IQueryResult<IInstallableUnit> result = mdr.query(query, null);
		return !result.isEmpty()
				? result.iterator().next()
				: null;
	}

	private static boolean deleteAndCheck(File folder, String fileName) throws CoreException {
		File file = new File(folder, fileName);
		try {
			return file.delete();
		}
		finally {
			if(file.exists())
				throw ExceptionUtils.fromMessage("Unable to delete file %s\n", file.getAbsolutePath());
		}
	}

	private static boolean deleteMetadataRepository(IMetadataRepositoryManager mdrMgr, File repoFolder)
			throws CoreException {
		URI repoURI = Builder.createURI(repoFolder);
		boolean folderWasRepo = false;

		if(mdrMgr.removeRepository(repoURI))
			folderWasRepo = true;
		if(deleteAndCheck(repoFolder, "compositeContent.jar") && !folderWasRepo)
			folderWasRepo = true;
		if(deleteAndCheck(repoFolder, "compositeContent.xml") && !folderWasRepo)
			folderWasRepo = true;
		if(deleteAndCheck(repoFolder, "content.jar") && !folderWasRepo)
			folderWasRepo = true;
		if(deleteAndCheck(repoFolder, "content.xml") && !folderWasRepo)
			folderWasRepo = true;

		return folderWasRepo;
	}

	private static void deleteTwoLevelMetadataRepository(IMetadataRepositoryManager mdrMgr, File repoFolder)
			throws CoreException {
		deleteMetadataRepository(mdrMgr, repoFolder);

		File[] secondLevelRepositories = repoFolder.listFiles();
		if(secondLevelRepositories != null) {
			for(File repoSubFolder : repoFolder.listFiles()) {
				if(repoSubFolder.isDirectory()) {
					if(deleteMetadataRepository(mdrMgr, repoSubFolder))
						// try to delete the directory if it seems it used to be a metadata repository
						repoSubFolder.delete();
				}
			}
		}
	}

	// === OPTIONS ===

	private static void getExceptionMessages(Throwable e, StringBuilder bld) {
		bld.append(e.getClass().getName());
		bld.append(": ");
		if(e.getMessage() != null)
			bld.append(e.getMessage());

		if(e instanceof CoreException)
			e = ((CoreException) e).getStatus().getException();
		else {
			Throwable t = e.getCause();
			e = (t == e)
					? null
					: t;
		}
		if(e != null) {
			bld.append("\nCaused by: ");
			getExceptionMessages(e, bld);
		}
	}

	// @Option(name = "--buildModel", required = true, usage = "Appoints the aggregation definition that drives the execution")
	// private File buildModelLocation;

	private static void send(String host, int port, EmailAddress from, List<EmailAddress> toList, EmailAddress cc,
			String subject, String message) throws IOException {
		MailMessage mailMessage = new MailMessage(host, port);
		mailMessage.from(from.toString());
		for(EmailAddress to : toList)
			mailMessage.to(to.toString());
		if(cc != null)
			mailMessage.cc(cc.toString());
		mailMessage.setSubject(subject);
		mailMessage.setHeader("Date", DateUtils.getDateForHeader());
		mailMessage.setHeader("Content-Type", "text/plain; charset=us-ascii");
		PrintStream out = mailMessage.getPrintStream();
		out.print(message);
		mailMessage.sendAndClose();
	}

	@Option(name = "--action", usage = "Specifies the type of the execution. Default is BUILD.")
	private ActionType action = ActionType.BUILD;

	@Option(name = "--buildId", usage = "Assigns a build identifier to the aggregation. "
			+ "The identifier is used to identify the build in notification emails. Defaults to: "
			+ "build-<timestamp> where <timestamp> is formatted according as yyyyMMddHHmm, i.e. build-200911031527", metaVar = "<ID>")
	private String buildID;

	@Option(name = "--buildRoot", usage = "Controls the output. Defaults to the build root defined in the"
			+ " aggregation definition.")
	private File buildRoot;

	@Option(name = "--logURL", usage = "The URL that will be pasted into the emails. "
			+ "Should normally point to the a public URL for output log for the aggregator so "
			+ "that the receiver can browse the log for details on failures.", metaVar = "<url>")
	private String logURL;

	@Option(name = "--production", usage = "Indicates that the build is running in real production. "
			+ "That means that no mock emails will be sent. Instead, the contacts listed for each contribution will get emails when things go wrong.")
	private boolean production = false;

	@Option(name = "--mockEmailCc", usage = "Becomes the CC receiver of the mock-emails sent from the aggregator", metaVar = "<address>")
	private String mockEmailCC;

	@Option(name = "--emailFrom", usage = "Becomes the sender of the emails sent from the aggregator. "
			+ "Defaults to the build master defined in the aggregator definition.", metaVar = "<address>")
	private String emailFrom;

	@Option(name = "--emailFromName", usage = "Mock sender's name", metaVar = "<name>")
	private String emailFromName;

	@Option(name = "--mockEmailTo", usage = "Becomes the receiver of the mock-emails sent from the aggregator", metaVar = "<address>")
	private String mockEmailTo;

	@Option(name = "--smtpHost", usage = "The SMTP host to talk to when sending emails. Defaults to \"localhost\".", metaVar = "<host>")
	private String smtpHost;

	// Deprecated options

	@Option(name = "--smtpPort", usage = "The SMTP port number to use when talking to the SMTP host. Default is 25.", metaVar = "<port>")
	private int smtpPort;

	@Option(name = "--subjectPrefix", usage = "The prefix to use for the subject when sending emails. "
			+ "Defaults to the label defined in the aggregation definition. "
			+ "The subject is formatted as: \"[<subjectPrefix>] Failed for build <buildId>\"", metaVar = "<subject>")
	private String subjectPrefix;

	@Option(name = "--packedStrategy", usage = "(Deprecated) Controls how mirroring is done of packed artifacts found in the source repository."
			+ "Defaults to the setting in the aggregation definition.")
	private PackedStrategy packedStrategy;

	@Option(name = "--trustedContributions", usage = "(Deprecated) A comma separated list of contributions with repositories that will be referenced directly "
			+ "(through a composite repository) rather than mirrored into the final repository "
			+ "(even if the repository is set to mirror artifacts by default)", metaVar = "<contributions>")
	private String trustedContributions;

	@Option(name = "--validationContributions", usage = "(Deprecated) A comma separated list of contributions with repositories that will be used for aggregation validation only rather than mirrored or referenced into the final repository.", metaVar = "<contributions>")
	private String validationContributions;;

	@Option(name = "--mavenResult", usage = "(Deprecated) Tells the aggregator to generate a hybrid repository that is compatible with p2 and maven2.")
	private Boolean mavenResult;

	@Option(name = "--mirrorReferences", usage = "(Deprecated) Mirror all meta-data repository references from the contributed repositories.")
	private boolean mirrorReferences = false;

	// End of deprecated options

	@Option(name = "--referenceIncludePattern", usage = "(Deprecated) Include only those references that matches the given regular expression pattern.", metaVar = "<regexp>", handler = PatternOptionHandler.class)
	private Pattern referenceIncludePattern;

	// === END OF OPTIONS ===

	@Option(name = "--referenceExcludePattern", usage = "(Deprecated) Exclude all references that matches the given regular expression pattern. An exclusion takes precedence over an inclusion in case both patterns match a reference.", metaVar = "<regexp>", handler = PatternOptionHandler.class)
	private Pattern referenceExcludePattern;

	@Argument
	private List<String> unparsed = new ArrayList<String>();

	private Aggregation aggregator;

	private String buildLabel;

	private String buildMasterEmail;

	private String buildMasterName;

	private List<IInstallableUnit> categoryIUs;

	private List<IInstallableUnit> validationIUs;

	private boolean sendmail = false;

	final private Set<IInstallableUnit> allUnitsToAggregate = new HashSet<IInstallableUnit>();

	final private Map<ValidationSet, Set<IInstallableUnit>> unitsToAggregateMap = new HashMap<ValidationSet, Set<IInstallableUnit>>();

	private Set<MappedRepository> exclusions;

	private CompositeMetadataRepository sourceComposite;

	private IProvisioningAgent provisioningAgent;

	private boolean fromIDE;

	private Map<ValidationSet, String> safeValidationSetNameMap = new HashMap<ValidationSet, String>();

	private Map<MappedRepository, String> safeRepositoryNameMap = new HashMap<MappedRepository, String>();

	private Set<String> safeRepositoryNames = new HashSet<String>();

	/**
	 * Mark the specified repository as not eligible for verbatim mapping.
	 * 
	 * @param repository
	 *            The repository for which to exclude a mapping
	 * @param rc
	 *            The required capability to be excluded/replaced
	 */
	public void addMappingExclusion(MappedRepository repository) {
		if(exclusions == null) {
			exclusions = new HashSet<MappedRepository>();
		}
		exclusions.add(repository);
	}

	public Aggregation getAggregator() {
		return aggregator;
	}

	public Set<IInstallableUnit> getAllUnitsToAggregate() {
		return allUnitsToAggregate;
	}

	public String getBuildID() {
		return buildID;
	}

	public File getBuildModelLocation() {
		return buildModelLocation;
	}

	public File getBuildRoot() {
		return buildRoot;
	}

	public List<IInstallableUnit> getCategoryIUs() {
		return categoryIUs;
	}

	public Collection<String> getChildrenSubdirectories() {
		ArrayList<String> childrenSubdirectories = new ArrayList<String>(unitsToAggregateMap.size());

		for(ValidationSet validationSet : unitsToAggregateMap.keySet()) {
			childrenSubdirectories.add(getValidationSetSubdirectory(validationSet));
		}

		return childrenSubdirectories;
	}

	public List<ValidationSet> getValidationSetsIncludingMain() {
		List<ValidationSet> validationSets = aggregator.getValidationSets();
		List<ValidationSet> validationSetsIncludingMain = new ArrayList<ValidationSet>(
			1 + validationSets.size());
		// null validationSet is the main (implicit) one
		// note that it must come first otherwise some directories created during
		// creation of validationSets will be deleted
		validationSetsIncludingMain.add(null);
		validationSetsIncludingMain.addAll(validationSets);
		return validationSetsIncludingMain;
	}

	public String getValidationSetSubdirectory(ValidationSet validationSet) {
		String safeName = getSafeValidationSetName(validationSet);
		if(safeName == null)
			return "";
		return "/validationSet_" + safeName;
	}

	public String getMappedRepositoryVerificationIUName(MappedRepository repository) {
		return VERIFICATION_IU_PREFIX + "repository_" + getSafeRepositoryName(repository);
	}

	/**
	 * @return the provisioningAgent
	 */
	public IProvisioningAgent getProvisioningAgent() {
		return provisioningAgent;
	}

	public String getSafeValidationSetName(ValidationSet validationSet) {
		if(validationSet == null)
			return null;

		String safeName = safeValidationSetNameMap.get(validationSet);

		if(safeName != null)
			return safeName;

		safeName = validationSet.getLabel().replaceAll("[^-0-9a-zA-Z_.~]", "_");

		if(safeValidationSetNameMap.values().contains(safeName))
			throw new IllegalArgumentException("Could not generate safe unique name for validationSet: " +
					validationSet.getLabel());

		safeValidationSetNameMap.put(validationSet, safeName);

		return safeName;
	}

	public String getSafeRepositoryName(MappedRepository repository) {
		String safeName = safeRepositoryNameMap.get(repository);

		if(safeName != null)
			return safeName;

		int i;
		String location;
		{
			org.eclipse.emf.common.util.URI repoURI = org.eclipse.emf.common.util.URI.createURI(repository.getResolvedLocation());
			String scheme = repoURI.scheme();

			location = repoURI.toString();

			// discard scheme, if present
			i = (scheme != null)
					? scheme.length() + 1
					: 0;

			// discard leading slashes
			while(location.charAt(i) == '/')
				++i;

			if(i > 0)
				location = location.substring(i);

			// make a safe name out of the rest of the URI
			location = location.replaceAll("[^-0-9a-zA-Z_.~]", "_");
		}

		i = 0;
		safeName = location;
		while(safeRepositoryNames.contains(safeName)) {
			if(++i < 0) // if overflow
				throw new IllegalArgumentException("Could not generate safe unique name for mapped repository: " +
						repository.getLocation());

			safeName = location + '_' + i;
		}

		safeRepositoryNames.add(safeName);
		safeRepositoryNameMap.put(repository, safeName);

		return safeName;
	}

	@Override
	public String getShortDescription() {
		return "Aggregates source repositories into a resulting repository using aggregator definition";
	}

	public CompositeMetadataRepository getSourceComposite() {
		return sourceComposite;
	}

	public URI getSourceCompositeURI() throws CoreException {
		return createURI(new File(buildRoot, REPO_FOLDER_INTERIM));
	}

	public URI getTargetCompositeURI() throws CoreException {
		if(aggregator.getValidationSets().isEmpty())
			return null;
		return createURI(new File(buildRoot, REPO_FOLDER_FINAL));
	}

	public File getTempRepositoryFolder() {
		return new File(buildRoot, REPO_FOLDER_TEMP);
	}

	public Transport getTransport() {
		return (Transport) provisioningAgent.getService(Transport.SERVICE_NAME);
	}

	public Set<IInstallableUnit> getUnitsToAggregate(ValidationSet validationSet) {
		Set<IInstallableUnit> units = unitsToAggregateMap.get(validationSet);

		if(units == null) {
			units = new HashSet<IInstallableUnit>();
			unitsToAggregateMap.put(validationSet, units);
		}

		return units;
	}

	public List<IInstallableUnit> getValidationIUs() {
		return validationIUs;
	}

	public String getVerificationIUName(ValidationSet validationSet) {
		if(validationSet == null)
			return VERIFICATION_IU_PREFIX + "main";

		return VERIFICATION_IU_PREFIX + "validationSet_" + getSafeValidationSetName(validationSet);
	}

	public boolean isCleanBuild() {
		return action == ActionType.CLEAN_BUILD;
	}

	/**
	 * Checks if the repository can be included verbatim. If it can, the builder will include a reference to it in a
	 * composite repository instead of copying everything into the aggregate.
	 * 
	 * @param repo
	 *            The repository to check
	 * @return true if the repository is mapped verbatim.
	 */
	public boolean isMapVerbatim(MappedRepository repo) {
		return !(repo.isMapExclusive() || repo.isMirrorArtifacts()) &&
				StringUtils.trimmedOrNull(repo.getCategoryPrefix()) == null &&
				!(exclusions != null && exclusions.contains(repo) && "p2".equals(repo.getNature()));
	}

	public boolean isMatchedReference(String reference) {
		reference = StringUtils.trimmedOrNull(reference);
		if(reference == null)
			return false;

		if(referenceIncludePattern != null) {
			Matcher m = referenceIncludePattern.matcher(reference);
			if(!m.matches())
				return false;
		}

		if(referenceExcludePattern != null) {
			Matcher m = referenceExcludePattern.matcher(reference);
			if(m.matches())
				return false;
		}
		return true;
	}

	public boolean isMirrorReferences() {
		return mirrorReferences;
	}

	public boolean isProduction() {
		return production;
	}

	public boolean isTopLevelCategory(IInstallableUnit iu) {
		return iu != null && "true".equalsIgnoreCase(iu.getProperty(InstallableUnitDescription.PROP_TYPE_CATEGORY)) &&
				!"true".equalsIgnoreCase(iu.getProperty(InstallableUnitDescription.PROP_TYPE_GROUP));
	}

	public boolean isVerifyOnly() {
		return action == ActionType.VERIFY;
	}

	public void removeUnitsToAggregate(ValidationSet validationSet) {
		unitsToAggregateMap.remove(validationSet);
	}

	/**
	 * Run the build
	 * 
	 * @param monitor
	 */
	public int run(boolean fromIDE, IProgressMonitor monitor) throws Exception {
		this.fromIDE = fromIDE;
		int ticks;
		switch(action) {
			case CLEAN:
				ticks = 50;
				break;
			case VERIFY:
				ticks = 200;
				break;
			case BUILD:
				ticks = 2150;
				break;
			default:
				ticks = 2200;
		}
		MonitorUtils.begin(monitor, ticks);

		try {
			if(buildModelLocation == null)
				throw ExceptionUtils.fromMessage("No buildmodel has been set");

			Date now = new Date();
			if(buildID == null)
				buildID = "build-" + DATE_FORMAT.format(now) + TIME_FORMAT.format(now);

			if(smtpHost == null)
				smtpHost = "localhost";

			if(smtpPort <= 0)
				smtpPort = 25;

			loadModel();

			provisioningAgent = P2Utils.createDedicatedProvisioningAgent(new File(buildRoot, "p2").toURI());

			switch(action) {
				case CLEAN:
				case CLEAN_BUILD:
					cleanAll();
					break;
				default:
					cleanMetadata(provisioningAgent);
			}

			if(action == ActionType.CLEAN)
				return 0;

			cleanMemoryCaches();

			// Associate current resource set with the dedicated provisioning agent
			((ResourceSetWithAgent) resourceSet).setProvisioningAgent(provisioningAgent);

			buildRoot.mkdirs();
			if(!buildRoot.exists())
				throw ExceptionUtils.fromMessage("Failed to create folder %s", buildRoot);

			// Remove previously used profiles.
			//
			IProfileRegistry profileRegistry = P2Utils.getProfileRegistry(provisioningAgent);
			try {
				List<String> knownIDs = new ArrayList<String>();
				for(IProfile profile : profileRegistry.getProfiles()) {
					String id = profile.getProfileId();
					if(id.startsWith(PROFILE_ID))
						knownIDs.add(id);
				}

				for(String id : knownIDs)
					profileRegistry.removeProfile(id);

				String instArea = buildRoot.toString();
				Map<String, String> props = new HashMap<String, String>();
				// TODO Where is PROP_FLAVOR gone?
				//props.put(IProfile.PROP_FLAVOR, "tooling"); //$NON-NLS-1$
				props.put(IProfile.PROP_NAME, aggregator.getLabel());
				props.put(IProfile.PROP_DESCRIPTION, format("Default profile during %s build", aggregator.getLabel()));
				props.put(IProfile.PROP_CACHE, instArea);
				props.put(IProfile.PROP_INSTALL_FOLDER, instArea);
				profileRegistry.addProfile(PROFILE_ID, props);
			}
			finally {
				P2Utils.ungetProfileRegistry(provisioningAgent, profileRegistry);
			}

			loadAllMappedRepositories();

			List<ValidationSet> validationSetsIncludingMain = getValidationSetsIncludingMain();
			runCompositeGenerator(MonitorUtils.subMonitor(monitor, 70));

			// we generate the verification IUs in a separate loop
			// to detect non p2 related problems early
			for(ValidationSet validationSet : validationSetsIncludingMain)
				runVerificationIUGenerator(validationSet, MonitorUtils.subMonitor(monitor, 15));

			for(ValidationSet validationSet : validationSetsIncludingMain)
				runRepositoryVerifier(validationSet, MonitorUtils.subMonitor(monitor, 100));

			runCategoriesRepoGenerator(MonitorUtils.subMonitor(monitor, 15));

			if(action != ActionType.VERIFY) {
				for(ValidationSet validationSet : validationSetsIncludingMain)
					runMetadataMirroring(validationSet, MonitorUtils.subMonitor(monitor, 100));
				runMirroring(MonitorUtils.subMonitor(monitor, 2000));
			}
			return 0;
		}
		catch(Throwable e) {
			LogUtils.error(e, "Build failed! Exception was %s", getExceptionMessages(e));
			if(e instanceof Exception)
				throw (Exception) e;

			throw new Exception(e);
		}
		finally {
			if(provisioningAgent != null) {
				P2Utils.destroyProvisioningAgent(provisioningAgent);
				provisioningAgent = null;
			}

			if(fromIDE && buildRoot != null) {
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				for(IContainer rootContainer : wsRoot.findContainersForLocationURI(buildRoot.toURI()))
					try {
						rootContainer.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 10));
					}
					catch(CoreException e) {
						// ignore
					}
			}

			monitor.done();
		}
	}

	public void sendEmail(Contribution contrib, List<String> errors) {
		boolean useMock = (mockEmailTo != null);
		if(!((production || useMock) && sendmail))
			return;

		try {
			EmailAddress buildMaster = new EmailAddress(buildMasterEmail, buildMasterName);
			EmailAddress emailFromAddr;
			if(emailFrom != null)
				emailFromAddr = new EmailAddress(emailFrom, emailFromName);
			else
				emailFromAddr = buildMaster;

			List<EmailAddress> toList = new ArrayList<EmailAddress>();
			if(contrib == null)
				toList.add(buildMaster);
			else
				for(Contact contact : contrib.getContacts())
					toList.add(new EmailAddress(contact.getEmail(), contact.getName()));

			StringBuilder msgBld = new StringBuilder();
			msgBld.append("The following error");
			if(errors.size() > 1)
				msgBld.append('s');
			msgBld.append(" occured when building ");
			msgBld.append(buildLabel);
			msgBld.append(":\n\n");
			for(String error : errors) {
				msgBld.append(error);
				msgBld.append("\n\n");
			}

			if(logURL != null) {
				msgBld.append("Check the log file for more information: ");
				msgBld.append(logURL);
				msgBld.append('\n');
			}

			if(useMock) {
				msgBld.append("\nThis is a mock mail. Real recipients would have been:\n");
				for(EmailAddress to : toList) {
					msgBld.append("  ");
					msgBld.append(to);
					msgBld.append('\n');
				}
			}
			String msgContent = msgBld.toString();
			if(subjectPrefix == null)
				subjectPrefix = buildLabel;

			String subject = format("[%s] Failed for build %s", subjectPrefix, buildID);

			msgBld.setLength(0);
			msgBld.append("Sending email to: ");
			for(EmailAddress to : toList) {
				msgBld.append(to);
				msgBld.append(',');
			}
			msgBld.append(buildMaster);
			if(useMock) {
				msgBld.append(" *** Using mock: ");
				if(mockEmailTo != null) {
					msgBld.append(mockEmailTo);
					if(mockEmailCC != null) {
						msgBld.append(',');
						msgBld.append(mockEmailTo);
					}
				}
				else
					msgBld.append(mockEmailCC);
				msgBld.append(" ***");
			}
			LogUtils.info(msgBld.toString());
			LogUtils.info("From: %s", emailFromAddr);
			LogUtils.info("Subject: %s", subject);
			LogUtils.info("Message content: %s", msgContent);

			List<EmailAddress> recipients;
			EmailAddress ccRecipient = null;
			if(useMock) {
				recipients = mockRecipients();
				ccRecipient = mockCCRecipient();
			}
			else {
				recipients = toList;
				if(contrib != null)
					ccRecipient = buildMaster;
			}
			send(smtpHost, smtpPort, emailFromAddr, recipients, ccRecipient, subject, msgContent);

		}
		catch(IOException e) {
			LogUtils.error(e, "Failed to send email: %s", e.getMessage());
		}
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public void setBuildID(String buildId) {
		this.buildID = buildId;
	}

	public void setBuildModelLocation(File buildModelLocation) {
		this.buildModelLocation = buildModelLocation;
	}

	public void setBuildRoot(File buildRoot) {
		this.buildRoot = buildRoot;
	}

	public void setCategoryIUs(List<IInstallableUnit> categoryIUs) {
		this.categoryIUs = categoryIUs;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public void setEmailFromName(String emailFromName) {
		this.emailFromName = emailFromName;
	}

	public void setLogLevel(int level) {
		throw new UnsupportedOperationException("Log levels are not supported");
	}

	public void setLogURL(String logURL) {
		this.logURL = logURL;
	}

	public void setMirrorReferences(boolean mirrorReferences) {
		this.mirrorReferences = mirrorReferences;
	}

	public void setMockEmailCC(String mockEmailCc) {
		this.mockEmailCC = mockEmailCc;
	}

	public void setMockEmailTo(String mockEmailTo) {
		this.mockEmailTo = mockEmailTo;
	}

	public void setProduction(boolean production) {
		this.production = production;
	}

	public void setReferenceExcludePattern(String pattern) {
		pattern = StringUtils.trimmedOrNull(pattern);
		referenceExcludePattern = pattern == null
				? null
				: Pattern.compile(pattern);
	}

	public void setReferenceIncludePattern(String pattern) {
		pattern = StringUtils.trimmedOrNull(pattern);
		referenceIncludePattern = pattern == null
				? null
				: Pattern.compile(pattern);
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setSourceComposite(CompositeMetadataRepository sourceComposite) {
		this.sourceComposite = sourceComposite;
	}

	public void setSubjectPrefix(String subjectPrefix) {
		this.subjectPrefix = subjectPrefix;
	}

	public void setValidationIUs(List<IInstallableUnit> validationIUs) {
		this.validationIUs = validationIUs;
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception {
		if(unparsed.size() > 0)
			throw new Exception("Too many arguments");
		return run(false, monitor);
	}

	private void cleanAll() throws CoreException {
		if(buildRoot.exists()) {
			FileUtils.deleteAll(buildRoot);
			if(buildRoot.exists())
				throw ExceptionUtils.fromMessage("Failed to delete folder %s", buildRoot.getAbsolutePath());
		}
	}

	private void cleanMemoryCaches() {
		safeValidationSetNameMap.clear();
		safeRepositoryNameMap.clear();
		safeRepositoryNames.clear();
		categoryIUs = null;
		allUnitsToAggregate.clear();
		unitsToAggregateMap.clear();
	}

	private void cleanMetadata(IProvisioningAgent agent) throws CoreException {
		IMetadataRepositoryManager mdrMgr = P2Utils.getRepositoryManager(agent, IMetadataRepositoryManager.class);
		try {
			File finalRepo = new File(buildRoot, Builder.REPO_FOLDER_FINAL);
			deleteMetadataRepository(mdrMgr, finalRepo);
			deleteTwoLevelMetadataRepository(mdrMgr, new File(finalRepo, Builder.REPO_FOLDER_AGGREGATE));
			File interimRepo = new File(buildRoot, Builder.REPO_FOLDER_INTERIM);
			deleteMetadataRepository(mdrMgr, interimRepo);
			deleteTwoLevelMetadataRepository(mdrMgr, new File(interimRepo, Builder.REPO_FOLDER_VERIFICATION));
		}
		finally {
			P2Utils.ungetRepositoryManager(provisioningAgent, mdrMgr);
		}
	}

	private void loadAllMappedRepositories() throws CoreException {
		LogUtils.info("Loading all repositories");

		Set<MetadataRepositoryReference> repositoriesToLoad = new HashSet<MetadataRepositoryReference>();

		Aggregation aggregator = getAggregator();
		ResourceSet topSet = ((EObject) aggregator).eResource().getResourceSet();
		// first, set up asynchronous loading jobs so that the repos are loaded in parallel
		for(MetadataRepositoryReference repo : aggregator.getAllMetadataRepositoryReferences(true)) {
			org.eclipse.emf.common.util.URI repoURI = org.eclipse.emf.common.util.URI.createGenericURI(
				"b3", repo.getNature() + ":" + repo.getResolvedLocation(), null);
			P2ResourceImpl res = (P2ResourceImpl) topSet.getResource(repoURI, false);
			if(res == null)
				res = (P2ResourceImpl) topSet.createResource(repoURI);
			res.startAsynchronousLoad();
			repositoriesToLoad.add(repo);
		}

		try {
			Map<Contribution, List<String>> errors = new HashMap<Contribution, List<String>>();
			// and now, wait until all the jobs are done (we need all repositories anyway)
			for(MetadataRepositoryReference repo : repositoriesToLoad) {
				MetadataRepository mdr;
				if((mdr = repo.getMetadataRepository()) == null || ((EObject) mdr).eIsProxy()) {
					Contribution contrib = null;
					if(repo instanceof MappedRepository)
						contrib = (Contribution) ((EObject) repo).eContainer();
					String msg = String.format("Unable to load repository %s:%s", repo.getNature(), repo.getLocation());
					LogUtils.error(msg);

					if(fromIDE)
						throw ExceptionUtils.fromMessage(msg);

					List<String> contribErrors = errors.get(contrib);
					if(contribErrors == null)
						errors.put(contrib, contribErrors = new ArrayList<String>());
					contribErrors.add(msg);
				}
			}

			if(errors.size() > 0) {
				for(Map.Entry<Contribution, List<String>> entry : errors.entrySet())
					sendEmail(entry.getKey(), entry.getValue());
				throw ExceptionUtils.fromMessage("Not all repositories could be loaded (see log for details)");
			}
		}
		catch(CoreException e) {
			for(MetadataRepositoryReference repo : repositoriesToLoad) {
				repo.cancelRepositoryLoad();
			}
			throw e;
		}
	}

	/**
	 * Loads the model into memory
	 * 
	 * @throws CoreException
	 *             If something goes wrong with during the process
	 */
	private void loadModel() throws CoreException {
		try {
			aggregator = loadModelFromFile();

			verifyContributions();

			if(packedStrategy != null)
				aggregator.setPackedStrategy(packedStrategy);

			if(trustedContributions != null) {
				for(String contributionLabel : trustedContributions.split(",")) {
					contributionLabel = StringUtils.trimmedOrNull(contributionLabel);
					boolean found = false;

					if(contributionLabel != null)
						for(Contribution contribution : aggregator.getContributions()) {
							if(contributionLabel.equals(contribution.getLabel())) {
								for(MappedRepository repository : contribution.getRepositories(true))
									repository.setMirrorArtifacts(false);
								found = true;
							}
						}

					if(!found)
						throw ExceptionUtils.fromMessage("Unable to trust contribution " + contributionLabel +
								": contribution does not exist");
				}
			}

			if(validationContributions != null) {
				for(String contributionLabel : validationContributions.split(",")) {
					contributionLabel = StringUtils.trimmedOrNull(contributionLabel);
					boolean found = false;
					HashSet<MappedUnit> removedMappings = new HashSet<MappedUnit>();

					if(contributionLabel != null) {
						Iterator<Contribution> iterator = aggregator.getContributions().iterator();
						while(iterator.hasNext()) {
							Contribution contribution = iterator.next();

							if(contributionLabel.equals(contribution.getLabel())) {
								for(MappedRepository repository : contribution.getRepositories(true)) {
									MetadataRepositoryReferenceImpl validationRepo = (MetadataRepositoryReferenceImpl) AggregatorFactory.eINSTANCE.createMetadataRepositoryReference();
									validationRepo.setNature(repository.getNature());
									validationRepo.setLocation(repository.getLocation());
									aggregator.getValidationRepositories().add(validationRepo);

									removedMappings.addAll(repository.getFeatures());
								}
								iterator.remove();
								found = true;
							}
						}
					}

					if(!found)
						throw ExceptionUtils.fromMessage("Unable to use contribution " + contributionLabel +
								" for validation only: contribution does not exist");

					for(CustomCategory customCategory : aggregator.getCustomCategories()) {
						Iterator<Feature> iterator = customCategory.getFeatures().iterator();
						while(iterator.hasNext()) {
							Feature feature = iterator.next();
							if(removedMappings.contains(feature))
								iterator.remove();
						}
					}
				}

				EList<ValidationSet> validationSets = aggregator.getValidationSets();
				if(validationSets.size() > 0) {
					// TODO handle custom categories spanning validationSets - for now we remove all but features contributed
					// by contributions which are part of the main (implicit) validationSet from the custom categories
					HashSet<MappedUnit> allMainValidationSetFeatures = new HashSet<MappedUnit>();

					for(Contribution mainValidationSetContribution : aggregator.getValidationSetContributions(null)) {
						for(MappedRepository mainValidationSetRepository : mainValidationSetContribution.getRepositories())
							allMainValidationSetFeatures.addAll(mainValidationSetRepository.getFeatures());
					}

					for(CustomCategory customCategory : aggregator.getCustomCategories()) {
						Iterator<Feature> iterator = customCategory.getFeatures().iterator();
						while(iterator.hasNext()) {
							Feature feature = iterator.next();
							if(!allMainValidationSetFeatures.contains(feature))
								iterator.remove();
						}
					}
				}
			}

			if(mavenResult != null)
				aggregator.setMavenResult(mavenResult.booleanValue());

			if(trustedContributions != null && aggregator.isMavenResult())
				throw ExceptionUtils.fromMessage("Options --trustedContributions cannot be used if maven result is required");

			sendmail = aggregator.isSendmail();
			buildLabel = aggregator.getLabel();

			Contact buildMaster = aggregator.getBuildmaster();
			if(buildMaster != null) {
				buildMasterName = buildMaster.getName();
				buildMasterEmail = buildMaster.getEmail();
			}

			Diagnostic diag = Diagnostician.INSTANCE.validate((EObject) aggregator);
			if(diag.getSeverity() == Diagnostic.ERROR) {
				for(Diagnostic childDiag : diag.getChildren())
					LogUtils.error(childDiag.getMessage());
				throw ExceptionUtils.fromMessage("Build model validation failed: %s", diag.getMessage());
			}

			if(buildRoot == null) {
				setBuildRoot(new File(PROPERTY_REPLACER.replaceProperties(aggregator.getBuildRoot())));

				if(!buildRoot.isAbsolute())
					setBuildRoot(new File(buildModelLocation.getParent(), buildRoot.getPath()).getAbsoluteFile());
			}
			else if(!buildRoot.isAbsolute())
				setBuildRoot(buildRoot.getAbsoluteFile());
		}
		catch(Exception e) {
			throw ExceptionUtils.wrap(e);
		}
	}

	private EmailAddress mockCCRecipient() throws UnsupportedEncodingException {
		EmailAddress mock = null;
		if(mockEmailCC != null)
			mock = new EmailAddress(mockEmailCC, null);
		return mock;
	}

	private List<EmailAddress> mockRecipients() throws UnsupportedEncodingException {
		if(mockEmailTo != null)
			return Collections.singletonList(new EmailAddress(mockEmailTo, null));
		return Collections.emptyList();
	}

	private void runCategoriesRepoGenerator(IProgressMonitor monitor) throws CoreException {
		CategoriesGenerator generator = new CategoriesGenerator(this);
		generator.run(monitor);
	}

	private void runCompositeGenerator(IProgressMonitor monitor) throws CoreException {
		SourceCompositeGenerator generator = new SourceCompositeGenerator(this);
		generator.run(monitor);
	}

	private void runMetadataMirroring(ValidationSet validationSet, IProgressMonitor monitor) throws CoreException {
		MetadataMirrorGenerator generator = new MetadataMirrorGenerator(this, validationSet);
		generator.run(monitor);
	}

	private void runMirroring(IProgressMonitor monitor) throws CoreException {
		MirrorGenerator generator = new MirrorGenerator(this);
		generator.run(monitor);
	}

	private void runRepositoryVerifier(ValidationSet validationSet, IProgressMonitor monitor) throws CoreException {
		RepositoryVerifier ipt = new RepositoryVerifier(this, validationSet);
		ipt.run(monitor);
	}

	private void runVerificationIUGenerator(ValidationSet validationSet, IProgressMonitor monitor)
			throws CoreException {
		VerificationIUGenerator generator = new VerificationIUGenerator(this, validationSet);
		generator.run(monitor);
	}

	private void verifyContributions() throws CoreException {
		List<String> errors = new ArrayList<String>();
		for(Contribution contribution : aggregator.getContributions()) {
			Resource res = ((EObject) contribution).eResource();
			for(Resource.Diagnostic diag : res.getErrors()) {
				String msg = res.getURI() + ": " + diag.toString();
				LogUtils.error(msg);
				errors.add(msg);
			}
		}

		if(errors.size() > 0) {
			sendEmail(null, errors);
			throw ExceptionUtils.fromMessage("Not all contributions could be parsed");
		}
	}
}
