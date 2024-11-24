package factors;

import java.util.Scanner;

public class factors {
	public static void main(String[] args) {
	        
		Scanner scanner= new Scanner(System.in);
		
		System.out.print("Enter an integer: ");
		int number= scanner.nextInt();
			        
		System.out.println("The factors of "+ number+ " are: ");
		findFactors(number);
			        
		scanner.close();
	    }

	    public static void findFactors(int number) {
	        for (int i = 2; i <= number; i++) {
	            while (number % i == 0) {
	                System.out.println(i);  
	                number /= i;  
	            }
	        }
	    }
	}

