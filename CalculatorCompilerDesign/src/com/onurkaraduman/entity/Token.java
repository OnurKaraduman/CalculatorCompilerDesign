package com.onurkaraduman.entity;

public class Token {

	private String tokenValue;
	private int position;
	private int tokenType;
	public Token(String tokenValue, int position, int tokenType) {
		super();
		this.tokenValue = tokenValue;
		this.position = position;
		this.tokenType = tokenType;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getTokenType() {
		return tokenType;
	}
	public void setTokenType(int tokenType) {
		this.tokenType = tokenType;
	}
}
