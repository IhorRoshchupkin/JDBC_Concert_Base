package comp421;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddAddress extends JFrame {
	JTextField tname= new JTextField("Tour name");
	JTextField tid= new JTextField("Tour id");
	JTextField cname= new JTextField("Concert name");
	JTextField date=new JTextField("Date");
	JTextField country = new JTextField("Country");
	JTextField city = new JTextField("City");
	JTextField venue = new JTextField("Venue");
	JTextField capacity = new JTextField("Capacity");
	JTextField g_name = new JTextField("Group name");
	JTextField headliner = new JTextField("Headliner");
	JTextField track_name = new JTextField("Track name");
	JTextField album_name = new JTextField("Album name");
	JTextField genre_name = new JTextField("Genre name");
	JTextField price = new JTextField("Price");
	JTextField buy = new JTextField("Buy link");
	JButton add = new JButton("Add");
	MainFrame mainFrame = null;
	AddAddress frame = this;
	
	ResultSet rs;
	String sqlcode;
	SQL newaddr;
	int userid;
	int concid;
	
	public AddAddress(int uid,SQL sqlo,MainFrame mainFrame){
		this.mainFrame = mainFrame;
		userid = uid;
		System.out.println("userid = "+ userid);
		newaddr=sqlo;
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(15,2));
		jpanel.add(new JLabel("Tour name:")); jpanel.add(tname);
		jpanel.add(new JLabel("Tour id:")); jpanel.add(tid);
		jpanel.add(new JLabel("Concert name:")); jpanel.add(cname);
		jpanel.add(new JLabel("Date:")); jpanel.add(date);
		jpanel.add(new JLabel("Country: ")); jpanel.add(country);
		jpanel.add(new JLabel("City: ")); jpanel.add(city);
		jpanel.add(new JLabel("Venue:")); jpanel.add(venue);
		jpanel.add(new JLabel("Capacity:")); jpanel.add(capacity);
		jpanel.add(new JLabel("Group name:")); jpanel.add(g_name);
		jpanel.add(new JLabel("Headliner: ")); jpanel.add(headliner);
		jpanel.add(new JLabel("Track_name: ")); jpanel.add(track_name);
		jpanel.add(new JLabel("Album_name:")); jpanel.add(album_name);
		jpanel.add(new JLabel("Genre_name:")); jpanel.add(genre_name);
		jpanel.add(new JLabel("Price:")); jpanel.add(price);
		jpanel.add(new JLabel("Buy link:")); jpanel.add(buy);
		this.add(jpanel,BorderLayout.CENTER);
		add.addActionListener(new addrlistener());
		add.setPreferredSize(new Dimension(20,40));
		this.add(add,BorderLayout.SOUTH);
	}
	public static void invoke (int uid, SQL sqlo,MainFrame  mainFrame){
		JFrame address = new AddAddress(uid,sqlo,mainFrame);
		address.setTitle("Add a new address");
		address.setVisible(true);
		address.setLocationRelativeTo(null);
		address.setSize(800,520);
		address.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
			
			String Tname= tname.getText();
			String Tid= tid.getText();
			String Cname= cname.getText();
			String Date= date.getText();
			String Country = country.getText();
			String City = city.getText();
			String Venue = venue.getText();
			String Capacity = capacity.getText();
			String G_name= g_name.getText();
			String Headliner= headliner.getText();
			String Track_name= track_name.getText();
			String Album_name= album_name.getText();
			String Genre_name = genre_name.getText();
			String Price = price.getText();
			String Buy = buy.getText();
			
			if(Tname.trim().isEmpty()||Cname.trim().isEmpty()||Date.trim().isEmpty()||Country.trim().isEmpty()||City.trim().isEmpty()||Venue.trim().isEmpty()||Buy.trim().isEmpty()||Capacity.trim().isEmpty())
				JOptionPane.showMessageDialog(null, "It is required to fill in every blank","Error",JOptionPane.ERROR_MESSAGE);
			else
			{
				sqlcode="insert into groupa values ("+Tid+",\'"+G_name+"\',\'"+Headliner+"\');insert into genre values ("+Tid+","+Tid+",\'"+Genre_name+"\');insert into track values ("+Tid+","+Tid+",\'"+Track_name+"\',\'"+Album_name+"\');insert into venue values ("+Tid+",\'"+Venue+"\',"+Capacity+"); insert into city values ("+Tid+","+Tid+",\'"+City+"\'); insert into country values ("+Tid+","+Tid+",\'"+Country+"\'); insert into tour values ("+Tid+",\'"+Tname+"\'); insert into concert values ("+Tid+","+Tid+", "+Tid+", "+Tid+", \'"+Cname+"\', \'"+Date+"\');insert into ticket values ("+Tid+","+Tid+","+Price+",\'"+Buy+"\') ";
				newaddr.WriteExcute(sqlcode);
				JOptionPane.showMessageDialog(null, "You have successfully added a new address", "Success", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(AddAddress.class.getResource("success.png")));
				mainFrame.setVisible(true);
				mainFrame.setSearchAndBuyButtonEnable(true);
				frame.dispose();
			}   
		}
	}
}