package gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class View extends JFrame{
	
	private JLabel labelMinArrivingTime = new JLabel("Minimum arriving time:");
	private JLabel labelMaxArrivingTime = new JLabel("Maixmum arriving time:");
	private JLabel labelMinProcessingTime = new JLabel("Minimum processing time:");
	private JLabel labelMaxProcessingTime = new JLabel("Maximum processing time:");
	private JLabel labelNumberOfQueues = new JLabel("Number of queues:");
	private JLabel labelTimeLimit = new JLabel("Time Limit:");
	private JLabel labelResults = new JLabel("Results");
	private JLabel labelAvgWT = new JLabel("Average wating time:");
	private JLabel labelNumberOfClients = new JLabel("Number of Clients:");
	private JLabel labelServingTime = new JLabel("Average serving time:");
	private JLabel labelPeakHour = new JLabel("Peak Hour:");
	private JLabel labelEmptyQueue = new JLabel("Average Empty Queues:");
	private JTextField userInputMinArrivingTime = new JTextField(10);
	private JTextField userInputNumberOfClients = new JTextField(10);
	private JTextField userInputMaxArrivingTime = new JTextField(10);
	private JTextField userInputMinProcessingTime = new JTextField(10);
	private JTextField userInputMaxProcessingTime = new JTextField(10);
	private JTextField userInputNumberOfQueues = new JTextField(10);
	private JTextField userInputTimeLimit = new JTextField(10);
	private JTextField userOutputAvgWT = new JTextField(10);
	private JTextField userOutputAvgServiceT = new JTextField(10);
	private JTextField userOutputPeakHour = new JTextField(10);
	private JTextField userOutputEmptyQueues = new JTextField(10);
	private JButton butonSubmit = new JButton("Submit Data");
	private JButton butonStartSimulare = new JButton("Start Simulare");
	private JTextArea logTextArea = new JTextArea(50, 43);
	private JScrollPane scroll; 
	List<JLabel> labelC = new ArrayList<JLabel>();
	List<JTextField> fieldC = new ArrayList<JTextField>();
	List<JPanel> panelC = new ArrayList<JPanel>();
	JPanel contentCozi = new JPanel();
	
	public View(){
		this.setSize(1600, 450);
		this.setLocation(300, 300); 
		
		JPanel mainContent = new JPanel();
		JPanel content0 = new JPanel();
		JPanel content1 = new JPanel();
		JPanel content2 = new JPanel();
		JPanel content3 = new JPanel();
		JPanel contentArrivalTime = new JPanel();
		JPanel contentProcessingTime = new JPanel();
		JPanel contentNumberOfQueues = new JPanel();
		JPanel contentNumberOfClients = new JPanel();
		JPanel contentTimeLimit = new JPanel();
		JPanel contentSubmit = new JPanel();
		JPanel contentStart = new JPanel();
		JPanel contentResults = new JPanel();
		JPanel contentAvgWTime = new JPanel();
		JPanel contentServingTime = new JPanel();
		JPanel contentPeakHour = new JPanel();
		JPanel contentEmpty = new JPanel();
		
		logTextArea.setEditable(false);
		userOutputAvgWT.setEditable(false);
		userOutputAvgServiceT.setEditable(false);
		userOutputPeakHour.setEditable(false);
		userOutputEmptyQueues.setEditable(false);
		
		mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
		contentCozi.setLayout(new BoxLayout(contentCozi, BoxLayout.Y_AXIS));
		content0.setLayout(new BoxLayout(content0,BoxLayout.Y_AXIS));
		content1.setLayout(new BoxLayout(content1,BoxLayout.Y_AXIS));
		content2.setLayout(new BoxLayout(content2,BoxLayout.Y_AXIS));
		content3.setLayout(new BoxLayout(content3,BoxLayout.Y_AXIS));
		
		contentArrivalTime.add(labelMinArrivingTime);
		contentArrivalTime.add(userInputMinArrivingTime);
		contentArrivalTime.add(labelMaxArrivingTime);
		contentArrivalTime.add(userInputMaxArrivingTime);
		
		contentProcessingTime.add(labelMinProcessingTime);
		contentProcessingTime.add(userInputMinProcessingTime);
		contentProcessingTime.add(labelMaxProcessingTime);
		contentProcessingTime.add(userInputMaxProcessingTime);
		
		contentNumberOfQueues.add(labelNumberOfQueues);
		contentNumberOfQueues.add(userInputNumberOfQueues);
		
		contentNumberOfClients.add(labelNumberOfClients);
		contentNumberOfClients.add(userInputNumberOfClients);
		
		contentTimeLimit.add(labelTimeLimit);
		contentTimeLimit.add(userInputTimeLimit);
		
		contentSubmit.add(butonSubmit);
		contentStart.add(butonStartSimulare);
		
		contentResults.add(labelResults);
		
		contentAvgWTime.add(labelAvgWT);
		contentAvgWTime.add(userOutputAvgWT);
		
		contentServingTime.add(labelServingTime);
		contentServingTime.add(userOutputAvgServiceT);
		
		contentPeakHour.add(labelPeakHour);
		contentPeakHour.add(userOutputPeakHour);
		
		contentEmpty.add(labelEmptyQueue);
		contentEmpty.add(userOutputEmptyQueues );
		
		content0.add(contentArrivalTime);
		content0.add(contentProcessingTime);
		content0.add(contentNumberOfQueues);
		content0.add(contentNumberOfClients);
		content0.add(contentTimeLimit);
		content0.add(contentSubmit);
		content0.add(contentStart);
		
		content3.add(contentResults);
		content3.add(contentAvgWTime);
		content3.add(contentServingTime);
		content3.add(contentPeakHour);
		content3.add(contentEmpty);
		
		scroll = new JScrollPane(logTextArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		content1.add(new JLabel("Log-uri"));
		content1.add(scroll);
		
		content2.add(contentCozi);
		
		
		mainContent.add(content0);
		mainContent.add(content1);
		mainContent.add(content2);
		mainContent.add(content3);
		
		this.setContentPane(mainContent);
		this.setTitle("Magazin");
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void updateView() {
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void setTextArea(String text) {
		logTextArea.setText(text);
	}
	
	public String getTextArea() {
		return logTextArea.getText();
	}
	
	public String getMinArrivingTime() {
		return userInputMinArrivingTime.getText();
	}
	
	public String getMaxArrivingTime() {
		return userInputMaxArrivingTime.getText();
	}
	
	public String getMinProcessingTime() {
		return userInputMinProcessingTime.getText();
	}
	
	public String getMaxProcessingTime() {
		return userInputMaxProcessingTime.getText();
	}
	
	public String getNumberOfQueues() {
		return userInputNumberOfQueues.getText();
	}
	
	public String getNumberOfClients() {
		return userInputNumberOfClients.getText();
	}
	
	public String getTimeLimit() {
		return userInputTimeLimit.getText();
	}
	
	public void addSubmitListener(ActionListener submit) {
		butonSubmit.addActionListener(submit);
	}
	
	public void addStartListener(ActionListener start) {
		butonStartSimulare.addActionListener(start);
	}
	
	public void setUserOutputAvgWT(String text) {
		this.userOutputAvgWT.setText(text);
	}

	public void setUserOutputAvgServiceT(String text) {
		this.userOutputAvgServiceT.setText(text);
	}

	public void setUserOutputPeakHour(String text) {
		this.userOutputPeakHour.setText(text);
	}
	
	public void setUserOutputEmpty(String text) {
		this.userOutputEmptyQueues.setText(text);
	}

	public void setJText(int nrCase) {
		int nrCasa = 1;
		for(int i = 0; i < nrCase; i++) {
			nrCasa = i + 1;
			String casa = "Casa" + nrCasa + ":";
			labelC.add(i,new JLabel(casa));
			fieldC.add(i, new JTextField(10));
			fieldC.get(i).setEditable(false);
			panelC.add(new JPanel());
		}
		
		for(int i = 0; i < nrCase; i++) {
			panelC.get(i).setLayout(new BoxLayout(panelC.get(i), BoxLayout.Y_AXIS));
			panelC.get(i).add(labelC.get(i));
			panelC.get(i).add(fieldC.get(i));
			contentCozi.add(panelC.get(i));
		}
	}
	
	public void setJTextField(String string, int i) {
		fieldC.get(i).setText(string);
	}
	
	public void setJLabel(String string, int i) {
		labelC.get(i).setText(string);
	}
	
	public String getJTextField(int i) {
		return fieldC.get(i).getText();
	}
	
	public String getJTextLabel(int i) {
		return labelC.get(i).getText();
	}
	
}
