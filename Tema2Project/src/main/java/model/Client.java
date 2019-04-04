package model;

public class Client {
	
	private int id;
	private int arrivalTime;
	private int processingTime;
	private int finishTime;
	
	public Client(int id, int arrivalTime, int processingTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}
	
	public void setFinishTime(int waitingPeriod) {
		this.finishTime =  waitingPeriod + this.arrivalTime;
	}
	
	public String toString() {
		return "Client id: " + this.getId() + " avand timpul de finalizare: " + this.getFinishTime();
	}
	
}
