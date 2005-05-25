/*
 * GroupsEditor.java
 *
 * Created on January 7, 2005, 12:36 PM
 */

package de.berlios.nblayoutpack.formlayout.editors;

import java.awt.*;
import java.beans.*;
import java.io.IOException;

import org.w3c.dom.*;

import org.openide.explorer.propertysheet.editors.*;

import org.netbeans.modules.form.*;

import com.jgoodies.forms.layout.*;

import de.berlios.nblayoutpack.formlayout.*;
import de.berlios.nblayoutpack.formlayout.customizer.*;

/**
 *
 * @author  Illya Kysil
 */
public class GroupsEditor extends PropertyEditorSupport implements XMLPropertyEditor{
  
  private GroupCustomizer customizer;
  
  /** Creates a new instance of GroupsEditor */
  public GroupsEditor(){
  }
  
  public String getAsText(){
    return getAsString((int[][])getValue());
  }
  
  public static String getAsString(int[][] values){
    StringBuffer sb = new StringBuffer("");
    boolean isFirstGroup = true;
    for(int group = 0; group < values.length; group++){
      if(values[group].length > 0){
        if(isFirstGroup){
          isFirstGroup = false;
        }
        else{
          sb.append(";");
        }
        boolean isFirstValue = true;
        for(int item = 0; item < values[group].length; item++){
          if(isFirstValue){
            isFirstValue = false;
          }
          else{
            sb.append(",");
          }
          sb.append(values[group][item]);
        }
      }
    }
    return sb.toString();
  }

  public void setAsText(String str){
    setValue(parseGroups(str));
  }

  public String getJavaInitializationString(){
    int[][] values = (int[][])getValue();
    StringBuffer sb = new StringBuffer("new int[][]{");
    boolean isFirstGroup = true;
    for(int group = 0; group < values.length; group++){
      if(values[group].length > 0){
        if(isFirstGroup){
          isFirstGroup = false;
        }
        else{
          sb.append(", ");
        }
        sb.append("{");
        boolean isFirstValue = true;
        for(int item = 0; item < values[group].length; item++){
          if(isFirstValue){
            isFirstValue = false;
          }
          else{
            sb.append(", ");
          }
          sb.append(values[group][item]);
        }
        sb.append("}");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  public static int[][] parseGroups(String str){
    try{
      return parseGroups(str, true);
    }
    catch(Exception ignored){}
    return null;
  }

  public static int[][] parseGroups(String str, boolean ignoreExceptions) throws Exception{
    String[] groups = str.split(";");
    int[][] values = new int[groups.length][];
    for(int group = 0; group < groups.length; group++){
      String[] items = groups[group].split(",");
      int[] parsedValues = new int[items.length];
      int itemIndex = 0;
      for(int item = 0; item < items.length; item++){
        try{
          parsedValues[itemIndex] = Integer.parseInt(items[item]);
          itemIndex++;
        }
        catch(NumberFormatException e){
          if(!ignoreExceptions){
            throw e;
          }
        }
      }
      int[] itemValues = new int[itemIndex];
      System.arraycopy(parsedValues, 0, itemValues, 0, itemIndex);
      values[group] = itemValues;
    }
    return values;
  }
  
  /** Called to load property value from specified XML subtree. If succesfully loaded,
   * the value should be available via the getValue method.
   * An IOException should be thrown when the value cannot be restored from the specified XML element
   * @param element the XML DOM element representing a subtree of XML from which the value should be loaded
   * @exception IOException thrown when the value cannot be restored from the specified XML element
   */
  public void readFromXML(Node element) throws IOException{
    Node vNode = element.getAttributes().getNamedItem("value");
    setAsText(vNode.getNodeValue());
  }
  
  /** Called to store current property value into XML subtree. The property value should be set using the
   * setValue method prior to calling this method.
   * @param doc The XML document to store the XML in - should be used for creating nodes only
   * @return the XML DOM element representing a subtree of XML from which the value should be loaded
   */
  public Node storeToXML(Document doc){
    Element result = doc.createElement("Groups");
    result.setAttribute("value", getAsText());
    return result;
  }

  public boolean supportsCustomEditor(){
    return true;
  }  

  public Component getCustomEditor(){
    if(customizer == null){
      AbstractGroupCustomizerContext context = createContext();
      context.setPropertyEditor(this);
      customizer = new GroupCustomizer(context);
    }
    customizer.updateData();
    return customizer;
  }  

  protected AbstractGroupCustomizerContext createContext(){
    return new DefaultGroupCustomizerContext();
  }
  
  private class DefaultGroupCustomizerContext extends AbstractGroupCustomizerContext{
    
    public void initSpecLabel(javax.swing.JLabel label){
      label.setText("Group:");
      label.setDisplayedMnemonic('G');
      label.setDisplayedMnemonicIndex(0);
    }
    
    public String getValue(){
      return propertyEditor.getAsText();
    }
    
    public void setValue(String spec){
      propertyEditor.setAsText(spec);
    }
    
  }
  
}
