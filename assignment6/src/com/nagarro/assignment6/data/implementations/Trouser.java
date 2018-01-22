package com.nagarro.assignment6.data.implementations;

import com.nagarro.assignment6.data.Constants;
import com.nagarro.assignment6.data.Garment;

public class Trouser implements Garment {

	@Override
	public void print() {
		System.out.println(Constants.trouserMsg);
	}

}
