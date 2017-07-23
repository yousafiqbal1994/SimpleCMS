package CMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

public class View extends JFrame
{
  private JPanel contentPane;
  public JTable table;
  
  public View()
  {
    setDefaultCloseOperation(2);
    setBounds(100, 100, 835, 604);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(29, 41, 754, 471);
    this.contentPane.add(scrollPane);
    
    this.table = new JTable();
    scrollPane.setViewportView(this.table);
    database();
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
}
