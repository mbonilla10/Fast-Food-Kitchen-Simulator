package fastfoodkitchen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FastFoodKitchenDriver {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        FastFoodKitchen kitchen = new FastFoodKitchen();

        Scanner sc = new Scanner(System.in);

        try {
            while (kitchen.getNumOrdersPending() != 0) {
            // see what the user wants to do
                System.out.println("Please select from the following menu of options, by typing a number:");
                System.out.println("\t 1. Order food");
                System.out.println("\t 2. Cancel last order");
                System.out.println("\t 3. Show number of orders currently pending");
                System.out.println("\t 4. Complete order");
                System.out.println("\t 5. Check on order");
                System.out.println("\t 6. Cancel order");
                System.out.println("\t 7. Exit");
                
                int num = sc.nextInt();
                switch (num) {
                    case 1:
                        System.out.println("How many hamburgers do you want?");
                        int ham = sc.nextInt();
                        System.out.println("How many cheeseburgers do you want?");
                        int cheese = sc.nextInt();
                        System.out.println("How many veggieburgers do you want?");
                        int veggie = sc.nextInt();
                        System.out.println("How many sodas do you want?");
                        int sodas = sc.nextInt();
                        System.out.println("Is your order to go? (Y/N)");
                        char letter = sc.next().charAt(0);
                        boolean TOGO = false;
                        if (letter == 'Y' || letter == 'y') {
                            TOGO = true;
                        }
                        int orderNum = kitchen.addOrder(ham, cheese, veggie, sodas, TOGO);
                        System.out.println("Thank you. Your order number is " + orderNum);
                        System.out.println();
                        break;
                    case 2:
                        boolean ready = kitchen.cancelLastOrder();
                        if (ready) {
                            System.out.println("Thank you. The last order has been canceled");
                        } else {
                            System.out.println("Sorry. There are no orders to cancel.");
                        }
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("There are " + kitchen.getNumOrdersPending() + " pending orders");
                        break;
                    case 4:
                        System.out.println("Enter order number to complete?");
                        int order = sc.nextInt();
                        kitchen.completeSpecificOrder(order);
                        System.out.println("Your order is ready. Thank you!");
                        break;
                    case 5:
                        System.out.println("What is your order number?");
                        order = sc.nextInt();
                        ready = kitchen.isOrderDone(order);
                        if (ready) {
                            System.out.println("Sorry, no order with this number was found.");
                        } else {
                            System.out.println("No, it's not ready, but it should be up soon. Sorry for the wait.");
                        }
                        System.out.println();
                        break;
                    case 6:
                        System.out.println("What is your order number?");
                        order = sc.nextInt();
                        boolean cancel = kitchen.cancelOrder(order);
                        if (cancel) {
                            System.out.println("Your order has been successfully cancelled ");
                        } else {
                            System.out.println("Sorry, we can’t find your order number in the system");
                        }
                        System.out.println();
                        break;
                    case 7:
                        // Prints Report.txt
                        // Test harness utilizing File I/O ***
                        // Try block adds online orders to Report.txt and prints them upon exit.
                        try {
                            FileWriter fw = new FileWriter("/Users/melvinbonilla/Desktop/FastFoodKitchen_1/src/fastfoodkitchen/Report.txt",true);
                            PrintWriter out = new PrintWriter(fw);
                            
                            out.println("Orders Added Online : Complete Tomorrow!");
                            out.println("Order ID: 4, Hamburgers: 3, Cheeseburgers: 15, Veggieburgers: 4, Sodas: 10, To-go: Yes");
                            out.println("Order ID: 5, Hamburgers: 2, Cheeseburgers: 1, Veggieburgers: 2, Sodas: 1, To-go: No");
                            out.println("Order ID: 6, Hamburgers: 5, Cheeseburgers: 5, Veggieburgers: 5, Sodas: 5, To-go: Yes");
                            out.println("Order ID: 7, Hamburgers: 11, Cheeseburgers: 19, Veggieburgers: 2, Sodas: 1, To-go: No");
                            out.println("Order ID: 8, Hamburgers: 6, Cheeseburgers: 4, Veggieburgers: 3, Sodas: 4, To-go: No");
                            out.println("Order ID: 9, Hamburgers: 2, Cheeseburgers: 8, Veggieburgers: 9, Sodas: 4, To-go: No");
                            out.println("Order ID: 10, Hamburgers: 9, Cheeseburgers: 3, Veggieburgers: 1, Sodas: 1, To-go: Yes");
                            out.close();
                        } // IOException added to program. ***
                        catch(IOException e) {
                            System.out.println("Input/Output error! Please try again.");
                        }
                        
                        Scanner  fileScan = new Scanner(new File("/Users/melvinbonilla/Desktop/FastFoodKitchen_1/src/fastfoodkitchen/Report.txt"));
                        int i = 0;
                        while(i <= 11) {
                            System.out.println(fileScan.nextLine());
                            i++;
                        }
                        System.exit(0);
                        break;
                }
            }
        } // InputMismatchException added to program. ***
        catch (InputMismatchException e) {
                System.out.println("Not a number! Restart Program and follow directions!"); //end switch
        } //end while loop
    } // end main
}// end class

