package simulare;

import java.util.ArrayList;
import java.util.List;

import model.Casa;
import model.Client;

public class Scheduler {
	
	private List<Casa> caseDisponibile = new ArrayList<Casa>();;
	private int numarCase;
	private int numarClienti;

	public Scheduler(int numarCase, int nrClienti) {
		this.numarClienti = nrClienti;
		this.numarCase = numarCase;
		
		for(int i = 0; i < numarCase; i++) {
			Casa casa = new Casa(nrClienti, i + 1);
			caseDisponibile.add(i, casa);
			Thread t = new Thread(caseDisponibile.get(i));
			t.start();
		}
		
	}
	
	public List<Casa> getCaseDisponibile() {
		return caseDisponibile;
	}

	public void setCaseDisponibile(List<Casa> caseDisponibile) {
		this.caseDisponibile = caseDisponibile;
	}

	public int getNumarCase() {
		return numarCase;
	}

	public void setNumarCase(int numarCase) {
		this.numarCase = numarCase;
	}

	public void findBestOption(Client newClient) {
		
		int value = Integer.MAX_VALUE;
		int position = 0;
		
		for(Casa iterateCase : caseDisponibile) {
			if( iterateCase.getWaitingPeriod().intValue() < value && iterateCase.getClienti().size() < numarClienti)
				value = iterateCase.getWaitingPeriod().intValue();
		}
		
		
		for(Casa iterate : caseDisponibile) {
			if(iterate.getWaitingPeriod().intValue() == value) {
					 break;
			}
			 position++;
		}
		
		caseDisponibile.get(position).addClient(newClient);
		int numarCasa = position + 1;
		SimulationManager.setTextFieldClienti(SimulationManager.getTextFieldClienti(numarCasa - 1) + "C", numarCasa - 1);
		SimulationManager.setTextAreaString("Clientul cu id: " + newClient.getId() + " a ajuns la timpul: " + newClient.getArrivalTime() + " cu timpul de procesare: " + newClient.getProcessingTime() + " si s-a asezat la casa " + numarCasa);
	}
	
	public double getWaitingPeriod() {
		int averageWaitingTime = 0;
		for(Casa casa : caseDisponibile) {
			averageWaitingTime += casa.getCasaFunctionala();
		}
		return averageWaitingTime/(double)numarClienti;
	}
	
	public double getAverageServiceTime() {
		int averageProcessingTime = 0;
		for(Casa casa : caseDisponibile) {
			averageProcessingTime += casa.getTimpProcesare();
		}
		return averageProcessingTime/(double)numarClienti;
	}
	
	public int getPeakHour() {
		int size = 0;
		for(Casa casa : caseDisponibile) {
			size += casa.getNumarCurentDeClientiInCoada();
		}
		return size;
	}
	
	public double getCasaLibera() {
		int casaLibera = 0;
		for(Casa casa : caseDisponibile) {
			casaLibera += casa.getCasaLibera();
		}
		return casaLibera/(float)numarCase;
	}
	
}
