package com;

import java.util.ArrayList;
import java.util.List;

//numbers less than or equal to 50.
//
//4,5,6,7,1,2,3
// 
//Output:
//64
//125
//216
//343
public class GretterNumber {

	public static void main(String[] args) {
		List<Integer> num = List.of(4,5,6,7,1,2,3);
		List<Integer> gretNum =new ArrayList<>();
		for(int num1: num)
		{
	if (num1>50) {
		gretNum.addAll(num);
	}
		}
		
		for(Integer gretNum2 : gretNum) {
			System.out.println(gretNum2);
		}
		
//		int i, n;
//		Scanner sc =new Scanner(System.in);
//		n= sc.nextInt();
//		for (i= 1; i<=n; i ++) {
//			System.out.println("number" + i+ "cube"+i+ "i");
//		}
		
	}

}
