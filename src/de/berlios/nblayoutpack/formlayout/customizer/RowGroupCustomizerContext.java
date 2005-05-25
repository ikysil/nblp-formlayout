/*
 * RowGroupCustomizerContext.java
 *
 * Created on January 13, 2005, 9:35 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import javax.swing.*;

import de.berlios.nblayoutpack.formlayout.editors.*;

/**
 *
 * @author  Illya Kysil
 */
public class RowGroupCustomizerContext extends AbstractGroupCustomizerContext{
  
  /** Creates a new instance of RowGroupCustomizerContext */
  public RowGroupCustomizerContext(){
  }
  
  public String getValue(){
    if(layoutSupport != null){
      return GroupsEditor.getAsString(layoutSupport.getRowGroups());
    }
    if(propertyEditor != null){
      return propertyEditor.getAsText();
    }
    return "";
  }
  
  public void initSpecLabel(JLabel label){
    label.setText("Row group:");
    label.setDisplayedMnemonic('R');
    label.setDisplayedMnemonicIndex(0);
  }
  
  public void setValue(String spec){
    if(layoutSupport != null){
      layoutSupport.setRowGroups(GroupsEditor.parseGroups(spec));
    }
    if(propertyEditor != null){
      propertyEditor.setAsText(spec);
    }
  }
  
}
