import java.util.Random;
import java.util.Scanner;

import Formatter.AccountingFormatter;
import Formatter.BaseFormatter;
import Formatter.DecimalSeparatorFormatter;
import Formatter.DefaultFormatter;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World by Jihad ElSayed!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a random number to generate 15 number: ");
        int num = scanner.nextInt();
        Random random = new Random(num);

        int[] array = new int[15];
        for(int i = 0; i < array.length; i++){
            int randomBoolean = random.nextInt(2);
            int t = random.nextInt(1000000);
            if(randomBoolean == 1){
                t = t*-1;
            }else{
                t= t*1;
            }
            array[i] = t;
        }
        System.out.print("The generated array: ");

        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ", ");
        }
        System.out.println("----------------------------------------");

        printArray(array);
        scanner.close();
    }
    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            int num = array[i];
            DefaultFormatter defaultFormatter = new DefaultFormatter();
            String formattedDefaultNumber = defaultFormatter.format(num);
            System.out.println("Default Format:              " + formattedDefaultNumber);
            
            DecimalSeparatorFormatter decimalSeparatorFormatter = new DecimalSeparatorFormatter();
            String formattedDecimalNumber = decimalSeparatorFormatter.format(num);
            System.out.println("Decimal Separated Format:    " + formattedDecimalNumber);

            AccountingFormatter accountingFormatter = new AccountingFormatter();
            String formattedAccountingNumber = accountingFormatter.format(num);
            System.out.println("Account Format:              " + formattedAccountingNumber);

            BaseFormatter baseFormatter = new BaseFormatter();
            String formattedBaseNumber = baseFormatter.format(num);
            System.out.println("Base Format (base 2):        " + formattedBaseNumber);
            
            System.out.println("----------------------------------------");
        }
    }
}