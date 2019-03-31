package com.DouglasCarRental.Boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.DouglasCarRental.Control.ServiceConnector;
import com.DouglasCarRental.Entity.Reservation;
import com.DouglasCarRental.Entity.Vehicle;
import com.DouglasCarRental.Entity.User;
import com.mysql.jdbc.ResultSet;
import com.toedter.calendar.JDateChooser;

import Utils.MySQLConexion;

import java.awt.Checkbox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class DouglasCarRentalMain2 extends JFrame {

	private JPanel contentPane;
	private JTable vehiclesTable;
	private static List<Vehicle> vehicles;
	private List<Reservation> reservations;
	private static List<Reservation> reservations2;
	private String idx = null, makex = null, modelx = null, yearx = null, typex = null, pricex = null;
	private String getMake, getModel, getYear, getType, getPrice;
	private String selectedIns, startDate, endDate;
	private static String usName, usLname, usBday, usEmail, usPass;
	private static int resId;
	private static String resMake, resModel, resYear, resType, resOut, resIn, resTotalPrice;
	private static int usId;
	private static String email;
	private static String changeEmail, changePass, changePass2;
	private static double totalPrice, taxes, resTotPrice;
	private int insPrice, diff, dailyRate, totalBeforeTaxes, totalRent, totalIns, totalkms, totalAddDriver, kmsPrice,
			addDriverPrice;
	private JTable userJtable;
	private JTextField changeEmailTf;
	private JPasswordField changePasswordTf;
	private JPasswordField changePasswordTf2;
	private String getReservationId;
	private static String jsonArrayString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DouglasCarRentalMain2 frame = new DouglasCarRentalMain2();
					frame.setVisible(true);

					// readList();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DouglasCarRentalMain2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 25, 1264, 635);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Reservation", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 732, 252);
		panel.add(scrollPane);

		vehiclesTable = new JTable();
		vehiclesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {

					int row = vehiclesTable.getSelectedRow();
					getMake = (vehiclesTable.getModel().getValueAt(row, 0).toString());
					getModel = (vehiclesTable.getModel().getValueAt(row, 1).toString());
					getYear = (vehiclesTable.getModel().getValueAt(row, 2).toString());
					getType = (vehiclesTable.getModel().getValueAt(row, 3).toString());
					getPrice = (vehiclesTable.getModel().getValueAt(row, 4).toString());

					//System.out.println(getMake + " " + getModel + " " + getYear + " " + getType + " " + getPrice);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(vehiclesTable);

		JComboBox typeCb = new JComboBox();
		typeCb.setBounds(802, 68, 127, 20);
		panel.add(typeCb);

		typeCb.addItem("SUV");
		typeCb.addItem("Car");
		typeCb.addItem("Pick-Up");
		typeCb.addItem("Minivan");

		typeCb.setSelectedItem(null);

		JLabel label = new JLabel("TYPE OF VEHICLE");
		label.setBounds(955, 72, 108, 13);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JComboBox yearCb = new JComboBox();
		yearCb.setBounds(802, 99, 127, 20);
		panel.add(yearCb);

		yearCb.addItem("2017");
		yearCb.addItem("2018");
		yearCb.addItem("2019");

		yearCb.setSelectedItem(null);

		JLabel label_1 = new JLabel("YEAR");
		label_1.setBounds(955, 103, 108, 13);
		panel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JButton loadBtn = new JButton("Search");
		loadBtn.setBounds(802, 143, 127, 23);
		panel.add(loadBtn);

		JLabel lblAvailableVehicles = new JLabel("AVAILABLE VEHICLES");
		lblAvailableVehicles.setBounds(10, 0, 159, 17);
		panel.add(lblAvailableVehicles);
		lblAvailableVehicles.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblRentalInformation = new JLabel("RENTAL INFORMATION");
		lblRentalInformation.setBounds(13, 291, 168, 17);
		panel.add(lblRentalInformation);
		lblRentalInformation.setFont(new Font("Tahoma", Font.BOLD, 14));

		JComboBox insuranceCb = new JComboBox();
		insuranceCb.setBounds(802, 343, 164, 20);
		panel.add(insuranceCb);

		insuranceCb.addItem("Yes - $20 x Day");
		insuranceCb.addItem("No - I'm going to use my own");

		insuranceCb.setSelectedItem(null);

		JLabel label_2 = new JLabel("INSURANCE");
		label_2.setBounds(987, 347, 108, 13);
		panel.add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JDateChooser outDc = new JDateChooser();
		outDc.setBounds(802, 384, 164, 20);
		panel.add(outDc);

		JLabel label_3 = new JLabel("INITITAL DAY");
		label_3.setBounds(987, 391, 108, 13);
		panel.add(label_3);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JDateChooser inDc = new JDateChooser();
		inDc.setBounds(802, 423, 164, 20);
		panel.add(inDc);

		JLabel label_4 = new JLabel("RETURNING DATE");
		label_4.setBounds(987, 430, 120, 13);
		panel.add(label_4);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 10));

		Checkbox unlKmsCb = new Checkbox("Unlimited Kms - 10$ x Day");
		unlKmsCb.setBounds(802, 458, 232, 22);
		panel.add(unlKmsCb);

		Checkbox addDriverCb = new Checkbox("Additional Driver - 10$ x Day");
		addDriverCb.setBounds(802, 486, 232, 22);
		panel.add(addDriverCb);

		JButton calculateBtn = new JButton("Calculate");
		calculateBtn.setBounds(843, 514, 151, 23);
		panel.add(calculateBtn);

		JButton createReservationBtn = new JButton("Create Reservation");
		createReservationBtn.setBounds(843, 548, 151, 23);
		panel.add(createReservationBtn);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 327, 732, 244);
		panel.add(scrollPane_1);

		JList totalJlist = new JList();
		scrollPane_1.setViewportView(totalJlist);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("User Information", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 378, 813, 218);
		panel_1.add(scrollPane_3);

		userJtable = new JTable();
		userJtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// TODO INSERT THE LOGIC TO SELECT THE ROW OF THE RESERVATION TO
				// DELETE

				try {

					int row = userJtable.getSelectedRow();
					getReservationId = (userJtable.getModel().getValueAt(row, 0).toString());

					//System.out.println(getReservationId);

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});
		scrollPane_3.setViewportView(userJtable);

		JButton btnLoadInformation = new JButton("LOAD INFORMATION");
		btnLoadInformation.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnLoadInformation.setBounds(31, 266, 181, 23);
		panel_1.add(btnLoadInformation);

		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(31, 91, 46, 14);
		panel_1.add(lblName);

		JLabel lblBirthday = new JLabel("BIRTHDAY:");
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthday.setBounds(31, 128, 76, 14);
		panel_1.add(lblBirthday);

		JLabel lblEmial = new JLabel("EMAIL:");
		lblEmial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmial.setBounds(31, 162, 76, 14);
		panel_1.add(lblEmial);

		JLabel userNameLbl = new JLabel("");
		userNameLbl.setBounds(117, 91, 234, 14);
		panel_1.add(userNameLbl);

		JLabel userBdatLbl = new JLabel("");
		userBdatLbl.setBounds(117, 128, 234, 14);
		panel_1.add(userBdatLbl);

		JLabel userEmailLbl = new JLabel("");
		userEmailLbl.setBounds(117, 162, 234, 14);
		panel_1.add(userEmailLbl);

		JLabel userPaswordLbl = new JLabel("");
		userPaswordLbl.setBounds(117, 201, 234, 14);
		panel_1.add(userPaswordLbl);

		JLabel lblPersonalInformation = new JLabel("PERSONAL INFORMATION");
		lblPersonalInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPersonalInformation.setBounds(85, 40, 295, 14);
		panel_1.add(lblPersonalInformation);

		JLabel lblChangePassword = new JLabel("CHANGE PASSWORD:");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangePassword.setBounds(398, 201, 166, 14);
		panel_1.add(lblChangePassword);

		JButton changePasswordBtn = new JButton("CHANGE PASSWORD");
		changePasswordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getUserInfo();
				changePass = String.valueOf(changePasswordTf.getPassword());
				changePass2 = String.valueOf(changePasswordTf2.getPassword());

				if (changePass.equals("") || changePass2.equals("")) {
					JOptionPane.showMessageDialog(null, "The boxes are empty!");
				} else {
					if (changePass.equals(changePass2)) {

						try {
							Class.forName("com.mysql.jdbc.Driver");
							java.sql.Connection conexion2 = DriverManager
									.getConnection("jdbc:mysql://localhost:3306/douglascarrental", "root", "");

							String query2 = "UPDATE users SET password='" + changePass + "' WHERE id='" + usId + "'";
							Statement stmt1 = conexion2.createStatement();
							stmt1.executeUpdate(query2);

							JOptionPane.showMessageDialog(null, "Password change successfully!");

						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Unsuccessful password change!");
						}

					} else {
						JOptionPane.showMessageDialog(null, "The entered passwords are not equal!");
					}
				}

			}
		});
		changePasswordBtn.setBounds(526, 266, 166, 23);
		panel_1.add(changePasswordBtn);

		JLabel lblEnterTheNew = new JLabel("ENTER THE \r\nNEW PASSWORD");
		lblEnterTheNew.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEnterTheNew.setBounds(702, 197, 147, 23);
		panel_1.add(lblEnterTheNew);

		JLabel lblConfirmNewPassword = new JLabel("CONFIRM NEW PASSWORD");
		lblConfirmNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblConfirmNewPassword.setBounds(702, 233, 147, 23);
		panel_1.add(lblConfirmNewPassword);

		JLabel lblChangeEmail = new JLabel("CHANGE EMAIL:");
		lblChangeEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChangeEmail.setBounds(398, 95, 166, 14);
		panel_1.add(lblChangeEmail);

		changeEmailTf = new JTextField();
		changeEmailTf.setColumns(10);
		changeEmailTf.setBounds(526, 92, 166, 20);
		panel_1.add(changeEmailTf);

		JLabel lblEnterTheNew_1 = new JLabel("ENTER THE NEW EMAIL");
		lblEnterTheNew_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblEnterTheNew_1.setBounds(702, 91, 147, 23);
		panel_1.add(lblEnterTheNew_1);

		JButton ChangeEmailBtn = new JButton("CHANGE EMAIL");
		ChangeEmailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				getUserInfo();
				changeEmail = changeEmailTf.getText().toString();

				if (changeEmail.equals("")) {
					JOptionPane.showMessageDialog(null, "Insert an Email");
				} else {

					try {
						Class.forName("com.mysql.jdbc.Driver");
						java.sql.Connection conexion2 = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/douglascarrental", "root", "");

						String query2 = "UPDATE users SET email='" + changeEmail + "' WHERE id='" + usId + "'";
						Statement stmt1 = conexion2.createStatement();
						stmt1.executeUpdate(query2);

						JOptionPane.showMessageDialog(null, "Email change successfully!");

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Unsuccessful email change!");
					}

				}

			}
		});
		ChangeEmailBtn.setBounds(526, 123, 166, 23);
		panel_1.add(ChangeEmailBtn);

		JLabel lblChangePersonalInformation = new JLabel("CHANGE PERSONAL INFORMATION");
		lblChangePersonalInformation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChangePersonalInformation.setBounds(477, 40, 295, 14);
		panel_1.add(lblChangePersonalInformation);

		changePasswordTf = new JPasswordField();
		changePasswordTf.setBounds(526, 198, 166, 20);
		panel_1.add(changePasswordTf);

		changePasswordTf2 = new JPasswordField();
		changePasswordTf2.setBounds(526, 234, 166, 20);
		panel_1.add(changePasswordTf2);

		JButton deleteReservationBtn = new JButton("DELETE RESERVATION");
		deleteReservationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion2 = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/douglasreservations", "root", "");

					String query2 = "DELETE FROM reservations WHERE resnum ='" + Integer.parseInt(getReservationId)
							+ "'";
					Statement stmt1 = conexion2.createStatement();
					stmt1.executeUpdate(query2);

					JOptionPane.showMessageDialog(null, "Reservation deleted successfully!");

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Problems while deleting the reservation. Try again!");
				}

			}
		});
		deleteReservationBtn.setBounds(854, 474, 166, 23);
		panel_1.add(deleteReservationBtn);

		JLabel lblReservations = new JLabel("RESERVATIONS");
		lblReservations.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReservations.setBounds(10, 353, 295, 14);
		panel_1.add(lblReservations);

		JLabel lblDoulasCarRental = new JLabel("DOULAS CAR RENTAL");
		lblDoulasCarRental.setBounds(416, 10, 325, 37);
		contentPane.add(lblDoulasCarRental);
		lblDoulasCarRental.setFont(new Font("Tahoma", Font.BOLD, 30));
		createReservationBtn.addActionListener(new ActionListener() {// TODO
																		// create
																		// reservation
			public void actionPerformed(ActionEvent e) {

				String userName = usName + " " + usLname;

				try {

					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection conexion = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/douglasreservations", "root", "");

					String query = "INSERT INTO reservations (make,model,year,type,cusName,cusPhone,startDate,endDate,totalPrice,extraDriver,unlimitedKms,insurance,price,cusId) values('"
							+ getMake + "','" + getModel + "','" + getYear + "','" + getType + "','" + userName + "','"
							+ usEmail + "','" + startDate + "','" + endDate + "','" + totalRent + "','" + totalAddDriver
							+ "','" + totalkms + "','" + totalIns + "','" + dailyRate + "','" + usId + "')";

					Statement stmt = conexion.createStatement();

					stmt.executeUpdate(query);

					// TODO add the reservation information to the MessageDialog
					JOptionPane.showMessageDialog(null, "reservation created successfully!");

				} catch (Exception e1) {
					System.out.println("Connection error to the Database");
					e1.printStackTrace();
				}

				reservations = new ArrayList<>();

//				System.out.println(getMake + " " + getModel + " " + getYear + " " + getType + " " + dailyRate + " "
//						+ totalIns + " " + usId + " " + userName + " " + usEmail + " " + startDate + " " + endDate + " "
//						+ totalkms + " " + totalAddDriver + " " + totalRent);

				Reservation reservation = new Reservation(getMake, getModel, getYear, getType, dailyRate, totalIns,
						usId, userName, usEmail, startDate, endDate, totalkms, totalAddDriver, totalRent);
				reservations.add(reservation);

//				for (Reservation b : reservations) {
//					System.out.println(b);
//				}

			}
		});
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy"); 
				startDate = sdf.format(outDc.getDate());
				endDate = sdf.format(inDc.getDate());

				int month1 = Integer.parseInt(startDate.substring(0, 2));
				int day1 = Integer.parseInt(startDate.substring(3, 5));
				int year1 = Integer.parseInt(startDate.substring(6, 8));

				int month2 = Integer.parseInt(endDate.substring(0, 2));
				int day2 = Integer.parseInt(endDate.substring(3, 5));
				int year2 = Integer.parseInt(endDate.substring(6, 8));

				LocalDate firstdate = LocalDate.of(year1, month1, day1);
				LocalDate seconddate = LocalDate.of(year2, month2, day2);

				Period period = Period.between(firstdate, seconddate);
				diff = period.getDays(); // TODO Here we get the days

				try {

					dailyRate = Integer.parseInt(getPrice.substring(1)); // Get
																			// the
																			// daily
																			// price
				} catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Please select at least one vehicle!");

				}

				totalRent = dailyRate * diff;// get the total of the rental, not
												// included taxes or extras

				System.out.println("The amount of the days are: " + diff);
				System.out.println();

				System.out.println("Rental: " + "$" + totalRent);

				selectedIns = (String) insuranceCb.getSelectedItem();
				if (selectedIns.equals("Yes - $20 x Day")) {

					insPrice = 20;
				} else {
					insPrice = 0;
				}

				totalIns = insPrice * diff;
				System.out.println("Insurance: " + "$" + totalIns);

				if (unlKmsCb.getState() == true) {
					kmsPrice = 10;
				} else {
					kmsPrice = 0;
				}
				totalkms = kmsPrice * diff;
				System.out.println("Unlimited Kms: " + "$" + totalkms);

				if (addDriverCb.getState() == true) {
					addDriverPrice = 10;
				} else {
					addDriverPrice = 0;
				}
				totalAddDriver = addDriverPrice * diff;
				System.out.println("Additional Driver: " + "$" + totalAddDriver);

				DecimalFormat df2 = new DecimalFormat(".##");

				totalBeforeTaxes = (totalRent + totalIns);
				taxes = totalBeforeTaxes * 0.12;
				totalPrice = totalBeforeTaxes + taxes;
				System.out.println("Total before taxes: " + "$" + totalBeforeTaxes);
				System.out.println("Taxes: " + "$" + df2.format(taxes));
				System.out.println("Total: " + "$" + totalPrice);

				// Adding the information to the Jlist
				DefaultListModel listmodel;
				listmodel = new DefaultListModel();

				listmodel.addElement("The Selected car is: " + getMake + " " + getModel + " - " + getYear);
				listmodel.addElement("The daily rate is: " + getPrice);
				listmodel.addElement(
						"Total amount of days: " + diff + " -- ( From: " + startDate + " - Until: " + endDate + " )");
				listmodel.addElement("Rental: " + "$" + totalRent);
				listmodel.addElement("Insurance: " + "$" + totalIns);
				listmodel.addElement("Unlimited Kms: " + "$" + totalkms);
				listmodel.addElement("Additional Driver: " + "$" + totalAddDriver);
				listmodel.addElement("Total before taxes: " + "$" + totalBeforeTaxes);
				listmodel.addElement("Taxes PST + GST (12%): " + "$" + df2.format(taxes));
				listmodel.addElement("");
				listmodel.addElement("TOTAL OF THE RENTAL: " + "$" + totalPrice);

				totalJlist.setModel(listmodel);

			}
		});
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getUserInfo();
				readList();
				// System.out.println("confirm: "+email);

				typex = (String) typeCb.getSelectedItem();
				yearx = (String) yearCb.getSelectedItem();

				String col[] = { "MAKE", "MODEL", "YEAR", "TYPE", "PRICE / DAY" };

				DefaultTableModel DTM = new DefaultTableModel(col, 0);

				JTable table = new JTable(DTM);

				for (int i = 1; i < vehicles.size(); i++) {
					if (vehicles.get(i).type.equals(typex) && vehicles.get(i).year.equals(yearx)) {// only
																									// search
																									// for
																									// SUV
						String id = vehicles.get(i).id;
						String make = vehicles.get(i).make;
						String model = vehicles.get(i).model;
						String year = vehicles.get(i).year;
						String type = vehicles.get(i).type;
						String price = vehicles.get(i).price;

						Object[] data = { make, model, year, type, "$" + price };

						DTM.addRow(data);
					}
				}

				vehiclesTable.setModel(DTM);

			}
		});

		btnLoadInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getUserInfo();
				getUserReservations();

				userNameLbl.setText(usName.toUpperCase() + " " + usLname.toUpperCase());
				userBdatLbl.setText(usBday.toUpperCase());
				userEmailLbl.setText(usEmail);


				// ----CREATE TABLE

				DefaultTableModel DTM;
				DTM = getUserReservations();
				userJtable.setModel(DTM);

			}
		});

	}
	

	public static void readList() {

		ServiceConnector SC = new ServiceConnector();

		SC.serviceStart();
		vehicles = SC.getVehicleList();

	}

	public void printEmail(String receivedEmail) {

		email = receivedEmail;
		//System.out.println(email);
	}

	private static void getUserInfo() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/douglascarrental",
					"root", "");

			// String sql = "SELECT * FROM users";
			String sql = "SELECT * FROM users WHERE email = '" + email + "'";

			Statement st = conexion.createStatement();

			ResultSet rs = (ResultSet) st.executeQuery(sql);

			while (rs.next()) {
				usId = rs.getInt("id");
				usName = rs.getString("name");
				usLname = rs.getString("lastname");
				usBday = rs.getString("bithdate");
				usEmail = rs.getString("email");
				usPass = rs.getString("password");

				// print the results
				//System.out.format("%s, %s, %s, %s, %s, %s\n", usId, usName, usLname, usBday, usEmail, usPass);
			}
			st.close();

		} catch (Exception e) {
			System.out.println("Error>> Getting User");
			System.err.println(e.getMessage());

		}
	}

	private static DefaultTableModel getUserReservations() {

		String col[] = { "RESERVATION ID", "VEHICLE", "TYPE", "CHECK OUT DATE", "CHECK IN DATE", "TOTAL PRICE" };

		DefaultTableModel DTM = new DefaultTableModel(col, 0);

		try {

			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conexion = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/douglasreservations", "root", "");

			// String sql = "SELECT * FROM users";
			String sql = "SELECT * FROM reservations WHERE cusId = '" + usId + "'";

			Statement st = conexion.createStatement();

			ResultSet rs = (ResultSet) st.executeQuery(sql);

			while (rs.next()) {
				resId = rs.getInt("resnum");
				resMake = rs.getString("make");
				resModel = rs.getString("model");
				resYear = rs.getString("year");
				resType = rs.getString("type");
				resOut = rs.getString("startDate");
				resIn = rs.getString("endDate");
				resTotalPrice = rs.getString("totalPrice");

				// print the results
				//System.out.format("%s, %s, %s, %s, %s, %s, %s, %s\n", resId, resMake, resModel, resYear, resType,
						//resOut, resIn, resTotalPrice);
				setUserReservationsIntoArray();

				// -- CREATE TABLE

				JTable table = new JTable(DTM);

				for (int i = 0; i < reservations2.size(); i++) {

					int id = reservations2.get(i).resid;
					String make = reservations2.get(i).make;
					String model = reservations2.get(i).model;
					String year = reservations2.get(i).year;
					String type = reservations2.get(i).type;
					String outDate = reservations2.get(i).startDate;
					String inDate = reservations2.get(i).endDate;
					double price = reservations2.get(i).totalPrice;

					Object[] data = { id, make + " " + model + " " + year, type, outDate, inDate, "$" + price };

					DTM.addRow(data);

				}

			}
			st.close();

		} catch (Exception e) {
			System.out.println("Error>> Getting User");
			System.err.println(e.getMessage());

		}

		return DTM;

	}

	private static void setUserReservationsIntoArray() {
		reservations2 = new ArrayList<>();

		resTotPrice = Double.parseDouble(resTotalPrice);

		Reservation reservation = new Reservation(resId, resMake, resModel, resYear, resType, resOut, resIn,
				resTotPrice);
		reservations2.add(reservation);

//		for (Reservation b : reservations2) {
//			System.out.println(b);
//		}

	}
}
