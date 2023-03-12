package comp421;
// sql code to be implemented in button  --done 
// test if the address is empty 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame{
	JTextField login_id = new JTextField ("user id");
	JTextField pass = new JTextField("Password(Telefon)");
	JButton login = new JButton("Log in");
	JButton back = new JButton("Back");
	
	SQL loginsql;
	ResultSet rs;
	String sqlcode;
	String lid;
	String pnum;
	MainFrame mainFrame = null;
	Login frame = this;
	public Login(SQL sqlo,MainFrame mainFrame){
		loginsql=sqlo;
		this.mainFrame = mainFrame;
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,2));
		panel1.add(new JLabel("User id: ")); panel1.add (login_id);
		panel1.add(new JLabel("Phone Number: "));panel1.add(pass);
		this.add(panel1, BorderLayout.CENTER);
		JPanel buttonpanel=new JPanel();
		buttonpanel.setLayout(new GridLayout(1,2,25,25));
		buttonpanel.add(login); buttonpanel.add(back);
		login.addActionListener(new loginListener());
		back.addActionListener(new loginListener());
		this.add(buttonpanel,BorderLayout.SOUTH);
	}
	public static void invoke(SQL sqlo,MainFrame mainFrame){
		JFrame login = new Login(sqlo,mainFrame);
		login.setTitle("User log in");
		login.setLocationRelativeTo(null);
		login.setSize(400,150);
		login.setVisible(true);
		login.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	class loginListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==login){
				lid = login_id.getText();
				pnum = pass.getText();
				if(lid.trim().isEmpty() & pnum.trim().isEmpty())
					JOptionPane.showMessageDialog(null,"User id and phone number cannot leave blank ","Error", JOptionPane.ERROR_MESSAGE);
				else if(lid.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "You must input your user id","Error", JOptionPane.ERROR_MESSAGE);
				else if(pnum.trim().isEmpty())
					JOptionPane.showMessageDialog(null, "You must input your phone number","Error", JOptionPane.ERROR_MESSAGE);
				else
					try {
						// The information is correct
						if(pnum.equals(getresult())){
							int userid= Integer.parseInt(lid); 
							JOptionPane.showMessageDialog(null, "You have logged in successfully", "Log in successfully", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(Login.class.getResource("success.png")));
						
							mainFrame.setUserid(userid);
							mainFrame.setAddAddressButtonEnable(true);
							mainFrame.setVisible(true);
							frame.dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "The user id or Phone number is not correct, Please enter again", "Log in failed",JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException | HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
						}
			}
			else if(e.getSource()==back){
				mainFrame.setVisible(true);
				frame.dispose();
			}
		}
	}
	public String getresult() throws SQLException{
		
		sqlcode="select pass from login where login_id = " + lid;// Check the pnum based on the lid
		rs=loginsql.QueryExchte(sqlcode);
		rs.next();
		String pnumindb = rs.getString(1);
		System.out.println("for login_id "+lid+" the pass should be "+ pnumindb);
		return pnumindb;
	}
	
	public boolean isbuyer(String lid){
		sqlcode="select login_id from login where login_id = "+lid;
		rs=loginsql.QueryExchte(sqlcode);
		int rowCount = 0;
         try {
             rs.last();
             rowCount = rs.getRow();
             rs.first();
         } catch (Exception e) {
             // TODO: handle exception
             e.printStackTrace();
         }
         if(rowCount == 0){			   
         	return false;
         }
         return true;
	}
	
}