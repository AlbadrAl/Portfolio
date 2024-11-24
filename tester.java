package tester;

import java.util.Scanner;

public class tester {
    private String name;
    private String email;
    private String phoneNumber;

    public tester(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void printContact() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter the email address: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();
        
        tester contact = new tester(name, email, phoneNumber);
        
        System.out.println("\nOriginal Contact:");
        contact.printContact();
        
        System.out.print("\nEnter the new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        
        contact.setPhoneNumber(newPhoneNumber);
        System.out.println("\nUpdated Contact:");
        contact.printContact();
    }
}

