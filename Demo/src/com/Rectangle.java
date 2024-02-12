package com;

public class Rectangle implements Shape {
	
	 
	public static void main(String[] args) {
		
		Rectangle rec = new Rectangle();
		rec.draw();
		rec.show();
	}

	@Override
	public void draw() {
	System.out.println("Draw the rectangle");
	}

	@Override
	public void show() {
	System.out.println("show rectangle");		
	}

}
