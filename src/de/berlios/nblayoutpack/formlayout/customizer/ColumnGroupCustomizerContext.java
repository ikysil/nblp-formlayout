/*
 * ColumnGroupCustomizerContext.java
 *
 * Created on January 13, 2005, 9:27 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import javax.swing.*;

import de.berlios.nblayoutpack.formlayout.editors.*;

/**
 *
 * @author  Illya Kysil
 */
public class ColumnGroupCustomizerContext extends AbstractGroupCustomizerContext{
  
  /** Creates a new instance of ColumnGroupCustomizerContext */
  public ColumnGroupCustomizerContext(){
  }
  
  public String getValue(){
    if(layoutSupport != null){
      return GroupsEditor.getAsString(layoutSupport.getColumnGroups());
    }
    if(propertyEditor != null){
      return propertyEditor.getAsText();
    }
    return "";
  }
  
  public void initSpecLabel(JLabel label){
    label.setText("Column group:");
    label.setDisplayedMnemonic('C');
    label.setDisplayedMnemonicIndex(0);
  }
  
  public void setValue(String spec){
    if(layoutSupport != null){
      layoutSupport.setColumnGroups(GroupsEditor.parseGroups(spec));
    }
    if(propertyEditor != null){
      propertyEditor.setAsText(spec);
    }
  }
  
}
