package com.joarez.mathmod.util;

import java.util.Hashtable;
import java.util.TreeMap;

public class RomanNumbersUtil {
	  private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	    static {
	        map.put(1000, "M");
	        map.put(900, "CM");
	        map.put(500, "D");
	        map.put(400, "CD");
	        map.put(100, "C");
	        map.put(90, "XC");
	        map.put(50, "L");
	        map.put(40, "XL");
	        map.put(10, "X");
	        map.put(9, "IX");
	        map.put(5, "V");
	        map.put(4, "IV");
	        map.put(1, "I");
	    }
	    private final static Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
	    static {
	    	ht.put('I',1);
	        ht.put('X',10);
	        ht.put('C',100);
	        ht.put('M',1000);
	        ht.put('V',5);
	        ht.put('L',50);
	        ht.put('D',500);
	    }
        
	    public final static String toRoman(int number) {
	    	if(number == 0) {
	    		return " ";
	    	}
	        int l =  map.floorKey(number);
	        if ( number == l ) {
	            return map.get(number);
	        }
	        return map.get(l) + toRoman(number-l);
	    }
	    
	    

	    
	    public static int toDecimal(String num) {
	        int intNum=0;
	        int prev = 0;
	        for(int i = num.length()-1; i>=0 ; i--)
	        {
	                int temp = ht.get(num.charAt(i));
	                if(temp < prev)
	                    intNum-=temp;
	                else
	                    intNum+=temp;
	                prev = temp;
	        }
	        return intNum; 	
	    }

}