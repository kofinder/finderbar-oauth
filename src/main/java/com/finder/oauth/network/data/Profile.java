package com.finder.oauth.network.data;

import java.util.ArrayList;

public class Profile {
	private String avatar;
	private float user_seq;
	private String gender;
	private String birthday;
	private String relationship;
	private boolean facebook;
	private boolean google;
	ArrayList<Object> experience = new ArrayList<Object>();
	ArrayList<Object> education = new ArrayList<Object>();
	ArrayList<Object> skill = new ArrayList<Object>();
	ArrayList<Object> hobbie = new ArrayList<Object>();
	private String wAvatar;
	private String fullName;
	private String address;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public float getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(float user_seq) {
		this.user_seq = user_seq;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public boolean isFacebook() {
		return facebook;
	}

	public void setFacebook(boolean facebook) {
		this.facebook = facebook;
	}

	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}

	public ArrayList<Object> getExperience() {
		return experience;
	}

	public void setExperience(ArrayList<Object> experience) {
		this.experience = experience;
	}

	public ArrayList<Object> getEducation() {
		return education;
	}

	public void setEducation(ArrayList<Object> education) {
		this.education = education;
	}

	public ArrayList<Object> getSkill() {
		return skill;
	}

	public void setSkill(ArrayList<Object> skill) {
		this.skill = skill;
	}

	public ArrayList<Object> getHobbie() {
		return hobbie;
	}

	public void setHobbie(ArrayList<Object> hobbie) {
		this.hobbie = hobbie;
	}

	public String getwAvatar() {
		return wAvatar;
	}

	public void setwAvatar(String wAvatar) {
		this.wAvatar = wAvatar;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
