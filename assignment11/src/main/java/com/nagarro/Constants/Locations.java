package com.nagarro.Constants;

import java.util.HashMap;
import java.util.Map;

public enum Locations {

	DELHI("DEL", "Delhi"), MUMBAI("MUB", "Mumbai"), BARNALA("BNL", "Barnala");

	private final String code;
	private final String name;
	private static Map<String, String> lMap;

	private Locations(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static Map<String, String> getMap() {
		if (lMap == null) {
			initializeMapping();
		}

		return lMap;
	}

	private static void initializeMapping() {
		lMap = new HashMap<String, String>();
		for (Locations l : Locations.values()) {
			lMap.put(l.code, l.name);
		}
	}
}
