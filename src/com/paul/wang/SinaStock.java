package com.paul.wang;

public class SinaStock {
	private String code;
	private String name;
	private double openQuotationPrice;
	private double marketClosePrice;
	private double currentPrice;

	public SinaStock(String[] sinaResult) {
		this.code = sinaResult[0].substring(13, 19);
		this.name = sinaResult[0].substring(21);
		this.openQuotationPrice = Double.valueOf(sinaResult[1]);
		this.marketClosePrice = Double.valueOf(sinaResult[2]);
		this.currentPrice = Double.valueOf(sinaResult[3]);
	}

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

	public double getOpenQuotationPrice() {
		return openQuotationPrice;
	}

	public void setOpenQuotationPrice(double openQuotationPrice) {
		this.openQuotationPrice = openQuotationPrice;
	}

	public double getMarketClosePrice() {
		return marketClosePrice;
	}

	public void setMarketClosePrice(double marketClosePrice) {
		this.marketClosePrice = marketClosePrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "code: " + code + ", name: " + name + ", openQuotationPrice: "
				+ openQuotationPrice + ", marketClosePrice: "
				+ marketClosePrice + ", currentPrice: " + currentPrice;
	}
}
