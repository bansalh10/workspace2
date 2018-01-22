package com.nagarro.RetailManagement.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int id;
	private String name;
	private String type;
	private int cost;
	private double discount;
	private boolean offer;
	private boolean outofstock;

	public Product(int id, String name, String type, int cost, double discount, boolean offer, boolean outofstock) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.discount = discount;
		this.offer = offer;
		this.outofstock = outofstock;
	}

	public Product() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isOffer() {
		return offer;
	}

	public void setOffer(boolean offer) {
		this.offer = offer;
	}

	public boolean isOutofstock() {
		return outofstock;
	}

	public void setOutofstock(boolean outofstock) {
		this.outofstock = outofstock;
	}
}
