/*
 * ColumnCustomizerContext.java
 *
 * Created on January 9, 2005, 3:19 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import java.awt.*;
import java.beans.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.layout.*;

import de.berlios.nblayoutpack.formlayout.*;

/**
 *
 * @author  Illya Kysil
 */
public class ColumnCustomizerContext extends AbstractColumnRowCustomizerContext{
  
  /** Creates a new instance of ColumnCustomizerContext */
  public ColumnCustomizerContext(){
  }
  
  public void afterInsert(int index, String spec){
  }
  
  public void beforeDelete(int index){
  }
  
  public void checkSpecification(String spec) throws Exception{
    ColumnSpec[] specs = ColumnSpec.decodeSpecs(spec);
  }
  
  public String getValue(){
    if(layoutSupport != null){
      return layoutSupport.getColumns();
    }
    if(propertyEditor != null){
      return propertyEditor.getAsText();
    }
    return "";
  }
  
  public void initSpecLabel(JLabel label){
    label.setText("Column specification:");
    label.setDisplayedMnemonic('C');
    label.setDisplayedMnemonicIndex(0);
  }
  
  public void setValue(String spec){
    if(layoutSupport != null){
      layoutSupport.setColumns(spec);
    }
    if(propertyEditor != null){
      propertyEditor.setAsText(spec);
    }
  }
  
}
