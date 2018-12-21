package lemin.visual;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import lemin.logic.AntGraph;
import lemin.logic.Main;
import lemin.objects.Path;
import lemin.objects.PathSet;

public class SwingVisualizer {
	private JFrame mainFrame;
	private JTextField loginTextField, graphNameTextField;
	private JPasswordField passwordTextField;
	private JTextArea graphInputTextArea;
	private DefaultTableModel setsModel, pathsModel;

	public SwingVisualizer(){
		prepareMainFrame();
		prepareDBPanel();
		prepareProcessPanel();
		prepareGraphInfoPanel();
	}

	private void prepareMainFrame(){
		mainFrame = new JFrame("Lemin 2.0");
	   mainFrame.setSize(920,1010);
		mainFrame.setLayout(new FlowLayout());
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

	private void run() {
		AntGraph graph = Main.lemin(new ByteArrayInputStream((graphInputTextArea.getText() + "\n\n").getBytes(StandardCharsets.UTF_8)));

		if (graph == null) {
			return;
		}

		PathSet set = graph.getBestSet();
		int ants = graph.getFarm().getAnts().getAmount();

		emptyTableModel(setsModel);
		setsModel.addRow(new String[]{"undefined", Integer.toString(set.size()), Integer.toString(ants), Integer.toString(set.getSteps())});

		java.util.List<Path> paths = graph.getPaths();
		emptyTableModel(pathsModel);
		for (int i = 0; i < paths.size(); i++) {
			pathsModel.addRow(new String[]{"undefined", paths.get(i).StringNodes(), Integer.toString(paths.get(i).getLen())});
		}
	}

	private void prepareProcessPanel() {
		JPanel processPanel = new JPanel();
		TitledBorder processBorder = new TitledBorder("Graph processing");
		processPanel.setBorder(processBorder);
		processPanel.setLayout(new FlowLayout());
		mainFrame.add(processPanel);

		JLabel hintLabel = new JLabel("Enter graph name: ");
		processPanel.add(hintLabel);

		graphNameTextField = new JTextField(15);
		processPanel.add(graphNameTextField);

		JButton runButton = new JButton("Run");
		runButton.addActionListener(x -> {
			run();
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

		setsModel = new DefaultTableModel();
		setsModel.addColumn("index");
		setsModel.addColumn("size");
		setsModel.addColumn("object num");
		setsModel.addColumn("efficiency");
		JTable setsTable = new JTable(setsModel);
		JScrollPane setsPane = new JScrollPane(setsTable);
		setsAndPathsPanel.add(setsPane);

		pathsModel = new DefaultTableModel();
		pathsModel.addColumn("index");
		pathsModel.addColumn("nodes");
		pathsModel.addColumn("length");
		JTable pathsTable = new JTable(pathsModel);
		JScrollPane pathsPane = new JScrollPane(pathsTable);
		setsAndPathsPanel.add(pathsPane);

		return setsAndPathsPanel;
	}

	private void prepareGraphInfoPanel() {
		JPanel graphInfoPanel = new JPanel();
		TitledBorder infoBorder = new TitledBorder("Graph definition");
		graphInfoPanel.setBorder(infoBorder);
	   graphInfoPanel.setLayout(new GridLayout(1, 2));
		mainFrame.add(graphInfoPanel);

		graphInputTextArea = new JTextArea("#example of lemin notation:\n3\n##start\ns 0 0\n##end\ne 0 0\ns-e", 25, 25);
		JScrollPane scrollPane = new JScrollPane(graphInputTextArea);
		graphInfoPanel.add(scrollPane);

		graphInfoPanel.add(formSetPathPanel());

		mainFrame.setVisible(true);
	}

	private void emptyTableModel(DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i);
		}
	}

 }
