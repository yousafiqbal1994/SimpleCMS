package CMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class Delete
  extends JFrame
{
  private JPanel contentPane;
  private JTable table;
  private String click;
  private String sql;
  private int row = -1;
  
  public Delete()
  {
    setDefaultCloseOperation(2);
    setBounds(100, 100, 832, 621);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JButton btnDelete = new JButton("Delete");
    btnDelete.setBounds(680, 28, 115, 29);
    this.contentPane.add(btnDelete);
    
    JScrollPane scrollPane = new JScrollPane();
    
    scrollPane.setBounds(15, 16, 642, 533);
    this.contentPane.add(scrollPane);
    
    this.table = new JTable();
    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        try
        {
          Delete.this.row = Delete.this.table.getSelectedRow();
          String click = Delete.this.table.getModel().getValueAt(Delete.this.row, 0).toString();
          Delete.this.sql = ("Delete from stock where id ='" + click + "'");
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
    scrollPane.setViewportView(this.table);
    database();
    
    btnDelete.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Delete.this.DeleteRow();
      }
    });
  }
  
  private void DeleteRow()
  {
    try
    {
      if (this.row == -1)
      {
        JOptionPane.showMessageDialog(null, "No row selected");
        return;
      }
      Connection mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
      Statement mystat = mycon.prepareStatement(this.sql);
      mystat.executeUpdate(this.sql);
      ResultSet myresult = mystat.executeQuery("Select * from stock ORDER BY id asc");
      
      this.table.setModel(DbUtils.resultSetToTableModel(myresult));
      this.row = -1;
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
}
