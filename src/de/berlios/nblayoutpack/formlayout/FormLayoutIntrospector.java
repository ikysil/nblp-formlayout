/*
 * FormLayoutIntrospector.java
 *
 * Created on January 3, 2005, 2:37 PM
 */

package de.berlios.nblayoutpack.formlayout;

import java.awt.*;
import java.lang.reflect.*;

import org.openide.ErrorManager;

import com.jgoodies.forms.layout.*;

/**
 *
 * @author  Illya Kysil
 */
public class FormLayoutIntrospector{
  
  private static Constructor layoutConstructor;
  private static Constructor constrConstructor;
  private static Method setColumnGroupsMethod;
  private static Method setRowGroupsMethod;
  private static Method valueOfMethod;

  /** Creates a new instance of FormLayoutIntrospector */
  private FormLayoutIntrospector(){
  }
  
  public static Constructor getLayoutConstructor(){
    if(layoutConstructor == null){
      try{
        layoutConstructor = FormLayout.class.getConstructor(new Class[]{String.class, String.class});
      }
      catch(NoSuchMethodException e){
        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
      }
    }
    return layoutConstructor;
  }
  
  public static Constructor getConstraintsConstructor(){
    if(constrConstructor == null){
      try{
        constrConstructor = CellConstraints.class.getConstructor(new Class[]{
          Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
          CellConstraints.Alignment.class, CellConstraints.Alignment.class,
          Insets.class
        });
      }
      catch(NoSuchMethodException e){
        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
      }
    }
    return constrConstructor;
  }
  
  public static Method getSetColumnGroupsMethod(){
    if(setColumnGroupsMethod == null){
      try{
        setColumnGroupsMethod = FormLayout.class.getMethod("setColumnGroups", new Class[]{int[][].class});
      }
      catch(NoSuchMethodException e){
        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
      }
    }
    return setColumnGroupsMethod;
  }
  
  public static Method getSetRowGroupsMethod(){
    if(setRowGroupsMethod == null){
      try{
        setRowGroupsMethod = FormLayout.class.getMethod("setRowGroups", new Class[]{int[][].class});
      }
      catch(NoSuchMethodException e){
        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
      }
    }
    return setRowGroupsMethod;
  }

  public static CellConstraints.Alignment getAlignment(String value){
    CellConstraints.Alignment result = CellConstraints.DEFAULT;
    if(value == null){
      return result;
    }
    if(valueOfMethod == null){
      try{
        valueOfMethod = CellConstraints.Alignment.class.getDeclaredMethod("valueOf", new Class[]{String.class});
        valueOfMethod.setAccessible(true);
      }
      catch(NoSuchMethodException e){
        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
      }
    }
    try{
      result = (CellConstraints.Alignment)valueOfMethod.invoke(null, new Object[]{value});
    }
    catch(Exception e){
      ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);
    }
    return result;
  }

}
