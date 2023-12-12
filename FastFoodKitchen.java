package fastfoodkitchen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FastFoodKitchen {

    private ArrayList<BurgerOrder> orderList = new ArrayList();

    private static int nextOrderNum = 1;

    FastFoodKitchen() throws FileNotFoundException {
        // Adds 3 orders from Orders.csv into program. ***
        // Change file for different orders & add more if statements to accomadate orders. ***
        try {
            Scanner scan = new Scanner(new File("/Users/melvinbonilla/Desktop/FastFoodKitchen_1/src/fastfoodkitchen/Orders.csv"));
            String header = scan.nextLine();
            String line;
            String[] lineSplit;
            while(scan.hasNext()) {
                line = scan.nextLine();
                lineSplit = line.split(",");
                
                if(lineSplit[0].equalsIgnoreCase("1")) {
                    BurgerOrder order1 = new BurgerOrder(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), Boolean.parseBoolean(lineSplit[5]), Integer.parseInt(lineSplit[0]));
                    orderList.add(order1);
                }
                
                if(lineSplit[0].equalsIgnoreCase("2")) {
                    BurgerOrder order2 = new BurgerOrder(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), Boolean.parseBoolean(lineSplit[5]), Integer.parseInt(lineSplit[0]));
                    orderList.add(order2);
                }
                
                if(lineSplit[0].equalsIgnoreCase("3")) {
                    BurgerOrder order3 = new BurgerOrder(Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), Boolean.parseBoolean(lineSplit[5]), Integer.parseInt(lineSplit[0]));
                    orderList.add(order3);
                }
            }
        } // FileNotFoundException added to program. ***
        catch(FileNotFoundException e) {
            System.out.println("File not found. Please try a new file and restart program!");
        }
    }
    
    public static int getNextOrderNum() {
        return nextOrderNum;
    }

    private void incrementNextOrderNum() {
        nextOrderNum++;
    }

    public int addOrder(int ham, int cheese, int veggie, int soda, boolean toGo) {
        int orderNum = getNextOrderNum();
        orderList.add(new BurgerOrder(ham, cheese, veggie, soda, toGo, orderNum));
        incrementNextOrderNum();
        orderCallOut(orderList.get(orderList.size() - 1));
        return orderNum;

    }

    public boolean isOrderDone(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                return false;
            }
        }
        return true;
    }

    public boolean cancelOrder(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                orderList.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getNumOrdersPending() {
        return orderList.size();
    }

    public boolean cancelLastOrder() {

        if (!orderList.isEmpty()) { // same as  if (orderList.size() > 0) 
            orderList.remove(orderList.size() - 1);
            return true;
        }

        return false;
    }

    private void orderCallOut(BurgerOrder order) {
        if (order.getNumCheeseburgers() > 0) {
            System.out.println("You have " + order.getNumHamburger() + " hamburgers");
        }
        if (order.getNumCheeseburgers() > 0) {
            System.out.println("You have " + order.getNumCheeseburgers() + " cheeseburgers");
        }
        if (order.getNumVeggieburgers() > 0) {
            System.out.println("You have " + order.getNumVeggieburgers() + " veggieburgers");
        }
        if (order.getNumSodas() > 0) {
            System.out.println("You have " + order.getNumSodas() + " sodas");
        }
    }

    public void completeSpecificOrder(int orderID) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderNum() == orderID) {
                System.out.println("Order number " + orderID + " is done!");
                if (orderList.get(i).isOrderToGo()) {
                    orderCallOut(orderList.get(i));
                }
                orderList.remove(i);
            }
        }
    }

    public void completeNextOrder() {
        int nextOrder = orderList.get(0).getOrderNum();
        completeSpecificOrder(nextOrder);
    }

    // Part 2
    public ArrayList<BurgerOrder> getOrderList() {
        return orderList;
    }

    public int findOrderSeq(int whatWeAreLookingFor) {
        for (int j = 0; j < orderList.size(); j++) {
            if (orderList.get(j).getOrderNum() == whatWeAreLookingFor) {
                return j;
            }
        }
        return -1;
    }

//    public int findOrderBin(int whatWeAreLookingFor) {
//        int left = 0;
//        int right = orderList.size() - 1;
//        while (left <= right) {
//            int middle = (left + right) / 2;
//            if (whatWeAreLookingFor < orderList.get(middle).getOrderNum()) {
//                right = middle - 1;
//            } else if (whatWeAreLookingFor > orderList.get(middle).getOrderNum()) {
//                left = middle + 1;
//            } else {
//                return middle;
//            }
//        }
//        return -1;
//    }

    public int findOrderBin(int orderID){
        int left = 0;
        int right = orderList.size()-1;
        while (left <= right){
            int middle = ((left + right)/2);
            if (orderID < orderList.get(middle).getOrderNum()){
                right = middle-1;
            }
            else if(orderID > orderList.get(middle).getOrderNum()){
                left = middle +1;
            }
            else{
                return middle;
            }
        }
        return -1;
        
    }
    
    public void selectionSort(){
        for (int i = 0; i< orderList.size()-1; i++){
            int minIndex = i;
            for (int k = i+1; k < orderList.size(); k++){
                if (orderList.get(minIndex).getTotalBurgers() > orderList.get(k).getTotalBurgers()){
                    minIndex = k;
                }
            }
            BurgerOrder temp = orderList.get(i);
            orderList.set(i , orderList.get(minIndex));
            orderList.set(minIndex, temp);
        }
    }

    public void insertionSort() {
        for (int j = 1; j < orderList.size(); j++) {
            BurgerOrder temp = orderList.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.getTotalBurgers() < orderList.get(possibleIndex - 1).getTotalBurgers()) {
                orderList.set(possibleIndex, orderList.get(possibleIndex - 1));
                possibleIndex--;
            }
            orderList.set(possibleIndex, temp);
        }
    }
    
//    public void selectionSort() { //weird method!
//
//        for (int j = 0; j < orderList.size() - 1; j++) {
//            int minIndex = j;
//            for (int k = j + 1; k < orderList.size(); k++) {
//
//                 if (orderList.get(minIndex).getTotalBurgers() > orderList.get(j).getTotalBurgers()){
//                    minIndex = k;
//                }
//            }
//            BurgerOrder temp = orderList.get(j);
//            orderList.set(j, orderList.get(minIndex));
//            orderList.set(minIndex, temp);
//
//        }
//    }

}
