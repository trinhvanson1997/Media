package vn.media.server.common;

import java.sql.Timestamp;
import java.util.Date;

public class TimeCurrent {
	public Timestamp getCurrentTime() {
		Date date = new Date();
		Timestamp timest = new Timestamp(date.getTime());
		return timest;
		
		
	}
}
