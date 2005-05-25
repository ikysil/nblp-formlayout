/*
 * ColumnGroupsEditor.java
 *
 * Created on January 13, 2005, 9:55 PM
 */

package de.berlios.nblayoutpack.formlayout.editors;

import de.berlios.nblayoutpack.formlayout.customizer.*;

/**
 *
 * @author  Illya Kysil
 */
public class ColumnGroupsEditor extends GroupsEditor{
  
  /** Creates a new instance of ColumnGroupsEditor */
  public ColumnGroupsEditor(){
  }
  
  protected AbstractGroupCustomizerContext createContext(){
    return new ColumnGroupCustomizerContext();
  }
  
}
