package com.rest.retail.myRetail.vo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPriceVO {

	@JsonProperty(value = "value")
	private Double value;
	
	@JsonProperty(value = "currency_code")
	private String currencyCode;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "CurrentPriceVO [value=" + value + ", currencyCode=" + currencyCode + "]";
	}

}
