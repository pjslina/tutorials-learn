package com.panjin.springcloudgateway.oauth.backend.domain;


/**
 * @author panjin
 */
public class Quote {
    
    private String symbol;
    private double price;
    
    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }
    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    
}
