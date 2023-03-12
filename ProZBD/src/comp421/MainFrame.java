package comp421;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MainFrame extends JFrame{

	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	JButton addAddressButton = new JButton("Add concert info");
	JButton modConc = new JButton("Modify concert info");
	JButton SearchButton = new JButton("Search concert");
	JButton DeleteConcert = new JButton ("Usun concert");
	JButton viewconcert = new JButton ("View concert");
	JButton QuitButton = new JButton("Quit");
	JLabel noticeString = new JLabel("Please login or Register first");
	JLabel reminderString = new JLabel("");
	MainFrame mainFrame = this;
	SQL sql = null;
	int userid;
	public MainFrame() throws SQLException
	{
		// run initial the sql, build connection 
		sql = new SQL();
		 userid = 0;
		 
		// button panel for frame
		 JPanel mainArc  = new JPanel();
	     mainArc.setLayout(new GridLayout(8,1,0,0));
	     mainArc.setSize(200, 400);
	    // listener for main frame, need sql passed to subframe 
	     MainButtonListener listener = new MainButtonListener(sql); //need to pass the sql class and userid 
	
	    loginButton.addActionListener(listener);
	    mainArc.add(loginButton);
	    registerButton.addActionListener(listener);
		mainArc.add(registerButton);
		
		addAddressButton.addActionListener(listener);
        addAddressButton.setEnabled(false);
		mainArc.add(addAddressButton);
		DeleteConcert.addActionListener(listener);
		DeleteConcert.setEnabled(false);
		mainArc.add(DeleteConcert);
		modConc.addActionListener(listener);
		modConc.setEnabled(false);
		mainArc.add(modConc);
		viewconcert.addActionListener(listener);
		viewconcert.setEnabled(false);
		mainArc.add(viewconcert);
		SearchButton.addActionListener(listener);
		SearchButton.setEnabled(false);
		mainArc.add(SearchButton);
		
		QuitButton.addActionListener(listener);
	    mainArc.add(QuitButton); 
	    
	    
	    //setUserid(106);
	    this.add(mainArc,BorderLayout.WEST);
		//Right Side 
	    
	    JPanel logoPanel = new JPanel();
	    JLabel imageLabel = new JLabel(new ImageIcon(MainFrame.class.getResource("logo.png")));
	    logoPanel.add(imageLabel);
	    
	    JPanel stringPanel =  new JPanel();
	    
	    stringPanel.add(noticeString);
	    
	    JPanel stringPanel2 = new JPanel();
	    
	    stringPanel2.add(reminderString);
	    JPanel rightPanel = new JPanel();
	    
	    rightPanel.setLayout(new GridLayout(0,1));
	    rightPanel.add(logoPanel);
	    rightPanel.add(stringPanel);
	    rightPanel.add(stringPanel2);
	    
	    this.add(rightPanel, BorderLayout.EAST);   
	}
	
	
	public void setUserid(int id) throws SQLException
	{
		userid = id;
		noticeString.setText("You have login with user id "+ id);
		System.out.println("Userid is set to "+ userid);
		setAddAddressButtonEnable(true);
		setLoginButtonEnable(false);
		setRegisterEnable(false);
		//check if there is address ;
		String sqlCode = "select * from address where userid = "+id;
		java.sql.ResultSet result = sql.QueryExchte(sqlCode);
		if(result.next())
		{
			setSearchAndBuyButtonEnable(true);
		}
		else
		{
			reminderString.setText("Please add an address before your shopping");
		}
		
		
	}
	
	public void setAddAddressButtonEnable(boolean b)
	{
		addAddressButton.setEnabled(b);
	}
	public void setSearchAndBuyButtonEnable(boolean b)
	{
		reminderString.setText("Please enjoy your shopping!");
		DeleteConcert.setEnabled(b);
		viewconcert.setEnabled(b);
	}
	
	public void setLoginButtonEnable(boolean b){
		loginButton.setEnabled(b);
	}
	
	public void setRegisterEnable(boolean b){
		registerButton.setEnabled(b);
	}
	
	
	  public static void main(String[] args) throws Exception
	    {
	        
	        MainFrame frame = new MainFrame();
	        
	        frame.setTitle("C2C Online Electronic Shop");
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(500, 700);
	        
	        
	       frame.setVisible(true);
	       // frame.pack();
	    }
      
	  
	  
	 
	 public class MainButtonListener implements ActionListener {


			SQL sql = null;
			public MainButtonListener(SQL sqlpassed)
			{
			    sql = sqlpassed;	
			}
			
			
			public void actionPerformed(ActionEvent event ) {
				
				if(event.getSource() == loginButton)
				{
					//mainFrame to be passed 
				  Login.invoke(sql,mainFrame);
				  mainFrame.setVisible(false);
				  viewconcert.setEnabled(true);
				  DeleteConcert.setEnabled(true);
				  SearchButton.setEnabled(true);
				  modConc.setEnabled(true);
				  
				}
				else if(event.getSource() == registerButton)
				{
					//mainFrame to be passed 
					Register.invoke(sql,mainFrame);
					mainFrame.setVisible(false);
				}
				else if (event.getSource() == addAddressButton)
				{
					//mainFrame to be passed 
					System.out.println("userid = "+ userid+ "is passed to addAddressButton");
					AddAddress.invoke(userid,sql,mainFrame);
				
					mainFrame.setVisible(false);
				}
				else if (event.getSource() == modConc)
				{
					//done 
					mainFrame.setVisible(false);
					ModConcert.invoke(userid,sql,mainFrame);
					
					
				}
				else if (event.getSource() == DeleteConcert)
				{
					//done 
					mainFrame.setVisible(false);
					RemoveConcert.invoke(userid,sql,mainFrame);
					
					
				}
				else if (event.getSource() == SearchButton)
				{
					//done 
					mainFrame.setVisible(false);
					SearchConcert.invoke(userid,sql,mainFrame);
					
					
				}
				else if(event.getSource() == viewconcert)
				{
				 String sqlCode = "select login_id from login"+ userid + ";";
				 java.sql.ResultSet rs = sql.QueryExchte(sqlCode);
				 
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
		          	ViewConcert.invoke(userid,sql,mainFrame);
						mainFrame.setVisible(true);}			      	
		           
				if(event.getSource() == QuitButton)
				{
                    //done 
					System.exit(0);
				}
			
					
			}

		}

	    
	 }
	}