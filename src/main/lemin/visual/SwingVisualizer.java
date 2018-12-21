package lemin.visual;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class SwingVisualizer {
	private JFrame mainFrame;
	private JTextField loginTextField, graphNameTextField;
	private JPasswordField passwordTextField;
	private JTextArea graphInputTextArea;
	private JTable setsTable, pathsTable;

	public SwingVisualizer(){
		prepareMainFrame();
		prepareDBPanel();
		prepareProcessPanel();
		prepareGraphInfoPanel();
	}

	private void prepareMainFrame(){
		mainFrame = new JFrame("Lemin 2.0");
	   mainFrame.setSize(550,400);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
			  System.exit(0);
			}
		 });
		 mainFrame.setVisible(true);
	}

	private JPanel formLogPassPanel() {
		JPanel logPassPanel = new JPanel();
		logPassPanel.setLayout(new GridLayout(2, 2));

		JLabel logl = new JLabel("Login: ");
		logPassPanel.add(logl);

		loginTextField = new JTextField(15);
		logPassPanel.add(loginTextField);

		JLabel passl = new JLabel("Password: ");
		logPassPanel.add(passl);

		passwordTextField = new JPasswordField(15);
		logPassPanel.add(passwordTextField);

		return logPassPanel;
	}

	private void prepareDBPanel() {
		JPanel DBPanel = new JPanel();
		TitledBorder connectionBorder = new TitledBorder("Database connectivity");
		DBPanel.setBorder(connectionBorder);
	   DBPanel.setLayout(new GridLayout(2, 1));
		mainFrame.add(DBPanel);

		DBPanel.add(formLogPassPanel());

		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(x -> {
			 // TODO: connect
		});
		JPanel connectPanel = new JPanel();
		connectPanel.setLayout(new FlowLayout());
		connectPanel.add(connectButton);
		DBPanel.add(connectPanel);

		mainFrame.setVisible(true);
	}

	private void prepareProcessPanel() {
		JPanel processPanel = new JPanel();
		processPanel.setLayout(new FlowLayout());
		mainFrame.add(processPanel);

		JLabel hintLabel = new JLabel("Enter graph name: ");
		processPanel.add(hintLabel);

		graphNameTextField = new JTextField(15);
		processPanel.add(graphNameTextField);

		JButton runButton = new JButton("Run");
		runButton.addActionListener(x -> {
			 // TODO: run
		});
		processPanel.add(runButton);

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(x -> {
			 // TODO: load
		});
		processPanel.add(loadButton);

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(x -> {
			 // TODO: save
		});
		processPanel.add(saveButton);

		mainFrame.setVisible(true);
	}

	private JPanel formSetPathPanel() {
		JPanel setsAndPathsPanel = new JPanel();
		setsAndPathsPanel.setLayout(new GridLayout(2, 1));

		setsTable = new JTable(new String[][]{}, new String[]{"Index", "Object num", "Efficiency"});
		JScrollPane setsPane = new JScrollPane(setsTable);
		setsAndPathsPanel.add(setsPane);

		pathsTable = new JTable(new String[][]{}, new String[]{"Index", "Nodes", "Length"});
		JScrollPane pathsPane = new JScrollPane(pathsTable);
		setsAndPathsPanel.add(pathsPane);

		return setsAndPathsPanel;
	}

	private void prepareGraphInfoPanel() {
		JPanel graphInfoPanel = new JPanel();
		TitledBorder infoBorder = new TitledBorder("Graph definition");
		graphInfoPanel.setBorder(infoBorder);
	   graphInfoPanel.setLayout(new GridLayout(1, 2, 25, 15));
		mainFrame.add(graphInfoPanel);

		graphInputTextArea = new JTextArea("todo: what to show?", 5, 5);
		JScrollPane scrollPane = new JScrollPane(graphInputTextArea);
		graphInfoPanel.add(scrollPane);

		graphInfoPanel.add(formSetPathPanel());

		mainFrame.setVisible(true);
	}

	// public void showEventDemo(){
	//    JButton okButton = new JButton("OK");
	//    okButton.setActionCommand("OK");
	// 	okButton.addActionListener(e -> Main.lemin(new ByteArrayInputStream((descriptionTextArea.getText() + "\n\n").getBytes(StandardCharsets.UTF_8))));
	//    controlPanel.add(okButton);

	// 	descriptionTextArea = new JTextArea("XML or HTML String here", 5, 20);
	// 	JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
	// 	controlPanel.add(scrollPane);

	//    mainFrame.setVisible(true);
	// }

 }
