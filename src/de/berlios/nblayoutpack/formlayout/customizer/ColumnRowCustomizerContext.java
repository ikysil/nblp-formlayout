/*
 * ColumnRowCustomizerContext.java
 *
 * Created on January 9, 2005, 3:10 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import javax.swing.*;

/**
 *
 * @author  Illya Kysil
 */
public interface ColumnRowCustomizerContext{

  public void initSpecLabel(JLabel label);
  public String getValue();
  public void setValue(String spec);
  public void checkSpecification(String spec) throws Exception;
  public void afterInsert(int index, String spec);
  public void beforeDelete(int index);

}
