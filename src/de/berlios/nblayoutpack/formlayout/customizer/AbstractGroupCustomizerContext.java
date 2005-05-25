/*
 * AbstractGroupCustomizerContext.java
 *
 * Created on January 13, 2005, 9:17 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import java.beans.*;

import de.berlios.nblayoutpack.formlayout.*;
import de.berlios.nblayoutpack.formlayout.editors.*;

/**
 *
 * @author  Illya Kysil
 */
public abstract class AbstractGroupCustomizerContext implements GroupCustomizerContext{
  
  protected FormLayoutSupport layoutSupport;
  protected PropertyEditor propertyEditor;

  /** Creates a new instance of AbstractGroupCustomizerContext */
  public AbstractGroupCustomizerContext(){
  }

  /**
   * Getter for property propertyEditor.
   * @return Value of property propertyEditor.
   */
  public PropertyEditor getPropertyEditor(){
    return propertyEditor;
  }  
  
  /**
   * Setter for property propertyEditor.
   * @param propertyEditor New value of property propertyEditor.
   */
  public void setPropertyEditor(PropertyEditor propertyEditor){
    this.propertyEditor = propertyEditor;
  }  
  
  /**
   * Getter for property layoutSupport.
   * @return Value of property layoutSupport.
   */
  public FormLayoutSupport getLayoutSupport(){
    return layoutSupport;
  }
  
  /**
   * Setter for property layoutSupport.
   * @param layoutSupport New value of property layoutSupport.
   */
  public void setLayoutSupport(FormLayoutSupport layoutSupport){
    this.layoutSupport = layoutSupport;
  }

  public void checkGroup(String group) throws Exception{
    int[][] groups = GroupsEditor.parseGroups(group, false);
    if(groups.length != 1){
      throw new IllegalArgumentException();
    }
  }
  
}
