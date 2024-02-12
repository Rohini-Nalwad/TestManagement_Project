package com;

public class RunnableInt implements Runnable{

	@Override
	public void run(){
		
		
	}
	public static void main(String args[])
	{
		RunnableInt run = new RunnableInt();
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		
		t1.start();
		
	
//	private static Exception WrongNameException() throws WrongNameException(); {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
}