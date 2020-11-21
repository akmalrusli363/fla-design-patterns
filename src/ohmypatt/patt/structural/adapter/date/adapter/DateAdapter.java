package ohmypatt.patt.structural.adapter.date.adapter;

import java.util.Calendar;
import java.util.Date;

public class DateAdapter {
	private Calendar calendar;

	public DateAdapter(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
	}
	
	public int getDay() {
		return get(Calendar.DATE);
	}
	
	public void setDay(int day) {
		set(Calendar.DATE, day);
	}
	
	public int getMonth() {
		return get(Calendar.MONTH);
	}
	
	public void setMonth(int month) {
		set(Calendar.MONTH, month);
	}
	
	public int getYear() {
		return get(Calendar.YEAR);
	}
	
	public void setYear(int year) {
		set(Calendar.YEAR, year);
	}

	
	public int getHour() {
		return get(Calendar.HOUR);
	}
	
	public void setHour(int month) {
		set(Calendar.HOUR, month);
	}
	
	public int getMinute() {
		return get(Calendar.MINUTE);
	}
	
	public void setMinute(int minute) {
		set(Calendar.MINUTE, minute);
	}
	
	public Date getDate() {
		return calendar.getTime();
	}
	
	private int get(int dateField) {
		return calendar.get(dateField);
	}
	
	private void set(int dateField, int value) {
		calendar.set(dateField, value);
	}
}
