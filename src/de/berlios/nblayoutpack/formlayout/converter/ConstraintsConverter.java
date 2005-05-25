/*
 * ConstraintsConverter.java
 *
 * Created on January 8, 2005, 23:16 PM
 */

package de.berlios.nblayoutpack.formlayout.converter;

import java.awt.*;

import org.netbeans.modules.form.layoutsupport.*;

/**
 *
 * @author  Illya Kysil
 */
public abstract class ConstraintsConverter{

  public abstract void convertConstraints(LayoutSupportContext layoutSupportContext,
  LayoutSupportDelegate layoutSupportDelegate, LayoutConstraints[] previousConstraints,
  LayoutConstraints[] currentConstraints, Component[] components);

}
