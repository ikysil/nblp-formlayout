/*
 * DefaultConstraintsConverter.java
 *
 * Created on January 8, 2005, 23:19 PM
 */

package de.berlios.nblayoutpack.formlayout.converter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.*;

import org.openide.ErrorManager;

import org.netbeans.modules.form.layoutsupport.*;

import com.jgoodies.forms.debug.*;

import de.berlios.nblayoutpack.formlayout.*;

/**
 *
 * @author  Illya Kysil
 */
public class DefaultConstraintsConverter extends ConstraintsConverter{
  
  /** Creates a new instance of DefaultConstraintsConverter */
  public DefaultConstraintsConverter(){
  }
  
  public void convertConstraints(LayoutSupportContext layoutSupportContext,
  LayoutSupportDelegate layoutSupportDelegate, LayoutConstraints[] previousConstraints,
  LayoutConstraints[] currentConstraints, Component[] components){
    FormLayoutSupport formLayoutSupport = (FormLayoutSupport)layoutSupportDelegate;
    ArrayList xsList = new ArrayList();
    ArrayList ysList = new ArrayList();
    int prevX = 0;
    int prevY = 0;
    int maxX = 0;
    int maxY = 0;
    for(int i = 0; i < components.length; i++){
      Component comp = components[i];
      Rectangle cRect = comp.getBounds();
      xsList.add(new SpaceInfo(cRect.x, cRect.width));
      ysList.add(new SpaceInfo(cRect.y, cRect.height));
      if(cRect.x != prevX){
        if(cRect.x > prevX){
          xsList.add(new SpaceInfo(prevX, cRect.x - prevX));
        }
        else{
          xsList.add(new SpaceInfo(cRect.x, prevX - cRect.x));
        }
      }
      if(cRect.y != prevY){
        if(cRect.y > prevY){
          ysList.add(new SpaceInfo(prevY, cRect.y - prevY));
        }
        else{
          ysList.add(new SpaceInfo(cRect.y, prevY - cRect.y));
        }
      }
      prevX = cRect.x + cRect.width;
      prevY = cRect.y + cRect.height;
      maxX = Math.max(maxX, prevX);
      maxY = Math.max(maxY, prevY);
    }
//    Container container = layoutSupportContext.getPrimaryContainerDelegate();
//    Dimension cDim = container.getSize();
//    ErrorManager.getDefault().log(ErrorManager.WARNING, "cDim: " + cDim);
//    ErrorManager.getDefault().log(ErrorManager.WARNING, "maxX: " + maxX + ", maxY: " + maxY);
//    if(cDim.width - maxX > 0){
//      xsList.add(new SpaceInfo(maxX, cDim.width - maxX));
//    }
//    if(cDim.height - maxY > 0){
//      ysList.add(new SpaceInfo(maxY, cDim.height - maxY));
//    }
    splitSpace(xsList);
    splitSpace(ysList);
    SpaceInfo[] xsi = (SpaceInfo[])xsList.toArray(new SpaceInfo[xsList.size()]);
    SpaceInfo[] ysi = (SpaceInfo[])ysList.toArray(new SpaceInfo[ysList.size()]);
    String[] columns = new String[xsi.length];
    String[] rows = new String[ysi.length];
    copySize(xsi, columns);
    copySize(ysi, rows);
    for(int i = 0; i < components.length; i++){
      Component comp = components[i];
      Rectangle cRect = comp.getBounds();
      Dimension prefSize = comp.getPreferredSize();
      Dimension minSize = comp.getMinimumSize();
      FormLayoutSupport.FormLayoutSupportConstraints constr = (FormLayoutSupport.FormLayoutSupportConstraints)formLayoutSupport.createDefaultConstraints();
      for(int j = 0; j < xsi.length; j++){
        if(xsi[j].position == cRect.x){
          constr.gridX = j + 1;
          constr.gridWidth = 0;
        }
        if((cRect.width > 0) && (xsi[j].position >= cRect.x)){
          constr.gridWidth++;
          cRect.width -= xsi[j].size;
        }
        if(cRect.width == 0){
          if(constr.gridWidth == 1){
            if(xsi[constr.gridX - 1].size == minSize.width){
              columns[constr.gridX - 1] = "min(" + minSize.width + ";pref)";
            }
            if(xsi[constr.gridX - 1].size == prefSize.width){
              columns[constr.gridX - 1] = "pref";
            }
          }
          break;
        }
      }
      for(int j = 0; j < ysi.length; j++){
        if(ysi[j].position == cRect.y){
          constr.gridY = j + 1;
          constr.gridHeight = 0;
        }
        if((cRect.height > 0) && (ysi[j].position >= cRect.y)){
          constr.gridHeight++;
          cRect.height -= ysi[j].size;
        }
        if(cRect.height == 0){
          if(constr.gridHeight == 1){
            if(ysi[constr.gridY - 1].size == minSize.height){
              rows[constr.gridY - 1] = "min(" + minSize.height + ";pref)";
            }
            if(ysi[constr.gridY - 1].size == prefSize.height){
              rows[constr.gridY - 1] = "pref";
            }
          }
          break;
        }
      }
      constr.gridWidth = Math.max(1, constr.gridWidth);
      constr.gridHeight = Math.max(1, constr.gridHeight);
      currentConstraints[i] = constr;
    }
    formLayoutSupport.setColumns(combineSpec(columns));
    formLayoutSupport.setRows(combineSpec(rows));
  }

  private void copySize(SpaceInfo[] si, String[] size){
    for(int i = 0; i < si.length; i++){
      size[i] = String.valueOf(si[i].size);
    }
  }

  private String combineSpec(String[] specs){
    StringBuffer sb = new StringBuffer();
    boolean isFirst = true;
    for(int i = 0; i < specs.length; i++){
      if(isFirst){
        isFirst = false;
      }
      else{
        sb.append(",");
      }
      sb.append(specs[i]);
    }
    return sb.toString();
  }
  
  private void splitSpace(List list){
    List result = new ArrayList();
    List list2 = new ArrayList(list);
    Collections.reverse(list2);
    for(Iterator iter = list.iterator(); iter.hasNext(); ){
      SpaceInfo si1 = (SpaceInfo)iter.next();
      int sipL1 = si1.position;
      int sipR1 = si1.position + si1.size - 1;
      boolean hasIntersections = false;
      for(Iterator iter2 = list2.iterator(); iter2.hasNext(); ){
        SpaceInfo si2 = (SpaceInfo)iter2.next();
        if(si1 == si2){
          break;
        }
        int sipL2 = si2.position;
        int sipR2 = si2.position + si2.size - 1;
        if((sipL1 > sipL2) && (sipL1 < sipR2) ||
           (sipR1 > sipL2) && (sipR1 < sipR2) ||
           (sipL2 > sipL1) && (sipL2 < sipR1) ||
           (sipR2 > sipL1) && (sipR2 < sipR1)){
          hasIntersections = true;
          int L1 = Math.min(sipL1, sipL2);
          int L2 = Math.max(sipL1, sipL2);
          int R1 = Math.min(sipR1, sipR2);
          int R2 = Math.max(sipR1, sipR2);
          int[] sizes = new int[]{L2 - L1, R1 - L2 + 1, R2 - R1};
          int position = L1;
          for(int i = 0; i < sizes.length; i++){
            if(sizes[i] > 0){
              result.add(new SpaceInfo(position, sizes[i]));
            }
            position += sizes[i];
          }
        }
      }
      if(!hasIntersections){
        result.add(si1);
      }
    }
    list2 = new ArrayList(result);
    Collection toRemove = new ArrayList();
    for(Iterator iter = result.iterator(); iter.hasNext(); ){
      SpaceInfo si1 = (SpaceInfo)iter.next();
      int sipL1 = si1.position;
      int sipR1 = si1.position + si1.size - 1;
      for(Iterator iter2 = list2.iterator(); iter2.hasNext(); ){
        SpaceInfo si2 = (SpaceInfo)iter2.next();
        if(si1 == si2){
          continue;
        }
        if((si1.position == si2.position) && (si1.size >= si2.size) && !toRemove.contains(si2)){
          toRemove.add(si1);
          break;
        }
      }
    }
    result.removeAll(toRemove);
    Collections.sort(result);
    list.clear();
    list.addAll(result);
  }
  
  private static class SpaceInfo implements Comparable{
    int position;
    int size;

    public SpaceInfo(int position, int size){
      this.position = position;
      this.size = size;
    }

    public int compareTo(Object o){
      SpaceInfo si = (SpaceInfo)o;
      return position - si.position;
    }

    public String toString(){
      StringBuffer sb = new StringBuffer(getClass().getName()).append("[");
      sb.append("position=").append(position).append(",");
      sb.append("size=").append(size).append("]");
      return sb.toString();
    }
    
  }

  public static void main(String[] args){
    List sil = new ArrayList();
    sil.add(new SpaceInfo(4, 8));
    sil.add(new SpaceInfo(0, 10));
    sil.add(new SpaceInfo(2, 4));
    sil.add(new SpaceInfo(1, 10));
    new DefaultConstraintsConverter().splitSpace(sil);
    for(Iterator iter = sil.iterator(); iter.hasNext(); ){
      SpaceInfo si = (SpaceInfo)iter.next();
      System.out.println(si);
    }
  }
  
}
