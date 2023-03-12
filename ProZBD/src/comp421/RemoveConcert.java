package comp421;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveConcert extends JFrame {
	JTextField tid= new JTextField("Tour id");
	JButton del = new JButton("Delete");
	MainFrame mainFrame = null;
	RemoveConcert frame = this;
	
	ResultSet rs;
	String sqlcode;
	SQL newaddr;
	int userid;
	int concid;
	
	public RemoveConcert(int uid,SQL sqlo,MainFrame mainFrame){
		this.mainFrame = mainFrame;
		userid = uid;
		System.out.println("userid = "+ userid);
		newaddr=sqlo;
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(1,2));
		jpanel.add(new JLabel("Tour id:")); jpanel.add(tid);
		this.add(jpanel,BorderLayout.CENTER);
		del.addActionListener(new addrlistener());
		del.setPreferredSize(new Dimension(20,40));
		this.add(del,BorderLayout.SOUTH);
	}
	public static void invoke (int uid, SQL sqlo,MainFrame  mainFrame){
		JFrame Remove = new RemoveConcert(uid,sqlo,mainFrame);
		Remove.setTitle("Remove concert");
		Remove.setVisible(true);
		Remove.setLocationRelativeTo(null);
		Remove.setSize(800,520);
		Remove.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	class addrlistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// sqlcode="select max(concert_id) from concert";
			// rs=newaddr.QueryExchte(sqlcode);
			// try {	
			// 	rs.next();
			// 	concid= rs.getInt(1)+1;
			// } catch (SQLException e1) {
			// 	// TODO Auto-generated catch block
			// 	e1.printStackTrace();
			// }
			
			String Tid= tid.getText();
			
			if(Tid.trim().isEmpty())
				JOptionPane.showMessageDialog(null, "It is required to fill in every blank","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				sqlcode="DELETE from Genre where genre_id = " + Tid + ";DELETE from Track where track_id = " + Tid + ";DELETE from Ticket where ticket_id =" + Tid + ";DELETE from Concert where concert_id =  "+Tid + ";DELETE from country where country_id = "+Tid+ ";DELETE from city where city_id = " + Tid + ";DELETE from venue where venue_id = "+Tid + ";DELETE from Tour where tour_id = " + Tid + ";DELETE from Groupa where group_id = " + Tid;
				newaddr.WriteExcute(sqlcode);
				JOptionPane.showMessageDialog(null, "You have successfully added a new address", "Success", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(AddAddress.class.getResource("success.png")));
				mainFrame.setVisible(true);
				mainFrame.setSearchAndBuyButtonEnable(true);
				frame.dispose();
			}   
		}
	}
}