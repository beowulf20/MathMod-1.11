package com.joarez.mathmod.util;

public class  MathMethods {
	public static int A = 63;
	public static int B = 32;
	private static int RESULT = 0;
	public static String OPERATION = "+";
	public static int DIF = 1;
	
	
	
	public static int GetResult() {
		if(OPERATION == "+") {
			RESULT = A + B;			
		}
		if(OPERATION == "-") {
			RESULT = A-B;
		}
		if(OPERATION == "*") {
			RESULT = A*B;
		}
		if(OPERATION == "/") {
			if(B == 0) {
				B = 1;
			}
			RESULT = A/B;
		}
		
		return RESULT;
	}
	
	public static void Randomize() {
		int a = 0; int b = 0;	 
		if(DIF == 1){ //ADD EASY
            int r = (int) (Math.random() * 101);
            a = (int) (Math.random() * (r+1));
            b = (int) r - a;  
       }
       if(DIF == 2){ //ADD HARD
            int r = (int) (Math.random() * 1001);
            a = (int) (Math.random() * (r+1));
            b = (int) r - a; 
       }
       if(DIF == 3){ //SUB EASY
          a = (int) (Math.random() * 201);
          b = (int) (Math.random() * (a+1));
       }
       if(DIF == 4){ //SUB HARD
          a = (int) (Math.random() * 1001);
          b = (int) (Math.random() * (a+1));
       }
       if(DIF == 5){ //MUL EASY
          a = (int) (Math.random() * 11);
          b = (int) (Math.random() * 11);
       }
        if(DIF == 6){ //MUL HARD
          a = (int) (Math.random() * 21);
          b = (int) (Math.random() * 21);
       }
       if(DIF == 7){ //DIV EASY
          int r = (int) (Math.random() * 11);
          b = (int) (Math.random() * 11);
          if(b == 0) {
        	  b =1;
          }
          a = r*b;
       }
       if(DIF == 8){ //DIV HARD
          int r = (int) (Math.random() * 31);
          b = (int) (Math.random() * 31);
          if(b == 0) {
        	  b =1;
          }
          a = r*b;
       }
       
       A = a;
       B = b;
		
	}
	
	
	
}
