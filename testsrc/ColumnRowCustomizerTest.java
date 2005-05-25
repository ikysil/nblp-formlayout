/*
 * ColumnRowCustomizerTest.java
 *
 * Created on January 7, 2005, 9:44 PM
 */

import java.awt.*;

import javax.swing.*;

import com.jgoodies.forms.layout.*;

import de.berlios.nblayoutpack.formlayout.customizer.*;

/**
 *
 * @author  Illya Kysil
 */
public class ColumnRowCustomizerTest implements ColumnRowCustomizerContext{
  
  /** Creates a new instance of ColumnRowCustomizerTest */
  public ColumnRowCustomizerTest(){
  }
  
  public void checkSpecification(String spec) throws Exception{
    ColumnSpec[] specs = ColumnSpec.decodeSpecs(spec);
  }
  
  public String getValue(){
    return "4dlu,p,4dlu";
  }
  
  public void setValue(String spec){
    System.out.println(spec);
  }
  
  public void initSpecLabel(JLabel label){
    label.setText("Column specification:");
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("ColumnRowCustomizer Test");
    frame.getContentPane().add(new ColumnRowCustomizer(new ColumnRowCustomizerTest()));
    frame.pack();
    frame.setLocation(200, 200);
    frame.show();
  }
  
  public void afterInsert(int index, String spec){
  }
  
  public void beforeDelete(int index){
  }
  
}
