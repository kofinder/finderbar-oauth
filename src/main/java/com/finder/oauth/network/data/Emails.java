package com.finder.oauth.network.data;

import java.util.ArrayList;

public class Emails {
	ArrayList<Object> verificationTokens = new ArrayList<Object>();

	public ArrayList<Object> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(ArrayList<Object> verificationTokens) {
		this.verificationTokens = verificationTokens;
	}

}
