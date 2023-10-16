package com.finder.oauth.domain;

/**
 * @author jovianjack
 *
 * @email finderbar.theinlwin@gmail.com
 *
 * @createdAt 2020/10/30
 */
public enum Action {
	INSERTED("INSERTED"), UPDATED("UPDATED"), DELETED("DELETED");

	private final String name;

	private Action(String value) {
		this.name = value;
	}

	public String value() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}

}
