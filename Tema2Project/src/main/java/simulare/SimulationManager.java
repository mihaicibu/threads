package simulare;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import gui.View;
import model.Client;

class Sortare implements Comparator<Client>{

	public int compare(Client c1, Client c2) {
		return c1.getArrivalTime() - c2.getArrivalTime();
	}
}

public class SimulationManager implements Runnable{

	private int timeLimit;
	private int maxProcessingTime;
	private int minProcessingTime;
	private int maxArrivingTime;
	private int minArrivingTime;
	private int nrCase;
	private int nrClienti;
	private int peakHour;
	private static boolean startThread = false;
	private static boolean submitButton = false;
	private Scheduler scheduler;
	private static View view;
	private List<Client> listaClienti=new ArrayList<Client>();
	
	public SimulationManager(View gui) {
		view = gui;
		view.addSubmitListener(new SubmitButton());
		view.addStartListener(new StartButton());
		while(!submitButton) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		scheduler = new Scheduler(nrCase, nrClienti);
		peakHour = 0;
	}
	
	public void generateClients() {
		int arrivalTime, processingTime;
		
		for(int i = 0; i < nrClienti ; i++) {
			
			arrivalTime = (int) (Math.random()*((maxArrivingTime - minArrivingTime) + 1) + minArrivingTime);
			processingTime = (int) (Math.random()*((maxProcessingTime - minProcessingTime) + 1) + minProcessingTime);
			
			Client client = new Client(i, arrivalTime, processingTime);
			listaClienti.add(client);
		}
		
		Collections.sort(listaClienti, new Sortare());
	}
	
	public static void setTextAreaString(String text) {
		view.setTextArea(view.getTextArea() + '\n' + text);
	}
	
	public static void setTextFieldClienti(String text, int i) {
		view.setJTextField(text, i);
	}
	
	public static void setTextLabelClienti(String text, int i) {
		view.setJLabel(view.getJTextLabel(i).substring(0, 5) + text , i);
	}
	
	public static String getTextFieldClienti(int i) {
		return view.getJTextField(i);
	}
	
	public void run() {
		int lastTime = 0;
		int currentTime = 0;
		while(!startThread) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				}
		}
			generateClients();
			while(currentTime < timeLimit) {
				for(Client client: listaClienti) {
					if(client.getArrivalTime() == currentTime)
						scheduler.findBestOption(client);
				}
				int time = scheduler.getPeakHour();
				if(time > lastTime) {
					lastTime = time;
					peakHour = currentTime;
				}
				currentTime++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				
			}
			
			view.setUserOutputAvgWT(String.format("%.2f", scheduler.getWaitingPeriod()) + " seconds");
			view.setUserOutputAvgServiceT(String.format("%.2f", scheduler.getAverageServiceTime()) + " seconds");
			view.setUserOutputPeakHour( peakHour + " ");
			view.setUserOutputEmpty(String.format("%.2f", scheduler.getCasaLibera())+ " seconds");
	}

	class SubmitButton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String inputMinArrivingTime = "";
			String inputMaxArrivingTime = "";
			String inputMinProcessingTime = "";
			String inputMaxProcessingTime = "";
			String inputNumberofQueues = "";
			String inputNumberofClients = "";
			String inputTimeLimit = "";
			
			try {
				inputMinArrivingTime = view.getMinArrivingTime();
				inputMaxArrivingTime = view.getMaxArrivingTime();
				inputMinProcessingTime = view.getMinProcessingTime();
				inputMaxProcessingTime = view.getMaxProcessingTime();
				inputNumberofQueues = view.getNumberOfQueues();
				inputNumberofClients = view.getNumberOfClients();
				inputTimeLimit = view.getTimeLimit();
				
			} catch(NumberFormatException nfex) {
			}
			if(inputMinArrivingTime.equals("") || inputMaxArrivingTime.equals("") || inputMinProcessingTime.equals("") || inputMaxProcessingTime.equals("") || inputNumberofQueues.equals("") || inputTimeLimit.equals("") || inputNumberofQueues.equals("")) {
				view.setTextArea("Introduceti date in toate campurile");
			}
			else {
				try {
				timeLimit = Integer.parseInt(inputTimeLimit);
				maxProcessingTime = Integer.parseInt(inputMaxProcessingTime);
				minProcessingTime = Integer.parseInt(inputMinProcessingTime);
				maxArrivingTime = Integer.parseInt(inputMaxArrivingTime);
				minArrivingTime = Integer.parseInt(inputMinArrivingTime);
				nrCase = Integer.parseInt(inputNumberofQueues);
				nrClienti = Integer.parseInt(inputNumberofClients);
				} catch(NumberFormatException nfex) {
					view.setTextArea("Introduceti valori intregi in campurile corespunzatoare");
				}
				if(nrCase <= 0) {
					view.setTextArea(view.getTextArea() + "\n"+"Numarul caselor trebuie sa fie mai mare decat 0");
				}
				else if(timeLimit < maxArrivingTime) {
					view.setTextArea("Timpul de simulare trebuie sa ie mai mare sau egal cu timpul maxim de sosire");
				}
				else {
					submitButton = true;
					view.setJText(nrCase);
					view.updateView();
				}
			}	
		}
	}
	class StartButton implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			startThread = true;
		}	
	}
	
	public static void main(String[] args) {
		View view = new View();
    	view.setVisible(true);
		SimulationManager manager = new SimulationManager(view);
		Thread t = new Thread(manager);
		t.start();
	}
}
