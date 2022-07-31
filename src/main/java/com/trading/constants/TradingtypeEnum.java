package com.trading.constants;

public enum TradingtypeEnum {
	
	BUY("buy", "BUY"),
	SELL("sell", "SELL"),
	CANCEL("cancel", "CANCEL");
	
	
	private String name;
	private String displayName;
	
	private TradingtypeEnum(String name, String displayName) {
		this.name = name;
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	public static TradingtypeEnum fromString(String text) {
        for (TradingtypeEnum type : TradingtypeEnum.values()) {
            if (type.displayName.equals(text)) {
                return type;
            }
        }
        return null;
    }
	
}
