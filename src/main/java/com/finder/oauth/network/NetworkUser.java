package com.finder.oauth.network;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.finder.oauth.network.data.Profile;



public class NetworkUser {
	private String _id;
	private String username;
	private Profile profile;
	private String updateAt;
	private String createdAt;
	private String createdBy;
	private String updatedBy;
	private ArrayList<LinkedHashMap<String, Object>> emails;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public ArrayList<LinkedHashMap<String, Object>> getEmails() {
		return emails;
	}

	public void setEmails(ArrayList<LinkedHashMap<String, Object>> emails) {
		this.emails = emails;
	}


}
