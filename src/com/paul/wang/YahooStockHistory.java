package com.paul.wang;

import java.util.Date;

public class YahooStockHistory {
	private double adjClose;
	private double close;
	private Date date;
	private double high;
	private double low;
	private double open;
	private int volume;
	public YahooStockHistory(Date date, double open, double high, double low,
			double close, int volume, double adjClose) {
		super();
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
	}
	public double getAdjClose() {
		return adjClose;
	}
	public double getClose() {
		return close;
	}
	public Date getDate() {
		return date;
	}
	public double getHigh() {
		return high;
	}
	public double getLow() {
		return low;
	}
	public double getOpen() {
		return open;
	}
	public int getVolume() {
		return volume;
	}
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
}
