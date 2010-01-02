/*
* generated by Xtext
*/
package org.eclipse.b3.labeling;

import java.lang.reflect.Type;

import org.eclipse.b3.backend.evaluator.b3backend.B3FunctionType;
import org.eclipse.b3.backend.evaluator.b3backend.B3ParameterizedType;
import org.eclipse.b3.backend.evaluator.b3backend.BAtExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BChainedExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BFunction;
import org.eclipse.b3.backend.evaluator.b3backend.BLiteralExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BLiteralListExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BLiteralMapExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BLiteralType;
import org.eclipse.b3.backend.evaluator.b3backend.BRegularExpression;
import org.eclipse.b3.backend.evaluator.b3backend.BVariableExpression;
import org.eclipse.b3.build.build.BuildUnit;
import org.eclipse.xtext.ui.core.DefaultLabelProvider;
/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class BeeLangLabelProvider extends DefaultLabelProvider {
	String text(BuildUnit ele) {
		return "unit: " + ele.getName();
	}
	String text(BLiteralExpression ele) {
	  return "literal<"+ safeClassToString(ele.getValue()) +">"+safeToString(ele.getValue());
	}
	String text(B3ParameterizedType ele) {
		Type t = ele.getRawType();
		return "type: "+ safeToString(t);
	}
	String text(B3FunctionType ele) {
		StringBuffer buf = new StringBuffer();
		buf.append("type: (");
		int counter = 0;
		for(Type t : ele.getParameterTypes()) {
			if(counter++ > 0)
				buf.append(", ");
			buf.append(t);
		}
		buf.append(")=>");
		buf.append(safeToString(ele.getReturnType()));
		return buf.toString();
	}
	String text(BFunction ele) {
		return "func: " + ele.getName() + " => " + safeToString(ele.getReturnType());
	}
	String text(BVariableExpression ele) {
		return "value of: " + ele.getName();
	}
	String text(BRegularExpression ele) {
		return "regexp: " + safeToString(ele.getPattern());
	}
	String text(BLiteralListExpression ele) {
		return "list: <" + safeToString(ele.getEntryType())+">";
	}
	String text(BLiteralMapExpression ele) {
		return "list: <" + safeToString(ele.getKeyType())+", "+safeToString(ele.getValueType())+">";
	}
	String text(BChainedExpression ele) {
		return "{ ... }";
	}
	String text(BAtExpression ele) {
		return "[n]";
	}
	String text(BLiteralType ele) {
		return "literal type:";
	}
	
	private String safeToString(Object o) {
		return (o == null) ? "null" : o.toString();
	}
	private String safeClassToString(Object o) {
		return o == null ? "null" : o.getClass().getName();
	}
/*	 
    String image(MyModel ele) {
      return "MyModel.gif";
    }
	 
*/
}
