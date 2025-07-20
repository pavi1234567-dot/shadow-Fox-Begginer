import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void display() {
        System.out.println("Name : " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("-----------------------");
    }
}

public class ContactManager {
    static ArrayList<Contact> contacts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Contact Management System =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: updateContact(); break;
                case 4: deleteContact(); break;
                case 5: searchContact(); break;
                case 6: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    public static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();
        while (!isValidPhone(phone)) {
            System.out.print("Invalid phone. Enter 10-digit number: ");
            phone = sc.nextLine();
        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        while (!isValidEmail(email)) {
            System.out.print("Invalid email. Enter again: ");
            email = sc.nextLine();
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println("✅ Contact added successfully!");
    }

    public static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("Contact #" + (i + 1));
            contacts.get(i).display();
        }
    }

    public static void updateContact() {
        viewContacts();
        System.out.print("Enter contact number to update: ");
        int index = sc.nextInt();
        sc.nextLine(); // clear buffer

        if (index <= 0 || index > contacts.size()) {
            System.out.println("Invalid contact number.");
            return;
        }

        Contact c = contacts.get(index - 1);

        System.out.print("Enter New Name (" + c.name + "): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) c.name = name;

        System.out.print("Enter New Phone (" + c.phone + "): ");
        String phone = sc.nextLine();
        if (!phone.isEmpty()) {
            while (!isValidPhone(phone)) {
                System.out.print("Invalid phone. Enter 10-digit number: ");
                phone = sc.nextLine();
            }
            c.phone = phone;
        }

        System.out.print("Enter New Email (" + c.email + "): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) {
            while (!isValidEmail(email)) {
                System.out.print("Invalid email. Enter again: ");
                email = sc.nextLine();
            }
            c.email = email;
        }

        System.out.println("✅ Contact updated successfully!");
    }

    public static void deleteContact() {
        viewContacts();
        System.out.print("Enter contact number to delete: ");
        int index = sc.nextInt();
        sc.nextLine(); // clear buffer

        if (index <= 0 || index > contacts.size()) {
            System.out.println("Invalid contact number.");
            return;
        }

        contacts.remove(index - 1);
        System.out.println("🗑️ Contact deleted successfully!");
    }

    public static void searchContact() {
        System.out.print("Enter name or email to search: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Contact c : contacts) {
            if (c.name.toLowerCase().contains(keyword) || c.email.toLowerCase().contains(keyword)) {
                c.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contact found.");
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^\\d{10}$");
    }
}
