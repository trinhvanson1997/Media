package vn.media.models;

import java.sql.Timestamp;

public class Paid {
	private String ID;
	private String feeName;
	private int paid;
	private long feeValue;
	private Timestamp requestTime;
	private Timestamp paidTime;
	public static int idNumber;
	
	
	
	public Paid(String iD, String feeName, int status, long feeValue, Timestamp requestTime, Timestamp paidTime) {
		super();
		ID = iD;
		this.feeName = feeName;
		this.paid = status;
		this.feeValue = feeValue;
		this.requestTime = requestTime;
		this.paidTime = paidTime;
	}

	public long getFeeValue() {
		return feeValue;
	}

	public void setFeeValue(long feeValue) {
		this.feeValue = feeValue;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public int getStatus() {
		return paid;
	}
	public void setStatus(int status) {
		this.paid = status;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	public Timestamp getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(Timestamp paidTime) {
		this.paidTime = paidTime;
	}

	
}
