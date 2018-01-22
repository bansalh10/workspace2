package com.nagarro.assignment1.data;



public class RawItem extends Item {

	private double tax;
	public RawItem(String name,double price,double quantity) {
		super(name,price,quantity);
		type="raw";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void amountCalculator() {
		price=price*quantity;
		 tax=(12.5*price)/100;
		 amount=price+tax;
	}
    
}
