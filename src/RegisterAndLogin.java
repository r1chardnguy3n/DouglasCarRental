import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;


import javax.swing.JPasswordField;

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
		frmDouglas.setTitle("DOUGLASCarRental");
		frmDouglas.setBounds(100, 100, 551, 475);
		frmDouglas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDouglas.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 535, 436);
		frmDouglas.getContentPane().add(tabbedPane);
		
		JPanel Login = new JPanel();
		tabbedPane.addTab("LOG IN", null, Login, null);
		Login.setLayout(null);
		
		JPanel SubLogin = new JPanel();
		SubLogin.setBounds(31, 30, 462, 345);
		Login.add(SubLogin);
		SubLogin.setLayout(null);
		
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setFont(new Font("Arial", Font.BOLD, 16));
		lblLogIn.setBounds(201, 11, 59, 14);
		SubLogin.add(lblLogIn);
		
		JLabel lblEmail = new JLabel("E-MAIL:");
		lblEmail.setBounds(72, 110, 89, 14);
		SubLogin.add(lblEmail);
		
		JLabel label = new JLabel("PASSWORD:");
		label.setBounds(72, 170, 89, 14);
		SubLogin.add(label);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(72, 263, 89, 23);
		SubLogin.add(btnLogin);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(296, 263, 89, 23);
		SubLogin.add(btnExit);
		
		textEmailLog = new JTextField();
		textEmailLog.setText("roger@email.com");
		textEmailLog.setColumns(10);
		textEmailLog.setBounds(178, 98, 207, 26);
		SubLogin.add(textEmailLog);
		
		textPassLogin = new JPasswordField();
		textPassLogin.setEchoChar('*');
		textPassLogin.setBounds(178, 158, 207, 26);
		SubLogin.add(textPassLogin);
		
		JPanel Register = new JPanel();
		tabbedPane.addTab("REGISTER", null, Register, null);
		Register.setLayout(null);
		
		JPanel SubRegister = new JPanel();
		SubRegister.setLayout(null);
		SubRegister.setBounds(35, 30, 462, 345);
		Register.add(SubRegister);
		
		JLabel label_1 = new JLabel("REGISTER");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(185, 11, 89, 14);
		SubRegister.add(label_1);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				register();
			}
		});
		btnRegister.setBounds(84, 269, 100, 23);
		SubRegister.add(btnRegister);
		
		JButton butExit = new JButton("EXIT");
		butExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		butExit.setBounds(299, 269, 89, 23);
		SubRegister.add(butExit);
		
		JLabel label_2 = new JLabel("Name:");
		label_2.setBounds(76, 59, 54, 20);
		SubRegister.add(label_2);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(160, 56, 230, 26);
		SubRegister.add(textName);
		
		JLabel label_3 = new JLabel("Last Name:");
		label_3.setBounds(76, 95, 80, 20);
		SubRegister.add(label_3);
		
		textLname = new JTextField();
		textLname.setColumns(10);
		textLname.setBounds(160, 92, 230, 26);
		SubRegister.add(textLname);
		
		JLabel label_4 = new JLabel("Bithday:");
		label_4.setBounds(76, 133, 71, 20);
		SubRegister.add(label_4);
		
		textDOB = new JDateChooser();
		textDOB.setBounds(160, 129, 230, 26);
		SubRegister.add(textDOB);
		
		JLabel label_5 = new JLabel("Email:");
		label_5.setBounds(76, 170, 54, 20);
		SubRegister.add(label_5);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(160, 167, 230, 26);
		SubRegister.add(textEmail);
		
		JLabel label_6 = new JLabel("Password:");
		label_6.setBounds(76, 211, 80, 20);
		SubRegister.add(label_6);
		
		textPass = new JPasswordField();
		textPass.setBounds(160, 208, 230, 26);
		SubRegister.add(textPass);
	}
	
protected void login() {
		
		String email = textEmailLog.getText();
		String password = String.valueOf(textPassLogin.getPassword());
		
		UserManagement userManagement = new UserManagement();
		
		User user2 = new User();
		user2.setEmail(email);
		user2.setPassword(password);
		
		User usu = userManagement.getUser(user2);
		
		if (usu != null) {
			//JOptionPane.showMessageDialog(contentPane, "Welcome!!");
			
			
			DouglasCarRentalMain welcome = new DouglasCarRentalMain();
			welcome.setVisible(true);
			welcome.setExtendedState( welcome.getExtendedState()|JFrame.MAXIMIZED_BOTH );
			frmDouglas.dispose();
			
			
		} else {
			JOptionPane.showMessageDialog(contentPane, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
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
			 
			 String query = "INSERT INTO users (name,lastname,bithdate,email,password) values('"+name+"','"+lastname+"','"+birthday+"','"+email+"','"+password+"')";
			 
			 Statement stmt = conexion.createStatement();
			 
			 stmt.executeUpdate(query);
			 
			 JOptionPane.showMessageDialog(null, "Registration successful!!!!");
		
	} catch (Exception e) {
		System.out.println("Connection error to the Database");
		e.printStackTrace();
	}
}

}
