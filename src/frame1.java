import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import java.awt.Color;

public class frame1 {
	private BoardClient client = null;
	private JFrame frmClientPortal;
	private JTextField txtIPaddress;
	private JTextField txtPORT;
	private JTextField txtHEIGHT;
	private JTextField txtWIDTH;

	private JTextField textRefersTo;

	// Swing objects that will get passed to the BoardClient class
	private JTextArea textDisplay;
	JSpinner spinX;
	JSpinner spinY;
	JSpinner spinColour;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 window = new frame1();
					window.frmClientPortal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frame1() {
		initialize();
	}

	/**
	 * Create frmClientPortal JFrame
	 */
	private void renderClientPortal() {
		frmClientPortal = new JFrame();
		frmClientPortal.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmClientPortal.setBackground(new Color(102, 255, 51));
		frmClientPortal.setTitle("Client Portal");
		frmClientPortal.setBounds(100, 100, 652, 594);
		frmClientPortal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frmClientPortal.getContentPane().setLayout(gridBagLayout);
	};

	/**
	 * Create txtIPaddress text field
	 */
	private void renderTextIPAddress() {
		txtIPaddress = new JTextField();
		txtIPaddress.setToolTipText("enter IP address of server in format xxx.x.x.x");
		txtIPaddress.setText("enter IP address");
		GridBagConstraints gbc_txtIPaddress = new GridBagConstraints();
		gbc_txtIPaddress.gridwidth = 5;
		gbc_txtIPaddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtIPaddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIPaddress.gridx = 1;
		gbc_txtIPaddress.gridy = 2;
		frmClientPortal.getContentPane().add(txtIPaddress, gbc_txtIPaddress);
		txtIPaddress.setColumns(10);
	}

	/**
	 * create txtPORT JtextField();
	 */
	private void renderTextPORT() {
		txtPORT = new JTextField();
		txtPORT.setToolTipText("enter the 4 digit port number");
		txtPORT.setText("enter Port number");
		GridBagConstraints gbc_txtPORT = new GridBagConstraints();
		gbc_txtPORT.gridwidth = 3;
		gbc_txtPORT.insets = new Insets(0, 0, 5, 5);
		gbc_txtPORT.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPORT.gridx = 7;
		gbc_txtPORT.gridy = 2;
		frmClientPortal.getContentPane().add(txtPORT, gbc_txtPORT);
		txtPORT.setColumns(10);

		JButton btnCONNECT = new JButton("CONNECT");
		btnCONNECT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnCONNECT = new GridBagConstraints();
		gbc_btnCONNECT.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCONNECT.insets = new Insets(0, 0, 5, 5);
		gbc_btnCONNECT.gridx = 12;
		gbc_btnCONNECT.gridy = 2;
		frmClientPortal.getContentPane().add(btnCONNECT, gbc_btnCONNECT);
	}

	/**
	 * create txtHEIGHT JtextField();
	 */
	private void renderTextHEIGHT() {
		txtHEIGHT = new JTextField();
		txtHEIGHT.setToolTipText("enter the height");
		//txtHEIGHT.setText("enter Port number");
		GridBagConstraints gbc_txtHEIGHT = new GridBagConstraints();
		gbc_txtHEIGHT.gridwidth = 3;
		gbc_txtHEIGHT.insets = new Insets(0, 0, 5, 5);
		gbc_txtHEIGHT.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHEIGHT.gridx = 3;
		gbc_txtHEIGHT.gridy = 9;
		frmClientPortal.getContentPane().add(txtHEIGHT, gbc_txtHEIGHT);
		txtHEIGHT.setColumns(10);
	
	}

	/**
	 * create txtHEIGHT JtextField();
	 */
	private void renderTextWIDTH() {
		txtWIDTH = new JTextField();
		txtWIDTH.setToolTipText("enter the width");
		//txtHEIGHT.setText("enter Port number");
		GridBagConstraints gbc_txtWIDTH = new GridBagConstraints();
		gbc_txtWIDTH.gridwidth = 3;
		gbc_txtWIDTH.insets = new Insets(0, 0, 5, 5);
		gbc_txtWIDTH.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWIDTH.gridx = 3;
		gbc_txtWIDTH.gridy = 10;
		frmClientPortal.getContentPane().add(txtWIDTH, gbc_txtWIDTH);
		txtHEIGHT.setColumns(10);
	}

	/**
	 * Create btnCONNECT JButton
	 */
	private void renderBtnCONNECT() {
		JButton btnCONNECT = new JButton("CONNECT");
		actionBtnCONNECT(btnCONNECT);
		GridBagConstraints gbc_btnCONNECT = new GridBagConstraints();
		gbc_btnCONNECT.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCONNECT.insets = new Insets(0, 0, 5, 5);
		gbc_btnCONNECT.gridx = 12;
		gbc_btnCONNECT.gridy = 2;
		frmClientPortal.getContentPane().add(btnCONNECT, gbc_btnCONNECT);
	}

	/**
	 * create ColoursLabel JLabel()
	 */
	private void renderColoursLabel() {
		JLabel ColoursLabel = new JLabel("Color's");
		GridBagConstraints gbc_ColoursLabel = new GridBagConstraints();
		gbc_ColoursLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ColoursLabel.gridx = 1;
		gbc_ColoursLabel.gridy = 4;
		frmClientPortal.getContentPane().add(ColoursLabel, gbc_ColoursLabel);
	}

	/**
	 * create HeightLabel JLabel()
	 */
	private void renderHeightLabel() {
		JLabel HeightLabel = new JLabel("Note Height: ");
		GridBagConstraints gbc_HeightLabel = new GridBagConstraints();
		gbc_HeightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_HeightLabel.gridx = 1;
		gbc_HeightLabel.gridy = 9;
		frmClientPortal.getContentPane().add(HeightLabel, gbc_HeightLabel);
	}

	/**
	 * create HeightLabel JLabel()
	 */
	private void renderWidthLabel() {
		JLabel WidthLabel = new JLabel("Note Width: ");
		GridBagConstraints gbc_WidthLabel = new GridBagConstraints();
		gbc_WidthLabel.insets = new Insets(0, 0, 5, 5);
		gbc_WidthLabel.gridx = 1;
		gbc_WidthLabel.gridy = 10;
		frmClientPortal.getContentPane().add(WidthLabel, gbc_WidthLabel);
	}
	/**
	 * create spinColour JSpinner()
	 */
	private void renderSpinColour() {
		JSpinner spinColour = new JSpinner();
		spinColour.setModel(new SpinnerListModel(new String[] {"Default", "Red", "Yellow", "Blue"}));
		GridBagConstraints gbc_spinColour = new GridBagConstraints();
		gbc_spinColour.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinColour.gridwidth = 3;
		gbc_spinColour.insets = new Insets(0, 0, 5, 5);
		gbc_spinColour.gridx = 3;
		gbc_spinColour.gridy = 4;
		frmClientPortal.getContentPane().add(spinColour, gbc_spinColour);
	}

	/**
	 * Create btnGET JButton
	 */
	private JButton renderBtnGET() {
		JButton btnGET = new JButton("GET");
		actionBtnGET(btnGET);
		GridBagConstraints gbc_btnGET = new GridBagConstraints();
		gbc_btnGET.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGET.insets = new Insets(0, 0, 5, 5);
		gbc_btnGET.gridx = 12;
		gbc_btnGET.gridy = 4;
		frmClientPortal.getContentPane().add(btnGET, gbc_btnGET);

		return btnGET;
	}

	/**
	 * Create btnPOST JButton
	 */
	private JButton renderBtnPOST() {
		JButton btnPOST = new JButton("POST");
		actionBtnPOST(btnPOST);
		GridBagConstraints gbc_btnPOST = new GridBagConstraints();
		gbc_btnPOST.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPOST.insets = new Insets(0, 0, 5, 5);
		gbc_btnPOST.gridx = 12;
		gbc_btnPOST.gridy = 5;
		frmClientPortal.getContentPane().add(btnPOST, gbc_btnPOST);

		return btnPOST;
	}



	/**
	 * Action Listener function for BtnGET();
	 */
	private void addActionBtnGET(JButton btnGET) {
		btnGET.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

	/**
	 * create lvlRefersTo JLabel()
	 */
	private void renderLblRefersTo() {
		JLabel lblRefersTo = new JLabel("Refers To:");
		GridBagConstraints gbc_lblRefersTo = new GridBagConstraints();
		gbc_lblRefersTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblRefersTo.gridx = 1;
		gbc_lblRefersTo.gridy = 6;
		frmClientPortal.getContentPane().add(lblRefersTo, gbc_lblRefersTo);
	}

	/**
	 * create textRefersTo JTextFiel()
	 */
	private void renderTextRefersTo() {
		textRefersTo = new JTextField();
		textRefersTo.setToolTipText("If valued supplied, server will provide all notes including the inputted text");
		GridBagConstraints gbc_textRefersTo = new GridBagConstraints();
		gbc_textRefersTo.gridwidth = 3;
		gbc_textRefersTo.insets = new Insets(0, 0, 5, 5);
		gbc_textRefersTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRefersTo.gridx = 3;
		gbc_textRefersTo.gridy = 6;
		frmClientPortal.getContentPane().add(textRefersTo, gbc_textRefersTo);
		textRefersTo.setColumns(10);
	}

	/**
	 * create x_yCoordinatesLabel JLabel
	 */
	private void renderX_YCoordinatesLabel() {
		JLabel x_yCoordinatesLabel = new JLabel("Coordinates (x then y)");
		GridBagConstraints gbc_x_yCoordinatesLabel = new GridBagConstraints();
		gbc_x_yCoordinatesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_x_yCoordinatesLabel.gridx = 1;
		gbc_x_yCoordinatesLabel.gridy = 7;
		frmClientPortal.getContentPane().add(x_yCoordinatesLabel, gbc_x_yCoordinatesLabel);
	}

	/**
	 * create spinX Spinner
	 */
	private void renderSpinX() {
		JSpinner spinX = new JSpinner();
		spinX.setToolTipText("location of X coordinate on board");
		spinX.setModel(new SpinnerNumberModel(-1, -1, 500, 1));
		GridBagConstraints gbc_spinX = new GridBagConstraints();
		gbc_spinX.gridwidth = 3;
		gbc_spinX.insets = new Insets(0, 0, 5, 5);
		gbc_spinX.gridx = 2;
		gbc_spinX.gridy = 7;
		frmClientPortal.getContentPane().add(spinX, gbc_spinX);
	}

	/**
	 * create spinY JSpinner()
	 */
	private void renderSpinY() {
		JSpinner spinY = new JSpinner();
		spinY.setToolTipText("location of Y coordinate on board");
		spinY.setModel(new SpinnerNumberModel(-1, -1, 500, 1));
		GridBagConstraints gbc_spinY = new GridBagConstraints();
		gbc_spinY.gridwidth = 2;
		gbc_spinY.insets = new Insets(0, 0, 5, 5);
		gbc_spinY.gridx = 4;
		gbc_spinY.gridy = 7;
		frmClientPortal.getContentPane().add(spinY, gbc_spinY);
	}

	/**
	 * create btnPIN JButton()
	 */
	private void renderBtnPIN() {
		JButton btnPIN = new JButton("PIN");
		actionBtnPIN(btnPIN);
		btnPIN.setToolTipText("Requests server to pin all relevant notes (if a valid x and y coordinate is provided)");
		GridBagConstraints gbc_btnPIN = new GridBagConstraints();
		gbc_btnPIN.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPIN.insets = new Insets(0, 0, 5, 5);
		gbc_btnPIN.gridx = 12;
		gbc_btnPIN.gridy = 7;
		frmClientPortal.getContentPane().add(btnPIN, gbc_btnPIN);
	}

	/**
	 * create btnUNPIN JButton()
	 */
	private void renderBtnUNPIN() {
		JButton btnUNPIN = new JButton("UNPIN");
		btnUNPIN.setToolTipText("Requests server to unpin all relevant notes (if a valid x and y coordinate is provided)");
		actionBtnUNPIN(btnUNPIN);
		GridBagConstraints gbc_btnUNPIN = new GridBagConstraints();
		gbc_btnUNPIN.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUNPIN.insets = new Insets(0, 0, 5, 5);
		gbc_btnUNPIN.gridx = 12;
		gbc_btnUNPIN.gridy = 8;
		frmClientPortal.getContentPane().add(btnUNPIN, gbc_btnUNPIN);
	}

	/**
	 * create btnSHAKE JButton()
	 */
	private void renderBtnSHAKE() {
		JButton btnSHAKE = new JButton("SHAKE");
		btnSHAKE.setToolTipText("Requests server to forget all notes which are not pinned");
		actionBtnSHAKE(btnSHAKE);
		GridBagConstraints gbc_btnSHAKE = new GridBagConstraints();
		gbc_btnSHAKE.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSHAKE.insets = new Insets(0, 0, 5, 5);
		gbc_btnSHAKE.gridx = 12;
		gbc_btnSHAKE.gridy = 9;
		frmClientPortal.getContentPane().add(btnSHAKE, gbc_btnSHAKE);
	}

	/**
	 * create btnCLEAR JButton()
	 */
	private void renderBtnCLEAR() {
		JButton btnCLEAR = new JButton("CLEAR");
		btnCLEAR.setToolTipText("Request to the server to forget all notes and pins.");
		actionBtnCLEAR(btnCLEAR);
		GridBagConstraints gbc_btnCLEAR = new GridBagConstraints();
		gbc_btnCLEAR.fill = GridBagConstraints.BOTH;
		gbc_btnCLEAR.insets = new Insets(0, 0, 5, 5);
		gbc_btnCLEAR.gridx = 12;
		gbc_btnCLEAR.gridy = 10;
		frmClientPortal.getContentPane().add(btnCLEAR, gbc_btnCLEAR);
	}
	/**
	 * create btnDISCONNECT JButton()
	 */
	private void renderBtnDISCONNECT() {
		JButton btnDISCONNECT = new JButton("DISCONNECT");
		actionBtnDISCONNECT(btnDISCONNECT);
		GridBagConstraints gbc_btnDISCONNECT = new GridBagConstraints();
		gbc_btnDISCONNECT.insets = new Insets(0, 0, 5, 5);
		gbc_btnDISCONNECT.gridx = 12;
		gbc_btnDISCONNECT.gridy = 11;
		frmClientPortal.getContentPane().add(btnDISCONNECT, gbc_btnDISCONNECT);
	}

	/**
	 *
	 */
	private void renderTextDisplay() {
		JTextArea textDisplay = new JTextArea();
		GridBagConstraints gbc_textDisplay = new GridBagConstraints();
		gbc_textDisplay.gridwidth = 12;
		gbc_textDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_textDisplay.fill = GridBagConstraints.BOTH;
		gbc_textDisplay.gridx = 1;
		gbc_textDisplay.gridy = 12;
		frmClientPortal.getContentPane().add(textDisplay, gbc_textDisplay);
		textDisplay.setEditable(false);
	}

	/**
	 * adds Action listener to CONNECT buttion
	 */
	private void actionBtnCONNECT(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String IP = txtIPaddress.getText();
				try {
					int port = Integer.parseInt(txtPORT.getText());
					if (client == null) {
						// if client has not already been initialized, initializes it
						client = new BoardClient(IP, port, textDisplay, spinX, spinY, spinColour);
						client.CONNECT();
					}
				} catch (Exception ex) {
					textDisplay.setText("Invalid Request");
				}
			}
		});
	}

	/**
	 * adds Action listener to GET buttion
	 */
	private void actionBtnGET(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String refersTo = textRefersTo.getText();
				int x = (Integer) spinX.getValue();
				int y = (Integer) spinY.getValue();
				String colour = (String) spinColour.getValue();

				if (((x == -1) && (y ==-1)) && colour=="Default"){
					if(refersTo == null || refersTo.isEmpty() || refersTo.trim().isEmpty()){
						client.GET();
					}
					else{
						client.GET(colour, x, y, refersTo);
					}
				}
				else{
					client.GET(colour, x, y, refersTo);
				}
			}
		});
	}


	/**
	 * adds Action listener to POST buttion
	 */
	private void actionBtnPOST(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int x = (Integer) spinX.getValue();
					int y = (Integer) spinY.getValue();
					String width_str = txtWIDTH.getText();
					int width = Integer.parseInt(width_str);
					String height_str = txtHEIGHT.getText();
					int height = Integer.parseInt(height_str);
					String colour = (String) spinColour.getValue();
					String message = textRefersTo.getText();

					client.POST(x, y, width, height, colour, message);
					
				} catch (Exception ex) {
					textDisplay.setText("Invalid Request");
				}
			}
		});
	}

	/**
	 * adds Action listener to PIN buttion
	 */
	private void actionBtnPIN(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = (Integer) spinX.getValue();
				int y = (Integer) spinY.getValue();

				client.PIN(x, y);
			}
		});
	}

	/**
	 * adds Action listener to UNPIN buttion
	 */
	private void actionBtnUNPIN(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = (Integer) spinX.getValue();
				int y = (Integer) spinY.getValue();

				client.UNPIN(x, y);
			}
		});
	}

	/**
	 * adds Action listener to SHAKE buttion
	 */
	private void actionBtnSHAKE(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.SHAKE();
			}
		});
	}

	/**
	* adds Action listener to CLEAR buttion
	*/
	private void actionBtnCLEAR(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.CLEAR();
			}
		});
	}

	/**
	* adds Action listener to DISCONNECT buttion
	*/
	private void actionBtnDISCONNECT(JButton btnConnect) {
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.DISCONNECT();
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// render Encapsulating frame widget
		renderClientPortal();

		// render input boxes for IP address() and PORT
		renderTextIPAddress();
		renderTextPORT();
		renderBtnCONNECT();

		// render Label and Text boxes for Height and Width
		renderTextHEIGHT();
		renderTextWIDTH();
		renderHeightLabel();
		renderWidthLabel();


		// render Label and Spinner for colour selection
		renderColoursLabel();
		renderSpinColour();

		// Render GET button and Labels Spinners for items associated
		renderBtnGET();
		renderLblRefersTo();
		renderTextRefersTo();
		renderX_YCoordinatesLabel();
		renderSpinX();
		renderSpinY();


		//Render Buttons for Buttons for remaining methods
		renderBtnPIN();
		renderBtnUNPIN();
		renderBtnSHAKE();
		renderBtnCLEAR();
		renderBtnPOST();
		renderBtnDISCONNECT();

		//Renders the Text Display for information from Server
		renderTextDisplay();
	}

}
