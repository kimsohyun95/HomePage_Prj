package com.human.vo;

import java.util.Date;

public class FreeBoardVO {

	private int fb_id;
	private String fb_title;
	private String fb_content;
	private Date fb_date;
	private int fb_hit;
	private int fb_group;
	private int fb_step;
	private int fb_indent;
	private String m_id;
	
	
	public FreeBoardVO() {
		super();
	}
	
	public FreeBoardVO(int fb_id, String fb_title, String fb_content, Date fb_date, int fb_hit, int fb_group,
			int fb_step, int fb_indent, String m_id) {
		super();
		this.fb_id = fb_id;
		this.fb_title = fb_title;
		this.fb_content = fb_content;
		this.fb_date = fb_date;
		this.fb_hit = fb_hit;
		this.fb_group = fb_group;
		this.fb_step = fb_step;
		this.fb_indent = fb_indent;
		this.m_id = m_id;
	}

	public int getFb_id() {
		return fb_id;
	}
	public void setFb_id(int fb_id) {
		this.fb_id = fb_id;
	}
	public String getFb_title() {
		return fb_title;
	}
	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}
	public String getFb_content() {
		return fb_content;
	}
	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}
	public Date getFb_date() {
		return fb_date;
	}
	public void setFb_date(Date fb_date) {
		this.fb_date = fb_date;
	}
	public int getFb_hit() {
		return fb_hit;
	}
	public void setFb_hit(int fb_hit) {
		this.fb_hit = fb_hit;
	}
	public int getFb_group() {
		return fb_group;
	}
	public void setFb_group(int fb_group) {
		this.fb_group = fb_group;
	}
	public int getFb_step() {
		return fb_step;
	}
	public void setFb_step(int fb_step) {
		this.fb_step = fb_step;
	}
	public int getFb_indent() {
		return fb_indent;
	}
	public void setFb_indent(int fb_indent) {
		this.fb_indent = fb_indent;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	@Override
	public String toString() {
		return "FreeBoardVO [fb_id=" + fb_id + ", fb_title=" + fb_title + ", fb_content=" + fb_content + ", fb_date="
				+ fb_date + ", fb_hit=" + fb_hit + ", fb_group=" + fb_group + ", fb_step=" + fb_step + ", fb_indent="
				+ fb_indent + ", m_id=" + m_id + "]";
	}
	
}
