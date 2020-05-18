package com.temp.admin.dto;

import com.temp.common.model.BaseDTO;

public class wcheckDTO extends BaseDTO {
	private Integer id;
	private String time;
	private String usercode;
	private String username;
	private String stime;
	private String etime;
	private String outdesc;
	private String kqzt;
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getOutdesc() {
		return outdesc;
	}
	public void setOutdesc(String outdesc) {
		this.outdesc = outdesc;
	}
	public String getKqzt() {
		return kqzt;
	}
	public void setKqzt(String kqzt) {
		this.kqzt = kqzt;
	}

	
}