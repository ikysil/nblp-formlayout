/*
 * GroupCustomizerContext.java
 *
 * Created on January 13, 2005, 9:13 PM
 */

package de.berlios.nblayoutpack.formlayout.customizer;

import javax.swing.*;

/**
 *
 * @author  Illya Kysil
 */
public interface GroupCustomizerContext{
  
  public void initSpecLabel(JLabel label);
  public String getValue();
  public void setValue(String spec);
  public void checkGroup(String group) throws Exception;

}
