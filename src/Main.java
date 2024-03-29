
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Billing {
    private Customer customer;
    private double totalAmount;

    public Billing(Customer customer) {
        this.customer = customer;
        this.totalAmount = 0.0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addToBilling(double amount) {
        totalAmount += amount;
    }
}


class CleaningSchedule {
    private List<String> schedule;

    public CleaningSchedule() {
        this.schedule = new ArrayList<>();
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void addToSchedule(String task) {
        schedule.add(task);
    }

    public void deleteFromSchedule(String task) {
        schedule.remove(task);
    }
}

class Customer {
    private String customerId;
    private String name;
    private String address;
    private List<Order> orderHistory;

    // Constructor
    public Customer(String customerId, String name, String address) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.orderHistory = new ArrayList<>();
    }

    // Getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Methods
    public void editCustomer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void deleteCustomer() {
        // Set all member variables to null to destroy the customer object
        customerId = null;
        name = null;
        address = null;
        orderHistory = null;
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public void printOrderHistory() {
        System.out.println("Order History for Customer: " + name);
        for (Order order : orderHistory) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Order Total: " + order.getOrderTotal());
            System.out.println("---");
        }
    }

static class Order {
        private String orderId;
        private String orderDate;
        private double orderTotal;

        public Order(String orderId, String orderDate, double orderTotal) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.orderTotal = orderTotal;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public double getOrderTotal() {
            return orderTotal;
        }
    }
}


class Food implements Serializable {
    int itemno;
    int quantity;
    float price;

    Food(int itemno, int quantity) {
        this.itemno = itemno;
        this.quantity = quantity;
        switch (itemno) {
            case 1:
                price = quantity * 8;
                break;
            case 2:
                price = quantity * 15;
                break;
            case 3:
                price = quantity * 7;
                break;
            case 4:
                price = quantity * 4;
                break;
        }
    }
}

class Singleroom implements Serializable {
    String name;
    String contact;
    String gender;
    ArrayList<Food> food = new ArrayList<>();

    Singleroom() {
        this.name = "";
    }

    Singleroom(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }
}

class Doubleroom extends Singleroom implements Serializable {
    String name2;
    String contact2;
    String gender2;

    Doubleroom() {
        this.name = "";
        this.name2 = "";
    }

    Doubleroom(String name, String contact, String gender, String name2, String contact2, String gender2) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.name2 = name2;
        this.contact2 = contact2;
        this.gender2 = gender2;
    }
}

class NotAvailable extends Exception {
    @Override
    public String toString() {
        return "Not Available !";
    }
}

class holder implements Serializable {
    Doubleroom luxury_doublerrom[] = new Doubleroom[10]; // Luxury
    Doubleroom deluxe_doublerrom[] = new Doubleroom[20]; // Deluxe
    Singleroom luxury_singleerrom[] = new Singleroom[10]; // Luxury
    Singleroom deluxe_singleerrom[] = new Singleroom[20]; // Deluxe
}

class Hotel {
    static holder hotel_ob = new holder();
    static Scanner sc = new Scanner(System.in);

    static void CustDetails(int i, int rn) {
        String name, contact, gender;
        String name2 = null, contact2 = null;
        String gender2 = "";
        System.out.print("\nEnter customer name: ");
        name = sc.next();
        System.out.print("Enter contact number: ");
        contact = sc.next();
        System.out.print("Enter gender: ");
        gender = sc.next();
        if (i < 3) {
            System.out.print("Enter second customer name: ");
            name2 = sc.next();
            System.out.print("Enter contact number: ");
            contact2 = sc.next();
            System.out.print("Enter gender: ");
            gender2 = sc.next();
        }

        switch (i) {
            case 1:
                hotel_ob.luxury_doublerrom[rn] = new Doubleroom(name, contact, gender, name2, contact2, gender2);
                break;
            case 2:
                hotel_ob.deluxe_doublerrom[rn] = new Doubleroom(name, contact, gender, name2, contact2, gender2);
                break;
            case 3:
                hotel_ob.luxury_singleerrom[rn] = new Singleroom(name, contact, gender);
                break;
            case 4:
                hotel_ob.deluxe_singleerrom[rn] = new Singleroom(name, contact, gender);
                break;
            default:
                System.out.println("Wrong option");
                break;
        }
    }

    static void bookroom(int i) {
        int j;
        int rn;
        System.out.println("\nChoose room number from: ");
        Object[] roomArray = null;
        int roomNumberOffset = 0;

        switch (i) {
            case 1:
                roomArray = hotel_ob.luxury_doublerrom;
                roomNumberOffset = 1;
                break;
            case 2:
                roomArray = hotel_ob.deluxe_doublerrom;
                roomNumberOffset = 11;
                break;
            case 3:
                roomArray = hotel_ob.luxury_singleerrom;
                roomNumberOffset = 31;
                break;
            case 4:
                roomArray = hotel_ob.deluxe_singleerrom;
                roomNumberOffset = 41;
                break;
            default:
                System.out.println("Enter valid option");
                return;
        }

        for (j = 0; j < roomArray.length; j++) {
            if (roomArray[j] == null) {
                System.out.print(j + roomNumberOffset + ",");
            }
        }
        System.out.print("\nEnter room number: ");
        try {
            rn = sc.nextInt();
            rn = rn - roomNumberOffset;
            if (roomArray[rn] != null)
                throw new NotAvailable(); 
            CustDetails(i, rn);
            System.out.println("Room Booked");
        } catch (NotAvailable e) { 
            System.out.println("Room not available");
        } catch (Exception e) {
            System.out.println("Invalid Option");
        }
    }

    static void features(int i) {
        switch (i) {
            case 1:
                System.out
                        .println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day: RM 300 ");
                break;
            case 2:
                System.out
                        .println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day: RM 280  ");
                break;
            case 3:
                System.out
                        .println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day: RM 220  ");
                break;
            case 4:
                System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day: RM 120 ");
                System.out.println("Enter valid option");
                break;

        }
    }

    static void availability(int i) {
        int count = 0;
        Object[] roomArray;

        switch (i) {
            case 1:
                roomArray = hotel_ob.luxury_doublerrom;
                break;
            case 2:
                roomArray = hotel_ob.deluxe_doublerrom;
                break;
            case 3:
                roomArray = hotel_ob.luxury_singleerrom;
                break;
            case 4:
                roomArray = hotel_ob.deluxe_singleerrom;
                break;
            default:
                System.out.println("Enter a valid option");
                return;
        }

        for (int j = 0; j < roomArray.length; j++) {
            if (roomArray[j] == null)
                count++;
        }

        System.out.println("Number of rooms available: " + count);
    }

    static void bill(int rn, int rtype) //
    {
        double amount = 0;
        String list[] = { "Sandwich", "Pasta", "Noodles", "Coke" };
        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (rtype) {
            case 1:
                amount += 300;
                System.out.println("\nRoom Charge - " + 300);
                System.out.println("\n===============");
                System.out.println("Food Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.luxury_doublerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }

                break;
            case 2:
                amount += 280;
                System.out.println("Room Charge - " + 280);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.deluxe_doublerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 3:
                amount += 220;
                System.out.println("Room Charge - " + 220);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.luxury_singleerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            case 4:
                amount += 120;
                System.out.println("Room Charge - " + 120);
                System.out.println("\nFood Charges:- ");
                System.out.println("===============");
                System.out.println("Item   Quantity    Price");
                System.out.println("-------------------------");
                for (Food obb : hotel_ob.deluxe_singleerrom[rn].food) {
                    amount += obb.price;
                    String format = "%-10s%-10s%-10s%n";
                    System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
                }
                break;
            default:
                System.out.println("Not valid");
        }
        System.out.println("\nTotal Amount - RM " + amount);
    }

    static void deallocate(int rn, int rtype) {
        Doubleroom[] luxuryDoublerooms = hotel_ob.luxury_doublerrom;
        Doubleroom[] deluxeDoublerooms = hotel_ob.deluxe_doublerrom;
        Singleroom[] luxurySinglerooms = hotel_ob.luxury_singleerrom;
        Singleroom[] deluxeSinglerooms = hotel_ob.deluxe_singleerrom;

        String roomType;

        switch (rtype) {
            case 1:
                if (luxuryDoublerooms[rn] != null) {
                    System.out.println("Room used by " + luxuryDoublerooms[rn].name);
                } else {
                    System.out.println("Empty Already");
                    return;
                }
                roomType = "luxury double";
                break;
            case 2:
                if (deluxeDoublerooms[rn] != null) {
                    System.out.println("Room used by " + deluxeDoublerooms[rn].name);
                } else {
                    System.out.println("Empty Already");
                    return;
                }
                roomType = "deluxe double";
                break;
            case 3:
                if (luxurySinglerooms[rn] != null) {
                    System.out.println("Room used by " + luxurySinglerooms[rn].name);
                } else {
                    System.out.println("Empty Already");
                    return;
                }
                roomType = "luxury single";
                break;
            case 4:
                if (deluxeSinglerooms[rn] != null) {
                    System.out.println("Room used by " + deluxeSinglerooms[rn].name);
                } else {
                    System.out.println("Empty Already");
                    return;
                }
                roomType = "deluxe single";
                break;
            default:
                System.out.println("Enter a valid option");
                return;
        }

        System.out.println("Do you want to checkout? (y/n)");
        char w = sc.next().charAt(0);
        if (w == 'y' || w == 'Y') {
            bill(rn, rtype);

            switch (rtype) {
                case 1:
                    luxuryDoublerooms[rn] = null;
                    break;
                case 2:
                    deluxeDoublerooms[rn] = null;
                    break;
                case 3:
                    luxurySinglerooms[rn] = null;
                    break;
                case 4:
                    deluxeSinglerooms[rn] = null;
                    break;
            }

            System.out.println("Deallocated successfully");
        }
    }

    static void order(int rn, int rtype) {
        int i, q;
        char wish;
        try {
            System.out.println(
                    "\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRM 8\n2.Pasta\t\tRM 15\n3.Noodles\tRM 7\n4.Coke\t\tRM 4\n");
            do {
                i = sc.nextInt();
                System.out.print("Quantity- ");
                q = sc.nextInt();

                switch (rtype) {
                    case 1:
                        hotel_ob.luxury_doublerrom[rn].food.add(new Food(i, q));
                        break;
                    case 2:
                        hotel_ob.deluxe_doublerrom[rn].food.add(new Food(i, q));
                        break;
                    case 3:
                        hotel_ob.luxury_singleerrom[rn].food.add(new Food(i, q));
                        break;
                    case 4:
                        hotel_ob.deluxe_singleerrom[rn].food.add(new Food(i, q));
                        break;
                }
                System.out.println("Do you want to order anything else ? (y/n)");
                wish = sc.next().charAt(0);
            } while (wish == 'y' || wish == 'Y');
        } catch (NullPointerException e) {
            System.out.println("\nRoom not booked");
        } catch (Exception e) {
            System.out.println("Cannot be done");
        }
    }
}

class write implements Runnable {
    holder hotel_ob;

    write(holder hotel_ob) {
        this.hotel_ob = hotel_ob;
    }

    @Override
    public void run() {
        try {
            FileOutputStream fout = new FileOutputStream("backup");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(hotel_ob);
        } catch (Exception e) {
            System.out.println("Error in writing " + e);
        }

    }
}

public class Main {
    public static void main(String[] args) {

        try {
            File f = new File("backup");
            if (f.exists()) {
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fin);
                Hotel.hotel_ob = (holder) ois.readObject();
            }
            Scanner sc = new Scanner(System.in);
            int ch, ch2;
            char wish;
            x: do {

                System.out.println("\nEnter your choice :");
                System.out.println("1. Display room details");
                System.out.println("2. Display room availability");
                System.out.println("3. Book a room");
                System.out.println("4. Order food");
                System.out.println("5. Checkout");
                System.out.println("6. Customer Management");
                System.out.println("7. Billings");
                System.out.println("8. Cleaning Schedule");
                System.out.println("9. Exit");

                ch = sc.nextInt();
                switch (ch) {
                    case 1:
                        // Existing code for displaying room details
                        System.out.println("\nChoose room type :");
                        System.out.println("1. Luxury Double Room");
                        System.out.println("2. Deluxe Double Room");
                        System.out.println("3. Luxury Single Room");
                        System.out.println("4. Deluxe Single Room");
                        ch2 = sc.nextInt();
                        Hotel.features(ch2);
                        break;
                    case 2:
                        // Existing code for displaying room availability
                        System.out.println("\nChoose room type :");
                        System.out.println("1. Luxury Double Room");
                        System.out.println("2. Deluxe Double Room");
                        System.out.println("3. Luxury Single Room");
                        System.out.println("4. Deluxe Single Room");
                        ch2 = sc.nextInt();
                        Hotel.availability(ch2);
                        break;
                    case 3:
                        // Existing code for booking a room
                        System.out.println("\nChoose room type :");
                        System.out.println("1. Luxury Double Room");
                        System.out.println("2. Deluxe Double Room");
                        System.out.println("3. Luxury Single Room");
                        System.out.println("4. Deluxe Single Room");
                        ch2 = sc.nextInt();
                        Hotel.bookroom(ch2);
                        break;
                    case 4:
                        // Existing code for ordering food
                        System.out.print("Room Number - ");
                        ch2 = sc.nextInt();
                        if (ch2 > 60)
                            System.out.println("Room doesn't exist");
                        else if (ch2 > 40)
                            Hotel.order(ch2 - 41, 4);
                        else if (ch2 > 30)
                            Hotel.order(ch2 - 31, 3);
                        else if (ch2 > 10)
                            Hotel.order(ch2 - 11, 2);
                        else if (ch2 > 0)
                            Hotel.order(ch2 - 1, 1);
                        else
                            System.out.println("Room doesn't exist");
                        break;
                    case 5:
                        // Existing code for checkout
                        System.out.print("Room Number - ");
                        ch2 = sc.nextInt();
                        if (ch2 > 60)
                            System.out.println("Room doesn't exist");
                        else if (ch2 > 40)
                            Hotel.deallocate(ch2 - 41, 4);
                        else if (ch2 > 30)
                            Hotel.deallocate(ch2 - 31, 3);
                        else if (ch2 > 10)
                            Hotel.deallocate(ch2 - 11, 2);
                        else if (ch2 > 0)
                            Hotel.deallocate(ch2 - 1, 1);
                        else
                            System.out.println("Room doesn't exist");
                        break;
                    case 6:
                        // Customer Management
                        System.out.println("\nCustomer Management");
                        System.out.println("1. Display Customer Information");
                        System.out.println("2. Delete Profile");
                        System.out.println("3. Exit");
                        int customerChoice = sc.nextInt();
                        switch (customerChoice) {
                            case 1:
                                // Display customer information
                                System.out.println("\nCustomer Information");
                                System.out.println("Name: Zeyad");
                                System.out.println("Age: 21");
                                // Display other mock information as needed
                                break;
                            case 2:
                                // Delete profile
                                System.out.println("\nProfile deleted successfully");
                                break;
                            case 3:
                                // Exit
                                break;
                            default:
                                System.out.println("Invalid Option");
                        }
                        break;
                    case 7:
                        // Billings
                        System.out.println("\nBillings");
                        System.out.println("1. Display Billing Information");
                        System.out.println("2. Exit");
                        int billingChoice = sc.nextInt();
                        switch (billingChoice) {
                            case 1:
                                System.out.println("\nBilling Information");
                                System.out.println("Customer Name: Zeyad");
                                System.out.println("Outstanding: RM 240");
                                System.out.println("Last Paid Bill Date: 12/07/2023");
                                System.out.println("Last Paid Bill Amount: RM 190");
                                break;
                            case 2:
                                // Exit
                                break;
                            default:
                                System.out.println("Invalid Option");
                        }
                        break;
                    case 8:
                        // Cleaning Schedule
                        System.out.println("\nCleaning Schedule");
                        System.out.println("1. Monday - 10:00 AM");
                        System.out.println("2. Tuesday - 2:00 PM");
                        System.out.println("3. Wednesday - 12:00 PM");
                        System.out.println("4. Exit");
                        int cleaningChoice = sc.nextInt();
                        switch (cleaningChoice) {
                            case 1:
                            case 2:
                            case 3:
                                System.out.println("Cleaning booked successfully");
                                break;
                            case 4:
                                // Exit
                                break;
                            default:
                                System.out.println("Invalid Option");
                        }
                        break;
                    case 9:
                        break x;
                    default:
                        System.out.println("Invalid Option");
                }
                System.out.println("\nContinue : (y/n)");
                wish = sc.next().charAt(0);
                if (!(wish == 'y' || wish == 'Y' || wish == 'n' || wish == 'N')) {
                    System.out.println("Invalid Option");
                    System.out.println("\nContinue : (y/n)");
                    wish = sc.next().charAt(0);
                }

            } while (wish == 'y' || wish == 'Y');

            Thread t = new Thread(new write(Hotel.hotel_ob));
            t.start();
        } catch (Exception e) {
            System.out.println("Not a valid input");
        }
    }
}
