package comp421;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewConcert extends JFrame {
	JTable cview= new JTable();
	JButton views = new JButton("Add");
	JTableHeader header = cview.getTableHeader();
	MainFrame mainFrame = null;
	ViewConcert frame = this;
	
	
	SQL sql;
	ResultSet rs;
	String sqlcode;
	SQL newaddr;
	int userid;
	int concid;
	
	public ViewConcert(int uid,SQL sqlo,MainFrame mainFrame){
		this.mainFrame = mainFrame;
		userid = uid;
		System.out.println("userid = "+ userid);
		newaddr=sqlo;
		JPanel jpanel = new JPanel();
		JScrollPane jScrollPane =  new JScrollPane(cview,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollBar bar = jScrollPane.getVerticalScrollBar();
		jScrollPane.setPreferredSize(new Dimension(1200, 720));
		jpanel.add(jScrollPane);
		this.add(jpanel,BorderLayout.CENTER);
		views.addActionListener(new addrlistener());
		views.setPreferredSize(new Dimension(20,40));
		
		this.add(views,BorderLayout.SOUTH);
		cview.setRowHeight(40);
	}
	public static void invoke (int uid, SQL sqlo,MainFrame  mainFrame){
		JFrame view = new ViewConcert(uid,sqlo,mainFrame);
		view.setTitle("View concerts");
		view.setVisible(true);
		view.setLocationRelativeTo(null);
		view.setSize(700,420);
		view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	class addrlistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
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
					"JOIN Ticket ON Concert.concert_id = Ticket.concert_id";
            ResultSet rs = newaddr.QueryExchte(sql);

            // Extract data from result set
            try {
				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
					   return false;
					}
				};
				model.addColumn("Tour ID");
				model.addColumn("Tour Name");
				model.addColumn("Concert Name");
				model.addColumn("Date");
				model.addColumn("Country Name");
				model.addColumn("City Name");
				model.addColumn("Venue Name");
				model.addColumn("Venue Capacity");
				model.addColumn("Group Name");
				model.addColumn("Headliner");
				model.addColumn("Track Name");
				model.addColumn("Album Name");
				model.addColumn("Genre Name");
				model.addColumn("Price");
				model.addColumn("Buy Link");
				
				cview.getTableHeader();
				cview.setModel(model);
				while (rs.next()) {
					int tour_id = rs.getInt(1);
					String Tour_name = rs.getString(2);
					String concert_name = rs.getString(3);
					String date = rs.getString(4);
					String country_name = rs.getString(5);
					String city_name = rs.getString(6);
					String venue_name = rs.getString(7);
					int venue_capacity = rs.getInt(8);
					String group_name  = rs.getString(9);
					String headliner = rs.getString(10);
					String track_name = rs.getString(11);
					String album_name = rs.getString(12);
					String genre_name  = rs.getString(13);
					int price = rs.getInt(14);
					String buy_link = rs.getString(15);
					

					String stringNumber = Integer.toString(tour_id);
					String stringNumber1 = Integer.toString(venue_capacity);
					String stringNumber2 = Integer.toString(price);
					model.addRow(new Object[]{stringNumber, Tour_name, concert_name, date, country_name, city_name, venue_name, stringNumber1, group_name, headliner, track_name, album_name, genre_name, stringNumber2, buy_link});
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

				newaddr.WriteExcute(sqlcode);
				JOptionPane.showMessageDialog(null, "You have successfully added a new address", "Success", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(AddAddress.class.getResource("success.png")));
				
				
				mainFrame.setVisible(true);
				mainFrame.setSearchAndBuyButtonEnable(true);
				frame.dispose();
			}   
		}
	}