package CMS;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainGUI
  extends JFrame
{
  private JPanel contentPane;
  
  public static void main(String[] args)
  {
    MainGUI frame = new MainGUI();
    frame.setVisible(true);
  }
  
  public MainGUI()
  {
    setDefaultCloseOperation(3);
    setBounds(100, 100, 851, 620);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("<html><font color='red'>Welcome to CMS</font></html>");
    lblNewLabel.setBounds(242, 76, 386, 58);
    lblNewLabel.setFont(new Font("Serif", 0, 50));
    this.contentPane.add(lblNewLabel);
    
    JButton btnViewStock = new JButton("View Stock");
    btnViewStock.setBounds(375, 216, 125, 29);
    this.contentPane.add(btnViewStock);
    btnViewStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        View viewobj = new View();
        viewobj.setVisible(true);
      }
    });
    JButton btnAddStock = new JButton("Add Stock");
    btnAddStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Add addobj = new Add();
        addobj.setVisible(true);
      }
    });
    btnAddStock.setBounds(375, 272, 125, 29);
    this.contentPane.add(btnAddStock);
    
    JButton btnUpdateStock = new JButton("Update Stock");
    btnUpdateStock.setBounds(375, 327, 125, 29);
    this.contentPane.add(btnUpdateStock);
    btnUpdateStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Update updobj = new Update();
        updobj.setVisible(true);
      }
    });
    JButton btnDeleteStock = new JButton("Delete Stock");
    btnDeleteStock.setBounds(375, 384, 125, 29);
    this.contentPane.add(btnDeleteStock);
    btnDeleteStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Delete delobj = new Delete();
        delobj.setVisible(true);
      }
    });
    JButton btnCloseCms = new JButton("Close CMS");
    btnCloseCms.setBounds(375, 438, 125, 29);
    this.contentPane.add(btnCloseCms);
    
    JLabel lblNewLabel_1 = new JLabel("");
    lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\YouCaf Iqbal\\Desktop\\Files\\cms.png"));
    lblNewLabel_1.setBounds(0, 0, 829, 564);
    this.contentPane.add(lblNewLabel_1);
    btnCloseCms.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        System.exit(0);
      }
    });
  }
}
