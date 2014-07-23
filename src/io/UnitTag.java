package io;

import java.util.HashMap;
import java.util.Map;

public enum UnitTag {
	KING('K'), ARCHER('A'), PEASANT('P'), KNIGHT('N');

	public static UnitTag getName(char tag) {
		if (mapping == null) {
			mapping = new HashMap<Character, UnitTag>();
			for (UnitTag ut : values()) {
				mapping.put(ut.tag, ut);
			}
		}
		return mapping.get(tag);
	}

	private final char tag;

	private static Map<Character, UnitTag> mapping;

	private UnitTag(char tag) {
		this.tag = tag;
	}
}
