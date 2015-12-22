import java.util.Scanner;

public class exampleApplication {

    public static void main(String[] args) {

        String firstName,
         lastName;

        //Create scanner to obtain user input
         Scanner scanner1 = new Scanner( System.in );

        //obtain user input
         System.out.print("Enter your first name: ");
         firstName = scanner1.nextLine();

         System.out.print("Enter your last name: ");
         lastName = scanner1.nextLine();

        //output information
         System.out.print("Your first name is " + firstName + " and your last name is "+ lastName);

    }

}