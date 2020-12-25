package com.example.demo.line.message.flex.entity;

import com.example.demo.line.message.entity.EntityMessage;

public class FlexMessage extends EntityMessage {

	private String title;
	private String logoUrl;
	private String date;
	private String place;
	private String message;
	private String logoUrlActionUrl;
	private String qrUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLogoUrlActionUrl() {
		return logoUrlActionUrl;
	}

	public void setLogoUrlActionUrl(String logoUrlActionUrl) {
		this.logoUrlActionUrl = logoUrlActionUrl;
	}

	public String getQrUrl() {
		return qrUrl;
	}

	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

}
