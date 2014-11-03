package com.songzi.model;

import java.util.Date;

/**
 * TbUser entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String telpone;
	private String latlngLately;
	private int state;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String username, String password, String telpone,
			String latlngLately,int state) {
		this.username = username;
		this.password = password;
		this.telpone = telpone;
		this.latlngLately = latlngLately;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelpone() {
		return this.telpone;
	}

	public void setTelpone(String telpone) {
		this.telpone = telpone;
	}

	public String getLatlngLately() {
		return this.latlngLately;
	}

	public void setLatlngLately(String latlngLately) {
		this.latlngLately = latlngLately;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}



}