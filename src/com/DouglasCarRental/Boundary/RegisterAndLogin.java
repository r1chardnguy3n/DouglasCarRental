package com.DouglasCarRental.Boundary;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

import com.DouglasCarRental.Control.UserManagement;
import com.DouglasCarRental.Entity.User;
import com.toedter.calendar.JDateChooser;


import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class RegisterAndLogin {

	private JPanel contentPane;
	private JFrame frmDouglas;
	private JTextField textName;
	private JTextField textLname;
	private JTextField textEmail;
	private JPasswordField textPass;
	private JTextField textEmailLog;
	private JPasswordField textPassLogin;
	private JDateChooser textDOB;
	String email;
	private String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAndLogin window = new RegisterAndLogin();
					window.frmDouglas.setVisible(true);
										
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterAndLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDouglas = new JFrame();
		frmDouglas.getContentPane().setBackground(new Color(0, 51, 51));
		frmDouglas.setTitle("DOUGLASCarRental");
		frmDouglas.setBounds(100, 100, 675, 515);
		frmDouglas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDouglas.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(255, 255, 255));
		tabbedPane.setBackground(new Color(51, 51, 51));
		tabbedPane.setFont(new Font("Serif", Font.PLAIN, 13));
		frmDouglas.getContentPane().add(tabbedPane);
		
		JPanel Login = new JPanel();
		Login.setBackground(new Color(204, 204, 204));
		tabbedPane.addTab("LOG IN", null, Login, null);
		Login.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		Login.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDouglasCarRental = new JLabel("Douglas Car Rental");
		lblDouglasCarRental.setForeground(new Color(46, 139, 87));
		lblDouglasCarRental.setFont(new Font("Showcard Gothic", Font.BOLD, 26));
		lblDouglasCarRental.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDouglasCarRental, BorderLayout.CENTER);
		
		JPanel SubLogin = new JPanel();
		SubLogin.setBackground(new Color(255, 255, 255));
		SubLogin.setBorder(BorderFactory.createEmptyBorder(0,28,10,28));
		Login.add(SubLogin);
		SubLogin.setLayout(new GridLayout(12, 0, 0, 0));
		
		JLabel label_7 = new JLabel("");
		SubLogin.add(label_7);
		
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setForeground(new Color(51, 102, 51));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Rockwell", Font.BOLD, 29));
		SubLogin.add(lblLogIn);
		
		JLabel label_8 = new JLabel("");
		SubLogin.add(label_8);
		
		JLabel lblEmail = new JLabel("E-MAIL");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		SubLogin.add(lblEmail);
		
		textEmailLog = new JTextField();
		textEmailLog.setText("roger@email.com");
		textEmailLog.setColumns(10);
		SubLogin.add(textEmailLog);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		SubLogin.add(lblPassword);
		
		textPassLogin = new JPasswordField();
		textPassLogin.setEchoChar('*');
		SubLogin.add(textPassLogin);
		
		JLabel label_9 = new JLabel("");
		SubLogin.add(label_9);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Serif", Font.PLAIN, 18));
		btnLogin.setBackground(new Color(51, 102, 51));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		SubLogin.add(btnLogin);
		
		JLabel label_10 = new JLabel("");
		SubLogin.add(label_10);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(51, 102, 51));
		btnExit.setFont(new Font("Serif", Font.PLAIN, 18));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		SubLogin.add(btnExit);
		
		JLabel label_11 = new JLabel("");
		SubLogin.add(label_11);
		
		JPanel Register = new JPanel();
		Register.setBackground(new Color(153, 153, 153));
		tabbedPane.addTab("REGISTER", null, Register, null);
		Register.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51, 51, 51));
		Register.add(panel_1);
		
		JPanel SubRegister = new JPanel();
		SubRegister.setBackground(new Color(255, 255, 255));
		Register.add(SubRegister);
		SubRegister.setLayout(new GridLayout(15, 4, 0, 0));
		SubRegister.setBorder(BorderFactory.createEmptyBorder(10,28,10,28));
		
		JLabel label_1 = new JLabel("REGISTER");
		label_1.setForeground(new Color(51, 102, 51));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Rockwell", Font.BOLD, 23));
		SubRegister.add(label_1);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBackground(new Color(51, 102, 51));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Serif", Font.PLAIN, 14));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				register();
			}
		});
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubRegister.add(lblName);
		
		textName = new JTextField();
		textName.setColumns(10);
		SubRegister.add(textName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubRegister.add(lblLastName);
		
		textLname = new JTextField();
		textLname.setColumns(10);
		SubRegister.add(textLname);
		
		JLabel lblBithday = new JLabel("Bithday");
		lblBithday.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubRegister.add(lblBithday);
		
		textDOB = new JDateChooser();
		textDOB.getCalendarButton().setBackground(new Color(102, 153, 153));
		SubRegister.add(textDOB);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubRegister.add(lblEmail_1);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		SubRegister.add(textEmail);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubRegister.add(lblPassword_1);
		
		textPass = new JPasswordField();
		SubRegister.add(textPass);
		
		JLabel lblNewLabel_1 = new JLabel("");
		SubRegister.add(lblNewLabel_1);
		SubRegister.add(btnRegister);
		
		JButton butExit = new JButton("EXIT");
		butExit.setBackground(new Color(51, 102, 51));
		butExit.setForeground(new Color(255, 255, 255));
		butExit.setFont(new Font("Serif", Font.PLAIN, 14));
		butExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		SubRegister.add(lblNewLabel);
		SubRegister.add(butExit);
	}
	
protected void login() {
		
		email = textEmailLog.getText();
		password = String.valueOf(textPassLogin.getPassword());
		
		UserManagement userManagement = new UserManagement();
		
		User user2 = new User();
		user2.setEmail(email);
		user2.setPassword(password);
		
		User usu = userManagement.getUser(user2);
		
		if (usu != null) {
			//JOptionPane.showMessageDialog(contentPane, "Welcome!!");

			
			DouglasCarRentalMain2 welcome = new DouglasCarRentalMain2();
			welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			welcome.setVisible(true);
			//welcome.setExtendedState( welcome.getExtendedState()|JFrame.MAXIMIZED_BOTH );
			frmDouglas.dispose();
			
			
		} else {
			JOptionPane.showMessageDialog(contentPane, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		DouglasCarRentalMain2 sendEmail = new DouglasCarRentalMain2();
		sendEmail.printEmail(email);
		
	}

protected void register(){
	try {
		 
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/douglascarrental", "root", "");

			 
			 
			 String name = textName.getText();
			 String lastname = textLname.getText();
			 SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy");
			 String birthday = sdf.format(textDOB.getDate());
			 String email = textEmail.getText();
			 String password = String.valueOf(textPass.getPassword());
			 
			String query = "INSERT INTO users (name,lastname,bithdate,email,password) values('" + name + "','"+lastname+"','"+birthday+"','"+email+"','"+password+"')";
			 
			 Statement stmt = conexion.createStatement();
			 
			 stmt.executeUpdate(query);
			 
			 JOptionPane.showMessageDialog(null, "Registration successful!!!!");
		
	} catch (Exception e) {
		System.out.println("Connection error to the Database");
		e.printStackTrace();
	}
}



}
