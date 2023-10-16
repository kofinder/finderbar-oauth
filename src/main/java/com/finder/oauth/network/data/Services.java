package com.finder.oauth.network.data;


public class Services {
	Password PasswordObject;
	Emails EmailsObject;
	Resume ResumeObject;

	// Getter Methods

	public Password getPassword() {
		return PasswordObject;
	}

	public Emails getEmails() {
		return EmailsObject;
	}

	public Resume getResume() {
		return ResumeObject;
	}

	// Setter Methods

	public void setPassword(Password passwordObject) {
		this.PasswordObject = passwordObject;
	}

	public void setEmails(Emails emailsObject) {
		this.EmailsObject = emailsObject;
	}

	public void setResume(Resume resumeObject) {
		this.ResumeObject = resumeObject;
	}
}
