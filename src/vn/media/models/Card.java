package vn.media.models;

public class Card {
private String serial;
private long value;
private int status;

public Card() {
	// TODO Auto-generated constructor stub
}

public Card(String serial, long value, int status) {
	super();
	this.serial = serial;
	this.value = value;
	this.status = status;
}

public String getSerial() {
	return serial;
}

public long getValue() {
	return value;
}

public int getStatus() {
	return status;
}

}
