package com.paul.wang;

import java.util.ArrayList;
import java.util.List;

public class SinaStockUtil {
	private static String sinaQueryPrefix = "http://hq.sinajs.cn/list=";

	public static List<SinaStock> getStockInfo(String... stockCodes) {
		List<SinaStock> stockList = new ArrayList<SinaStock>();
		String queryResult = getSinaStockResult(buildSinaQuery(stockCodes));
		String[] stocksResult = queryResult.split(";");
		for (String stockResult : stocksResult) {
			String[] datas = stockResult.split(",");
			if (datas.length > 1) {
				SinaStock stock = new SinaStock(datas);
				stockList.add(stock);
			}
		}
		return stockList;
	}
	
	public static List<SinaStock> getSHStocksInfo() {
		List<SinaStock> stockList = new ArrayList<SinaStock>();
		stockList.addAll(getStocksInfoForRange("sh", 600000, 600499));
		stockList.addAll(getStocksInfoForRange("sh", 600500, 600999));
		stockList.addAll(getStocksInfoForRange("sh", 601000, 601499));
		stockList.addAll(getStocksInfoForRange("sh", 601500, 601999));
//		stockList.addAll(getStocksInfoForRange("sh", 602000, 602499));
//		stockList.addAll(getStocksInfoForRange("sh", 602500, 602999));
		stockList.addAll(getStocksInfoForRange("sh", 603000, 603499));
		stockList.addAll(getStocksInfoForRange("sh", 603500, 603999));
		return stockList;
	}
	
	public static List<SinaStock> getStocksInfoForRange(String locationCode, int startIndex, int endIndex) {
		List<String> codeList = new ArrayList<String>();
		for (int i = startIndex; i < endIndex+1; i++) {
			codeList.add(String.valueOf(locationCode+i));
		}
		String[] stockCodes = codeList.toArray(new String[0]);
		return getStockInfo(stockCodes);
	}

	private static String buildSinaQuery(String[] stockCodes) {
		StringBuilder sb = new StringBuilder();
		sb.append(sinaQueryPrefix);
		for (int i = 0; i < stockCodes.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(stockCodes[i]);
		}
		return sb.toString();
	}

	private static String getSinaStockResult(String url) {
		String str = HttpRequest.sendGet(url, null);
		return str;
	}

}
