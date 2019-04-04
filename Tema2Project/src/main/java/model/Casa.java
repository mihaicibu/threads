package model;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import simulare.SimulationManager;


public class Casa implements Runnable {

	private BlockingQueue <Client> clienti;
	private AtomicInteger waitingPeriod;
	private int idCasa;
	private int casaFunctionala;
	private int timpProcesare;
	private int numarCurentDeClientiInCoada;
	private int casaLibera;
	private Client removeClient = null;

	
	public Casa(int maxNrClienti, int idCasa) {
		
		this.waitingPeriod = new AtomicInteger(0);
		this.idCasa = idCasa;
		this.casaFunctionala = 0;
		this.timpProcesare = 0; 
		this.numarCurentDeClientiInCoada = 0;
		this.casaLibera = 0;
		clienti = new ArrayBlockingQueue<Client>(maxNrClienti);
	}

	public int getNumarCurentDeClientiInCoada() {
		return numarCurentDeClientiInCoada;
	}

	public int getCasaFunctionala() {
		return casaFunctionala;
	}

	public void setCasaFunctionala(int casaFunctionala) {
		this.casaFunctionala = casaFunctionala;
	}
	
	public int getTimpProcesare() {
		return this.timpProcesare;
	}

	public int getCasaLibera() {
		return this.casaLibera;
	}
	
	public BlockingQueue<Client> getClienti() {
		return clienti;
	}

	public void setClienti(BlockingQueue<Client> clienti) {
		this.clienti = clienti;
	}
	
	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public int getIdCasa() {
		return idCasa;
	}

	public void setIdCasa(int idCasa) {
		this.idCasa = idCasa;
	}

	public void run() {
		
		while(true) {
		
			if(waitingPeriod.intValue() == 0) {
				try {
					casaLibera++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			
			else {	
				Client client = clienti.element();
				if(client != null)
				try {
	
					Thread.sleep(1000 * client.getProcessingTime());
					waitingPeriod = new AtomicInteger(waitingPeriod.intValue() - client.getProcessingTime());
				} catch (InterruptedException e) {
					
				}
				removeClient = clienti.poll();
		
				if(removeClient != null) {
					SimulationManager.setTextLabelClienti(" cu timpul de asteptare: " + waitingPeriod.intValue(), this.idCasa - 1);
					SimulationManager.setTextAreaString(removeClient.toString() +  " a parasit casa: " + this.idCasa);
					SimulationManager.setTextFieldClienti(SimulationManager.getTextFieldClienti(this.idCasa - 1).substring(0, SimulationManager.getTextFieldClienti(this.idCasa - 1).length() - 1), this.idCasa - 1 );
				}
			}
		}
		
	}
	
	public void addClient(Client newClient) {
		
		clienti.offer(newClient);
		numarCurentDeClientiInCoada = clienti.size();
		casaFunctionala += waitingPeriod.intValue();
		timpProcesare += newClient.getProcessingTime();
		waitingPeriod = new AtomicInteger(newClient.getProcessingTime() + waitingPeriod.intValue());
		SimulationManager.setTextLabelClienti(" cu timpul de asteptare: " + waitingPeriod.intValue(), this.idCasa - 1);
		newClient.setFinishTime(waitingPeriod.intValue());
	}
	
	public Client[] getClient() {
		return  (Client[]) clienti.toArray();
	}
	
}
