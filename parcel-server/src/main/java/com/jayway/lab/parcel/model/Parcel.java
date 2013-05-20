package com.jayway.lab.parcel.model;


public class Parcel {
	
	public enum Status {SUBMITTED, SENT, DELIVERED};
	
	private final String id;
	private String sender;
	private String receiver;
	private int weight;
	private Status status;

	public Parcel(String id, String sender, String receiver, int weight) {
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.weight = weight;
		status = Status.SUBMITTED;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

}
