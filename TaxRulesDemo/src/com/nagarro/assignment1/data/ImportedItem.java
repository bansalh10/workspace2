package com.nagarro.assignment1.data;



public class ImportedItem extends Item{

	private double tax;
	public ImportedItem(String name,double price,double quantity) {
		super(name,price,quantity);
		type="imported";
		
		// TODO Auto-generated constructor stub
	}
    
	@Override
	public void amountCalculator() {
		price=price*quantity;
		 double surcharge;
		 tax=(10*price)/100;
		 amount=price+tax;
		 if(amount<=100)
		 {
			 surcharge=5;
		 }
		 else if(amount>100 && amount<=200 )
		 {
			 
			 surcharge=10;
		 }
		 else{
			 surcharge=(5*amount)/100;
			 
		 }
		 amount=amount+surcharge;
		 
	}
}
