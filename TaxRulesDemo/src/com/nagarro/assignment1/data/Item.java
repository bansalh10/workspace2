package com.nagarro.assignment1.data;


 
 public abstract class Item {
	protected String name,type;
	protected double price,quantity,amount;
	public Item(String name,double price,double quantity)
	{
		this.name=name;
		
		this.price=price;
		this.quantity=quantity;
		}	
	

	public abstract void amountCalculator();
	
	public void showDetails(){
		System.out.println("name " +name+" price " + price+" quantity "+quantity+" calculated price " +amount);
	 
	}
}
