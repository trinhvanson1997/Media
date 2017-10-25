package vn.media.common;

import java.util.Date;
import java.sql.Timestamp;

public class TimeCurrent {
	public Timestamp getCurrentTime() {
		Date date = new Date();
		Timestamp timest = new Timestamp(date.getTime());
		return timest;
		
		
	}
}
