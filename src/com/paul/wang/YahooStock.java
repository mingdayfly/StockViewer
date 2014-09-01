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
		sb.append("股票代码： " + code);
		if (name != null) {
			sb.append(" 名称： "+ name);
		}
		sb.append(StockUtil.NEWLINE);
		for (YahooStockHistory history : stockHistory) {
			sb.append("日期：" + sdf.format(history.getDate()));
			sb.append(" 开盘价：" + history.getOpen());
			sb.append(" 最高价：" + history.getHigh());
			sb.append(" 最低价：" + history.getLow());
			sb.append(" 收盘价：" + history.getClose());
			sb.append(" 成交量：" + history.getVolume());
			sb.append(" 已调整收盘价：" + history.getAdjClose());
			sb.append(StockUtil.NEWLINE);
		}
		return sb.toString();
	}
}
