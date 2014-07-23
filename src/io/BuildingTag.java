package io;

import java.util.HashMap;
import java.util.Map;

public enum BuildingTag {
	CASTLE('C'), MILL('M');

	public static BuildingTag getName(char tag) {
		if (mapping == null) {
			mapping = new HashMap<Character, BuildingTag>();
			for (BuildingTag ut : values()) {
				mapping.put(ut.tag, ut);
			}
		}
		return mapping.get(tag);
	}

	private final char tag;

	private static Map<Character, BuildingTag> mapping;

	private BuildingTag(char tag) {
		this.tag = tag;
	}
}
