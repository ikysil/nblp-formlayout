/*
 * VAlignEditor.java
 *
 * Created on January 6, 2005, 12:35 AM
 */

package de.berlios.nblayoutpack.formlayout.editors;

import java.beans.*;
import java.io.IOException;

import org.w3c.dom.*;

import org.openide.explorer.propertysheet.editors.*;

import org.netbeans.modules.form.*;

import com.jgoodies.forms.layout.*;

import de.berlios.nblayoutpack.formlayout.*;

/**
 *
 * @author  Illya Kysil
 */
public class VAlignEditor extends PropertyEditorSupport implements XMLPropertyEditor{
  
  /** Creates a new instance of VAlignEditor */
  public VAlignEditor(){
  }
  
  private final String[] tags = {
    FormLayoutSupport.getBundle().getString("VALUE_vAlign_DEFAULT"), // NOI18N
    FormLayoutSupport.getBundle().getString("VALUE_vAlign_CENTER"),  // NOI18N
    FormLayoutSupport.getBundle().getString("VALUE_vAlign_FILL"),    // NOI18N
    FormLayoutSupport.getBundle().getString("VALUE_vAlign_TOP"),     // NOI18N
    FormLayoutSupport.getBundle().getString("VALUE_vAlign_BOTTOM"),  // NOI18N
  };
  private final CellConstraints.Alignment[] values = {
    CellConstraints.DEFAULT,
    CellConstraints.CENTER,
    CellConstraints.FILL,
    CellConstraints.TOP,
    CellConstraints.BOTTOM,
  };
  private final String[] javaInitStrings = {
    "com.jgoodies.forms.layout.CellConstraints.DEFAULT", // NOI18N
    "com.jgoodies.forms.layout.CellConstraints.CENTER",  // NOI18N
    "com.jgoodies.forms.layout.CellConstraints.FILL",    // NOI18N
    "com.jgoodies.forms.layout.CellConstraints.TOP",     // NOI18N
    "com.jgoodies.forms.layout.CellConstraints.BOTTOM",  // NOI18N
  };

  public String[] getTags(){
    return tags;
  }

  public String getAsText(){
    Object value = getValue();
    for(int i=0; i < values.length; i++){
      if(values[i].equals(value)){
        return tags[i];
      }
    }
    return null;
  }

  public void setAsText(String str){
    for(int i=0; i < tags.length; i++){
      if(tags[i].equals(str)){
        setValue(values[i]);
      }
    }
  }

  public String getJavaInitializationString(){
    Object value = getValue();
    for (int i=0; i < values.length; i++){
      if (values[i].equals(value)){
        return javaInitStrings[i];
      }
    }
    return null;
  }

  /** Called to load property value from specified XML subtree. If succesfully loaded,
   * the value should be available via the getValue method.
   * An IOException should be thrown when the value cannot be restored from the specified XML element
   * @param element the XML DOM element representing a subtree of XML from which the value should be loaded
   * @exception IOException thrown when the value cannot be restored from the specified XML element
   */
  public void readFromXML(Node element) throws IOException{
    try{
      Node vNode = element.getAttributes().getNamedItem("value");
      setValue(FormLayoutIntrospector.getAlignment(vNode.getNodeValue()));
    }
    catch(Exception e){
      setValue(FormLayoutIntrospector.getAlignment("default"));
    }
  }
  
  /** Called to store current property value into XML subtree. The property value should be set using the
   * setValue method prior to calling this method.
   * @param doc The XML document to store the XML in - should be used for creating nodes only
   * @return the XML DOM element representing a subtree of XML from which the value should be loaded
   */
  public Node storeToXML(Document doc){
    Element result = doc.createElement("VAlign");
    result.setAttribute("value", getValue().toString());
    return result;
  }

}
