package com.human.vo;

import java.util.Date;

public class FreeCommentVO {

	private int fb_id;
	private int fc_id;
	private String fc_content;
	private Date fc_date;
	private String m_id;
	
	
	public int getFb_id() {
		return fb_id;
	}
	public void setFb_id(int fb_id) {
		this.fb_id = fb_id;
	}
	public int getFc_id() {
		return fc_id;
	}
	public void setFc_id(int fc_id) {
		this.fc_id = fc_id;
	}
	public String getFc_content() {
		return fc_content;
	}
	public void setFc_content(String fc_content) {
		this.fc_content = fc_content;
	}
	public Date getFc_date() {
		return fc_date;
	}
	public void setFc_date(Date fc_date) {
		this.fc_date = fc_date;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	@Override
	public String toString() {
		return "FreeComment [fb_id=" + fb_id + ", fc_id=" + fc_id + ", fb_content=" + fc_content + ", fc_date="
				+ fc_date + ", m_id=" + m_id + "]";
	}
	
	
}
