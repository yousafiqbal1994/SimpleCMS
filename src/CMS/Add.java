package CMS;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Add
  extends JFrame
{
  private JPanel contentPane;
  public JTextField textField;
  public JTextField textField_1;
  public JTextField textField_2;
  public JTextField textField_3;
  private JLabel lblDate;
  
  public Add()
  {
    setDefaultCloseOperation(2);
    setBounds(100, 100, 770, 478);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    getContentPane().setLayout(null);
    this.contentPane.setLayout(null);
    
    JLabel lblItemId = new JLabel("Item ID");
    lblItemId.setBounds(34, 63, 69, 20);
    getContentPane().add(lblItemId);
    
    this.textField = new JTextField();
    this.textField.setBounds(118, 63, 146, 26);
    getContentPane().add(this.textField);
    this.textField.setColumns(10);
    
    JLabel lblItemName = new JLabel("Item name");
    lblItemName.setBounds(34, 249, 82, 20);
    getContentPane().add(lblItemName);
    
    this.textField_1 = new JTextField();
    this.textField_1.setBounds(118, 246, 146, 26);
    getContentPane().add(this.textField_1);
    this.textField_1.setColumns(10);
    
    JLabel lblItemPrice = new JLabel("Item price");
    lblItemPrice.setBounds(407, 66, 82, 20);
    getContentPane().add(lblItemPrice);
    
    this.textField_2 = new JTextField();
    this.textField_2.setBounds(510, 63, 146, 26);
    getContentPane().add(this.textField_2);
    this.textField_2.setColumns(10);
    
    this.lblDate = new JLabel("City");
    this.lblDate.setBounds(407, 249, 69, 20);
    getContentPane().add(this.lblDate);
    
    this.textField_3 = new JTextField();
    this.textField_3.setBounds(510, 246, 146, 26);
    getContentPane().add(this.textField_3);
    this.textField_3.setColumns(10);
    
    JButton btnAdd = new JButton("Add");
    btnAdd.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Add.this.database();
      }
    });
    btnAdd.setBounds(279, 349, 115, 29);
    this.contentPane.add(btnAdd);
  }
  
  private void database()
  {
    try
    {
      Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
      
      Statement mystat = mycon.createStatement();
      String Item_ID = this.textField.getText();
      String Item_Name = this.textField_1.getText();
      String Item_Price = this.textField_2.getText();
      String Item_City = this.textField_3.getText();
      if ((Item_ID.equals("")) || (Item_Name.equals("")) || (Item_Price.equals("")) || (Item_City.equals("")))
      {
        JOptionPane.showMessageDialog(null, "Please insert missing values.");
        return;
      }
      String sqladd = "INSERT INTO stock(id,itemname,itemprice,City)VALUES(" + Item_ID + ",'" + Item_Name + "','" + Item_Price + "','" + Item_City + "')";
      mystat.executeUpdate(sqladd);
      JOptionPane.showMessageDialog(null, "Value Inserted.");
      clear();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void clear()
  {
    this.textField.setText("");
    this.textField_1.setText("");
    this.textField_2.setText("");
    this.textField_3.setText("");
  }
}
