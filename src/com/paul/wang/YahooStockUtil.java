package com.paul.wang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class YahooStockUtil {
	private static String yahooStockQuery = "http://ichart.finance.yahoo.com/table.csv";
	
	public static List<YahooStock> getSHStocksInfo() {
		List<YahooStock> stockList = new ArrayList<YahooStock>();
		stockList.addAll(getStocksByRange("ss", 600000, 600318, 1));
		stockList.addAll(getStocksByRange("ss", 600319, 600499, 1));
//		stockList.addAll(getStocksByRange("ss", 600500, 600999, 1));
//		stockList.addAll(getStocksByRange("ss", 601000, 601499, 1));
//		stockList.addAll(getStocksByRange("ss", 601500, 601999, 1));
//		stockList.addAll(getStocksByRange("ss", 603000, 603499, 1));
//		stockList.addAll(getStocksByRange("ss", 603500, 603999, 1));
		return stockList;
	}
	
	public static List<YahooStock> getSHStocksInfo(int months) {
		List<YahooStock> stockList = new ArrayList<YahooStock>();
		stockList.addAll(getStocksByRange("ss", 600000, 600499, months));
		stockList.addAll(getStocksByRange("ss", 600500, 600999, months));
		stockList.addAll(getStocksByRange("ss", 601000, 601499, months));
		stockList.addAll(getStocksByRange("ss", 601500, 601999, months));
		stockList.addAll(getStocksByRange("ss", 603000, 603499, months));
		stockList.addAll(getStocksByRange("ss", 603500, 603999, months));
		return stockList;
	}
	
	public static YahooStock getStock(String stockCode, Date startDate, Date endDate ) {
		Calendar sdate = Calendar.getInstance();
		Calendar edate = Calendar.getInstance();
		sdate.setTime(startDate);
		edate.setTime(endDate);
		StringBuilder sb = new StringBuilder();
		sb.append("s=");
		sb.append(stockCode);
		sb.append("&d=");
		sb.append(edate.get(Calendar.MONTH));
		sb.append("&e=");
		sb.append(edate.get(Calendar.DATE));
		sb.append("&f=");
		sb.append(edate.get(Calendar.YEAR));
		sb.append("&a=");
		sb.append(sdate.get(Calendar.MONTH));
		sb.append("&b=");
		sb.append(sdate.get(Calendar.DATE));
		sb.append("&c=");
		sb.append(sdate.get(Calendar.YEAR));
		String param = sb.toString();
		return getStock(param);
	}

	public static YahooStock getStockAllHistory(String stockCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("s=");
		sb.append(stockCode);
		String param = sb.toString();
		return getStock(param);
	}
	
	public static YahooStock getStockLastMonthHistory(String stockCode) {
		return getStockLastMonthsHistory(stockCode, 1);
	}
	
	public static YahooStock getStockLastMonthsHistory(String stockCode, int months) {
		Calendar sdate = Calendar.getInstance();
		Calendar edate = Calendar.getInstance();
		sdate.add(Calendar.MONTH, -months);
		StringBuilder sb = new StringBuilder();
		sb.append("s=");
		sb.append(stockCode);
		sb.append("&d=");
		sb.append(edate.get(Calendar.MONTH));
		sb.append("&e=");
		sb.append(edate.get(Calendar.DATE));
		sb.append("&f=");
		sb.append(edate.get(Calendar.YEAR));
		sb.append("&a=");
		sb.append(sdate.get(Calendar.MONTH));
		sb.append("&b=");
		sb.append(sdate.get(Calendar.DATE));
		sb.append("&c=");
		sb.append(sdate.get(Calendar.YEAR));
		String param = sb.toString();
		return getStock(param);
	}
	
	public static List<YahooStock> getStocksByRange(String locationCode, int startIndex, int endIndex, int months) {
		List<YahooStock> stocks = new ArrayList<YahooStock>();
		for (int i = startIndex; i < endIndex+1; i++) {
			YahooStock stk = getStockLastMonthsHistory(i+"."+locationCode, months);
			stocks.add(stk);
		}
		return stocks;
	}

	private static InputStream getHistoryResult(String param) {
		try {
			InputStream queryResult = HttpRequest.getRequestResult(yahooStockQuery,
					param);
			return queryResult;
		} catch (FileNotFoundException fnfe) {
		}
		return null;
	}
	
	private static YahooStock getStock(String param) {
		YahooStock stock = new YahooStock();
		String stockCode = param.substring(2, param.indexOf("."));
		stock.setCode(stockCode);
		InputStream queryResult = getHistoryResult(param);
		if (queryResult != null) {
			stock.setStockHistory(resolveHistoryResult(queryResult));
		}
		return stock;
	}

	private static List<YahooStockHistory> resolveHistoryResult (InputStream queryResult) {
		List<YahooStockHistory> stockHistoryList = new ArrayList<YahooStockHistory>();
		BufferedReader in = null;
		String line;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		in = new BufferedReader(new InputStreamReader(queryResult));
		try {
			int index = 0;
			while ((line = in.readLine()) != null) {
				if (index == 0) {
					index++;
					continue;
				}
				String datas[] = line.split(",");
				Date date = sdf.parse(datas[0]);
				double open = Double.valueOf(datas[1]).doubleValue();
				double high = Double.valueOf(datas[2]).doubleValue();
				double low = Double.valueOf(datas[3]).doubleValue();
				double close = Double.valueOf(datas[4]).doubleValue();
				int volume = Integer.parseInt(datas[5]);
				double adjClose = Double.valueOf(datas[6]).doubleValue();
				YahooStockHistory history = new YahooStockHistory(date, open, high, low, close, volume, adjClose);
				stockHistoryList.add(history);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockHistoryList;
	}

}
