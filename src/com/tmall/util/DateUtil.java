package com.tmall.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateUtil{
	public static Timestamp d2t(Date time) {
		if (time == null)
			return null;
		return new Timestamp(time.getTime());
	}
	
	public static Date t2d(Timestamp time) {
		if (time == null)
			return null;
		return new Date(time.getTime());
	}
}