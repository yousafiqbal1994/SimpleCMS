package CMS;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Update
  extends Add
{
  String ID_updated;
  String Name_updated;
  String Price_updated;
  String City_updated;
  private JPanel contentPane;
  private String ID;
  private String Name;
  private String Price;
  private String City;
  private JTextField text1;
  private JTextField text2;
  private JTextField text3;
  private JTable table;
  String sql;
  
  public Update()
  {
    setDefaultCloseOperation(2);
    setBounds(100, 100, 814, 619);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(this.contentPane);
    getContentPane().setLayout(null);
    
    JLabel lblName = new JLabel("Name");
    lblName.setBounds(15, 113, 69, 20);
    getContentPane().add(lblName);
    
    this.text1 = new JTextField();
    this.text1.setBounds(74, 110, 146, 26);
    getContentPane().add(this.text1);
    this.text1.setColumns(10);
    
    JLabel lblPrice = new JLabel("Price");
    lblPrice.setBounds(15, 190, 69, 20);
    getContentPane().add(lblPrice);
    
    this.text2 = new JTextField();
    this.text2.setBounds(74, 187, 146, 26);
    getContentPane().add(this.text2);
    this.text2.setColumns(10);
    
    JLabel lblCity = new JLabel("City");
    lblCity.setBounds(15, 275, 69, 20);
    getContentPane().add(lblCity);
    
    this.text3 = new JTextField();
    this.text3.setBounds(74, 272, 146, 26);
    getContentPane().add(this.text3);
    this.text3.setColumns(10);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(281, 35, 472, 485);
    getContentPane().add(scrollPane);
    
    this.table = new JTable();
    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        int row = Update.this.table.getSelectedRow();
        
        Update.this.Name = Update.this.table.getModel().getValueAt(row, 1).toString();
        Update.this.text1.setText(Update.this.Name);
        Update.this.Price = Update.this.table.getModel().getValueAt(row, 2).toString();
        Update.this.text2.setText(Update.this.Price);
        Update.this.City = Update.this.table.getModel().getValueAt(row, 3).toString();
        Update.this.text3.setText(Update.this.City);
      }
    });
    scrollPane.setViewportView(this.table);
    
    JButton btnUpdate = new JButton("Update");
    btnUpdate.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Update.this.Name_updated = Update.this.Swap(Update.this.text1.getText(), Update.this.Name);
        Update.this.Price_updated = Update.this.Swap(Update.this.text2.getText(), Update.this.Price);
        Update.this.City_updated = Update.this.Swap(Update.this.text3.getText(), Update.this.City);
        Update.this.sql = 
          ("Update stock set itemname ='" + Update.this.Name_updated + "', itemprice ='" + Update.this.Price_updated + "', City ='" + Update.this.City_updated + "'" + "where itemname='" + Update.this.Name + "' OR itemprice='" + Update.this.Price + "' OR City='" + Update.this.City + "'");
        Update.this.UpdateRow();
      }
    });
    btnUpdate.setBounds(86, 398, 115, 29);
    getContentPane().add(btnUpdate);
    database();
  }
  
  private void UpdateRow()
  {
    try
    {
      Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
      Statement mystat = mycon.prepareStatement(this.sql);
      mystat.executeUpdate(this.sql);
      ResultSet myresult = mystat.executeQuery("Select * from stock ORDER BY id asc");
      this.table.setModel(DbUtils.resultSetToTableModel(myresult));
      
      this.text1.setText("");
      this.text2.setText("");
      this.text3.setText("");
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  private void database()
  {
    try
    {
      Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
      
      Statement mystat = mycon.createStatement();
      
      ResultSet myresult = mystat.executeQuery("Select * from stock ORDER BY id asc");
      
      this.table.setModel(DbUtils.resultSetToTableModel(myresult));
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
  
  private String Swap(String str1, String str2)
  {
    String temp = str1;
    str1 = this.ID;
    str2 = temp;
    return str2;
  }
}
