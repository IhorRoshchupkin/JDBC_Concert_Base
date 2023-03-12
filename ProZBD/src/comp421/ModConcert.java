package comp421;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModConcert extends JFrame {
	JTextField tid= new JTextField("Tour id");
	JTextField tname= new JTextField("Tour name");
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
	JButton search = new JButton("Add");
	JButton mod = new JButton("Add");
	MainFrame mainFrame = null;
	ModConcert frame = this;
	
	SQL adduser;
	ResultSet rs;
	String sqlcode;
	SQL newaddr;
	int userid;
	int concid;
	SQL sql;
	
	public ModConcert(int uid,SQL sqlo,MainFrame mainFrame){
		this.mainFrame = mainFrame;
		userid = uid;
		adduser=sqlo;
		sql = sqlo;
		System.out.println("userid = "+ userid);
		newaddr=sqlo;
		JPanel jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(15,2));
		jpanel.add(new JLabel("Tour id:")); jpanel.add(tid);
		jpanel.add(new JLabel("Tour name:")); jpanel.add(tname);
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
		search.addActionListener(new addrlistener());
		search.setPreferredSize(new Dimension(20,40));
		mod.addActionListener(new modlistener());
		mod.setPreferredSize(new Dimension(20,40));
		this.add(search,BorderLayout.SOUTH);
		this.add(mod,BorderLayout.EAST);
	}
	public static void invoke (int uid, SQL sqlo,MainFrame  mainFrame){
		JFrame modCon = new ModConcert(uid,sqlo,mainFrame);
		modCon.setTitle("Add a new address");
		modCon.setVisible(true);
		modCon.setLocationRelativeTo(null);
		modCon.setSize(800,520);
		modCon.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	class addrlistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
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
				sqlcode="DELETE from Genre where genre_id = " + Tid + ";DELETE from Track where track_id = " + Tid + ";DELETE from Ticket where ticket_id =" + Tid + ";DELETE from Concert where concert_id =  "+Tid + ";DELETE from country where country_id = "+Tid+ ";DELETE from city where city_id = " + Tid + ";DELETE from venue where venue_id = "+Tid + ";DELETE from Tour where tour_id = " + Tid + ";DELETE from Groupa where group_id = " + Tid;
				newaddr.WriteExcute(sqlcode);
				sqlcode="insert into groupa values ("+Tid+",\'"+G_name+"\',\'"+Headliner+"\');insert into genre values ("+Tid+","+Tid+",\'"+Genre_name+"\');insert into track values ("+Tid+","+Tid+",\'"+Track_name+"\',\'"+Album_name+"\');insert into venue values ("+Tid+",\'"+Venue+"\',"+Capacity+"); insert into city values ("+Tid+","+Tid+",\'"+City+"\'); insert into country values ("+Tid+","+Tid+",\'"+Country+"\'); insert into tour values ("+Tid+",\'"+Tname+"\'); insert into concert values ("+Tid+","+Tid+", "+Tid+", "+Tid+", \'"+Cname+"\', \'"+Date+"\');insert into ticket values ("+Tid+","+Tid+","+Price+",\'"+Buy+"\') ";
				newaddr.WriteExcute(sqlcode);
				JOptionPane.showMessageDialog(null, "You have successfully added a new address", "Success", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(AddAddress.class.getResource("success.png")));
				mainFrame.setVisible(true);
				mainFrame.setSearchAndBuyButtonEnable(true);
				frame.dispose();
			}   
		}
	}
	class modlistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String Tid= tid.getText();
			String sqlCode = ""; 			
					String sql = "SELECT Groupa.group_id, Tour.tour_name, Concert.concert_name, Concert.date, Country.country_name , City.city_name , Venue.venue_name, Venue.venue_capacity, Groupa.group_name, Groupa.headliner , Track.track_name, Track.album_name, Genre.genre_name,Ticket.price, Ticket.buy_link " +
					"FROM Groupa " +
					"JOIN genre ON groupa.group_id = genre.genre_id " +
					"JOIN track ON groupa.group_id = track.track_id " +
					"JOIN Concert ON Groupa.group_id = Concert.group_id " +
					"JOIN country ON Concert.concert_id = Country.country_id " +
					"JOIN city ON Country.country_id = city.city_id " +
					"JOIN venue ON City.city_id = venue.venue_id " +
					"JOIN Tour ON Concert.tour_id = Tour.tour_id " +
					"JOIN Ticket ON Concert.concert_id = Ticket.concert_id"+
					" WHERE tour.tour_id = "+ Tid;
            ResultSet rs = newaddr.QueryExchte(sql);
			try {
				while (rs.next()) {
					int tour_id = rs.getInt(1);
					String Tour_name = rs.getString(2);
					String concert_name = rs.getString(3);
					String dates = rs.getString(4);
					String country_name = rs.getString(5);
					String city_name = rs.getString(6);
					String venue_name = rs.getString(7);
					int venue_capacity = rs.getInt(8);
					String group_name  = rs.getString(9);
					String headliners = rs.getString(10);
					String track_names = rs.getString(11);
					String album_names = rs.getString(12);
					String genre_names  = rs.getString(13);
					int pricec = rs.getInt(14);
					String buy_link = rs.getString(15);
					
					String stringNumber = Integer.toString(tour_id);
					String stringNumber1 = Integer.toString(venue_capacity);
					String stringNumber2 = Integer.toString(pricec);

					tid.setText(stringNumber);
					tname.setText(Tour_name);
					cname.setText(concert_name);
					date.setText(dates);
					country.setText(country_name);
					city.setText(city_name);
					venue.setText(venue_name);
					capacity.setText(stringNumber1);
					g_name.setText(Tour_name);
					headliner.setText(headliners);
					track_name.setText(track_names);
					genre_name.setText(genre_names);
					album_name.setText(album_names);
					price.setText(stringNumber2);
					buy.setText(buy_link);

				}
		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}
}
}