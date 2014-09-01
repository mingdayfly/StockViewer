package com.paul.wang;

import java.text.SimpleDateFormat;
import java.util.List;

public class YahooStock {
	private String code;
	private String name;
	private List<YahooStockHistory> stockHistory;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<YahooStockHistory> getStockHistory() {
		return stockHistory;
	}
	public void setStockHistory(List<YahooStockHistory> stockHistory) {
		this.stockHistory = stockHistory;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append("��Ʊ���룺 " + code);
		if (name != null) {
			sb.append(" ���ƣ� "+ name);
		}
		sb.append(StockUtil.NEWLINE);
		for (YahooStockHistory history : stockHistory) {
			sb.append("���ڣ�" + sdf.format(history.getDate()));
			sb.append(" ���̼ۣ�" + history.getOpen());
			sb.append(" ��߼ۣ�" + history.getHigh());
			sb.append(" ��ͼۣ�" + history.getLow());
			sb.append(" ���̼ۣ�" + history.getClose());
			sb.append(" �ɽ�����" + history.getVolume());
			sb.append(" �ѵ������̼ۣ�" + history.getAdjClose());
			sb.append(StockUtil.NEWLINE);
		}
		return sb.toString();
	}
}
