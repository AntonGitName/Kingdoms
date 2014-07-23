package io;

import java.util.HashMap;
import java.util.Map;

public enum SquareTag {
	GRASS('G'), WATER('W'), MOUNTAIN('M'), TREE('T');

	public static SquareTag getName(char tag) {
		if (mapping == null) {
			mapping = new HashMap<Character, SquareTag>();
			for (SquareTag ut : values()) {
				mapping.put(ut.tag, ut);
			}
		}
		return mapping.get(tag);
	}

	private final char tag;

	private static Map<Character, SquareTag> mapping;

	private SquareTag(char tag) {
		this.tag = tag;
	}
}
