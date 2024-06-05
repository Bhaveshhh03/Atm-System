package bhavesh.ce2;

//Practical 10A-AWT application that contains the interface to add student information and display the same

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class Ce2 extends Frame implements ActionListener{
    Label l1,l2,l3;
    TextField t1, t2;
    Button b1;
    String s,s1;
    Ce2(){
      l1 = new Label("AccountNo.:");
      
      l2 = new Label("Pin:");
      
      l3= new Label("");
      t1 = new TextField();
      
      t2 = new TextField();
      
      
      b1 = new Button ("Login");
     
      setLayout(new GridLayout(4, 2));
      add(l1);
      add(t1);
      add(l2);
      add(t2);
      add(b1);
      add(l3);
      b1.addActionListener(this);
      addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setSize(400, 400);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
    

    try {
                //Step1-Loading the driver for the Mysql db in the memory
                Class.forName("com.mysql.jdbc.Driver");
                //Step2-Establish the Connection-db url,username,password
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ce2", "root", "22445");
                System.out.println("Succesfull");
                s=t2.getText();
                PreparedStatement ps =con.prepareStatement("select * from atm where id=?");
                ps.setString(1, t1.getText());
                
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    dispose();
                 
                    mainf m=new mainf(s);
                    m.setVisible(true);
                    
                }
                else 
                {
                    l3.setText("Invalid id or pin ");
                }
    }
            catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
    }}

public static void main(String args[])
{
    new Ce2();
}
}
  