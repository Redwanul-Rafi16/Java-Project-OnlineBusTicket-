import TICKET.Ticket;
import TICKET.TicInter;
import TICKET.TicImp;
import USER.User;
import USER.UserDaointer;
import USER.Userdaoimp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name;
        int id;
        int ticketId;
        UserDaointer dao = new Userdaoimp();
        TicInter obj = new TicImp();

        System.out.println("Welcome to Online Ticket Booking Application");
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\n1. Add Your Profile" +
                    "\n2. See Your Profile by ID" +
                    "\n3. Update Your Profile" +
                    "\n4. Delete Your Profile" +
                    "\n5. Buy Your Ticket" +
                    "\n6. Cancel Your Ticket" +
                    "\n7. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    User user = new User();
                    System.out.print("Enter ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    sc.nextLine(); // Consume newline
                    name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    user.setId(id);
                    user.setName(name);
                    user.setPhone(phone);
                    user.setAddress(address);

                    dao.createUser(user);
                }
                case 2 -> {
                    System.out.print("Enter Your ID to view profile: ");
                    id = sc.nextInt();
                    dao.showProfile(id);
                }
                case 3 -> {
                    System.out.print("Enter Your ID to update: ");
                    id = sc.nextInt();
                    System.out.print("Enter new Name: ");
                    sc.nextLine(); // Consume newline
                    name = sc.nextLine();
                    System.out.print("Enter new Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter new Address: ");
                    String address = sc.nextLine();
                    dao.updateUser(id, name,phone,address);
                }
                case 4 -> {
                    System.out.print("Enter User ID to delete: ");
                    id = sc.nextInt();
                    dao.deleteUser(id);
                }
                case 5 -> {
                    Ticket ticket = new Ticket();
                    System.out.print("Enter User ID: ");
                    id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter departure place: ");
                    String departure = sc.nextLine();
                    System.out.print("Enter destination place: ");
                    String destination = sc.nextLine();

                    ticket.setUserId(id);
                    ticket.setDeparture(departure);
                    ticket.setDestination(destination);

                    obj.buyTicket(ticket);
                }

                case 6 -> {
                    System.out.print("Enter User ID to cancel ticket: ");
                    id = sc.nextInt();
                    System.out.print("Enter Ticket ID: ");
                    ticketId = sc.nextInt();
                    obj.cancelTicket(id, ticketId);
                }

                case  7-> {
                    System.out.println("Thank you for using the Online Ticket Booking Application!");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid choice.");
            }
        } while (true);
    }
}
