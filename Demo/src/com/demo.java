package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Input: String s = "string data to count each character";
//Output: {s=1, t=5, r=3, i=1, n=2, g=1,  =5, d=1, a=5, o=2, c=4, u=1, e=2, h=2}
public class demo {
 
	public static void main(String[] args) {
		String str = "rohinI";
		ArrayList<Character> a1 = new ArrayList<>();
		for (int i = 0; i< str.length(); i ++) {
			a1.add(str.charAt(i));
		}
		HashMap<Character, Integer> h1 = new HashMap<>();
		for (int i= 0; i < str.length(); i ++) {
			h1.putIfAbsent(a1.get(i), Collections.frequency(a1, a1.get(i)));
						
		}
		for (Map.Entry<Character, Integer> m :h1.entrySet()) {
			System.out.println("char is:"+ m.getKey()+ "count is"+m.getValue());
		}

	}

}
