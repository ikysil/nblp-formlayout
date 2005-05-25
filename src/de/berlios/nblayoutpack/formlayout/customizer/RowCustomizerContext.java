/*
 * RowCustomizerContext.java
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
public class RowCustomizerContext extends AbstractColumnRowCustomizerContext{
  
  /** Creates a new instance of RowCustomizerContext */
  public RowCustomizerContext(){
  }
  
  public void afterInsert(int index, String spec){
  }
  
  public void beforeDelete(int index){
  }
  
  public void checkSpecification(String spec) throws Exception{
    RowSpec[] specs = RowSpec.decodeSpecs(spec);
  }
  
  public String getValue(){
    if(layoutSupport != null){
      return layoutSupport.getRows();
    }
    if(propertyEditor != null){
      return propertyEditor.getAsText();
    }
    return "";
  }
  
  public void initSpecLabel(JLabel label){
    label.setText("Row specification:");
    label.setDisplayedMnemonic('R');
    label.setDisplayedMnemonicIndex(0);
  }
  
  public void setValue(String spec){
    if(layoutSupport != null){
      layoutSupport.setRows(spec);
    }
    if(propertyEditor != null){
      propertyEditor.setAsText(spec);
    }
  }
  
}
