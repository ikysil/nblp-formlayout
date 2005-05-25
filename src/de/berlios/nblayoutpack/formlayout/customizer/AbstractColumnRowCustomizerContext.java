/*
 * AbstractColumnRowCustomizerContext.java
 *
 * Created on January 9, 2005, 3:21 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import java.beans.*;

import de.berlios.nblayoutpack.formlayout.*;

/**
 *
 * @author  Illya Kysil
 */
public abstract class AbstractColumnRowCustomizerContext implements ColumnRowCustomizerContext{
  
  protected FormLayoutSupport layoutSupport;
  protected PropertyEditor propertyEditor;

  /** Creates a new instance of AbstractColumnRowCustomizerContext */
  public AbstractColumnRowCustomizerContext(){
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

}
