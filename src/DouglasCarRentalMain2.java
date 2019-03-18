import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSet;
import com.toedter.calendar.JDateChooser;

import Utils.MySQLConexion;

import java.awt.Checkbox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class DouglasCarRentalMain2 extends JFrame {

	private JPanel contentPane;
	private JTable vehiclesTable;
	private static List<Vehicle> vehicles;
	private List<Reservation> reservations;
	private String idx = null, makex = null, modelx = null, yearx = null, typex = null, pricex = null;
	private String getMake, getModel, getYear, getType, getPrice;
	private String selectedIns, startDate, endDate;
	private static String usName, usLname, usBday, usEmail, usPass;
	private static int usId;
	private static String email;
	private double totalPrice, taxes;
	private int insPrice, diff, dailyRate, totalBeforeTaxes, totalRent, totalIns, totalkms, totalAddDriver, kmsPrice,
			addDriverPrice;

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

					System.out.println(getMake + " " + getModel + " " + getYear + " " + getType + " " + getPrice);

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
							+ usEmail + "','" + startDate + "','" + endDate + "','" + totalRent + "','"
							+ totalAddDriver + "','" + totalkms + "','" + totalIns + "','" + dailyRate + "','" + usId
							+ "')";

					Statement stmt = conexion.createStatement();

					stmt.executeUpdate(query);

					//TODO add the reservation information to the MessageDialog
					JOptionPane.showMessageDialog(null, "reservation created successfully!");

				} catch (Exception e1) {
					System.out.println("Connection error to the Database");
					e1.printStackTrace();
				}

				reservations = new ArrayList<>();

				System.out.println(getMake + " " + getModel + " " + getYear + " " + getType + " " + dailyRate + " "
						+ totalIns + " " + usId + " " + userName + " " + usEmail + " " + startDate + " " + endDate + " "
						+ totalkms + " " + totalAddDriver + " " + totalRent);

				Reservation reservation = new Reservation(getMake, getModel, getYear, getType, dailyRate, totalIns,
						usId, userName, usEmail, startDate, endDate, totalkms, totalAddDriver, totalRent);
				reservations.add(reservation);

				for (Reservation b : reservations) {
					System.out.println(b);
				}

			}
		});
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy"); // Get
																			// the
																			// amount
																			// of
																			// days
																			// between
																			// two
																			// dates
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

				// TODO:System.out.println(insPrice+" "+startDate+" "+endDate+"
				// day "+day1+" month "+month1+" year "+year1+" -second date-
				// "+" day "+day2+" month "+month2+" year "+year2);

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

	}

	public static void readList() {

		vehicles = new ArrayList<>();

		String filename = "CAR_DATA.csv";
		File file = new File(filename);

		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] values = data.split(",");
				Vehicle vehicle = createVehicle(values);
				vehicles.add(vehicle);

				// System.out.println(values[2]+" "+values[3]);
			}
			inputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// for (Vehicle b : vehicles) {
		// System.out.println(b);
		// }
	}

	private static Vehicle createVehicle(String[] metadata) {
		String id = metadata[0];
		String make = metadata[1];
		String model = metadata[2];
		String year = metadata[3];
		String type = metadata[4];
		String price = metadata[5];

		// create and return book of this metadata
		return new Vehicle(id, make, model, year, type, price);
	}

	public void printEmail(String receivedEmail) {

		email = receivedEmail;
		System.out.println(email);
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
				System.out.format("%s, %s, %s, %s, %s, %s\n", usId, usName, usLname, usBday, usEmail, usPass);
			}
			st.close();

		} catch (Exception e) {
			System.out.println("Error>> Getting User");
			System.err.println(e.getMessage());

		}
	}
}
// String query = "SELECT * FROM `users` WHERE `email`='"+email+"'";
