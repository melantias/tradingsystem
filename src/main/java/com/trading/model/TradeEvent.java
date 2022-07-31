package com.trading.model;

import com.trading.constants.TradingtypeEnum;

public class TradeEvent {

	private Integer id;

	private String account;

	private String security;

	private TradingtypeEnum tradingType;

	private Long quantiy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public TradingtypeEnum getTradingType() {
		return tradingType;
	}

	public void setTradingType(TradingtypeEnum tradingType) {
		this.tradingType = tradingType;
	}

	public Long getQuantiy() {
		return quantiy;
	}

	public void setQuantiy(Long quantiy) {
		this.quantiy = quantiy;
	}

	@Override
	public String toString() {
		return "[id: " + id + ", " + tradingType.getDisplayName() + ", " + account + ", " + security + ", " + quantiy
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((security == null) ? 0 : security.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeEvent other = (TradeEvent) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (security == null) {
			if (other.security != null)
				return false;
		} else if (!security.equals(other.security))
			return false;
		return true;
	}

}
