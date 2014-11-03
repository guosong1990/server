package com.songzi.model;

/**
 * TbRelation entity. @author MyEclipse Persistence Tools
 */

public class Relation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer myid;
	private Integer friendid;

	// Constructors

	/** default constructor */
	public Relation() {
	}

	/** full constructor */
	public Relation(Integer id, Integer myid, Integer friendid) {
		this.id = id;
		this.myid = myid;
		this.friendid = friendid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMyid() {
		return this.myid;
	}

	public void setMyid(Integer myid) {
		this.myid = myid;
	}

	public Integer getFriendid() {
		return this.friendid;
	}

	public void setFriendid(Integer friendid) {
		this.friendid = friendid;
	}

}