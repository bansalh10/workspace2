package com.nagarro.assignment1.data;



public class ManufacturedItem extends Item {
	private double tax;
	public ManufacturedItem(String name,double price,double quantity) {
		super(name,price,quantity);
		type="manufactured";
		
	}
	
	@Override
	public void amountCalculator() {
		price=price*quantity;
	      double part_tax=(12.5*price)/100;
		 tax=part_tax +2*(price + part_tax)/100;
		 amount=price+tax;
	}

}
