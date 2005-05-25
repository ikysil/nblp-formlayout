/*
 * RowEditor.java
 *
 * Created on January 7, 2005, 1:52 PM
 */

package de.berlios.nblayoutpack.formlayout.editors;

import java.awt.*;
import java.beans.*;

import org.netbeans.modules.form.*;

import com.jgoodies.forms.layout.*;

import de.berlios.nblayoutpack.formlayout.*;
import de.berlios.nblayoutpack.formlayout.customizer.*;

/**
 *
 * @author  Illya Kysil
 */
public class RowEditor extends PropertyEditorSupport{
  
  private ColumnRowCustomizer customizer;

  /** Creates a new instance of RowEditor */
  public RowEditor(){
  }
  
  public String getAsText(){
    return (String)getValue();
  }

  public void setAsText(String str){
    setValue(str);
  }

  public String getJavaInitializationString(){
    return new StringBuffer("\"").append(getAsText()).append("\"").toString();
  }

  public boolean supportsCustomEditor(){
    return true;
  }  
  
  public Component getCustomEditor(){
    if(customizer == null){
      RowCustomizerContext context = new RowCustomizerContext();
      context.setPropertyEditor(this);
      customizer = new ColumnRowCustomizer(context);
    }
    customizer.updateData();
    return customizer;
  }  
  
}
