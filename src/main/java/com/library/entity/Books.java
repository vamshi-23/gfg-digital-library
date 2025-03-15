package com.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="myBooks")
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bid;
	private String bname;
	private String bauthor;
	private String bpulicationyear;
	private String btype;
	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Books(int bid, String bname, String bauthor, String bpulicationyear, String btype) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.bauthor = bauthor;
		this.bpulicationyear = bpulicationyear;
		this.btype = btype;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getBpulicationyear() {
		return bpulicationyear;
	}
	public void setBpulicationyear(String bpulicationyear) {
		this.bpulicationyear = bpulicationyear;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	
}
