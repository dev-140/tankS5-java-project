package main;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.toedter.calendar.JCalendar;
import java.time.temporal.ChronoUnit;
public class UserInterface extends JFrame {

	// change default app icon
	ImageIcon logo = new ImageIcon("/sample-image-room-info.png");

	private static final long serialVersionUID = 1L;
	JPanel sideBarPanel = new JPanel();
	RoundedButton homeBtn = new RoundedButton("Home");
	RoundedButton roomsBtn = new RoundedButton("Rooms");
	RoundedButton AmenitiesBtn = new RoundedButton("Amenities");
	RoundedButton receiptBtn = new RoundedButton("Receipt");
	RoundedButton receiptBtn1 = new RoundedButton("Receipts");
	JPanel homePanel = new JPanel();
	JPanel panel_3 = new RoundedPanel(20, Color.white);
	JPanel panel = new JPanel();
	JPanel amenitiesPanel = new JPanel();
	JPanel roomsPanel = new JPanel();
	JPanel modalBg = new JPanel();
	JPanel infoSidePanel = new JPanel();

	// room side info
	JLabel roomInfoLabel = new JLabel();
	JLabel roomInfoPrice = new JLabel();
	JTextPane txtRoomName = new JTextPane();
	RoundedButton bookBtn = new RoundedButton("Book now");
	RoundedButton showMoreBtn = new RoundedButton("Show More");
	RoundedButton cancelCheckoutBtn = new RoundedButton("Cancel");

	// room modal variables
	String modalSetRoom = "romanticRetreat";
	JLabel roomModalTitle = new JLabel();
	JLabel feature1 = new JLabel();
	JLabel feature2 = new JLabel();
	JLabel feature3 = new JLabel();
	JLabel modalRoomPrice = new JLabel();
	JTextPane roomInfoDesc = new JTextPane();

	// checkout variables
	JPanel checkoutFirstPanel = new JPanel();
	JPanel dateChooserPanel = new JPanel();
	RoundedButton checkinDateBtn = new RoundedButton("Choose date");
	RoundedButton checkoutDateBtn = new RoundedButton("Choose date");
	RoundedButton btnContinueC = new RoundedButton("Continue");
	final JCalendar calendar = new JCalendar();
	JLabel dateText = new JLabel();
	LocalDate startDateF = LocalDate.now();
	LocalDate endDateF;
	Date checkinDate;
	Date checkinDateF, checkoutDateF;
	RoundedButton startDateBtn = new RoundedButton("Confirm");
	RoundedButton endDateBtn = new RoundedButton("Confirm");
	JLabel checkinHeading = new JLabel("Check-in");
	JLabel selectedCheckoutDate = new JLabel("Select Date");
	JLabel checkInHeading = new JLabel("Check-in Date:");
	JLabel selectedCheckinDate = new JLabel("Select Date");
	JLabel selectDateLabel_1 = new JLabel("Check-out Date:");
	String calendarParam = "startDate", startDateEng = "Select Date", endDateEng = "Select Date";
	long totalDaysF = 0;
	JLabel lblNewLabel_3 = new JLabel(totalDaysF + " days");
	boolean isCheckout = false, isCheckin = false, isFirstComplete = false;
	private JTextField formName;
	private JTextField formEmail;
	private JTextField formCardName;
	private JTextField formTelNo;
	private JTextField textField_4;
	private JTextField textField_5;
	RoundedButton checkoutConfirmBtn = new RoundedButton("Proceed");
	JPanel checkoutSecondPanel = new JPanel();
	JLabel checkinDateS3 = new JLabel("");
	JLabel checkoutDateS3 = new JLabel("");
	JLabel refNoS3 = new JLabel("");
	JLabel totalAmountS3 = new JLabel("");
	long totalAmount;
	long dayBetween;
	String cFName, cEmail, cAddress, cCardName, cCardNumber, cTelNo;
	private JTextField formRef;
	RoundedButton findRef = new RoundedButton("Continue");

// receipt variables
	JPanel receiptPanel = new JPanel();
	JLabel dName = new JLabel("----");
	JLabel dEmail = new JLabel("----");
	JLabel dTelNo = new JLabel("----");
	JLabel rRefNo = new JLabel("----");
	JLabel rCheckin = new JLabel("----");
	JLabel rDate = new JLabel("----");
	JLabel rTotPrice = new JLabel("----");
	JLabel rRoomName = new JLabel("Room Name");
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	close all panels
	public void closeAllPanel(boolean showSideBar) {
		homePanel.setVisible(false);
		amenitiesPanel.setVisible(false);
		roomsPanel.setVisible(false);
		dateChooserPanel.setVisible(false);
		modalBg.setVisible(false);
		infoSidePanel.setVisible(showSideBar);
		checkoutFirstPanel.setVisible(false);
		checkoutSecondPanel.setVisible(false);
		receiptPanel.setVisible(false);
		isCheckout = false;
	}

//	reset checkout
	public void resetCheckout() {
		isCheckout = false;
		formName.setText("");
		formEmail.setText("");
		textField_4.setText("");
		formCardName.setText("");
		textField_5.setText("");
		formTelNo.setText("");
		lblNewLabel_3.setText("0");
		selectedCheckoutDate.setText("Select Date");
		selectedCheckinDate.setText("Select Date");
		startDateF = null;
		endDateF = null;
		isCheckin = false;
		isFirstComplete = false;
	}

// 	primary btn
	public static void primaryBtn(final JButton btnMain, final int posX, final int posY, final int height,
			final int width, JPanel jPanel2) {
		btnMain.setBackground(new Color(255, 255, 255));
		btnMain.setFont(new Font("Helvetica", Font.PLAIN, 13));
		jPanel2.add(btnMain);
		btnMain.setBorder(new LineBorder(new Color(179, 179, 179), 1, true));
		btnMain.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnMain.setBounds(posX, posY, width, height);

		btnMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMain.setBackground(Color.lightGray);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMain.setBackground(Color.white);
			}
		});
	}

//	animation settings
	public void animatePanel(int endPosY, int posX, final JPanel homePanel2) {
		final int startY = -100;
		final int endY = endPosY;
		final int x = posX;

		int delay = 20; // set the delay in milliseconds
		final int increment = 5; // set the increment in pixels

		final Timer timer = new Timer(delay, new ActionListener() {
			int currentY = startY;

			public void actionPerformed(ActionEvent e) {
				currentY += increment;

				if (currentY > endY) {
					currentY = endY;
					((Timer) e.getSource()).stop(); // stop the timer
				}
				homePanel2.setLocation(x, currentY);
			}
		});

		timer.start();
	}

//	navigation 
	public void sideBar() {
		sideBarPanel.setBackground(Color.WHITE);
		sideBarPanel.setBounds(0, 0, 208, 647);
		getContentPane().add(sideBarPanel);
		sideBarPanel.setLayout(null);

		Label mainHeading = new Label("TANK S5");
		mainHeading.setFont(new Font("Helvetica", Font.BOLD, 32));
		mainHeading.setAlignment(Label.CENTER);
		mainHeading.setBounds(25, 29, 160, 38);
		sideBarPanel.add(mainHeading);
		mainHeading.setForeground(Color.BLACK);

		Panel borderBottomHeading = new Panel();
		borderBottomHeading.setBackground(new Color(0, 163, 255));
		borderBottomHeading.setBounds(25, 73, 160, 15);
		sideBarPanel.add(borderBottomHeading);

//		sidebar btns
		primaryBtn(homeBtn, 25, 110, 38, 160, sideBarPanel);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(true);
				homePanel.setVisible(true);
				animatePanel(0, 207, homePanel);
				isCheckout = false;
				setCheckOutBtn();
			}
		});

		primaryBtn(roomsBtn, 25, 170, 38, 160, sideBarPanel);
		roomsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(true);
				roomsPanel.setVisible(true);
				animatePanel(0, 208, roomsPanel);
				isCheckout = false;
				setCheckOutBtn();
			}
		});

		primaryBtn(AmenitiesBtn, 25, 230, 38, 160, sideBarPanel);
		AmenitiesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(false);
				amenitiesPanel.setVisible(true);
				animatePanel(0, 218, amenitiesPanel);
				isCheckout = false;
				setCheckOutBtn();
			}
		});

		primaryBtn(receiptBtn, 25, 290, 38, 160, sideBarPanel);
		receiptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(false);
				receiptPanel.setVisible(true);
				isCheckout = false;
				setCheckOutBtn();
			}
		});
	}

//	borders
	public void borders() {
		Panel bottomBorder = new Panel();
		bottomBorder.setBounds(0, 624, 1212, 23);
		getContentPane().add(bottomBorder);
		bottomBorder.setBackground(new Color(0, 163, 255));
		bottomBorder.setLayout(null);
		
		JLabel footerCopyRight = new JLabel("© 2023 TANKS5");
		footerCopyRight.setForeground(new Color(255, 255, 255));
		footerCopyRight.setFont(new Font("Helvetica", Font.PLAIN, 13));
		footerCopyRight.setHorizontalAlignment(SwingConstants.CENTER);
		footerCopyRight.setBounds(6, 5, 1200, 16);
		bottomBorder.add(footerCopyRight);
	}

//	product cards
	public void prodCards(final String roomDetails, int posX, int posY) {
		ReadJson.fetchData(roomDetails);
		JPanel prodCardsPanel = new RoundedPanel(20, Color.white);
		prodCardsPanel.setBounds(posX, posY, 192, 216);
		homePanel.add(prodCardsPanel);
		prodCardsPanel.setLayout(null);

		JLabel prodCardImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource(ReadJson.dummyProdCardsIcon)).getImage();
		prodCardImg.setIcon(new ImageIcon(img));
		prodCardImg.setBounds(6, 5, 180, 107);
		prodCardsPanel.add(prodCardImg);

		JLabel prodCardName = new JLabel(ReadJson.roomFName);
		prodCardName.setFont(new Font("Helvetica", Font.BOLD, 15));
		prodCardName.setBounds(6, 124, 180, 28);
		prodCardsPanel.add(prodCardName);

		JLabel prodCardRoomPrice = new JLabel(ReadJson.roomSPrice);
		prodCardRoomPrice.setFont(new Font("Helvetica", Font.PLAIN, 13));
		prodCardRoomPrice.setBounds(6, 151, 180, 16);
		prodCardsPanel.add(prodCardRoomPrice);

		RoundedButton prodCardBtn = new RoundedButton("View");
		prodCardBtn.setBackground(new Color(255, 255, 255));
		prodCardBtn.setFont(new Font("Helvetica", Font.PLAIN, 13));
		prodCardBtn.setBounds(6, 179, 180, 30);
		primaryBtn(prodCardBtn, 6, 179, 30, 180, prodCardsPanel);
		prodCardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ReadJson.fetchData(roomDetails);
				modalSetRoom = roomDetails;
				System.out.print(modalSetRoom);
				roomInfoPrice.setText(ReadJson.roomSPrice);
				roomInfoLabel.setText(ReadJson.roomFName);
				txtRoomName.setText(ReadJson.roomDesc);
			}
		});
	}

//	small product cards
	public void prodCardsSmall(String roomDetails, int posX, int posY) {
		ReadJson.fetchData(roomDetails);
		JPanel prodCardSmallPanel = new RoundedPanel(20, Color.white);
		prodCardSmallPanel.setBounds(posX, posY, 268, 69);
		homePanel.add(prodCardSmallPanel);
		prodCardSmallPanel.setLayout(null);

		JLabel prodCardImg = new JLabel("");
		prodCardImg.setBounds(6, 6, 101, 57);
		prodCardSmallPanel.add(prodCardImg);
		Image image = new ImageIcon(this.getClass().getResource(ReadJson.dummySmallCardsIcon)).getImage();
		prodCardImg.setIcon(new ImageIcon(image));

		JLabel prodCardName = new JLabel(ReadJson.roomFName);
		prodCardName.setFont(new Font("Helvetica", Font.BOLD, 13));
		prodCardName.setBounds(119, 6, 143, 24);
		prodCardSmallPanel.add(prodCardName);

		JLabel prodCardPrice = new JLabel(ReadJson.roomSPrice);
		prodCardPrice.setFont(new Font("Helvetica", Font.BOLD, 13));
		prodCardPrice.setBounds(119, 42, 143, 16);
		prodCardSmallPanel.add(prodCardPrice);

		JPanel border = new JPanel();
		border.setBackground(new Color(0, 0, 0));
		border.setBounds(119, 32, 120, 2);
		prodCardSmallPanel.add(border);
	}

//	home main panel
	public void homePanel() {
		animatePanel(0, 207, homePanel);

		JLabel homeHeading = new JLabel("Check our rooms");
		homeHeading.setFont(new Font("Helvetica", Font.BOLD, 20));
		homeHeading.setBounds(58, 34, 170, 21);
		homePanel.add(homeHeading);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 163, 255));
		panel.setBounds(58, 56, 74, 10);
		homePanel.add(panel);

		JPanel homeHeadingPanel = new RoundedPanel(20, Color.white);
		homeHeadingPanel.setBackground(new Color(240, 239, 239));
		homeHeadingPanel.setBounds(58, 78, 192, 39);
		homePanel.add(homeHeadingPanel);
		homeHeadingPanel.setLayout(null);

		JLabel homeHeadinglabel = new JLabel("Here some of our rooms");
		homeHeadinglabel.setBounds(23, 6, 140, 27);
		homeHeadingPanel.add(homeHeadinglabel);
		homeHeadinglabel.setForeground(new Color(126, 126, 126));
		homeHeadinglabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		homeHeadinglabel.setBackground(new Color(255, 255, 255));

		homePanel.setBorder(null);
		homePanel.setBackground(new Color(240, 239, 239));
		homePanel.setBounds(207, 0, 737, 647);
		getContentPane().add(homePanel);
		homePanel.setLayout(null);

//		home prodcards
		prodCards("romanticRetreat" , 58, 129);
		prodCards("fireplazeCozy" , 280, 129);
		prodCards("gardenOasis" , 502, 129);

//		availble rooms
		JPanel homeSubHeading = new RoundedPanel(20, Color.white);
		homeSubHeading.setBackground(new Color(240, 239, 239));
		homeSubHeading.setBounds(58, 372, 147, 39);
		homePanel.add(homeSubHeading);
		homeSubHeading.setLayout(null);

		JLabel homeHeadingSublabel = new JLabel("Available rooms");
		homeHeadingSublabel.setBounds(23, 6, 102, 27);
		homeSubHeading.add(homeHeadingSublabel);
		homeHeadingSublabel.setForeground(new Color(126, 126, 126));
		homeHeadingSublabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		homeHeadingSublabel.setBackground(new Color(255, 255, 255));

		prodCardsSmall("romanticRetreat", 58, 423);
		prodCardsSmall("panoramicPenthouse", 58, 510);
		prodCardsSmall("beachBungalow", 356, 423);
		prodCardsSmall("luxuryHaven", 356, 510);
	}

//	amenities cards
	public void amenitiesCards(String images, String label, int posX, int posY) {
		JPanel itemPanel = new RoundedPanel(20, Color.white);
		itemPanel.setBounds(posX, posY, 297, 189);
		itemPanel.setLayout(null);
		amenitiesPanel.add(itemPanel);

		JLabel image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBounds(10, 11, 277, 143);
		itemPanel.add(image);

		Image img = new ImageIcon(this.getClass().getResource(images)).getImage();
		image.setIcon(new ImageIcon(img));

		JLabel lblNewLabel_5 = new JLabel(label);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Helvetica", Font.BOLD, 20));
		lblNewLabel_5.setBounds(10, 155, 277, 32);
		itemPanel.add(lblNewLabel_5);
	}

//	amenities main panel
	public void amenitiesPanel() {
		amenitiesPanel.setBounds(218, 0, 978, 582);
		getContentPane().add(amenitiesPanel);
		amenitiesPanel.setLayout(null);

		JLabel amenitiesHeading = new JLabel("Amenities");
		amenitiesHeading.setFont(new Font("Dialog", Font.BOLD, 20));
		amenitiesHeading.setBounds(58, 34, 118, 26);
		amenitiesPanel.add(amenitiesHeading);

		JPanel heading_blue = new JPanel();
		heading_blue.setBackground(new Color(0, 163, 255));
		heading_blue.setBounds(58, 58, 45, 6);
		amenitiesPanel.add(heading_blue);

		JLabel amenitiesSubHeading = new JLabel("Perfect home with perfect space and amenities.");
		amenitiesSubHeading.setForeground(new Color(159, 158, 158));
		amenitiesSubHeading.setFont(new Font("Tahoma", Font.PLAIN, 13));
		amenitiesSubHeading.setBounds(57, 74, 273, 14);
		amenitiesPanel.add(amenitiesSubHeading);

		amenitiesCards("/item1.png", "Swimming Pools", 139, 99);
		amenitiesCards("/item2.png", "Indoor Fitness Area", 487, 99);
		amenitiesCards("/item3.png", "Outdoor Playground", 10, 336);
		amenitiesCards("/item4.png", "Billiards", 335, 336);
		amenitiesCards("/item5.png", "Golf Course", 671, 336);
	}

//	room cards
	public void roomsCards(String image, final String roomName, int posX, int posY) {
		ReadJson.fetchData(roomName);
		JPanel roomsCardPanel = new RoundedPanel(20, Color.white);
		roomsCardPanel.setBounds(posX, posY, 200, 220);
		roomsPanel.add(roomsCardPanel);
		roomsCardPanel.setLayout(null);

		JLabel roomsCardImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource(image)).getImage();
		roomsCardImg.setIcon(new ImageIcon(img));
		roomsCardImg.setBounds(10, 0, 180, 140);
		roomsCardPanel.add(roomsCardImg);

		JLabel roomsCardName = new JLabel(ReadJson.roomFName);
		roomsCardName.setFont(new Font("Helvetica", Font.BOLD, 15));
		roomsCardName.setHorizontalAlignment(SwingConstants.CENTER);
		roomsCardName.setBounds(35, 135, 128, 24);
		roomsCardPanel.add(roomsCardName);

		JLabel roomsCardPrice = new JLabel(ReadJson.roomSPrice);
		roomsCardPrice.setFont(new Font("Helvetica", Font.PLAIN, 13));
		roomsCardPrice.setHorizontalAlignment(SwingConstants.CENTER);
		roomsCardPrice.setBounds(45, 155, 108, 24);
		roomsCardPanel.add(roomsCardPrice);

		RoundedButton roomsCardBtn = new RoundedButton("View");
		primaryBtn(roomsCardBtn, 10, 180, 30, 180, roomsCardPanel);
		roomsCardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ReadJson.fetchData(roomName);
				modalSetRoom = roomName;
				System.out.print(modalSetRoom);
				roomInfoPrice.setText(ReadJson.roomSPrice);
				roomInfoLabel.setText(ReadJson.roomFName);
				txtRoomName.setText(ReadJson.roomDesc);
			}
		});
	}

//	room panel
	public void roomsPanel() {
		roomsPanel.setBounds(208, 0, 1004, 625);
		getContentPane().add(roomsPanel);
		roomsPanel.setLayout(null);

		JLabel roomsHeading = new JLabel("Available Rooms");
		roomsHeading.setFont(new Font("Dialog", Font.BOLD, 20));
		roomsHeading.setBounds(57, 22, 180, 20);
		roomsPanel.add(roomsHeading);

		JPanel panel_blue = new JPanel();
		panel_blue.setBackground(new Color(0, 163, 255));
		panel_blue.setBounds(57, 43, 70, 6);
		roomsPanel.add(panel_blue);

		roomsCards("/room2.png", "romanticRetreat", 57, 80);
		roomsCards("/room2.png", "luxuryHaven", 270, 80);
		roomsCards("/room2.png", "panoramicPenthouse", 485, 80);
		roomsCards("/room2.png", "beachBungalow", 57, 339);
		roomsCards("/room2.png", "gardenOasis", 270, 339);
		roomsCards("/room2.png", "fireplazeCozy", 485, 339);
	}

//	set checkout btn
	public void setCheckOutBtn() {
		if (isCheckout == true) {
			bookBtn.setVisible(false);
			showMoreBtn.setVisible(false);
			cancelCheckoutBtn.setVisible(true);
		} else {
			bookBtn.setVisible(true);
			showMoreBtn.setVisible(true);
			cancelCheckoutBtn.setVisible(false);
		}
	}

//	room side info
	public void roomInfo(String data, String panel) {
		ReadJson.fetchData(data);

		roomInfoPrice.setText(ReadJson.roomSPrice);
		roomInfoLabel.setText(ReadJson.roomFName);
		txtRoomName.setText(ReadJson.roomDesc);

		infoSidePanel.setBackground(new Color(255, 255, 255));
		infoSidePanel.setBounds(944, 0, 268, 647);
		infoSidePanel.setLayout(null);
		getContentPane().add(infoSidePanel);

		JLabel roomInfoHeading = new JLabel("Room Info");
		roomInfoHeading.setHorizontalAlignment(SwingConstants.CENTER);
		roomInfoHeading.setFont(new Font("Helvetica", Font.BOLD, 20));
		roomInfoHeading.setBounds(6, 18, 256, 28);
		infoSidePanel.add(roomInfoHeading);

		roomInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomInfoLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		roomInfoLabel.setBounds(6, 66, 256, 28);
		infoSidePanel.add(roomInfoLabel);

		JPanel headingBorder = new JPanel();
		headingBorder.setBackground(new Color(0, 163, 255));
		headingBorder.setBounds(46, 47, 170, 10);
		infoSidePanel.add(headingBorder);

		JLabel roomInfoImage = new JLabel("");
		roomInfoImage.setBounds(16, 106, 235, 140);
		infoSidePanel.add(roomInfoImage);

		Image image = new ImageIcon(this.getClass().getResource(ReadJson.dummyheroIcon)).getImage();
		roomInfoImage.setIcon(new ImageIcon(image));

		Color custom = Color.decode("#00A3FF");
		JPanel roomInfoPriceBg = new RoundedPanel(20, custom);
		roomInfoPriceBg.setBackground(new Color(255, 255, 255));
		roomInfoPriceBg.setBounds(46, 260, 181, 36);
		infoSidePanel.add(roomInfoPriceBg);
		roomInfoPriceBg.setLayout(null);

		roomInfoPrice.setForeground(new Color(255, 255, 255));
		roomInfoPrice.setFont(new Font("Helvetica", Font.BOLD, 13));
		roomInfoPrice.setHorizontalAlignment(SwingConstants.CENTER);
		roomInfoPrice.setBounds(6, 6, 169, 24);
		roomInfoPriceBg.add(roomInfoPrice);

		txtRoomName.setForeground(new Color(126, 126, 126));
		txtRoomName.setEditable(false);
		txtRoomName.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtRoomName.setBounds(16, 310, 235, 110);
		infoSidePanel.add(txtRoomName);
		StyledDocument doc = txtRoomName.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		primaryBtn(showMoreBtn, 46, 450, 38, 181, infoSidePanel);
		showMoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(false);
				modalBg.setVisible(true);
				ReadJson.fetchData(modalSetRoom);
				roomModalTitle.setText(ReadJson.roomFName);
				modalRoomPrice.setText(ReadJson.roomSPrice);
				roomInfoDesc.setText(ReadJson.roomDesc);
				feature1.setText(ReadJson.feature1);
				feature2.setText(ReadJson.feature2);
				feature3.setText(ReadJson.feature3);
			}
		});

		primaryBtn(cancelCheckoutBtn, 46, 450, 38, 181, infoSidePanel);
		cancelCheckoutBtn.setVisible(false);
		cancelCheckoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				isCheckout = false;
				closeAllPanel(true);
				homePanel.setVisible(true);
				setCheckOutBtn();
				resetCheckout();
			}
		});

		primaryBtn(bookBtn, 46, 498, 38, 181, infoSidePanel);
		bookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(true);
				checkoutFirstPanel.setVisible(true);
				animatePanel(0, 218, checkoutFirstPanel);
				isCheckout = true;
				setCheckOutBtn();
			}
		});
	}

//	room modal
	public void roomModal() {
		Color blueCircle = new Color(0, 163, 255);
		Image image1 = new ImageIcon(this.getClass().getResource("/sample-image-room-info.png")).getImage();

		modalBg.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		modalBg.setBounds(209, 0, 1003, 647);
		getContentPane().add(modalBg);
		modalBg.setLayout(null);

		JPanel roomModal = new JPanel();
		roomModal.setBounds(178, 81, 547, 466);
		modalBg.add(roomModal);
		roomModal.setBackground(new Color(255, 255, 255));
		roomModal.setLayout(null);

		JLabel lblNewLabel = new JLabel("Room Info");
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		lblNewLabel.setBounds(41, 21, 128, 45);
		roomModal.add(lblNewLabel);

		JPanel borderBottomModal = new JPanel();
		borderBottomModal.setBackground(new Color(160, 160, 160));
		borderBottomModal.setBounds(41, 56, 117, 10);
		roomModal.add(borderBottomModal);

		JPanel circleModal = new RoundedPanel(100, blueCircle);
		circleModal.setBackground(new Color(255, 255, 255));
		circleModal.setBounds(210, 29, 37, 37);
		roomModal.add(circleModal);

		roomModalTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
		roomModalTitle.setBounds(259, 31, 232, 37);
		roomModal.add(roomModalTitle);

		JLabel imageOne = new JLabel("");
		imageOne.setBounds(43, 105, 235, 140);
		roomModal.add(imageOne);
		imageOne.setIcon(new ImageIcon(image1));

		JLabel imageTwo = new JLabel("");
		imageTwo.setBounds(41, 268, 235, 140);
		roomModal.add(imageTwo);
		imageTwo.setIcon(new ImageIcon(image1));

		JLabel roomNumber = new JLabel("Room Details:");
		roomNumber.setHorizontalAlignment(SwingConstants.LEFT);
		roomNumber.setFont(new Font("Helvetica", Font.BOLD, 18));
		roomNumber.setBounds(308, 77, 196, 37);
		roomModal.add(roomNumber);

		JPanel roomPrice = new RoundedPanel(25, blueCircle);
		roomPrice.setBackground(new Color(255, 255, 255));
		roomPrice.setBounds(308, 115, 189, 37);
		roomModal.add(roomPrice);
		roomPrice.setLayout(null);

		modalRoomPrice.setForeground(new Color(255, 255, 255));
		modalRoomPrice.setBounds(6, 0, 177, 37);
		roomPrice.add(modalRoomPrice);
		modalRoomPrice.setHorizontalAlignment(SwingConstants.CENTER);
		modalRoomPrice.setFont(new Font("Helvetica", Font.BOLD, 13));

		roomInfoDesc.setEnabled(false);
		roomInfoDesc.setForeground(new Color(126, 126, 126));
		roomInfoDesc.setFont(new Font("Helvetica", Font.PLAIN, 12));
		roomInfoDesc.setText(ReadJson.roomDesc);
		roomInfoDesc.setBounds(308, 164, 196, 88);
		roomModal.add(roomInfoDesc);

		JLabel lblNewLabel_2 = new JLabel("Features:");
		lblNewLabel_2.setFont(new Font("Helvetica", Font.BOLD, 13));
		lblNewLabel_2.setBounds(308, 264, 82, 16);
		roomModal.add(lblNewLabel_2);

		feature1.setFont(new Font("Helvetica", Font.PLAIN, 13));
		feature1.setBounds(308, 290, 156, 16);
		roomModal.add(feature1);

		feature2.setFont(new Font("Helvetica", Font.PLAIN, 13));
		feature2.setBounds(308, 318, 156, 16);
		roomModal.add(feature2);

		feature3.setFont(new Font("Helvetica", Font.PLAIN, 13));
		feature3.setBounds(308, 346, 156, 16);
		roomModal.add(feature3);

		JButton closeModalBtn = new RoundedButton("Close");
		primaryBtn(closeModalBtn, 308, 387, 38, 160, roomModal);
		closeModalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				roomsPanel.setVisible(true);
				infoSidePanel.setVisible(true);
				modalBg.setVisible(false);
			}
		});

		closeModalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				roomsPanel.setVisible(true);
				modalBg.setVisible(false);
			}
		});
	}

// 	checkout first step
	public void checkoutFirstStep() {
		checkoutFirstPanel.setBounds(242, 0, 656, 624);
		getContentPane().add(checkoutFirstPanel);
		checkoutFirstPanel.setLayout(null);

		JLabel stepOneHeading = new JLabel("Select Date");
		stepOneHeading.setFont(new Font("Helvetica", Font.BOLD, 20));
		stepOneHeading.setBounds(44, 92, 108, 21);
		checkoutFirstPanel.add(stepOneHeading);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 163, 255));
		panel_1.setBounds(44, 125, 562, 32);
		checkoutFirstPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel steapHeading = new JLabel("Step 1");
		steapHeading.setFont(new Font("Helvetica", Font.BOLD, 17));
		steapHeading.setForeground(new Color(255, 255, 255));
		steapHeading.setBounds(6, 6, 61, 20);
		panel_1.add(steapHeading);

		checkInHeading.setFont(new Font("Helvetica", Font.BOLD, 18));
		checkInHeading.setBounds(88, 180, 132, 21);
		checkoutFirstPanel.add(checkInHeading);

		selectedCheckoutDate.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCheckoutDate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		selectedCheckoutDate.setBounds(424, 205, 124, 21);
		checkoutFirstPanel.add(selectedCheckoutDate);

		selectedCheckinDate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		selectedCheckinDate.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCheckinDate.setBounds(88, 205, 124, 21);
		checkoutFirstPanel.add(selectedCheckinDate);

		selectDateLabel_1.setFont(new Font("Helvetica", Font.BOLD, 18));
		selectDateLabel_1.setBounds(424, 180, 138, 21);
		checkoutFirstPanel.add(selectDateLabel_1);

		JLabel lblNewLabel_1 = new JLabel("Total Days:");
		lblNewLabel_1.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
		lblNewLabel_1.setBounds(256, 306, 81, 16);
		checkoutFirstPanel.add(lblNewLabel_1);

		lblNewLabel_3.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(339, 306, 61, 16);
		checkoutFirstPanel.add(lblNewLabel_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(147, 147, 147));
		panel_2.setBounds(88, 337, 481, 2);
		checkoutFirstPanel.add(panel_2);

		primaryBtn(checkinDateBtn, 88, 235, 38, 120, checkoutFirstPanel);
		checkinDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				closeAllPanel(false);
				dateChooserPanel.setVisible(true);
				endDateBtn.setVisible(false);
				startDateBtn.setVisible(true);
				checkinHeading.setText("Check-in");
				calendarParam = "startDate";
				checkinDate = checkinDateF;
				isFirstComplete = false;
				isCheckin = false;
				System.out.println(calendarParam + " : " + checkinDate);
				selectedCheckoutDate.setText("Select Date");
			}
		});

		primaryBtn(checkoutDateBtn, 424, 235, 38, 120, checkoutFirstPanel);
		checkoutDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (isCheckin == true) {
					closeAllPanel(false);
					dateChooserPanel.setVisible(true);
					endDateBtn.setVisible(true);
					startDateBtn.setVisible(false);
					checkinHeading.setText("Check-out");
					calendarParam = "endDate";
					isFirstComplete = true;
					System.out.println(calendarParam);
					checkinDate = checkoutDateF;
					System.out.println(calendarParam + " : " + checkinDate);
				} else {
					selectedCheckoutDate.setText("Check-in First");
				}
			}
		});

		primaryBtn(btnContinueC, 255, 375, 38, 120, checkoutFirstPanel);
		btnContinueC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (isFirstComplete == true) {
					closeAllPanel(true);
					checkoutSecondPanel.setVisible(true);
					animatePanel(0, 207, checkoutSecondPanel);
					checkinDateS3.setText(startDateEng);
					checkoutDateS3.setText(endDateEng);
					ReadJson.fetchData(modalSetRoom);
					totalAmount = ReadJson.roomPrice * dayBetween;
					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					String formattedNumber = numberFormat.format(totalAmount);
					totalAmountS3.setText("P " + formattedNumber);
					WriteJson.addRef();
					ReadJson.fetchRef();
					refNoS3.setText(ReadJson.refNo + "");
				}
			}
		});
	}

//	date converter method
	public void dateConverter(String selectedDate) {
		checkinDate = calendar.getDate();
		DateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy");
		DateFormat dateFormatYF = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputDateFormat = new SimpleDateFormat("MMMM dd yyyy");
		String strDate = dateFormat.format(checkinDate);
		String strDate2 = dateFormatYF.format(checkinDate);
		LocalDate conDate = LocalDate.parse(strDate2, DateTimeFormatter.ISO_LOCAL_DATE);
		Date date;

		try {
			date = dateFormat.parse(strDate);
			String englishDate = outputDateFormat.format(date);
			dateText.setText(englishDate);
			if (selectedDate == "startDate") {
				startDateF = conDate;
				startDateEng = englishDate;
				checkinDateF = checkinDate;
			} else if (selectedDate == "endDate") {
				endDateF = conDate;
				endDateEng = englishDate;
				checkoutDateF = checkinDate;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	calendar panel
	public void calendarPanel() {
		dateChooserPanel.setBounds(207, 0, 1005, 624);
		getContentPane().add(dateChooserPanel);
		dateChooserPanel.setLayout(null);

		JPanel modal = new RoundedPanel(20, Color.white);
		modal.setBounds(128, 106, 780, 437);
		dateChooserPanel.add(modal);
		modal.setLayout(null);

		checkinHeading.setBounds(291, 21, 200, 30);
		checkinHeading.setHorizontalAlignment(SwingConstants.CENTER);
		modal.add(checkinHeading);
		checkinHeading.setFont(new Font("Helvetica", Font.BOLD, 29));

		JPanel headingBorder = new JPanel();
		headingBorder.setBounds(317, 50, 152, 10);
		modal.add(headingBorder);
		headingBorder.setBackground(new Color(160, 160, 160));

		JPanel calendarPanel = new JPanel();
		calendarPanel.setBounds(82, 105, 628, 246);
		modal.add(calendarPanel);
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				dateConverter(calendarParam);
			}
		});

		dateText.setFont(new Font("Helvetica", Font.BOLD, 18));
		dateText.setHorizontalAlignment(SwingConstants.CENTER);
		dateText.setBounds(311, 72, 171, 21);
		modal.add(dateText);

		calendarPanel.add(calendar);

		primaryBtn(startDateBtn, 335, 372, 38, 160, modal);

		startDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dateConverter("startDate");
				LocalDate today = LocalDate.now();
				long daysBetween = ChronoUnit.DAYS.between(today, startDateF);

				if (daysBetween < 0) {
					dateText.setText("Please select again");
				} else {
					selectedCheckinDate.setText(startDateEng);
					closeAllPanel(true);
					checkoutFirstPanel.setVisible(true);
					isCheckin = true;
				}
			}
		});

		primaryBtn(endDateBtn, 335, 372, 38, 160, modal);
		endDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dateConverter("endDate");
				dayBetween = ChronoUnit.DAYS.between(startDateF, endDateF);

				if (dayBetween <= 0) {
					dateText.setText("Please select again");
				} else {
					selectedCheckoutDate.setText(endDateEng);
					lblNewLabel_3.setText(dayBetween + "");
					closeAllPanel(true);
					checkoutFirstPanel.setVisible(true);
					totalDaysF = dayBetween;
				}
			}
		});
	}

//	get ref code details
	public void showRef(int getRef) {
		ReadJson.fetchRefData(getRef);
		dName.setText(ReadJson.rFullName);
		dEmail.setText(ReadJson.rEmail);
		dTelNo.setText(ReadJson.rTelNo);
		rRefNo.setText(ReadJson.rRefNos + "");
		rCheckin.setText(ReadJson.rDateRange);
		rDate.setText(ReadJson.rdateBooked);
		rTotPrice.setText("P" + ReadJson.rTotal + "");
		ReadJson.fetchData(ReadJson.rRoomName);
		rRoomName.setText(ReadJson.roomFName);
	}

//	checkout last step
	public void checkoutLastStep() {
		formName = new JTextField();
		formEmail = new JTextField();
		formCardName = new JTextField();
		formTelNo = new JTextField();
		textField_4 = new JTextField();
		textField_5 = new JTextField();
		
		checkoutSecondPanel.setBounds(208, 0, 737, 625);
		getContentPane().add(checkoutSecondPanel);
		checkoutSecondPanel.setLayout(null);
		
		JPanel stepHeading = new JPanel();
		stepHeading.setBackground(new Color(0, 163, 255));
		stepHeading.setBounds(21, 43, 696, 38);
		checkoutSecondPanel.add(stepHeading);
		stepHeading.setLayout(null);
		
		JLabel heading = new JLabel("Step 2");
		heading.setForeground(new Color(255, 255, 255));
		heading.setFont(new Font("Helvetica", Font.BOLD, 15));
		heading.setBounds(20, 6, 80, 26);
		stepHeading.add(heading);

		JPanel panel = new JPanel();
		panel.setBounds(109, 122, 220, 38);
		panel.setBackground(Color.WHITE); // Set the background color of the panel
		panel.setBorder(new RoundedBorder(10, 2));
		panel.setLayout(null);

		formName.setFont(new Font("Helvetica", Font.PLAIN, 18));
		formName.setBounds(6, 6, 208, 26);
		formName.setColumns(10);
		formName.setOpaque(false);
		formName.setBorder(null); // Set the border to null

		panel.add(formName); // Add the text field to the panel
		checkoutSecondPanel.add(panel);
		
		JLabel labelForm = new JLabel("Full Name");
		labelForm.setFont(new Font("Helvetica", Font.BOLD, 16));
		labelForm.setBounds(109, 94, 208, 28);
		checkoutSecondPanel.add(labelForm);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new RoundedBorder(10, 2));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(109, 205, 220, 38);
		checkoutSecondPanel.add(panel_1);
		
		formEmail.setOpaque(false);
		formEmail.setFont(new Font("Helvetica", Font.PLAIN, 18));
		formEmail.setColumns(10);
		formEmail.setBorder(null);
		formEmail.setBounds(6, 6, 208, 26);
		panel_1.add(formEmail);
		
		JLabel labelForm_1 = new JLabel("Email");
		labelForm_1.setFont(new Font("Helvetica", Font.BOLD, 16));
		labelForm_1.setBounds(109, 177, 208, 28);
		checkoutSecondPanel.add(labelForm_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new RoundedBorder(10, 2));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(109, 292, 220, 38);
		checkoutSecondPanel.add(panel_1_1);
		
		formCardName.setOpaque(false);
		formCardName.setFont(new Font("Helvetica", Font.PLAIN, 18));
		formCardName.setColumns(10);
		formCardName.setBorder(null);
		formCardName.setBounds(6, 6, 208, 26);
		panel_1_1.add(formCardName);
		
		JLabel labelForm_2 = new JLabel("Card Name");
		labelForm_2.setFont(new Font("Helvetica", Font.BOLD, 16));
		labelForm_2.setBounds(109, 264, 208, 28);
		checkoutSecondPanel.add(labelForm_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new RoundedBorder(10, 2));
		panel_1_1_1.setBackground(Color.WHITE);
		panel_1_1_1.setBounds(399, 122, 220, 38);
		checkoutSecondPanel.add(panel_1_1_1);

		formTelNo.setOpaque(false);
		formTelNo.setFont(new Font("Helvetica", Font.PLAIN, 18));
		formTelNo.setColumns(10);
		formTelNo.setBorder(null);
		formTelNo.setBounds(6, 6, 208, 26);
		panel_1_1_1.add(formTelNo);
		
		JLabel labelForm_3 = new JLabel("Phone Number");
		labelForm_3.setFont(new Font("Helvetica", Font.BOLD, 16));
		labelForm_3.setBounds(399, 94, 208, 28);
		checkoutSecondPanel.add(labelForm_3);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new RoundedBorder(10, 2));
		panel_1_1_2.setBackground(Color.WHITE);
		panel_1_1_2.setBounds(399, 205, 220, 38);
		checkoutSecondPanel.add(panel_1_1_2);
		
		textField_4.setOpaque(false);
		textField_4.setFont(new Font("Helvetica", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBounds(6, 6, 208, 26);
		panel_1_1_2.add(textField_4);
		
		JLabel formAddress = new JLabel("Address");
		formAddress.setFont(new Font("Helvetica", Font.BOLD, 16));
		formAddress.setBounds(399, 177, 208, 28);
		checkoutSecondPanel.add(formAddress);
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setLayout(null);
		panel_1_1_3.setBorder(new RoundedBorder(10, 2));
		panel_1_1_3.setBackground(Color.WHITE);
		panel_1_1_3.setBounds(399, 292, 220, 38);
		checkoutSecondPanel.add(panel_1_1_3);

		textField_5.setOpaque(false);
		textField_5.setFont(new Font("Helvetica", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		textField_5.setBounds(6, 6, 208, 26);
		panel_1_1_3.add(textField_5);
		
		JLabel formCardNo = new JLabel("Card Number");
		formCardNo.setFont(new Font("Helvetica", Font.BOLD, 16));
		formCardNo.setBounds(399, 264, 208, 28);
		checkoutSecondPanel.add(formCardNo);
		
		JPanel stepHeading_1 = new JPanel();
		stepHeading_1.setLayout(null);
		stepHeading_1.setBackground(new Color(0, 163, 255));
		stepHeading_1.setBounds(21, 350, 696, 38);
		checkoutSecondPanel.add(stepHeading_1);
		
		JLabel lblStep = new JLabel("Step 3");
		lblStep.setForeground(Color.WHITE);
		lblStep.setFont(new Font("Helvetica", Font.BOLD, 15));
		lblStep.setBounds(20, 6, 80, 26);
		stepHeading_1.add(lblStep);
		
		JLabel step3Heading = new JLabel("Check-in Date:");
		step3Heading.setFont(new Font("Helvetica", Font.BOLD, 18));
		step3Heading.setBounds(173, 413, 132, 16);
		checkoutSecondPanel.add(step3Heading);
		
		JLabel lblCheckoutDate = new JLabel("Check-out Date:");
		lblCheckoutDate.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblCheckoutDate.setBounds(441, 413, 138, 16);
		checkoutSecondPanel.add(lblCheckoutDate);
		
		JLabel lblRefNumber = new JLabel("Ref Number:");
		lblRefNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefNumber.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblRefNumber.setBounds(173, 487, 132, 16);
		checkoutSecondPanel.add(lblRefNumber);
		
		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAmount.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblTotalAmount.setBounds(447, 487, 132, 16);
		checkoutSecondPanel.add(lblTotalAmount);
		
		checkinDateS3.setFont(new Font("Helvetica", Font.PLAIN, 15));
		checkinDateS3.setHorizontalAlignment(SwingConstants.CENTER);
		checkinDateS3.setBounds(173, 436, 124, 16);
		checkoutSecondPanel.add(checkinDateS3);
		
		checkoutDateS3.setHorizontalAlignment(SwingConstants.CENTER);
		checkoutDateS3.setFont(new Font("Helvetica", Font.PLAIN, 15));
		checkoutDateS3.setBounds(451, 435, 124, 16);
		checkoutSecondPanel.add(checkoutDateS3);
		
		refNoS3.setHorizontalAlignment(SwingConstants.CENTER);
		refNoS3.setFont(new Font("Helvetica", Font.PLAIN, 15));
		refNoS3.setBounds(173, 509, 124, 16);
		checkoutSecondPanel.add(refNoS3);
		
		totalAmountS3.setHorizontalAlignment(SwingConstants.CENTER);
		totalAmountS3.setFont(new Font("Helvetica", Font.PLAIN, 15));
		totalAmountS3.setBounds(455, 509, 124, 16);
		checkoutSecondPanel.add(totalAmountS3);
		
		primaryBtn(checkoutConfirmBtn, 290, 560, 38, 160, checkoutSecondPanel);
		checkoutConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cFName = formName.getText();
				cEmail = formEmail.getText();
				cAddress =  textField_4.getText(); 
				cCardName = formCardName.getText();
				cCardNumber = textField_5.getText();
				cTelNo = formTelNo.getText();
				DateFormat outputDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
				final Date startDate = Date.from(startDateF.atStartOfDay(ZoneOffset.UTC).toInstant());
				final Date endDate = Date.from(endDateF.atStartOfDay(ZoneOffset.UTC).toInstant());
				String englishDateStart = outputDateFormat.format(startDate);
				String englishDateEnd = outputDateFormat.format(endDate);
				String dateRange = englishDateStart + " - " + englishDateEnd;
				Date currentDate = new Date();
				String receiptDate = outputDateFormat.format(currentDate);
				WriteJson.setData(modalSetRoom, cFName, cEmail, cAddress, cTelNo, cCardName, cCardNumber, totalDaysF, ReadJson.refNo, totalAmount, dateRange, receiptDate);
				closeAllPanel(false);
				receiptPanel.setVisible(true);
				resetCheckout();
				showRef((int) ReadJson.refNo);
				formRef.setText(ReadJson.refNo + "");
			}
		});
	}

// 	receipt
	public void receipt() {
		receiptPanel.setBounds(207, 0, 1005, 624);
		getContentPane().add(receiptPanel);
		receiptPanel.setLayout(null);

		JLabel receiptHeading = new JLabel("Receipt");
		receiptHeading.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		receiptHeading.setBounds(32, 50, 92, 25);
		receiptPanel.add(receiptHeading);

		JPanel headingBorder = new JPanel();
		headingBorder.setBackground(new Color(0, 163, 255));
		headingBorder.setBounds(30, 77, 43, 10);
		receiptPanel.add(headingBorder);

		JLabel formLabel = new JLabel("Enter Ref Number");
		formLabel.setFont(new Font("Helvetica", Font.PLAIN, 17));
		formLabel.setBounds(32, 133, 135, 16);
		receiptPanel.add(formLabel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new RoundedBorder(10, 2));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(32, 161, 213, 37);
		receiptPanel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		formRef = new JTextField();
		formRef.setBounds(6, 0, 201, 37);
		panel_1_1.add(formRef);
		formRef.setOpaque(false);
		formRef.setFont(new Font("Helvetica", Font.PLAIN, 18));
		formRef.setColumns(10);
		formRef.setBorder(null);
		
		primaryBtn(findRef, 32, 211, 35, 213, receiptPanel);
		findRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showRef(Integer.parseInt(formRef.getText()));
			}
		});

		Color customColor = new Color(203,233,250);
		JPanel clientDetailsPanel = new RoundedPanel(20, customColor);
		clientDetailsPanel.setBounds(32, 312, 213, 116);
		receiptPanel.add(clientDetailsPanel);
		clientDetailsPanel.setLayout(null);
		
		JLabel detailsHeading = new JLabel("Details:");
		detailsHeading.setFont(new Font("Helvetica", Font.BOLD, 14));
		detailsHeading.setBounds(6, 6, 61, 16);
		clientDetailsPanel.add(detailsHeading);
		
		dName.setFont(new Font("Helvetica", Font.PLAIN, 14));
		dName.setBounds(6, 29, 201, 16);
		clientDetailsPanel.add(dName);
		
		dEmail.setFont(new Font("Helvetica", Font.PLAIN, 14));
		dEmail.setBounds(6, 55, 201, 16);
		clientDetailsPanel.add(dEmail);
		
		dTelNo.setFont(new Font("Helvetica", Font.PLAIN, 14));
		dTelNo.setBounds(6, 83, 201, 16);
		clientDetailsPanel.add(dTelNo);
		
		JLabel lblBillTo = new JLabel("Bill To:");
		lblBillTo.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblBillTo.setBounds(34, 269, 92, 25);
		receiptPanel.add(lblBillTo);
		
		JPanel headingBorder_1 = new JPanel();
		headingBorder_1.setBackground(new Color(0, 163, 255));
		headingBorder_1.setBounds(32, 296, 92, 5);
		receiptPanel.add(headingBorder_1);
		
		RoundedPanel clientDetailsPanel_1 = new RoundedPanel(20, new Color(203, 233, 250));
		clientDetailsPanel_1.setBounds(258, 312, 653, 116);
		receiptPanel.add(clientDetailsPanel_1);
		clientDetailsPanel_1.setLayout(null);
		
		JLabel rIcon = new JLabel("");
		rIcon.setBounds(6, 6, 190, 104);
		clientDetailsPanel_1.add(rIcon);

		Image img = new ImageIcon(this.getClass().getResource("/room2.png")).getImage();
		rIcon.setIcon(new ImageIcon(img));
		
		JLabel rHeading = new JLabel("Ref No.:");
		rHeading.setFont(new Font("Helvetica", Font.BOLD, 14));
		rHeading.setBounds(208, 14, 61, 16);
		clientDetailsPanel_1.add(rHeading);
		
		rRefNo.setFont(new Font("Helvetica", Font.PLAIN, 14));
		rRefNo.setBounds(208, 35, 92, 16);
		clientDetailsPanel_1.add(rRefNo);
		
		rCheckin.setFont(new Font("Helvetica", Font.PLAIN, 14));
		rCheckin.setBounds(208, 84, 231, 16);
		clientDetailsPanel_1.add(rCheckin);
		
		JLabel rHeading_1 = new JLabel("Check-in:");
		rHeading_1.setFont(new Font("Helvetica", Font.BOLD, 14));
		rHeading_1.setBounds(208, 63, 81, 16);
		clientDetailsPanel_1.add(rHeading_1);
		
		JLabel rHeading_2 = new JLabel("Receipt Date:");
		rHeading_2.setFont(new Font("Helvetica", Font.BOLD, 14));
		rHeading_2.setBounds(336, 14, 92, 16);
		clientDetailsPanel_1.add(rHeading_2);
		
		rDate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		rDate.setBounds(336, 35, 141, 16);
		clientDetailsPanel_1.add(rDate);
		
		JLabel rHeading_2_1 = new JLabel("Total:");
		rHeading_2_1.setFont(new Font("Helvetica", Font.BOLD, 14));
		rHeading_2_1.setBounds(507, 14, 92, 16);
		clientDetailsPanel_1.add(rHeading_2_1);
		
		rTotPrice.setFont(new Font("Helvetica", Font.PLAIN, 14));
		rTotPrice.setBounds(507, 34, 141, 16);
		clientDetailsPanel_1.add(rTotPrice);
		
		rRoomName.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		rRoomName.setBounds(258, 269, 686, 25);
		receiptPanel.add(rRoomName);
		
		JPanel headingBorder_1_1 = new JPanel();
		headingBorder_1_1.setBackground(new Color(0, 163, 255));
		headingBorder_1_1.setBounds(258, 296, 92, 5);
		receiptPanel.add(headingBorder_1_1);
		
		JLabel tyMessage = new JLabel("Thank you for your support");
		tyMessage.setFont(new Font("Helvetica", Font.PLAIN, 15));
		tyMessage.setBounds(368, 460, 183, 16);
		receiptPanel.add(tyMessage);
	}

//	all main methods
	public void allPanels() {
	//	border bottom
		borders();

	//	side info panel
		roomInfo("romanticRetreat", "homePanel");

	//	modal
		roomModal();

	//	home panel
		homePanel();

	//	amenities panel
		amenitiesPanel();

	//	rooms panel
		roomsPanel();

	//	checkout panel
		checkoutFirstStep();
		checkoutLastStep();

	//	calendar panel
		calendarPanel();

	//	receipt
		receipt();

	//	side bar
		sideBar();
	}
	
//	this function is for test and development only
	public void testPanel() {
		borders();
//		roomInfo("romanticRetreat", "homePanel");
		sideBar();
	}
	
	public UserInterface() throws IOException {
//		Resources.main(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1212, 675);
		getContentPane().setLayout(null);
// 		set app icon 
		setIconImage(logo.getImage());
		setResizable(false);

		allPanels();

// 		testPanel();

		closeAllPanel(true);
		homePanel.setVisible(true);
	}
}
