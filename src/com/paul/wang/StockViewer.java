package com.paul.wang;

import java.io.UnsupportedEncodingException;
import java.io.ObjectInputStream.GetField;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class StockViewer {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) {
		// Yahoo stock
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
//			YahooStock stock = YahooStockUtil.getStock("600000.ss", sdf.parse("2014-8-1"), sdf.parse("2014-8-31"));
//			YahooStock stock = YahooStockUtil.getStockAllHistory("600000.ss");
//			YahooStock stock = YahooStockUtil.getStockLastMonthHistory("600000.ss");
//			YahooStock stock = YahooStockUtil.getStockLastMonthsHistory("600000.ss", 4);
			List<YahooStock> stocks = YahooStockUtil.getSHStocksInfo();
			for (YahooStock stock : stocks) {
				System.out.println(stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
