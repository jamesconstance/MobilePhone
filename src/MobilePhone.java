import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MobilePhone {

    Scanner scanner = new Scanner(System.in);

    ArrayList<Contact> contacts;

    public MobilePhone() {
        contacts = new ArrayList<Contact>();
    }

    boolean isOn = false;

    public void bootUp() {
        System.out.println("Booting Up...");

        isOn = true;

        while (isOn) {
            printMenu();
            selectChoice(getChoice(6));
        }

    }

    private void printMenu() {
        System.out.println("\nOptions:");
        System.out.printf("\t%s%n\t%s%n\t%s%n\t%s%n\t%s%n\t%s%n: ",
                "0 - Quit",
                "1 - Print Contacts",
                "2 - Add New Contact",
                "3 - Update Existing Contact",
                "4 - Remove Contact",
                "5 - Search Contacts");
    }

    private int getChoice(int numberOfChoices) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("\nPlease enter a number 0 to " + (numberOfChoices - 1) + ": ");
            }
        }
        return choice;
    }

    private void selectChoice(int option) {
        switch (option) {
            case 0:
                isOn = false;
                break;
            case 1:
                printContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                updateContact();
                break;
            case 4:
                removeContact();
                break;
            case 5:
                searchContacts();
                break;
        }
    }

    private void printContacts() {
        if (contacts.size() == 0) {
            System.out.println("Contact list is empty");
        } else {
            for (Contact contact: contacts) {
                System.out.printf("%s:\t%s\n", contact.getName(), contact.getNumber());
            }
        }
    }

    private void addNewContact() {
        System.out.print("Enter new contact name: ");
        String name = scanner.nextLine();
        if (searchContacts(name) != null) {
            System.out.println("Contact already exists");
            return;
        }
        System.out.print("Enter new contact number: ");
        String number = scanner.nextLine();
        contacts.add(new Contact(name, number));
        System.out.println(name + " has been added to contacts list");
    }

    private void updateContact() {
        System.out.print("Enter contact name to update: ");
        String name = scanner.nextLine();
        Contact contact = searchContacts(name);
        if (contact != null) {
            updateExistingContact(contact);
        } else {
            System.out.println("Contact does not exist");
        }
    }

    private void updateExistingContact(Contact contact) {
        System.out.printf("%s%n\n%s%n%s%n:",
                "Options:",
                "0 - Update Name",
                "1 - Update Number");
        int input = getChoice(2);
        if (input == 0) {
            updateContactName(contact);
        } else {
            updateContactNumber(contact);
        }
    }

    private void updateContactName(Contact contact) {
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        contact.setName(newName);
    }

    private void updateContactNumber(Contact contact) {
        System.out.print("Enter new number: ");
        String newNumber = scanner.nextLine();
        contact.setNumber(newNumber);
    }

    private void removeContact() {
        System.out.print("Enter contact to remove: ");
        String name = scanner.nextLine();
        Contact contact = searchContacts(name);
        if (contact != null) {
            contacts.remove(contact);
            System.out.println("Removed " + contact.getName() + " from contact list");
        } else {
            System.out.println("Contact does not exist");
        }
    }

    private void searchContacts() {
        System.out.print("Search contacts for: ");
        String name = scanner.nextLine();
        Contact contact = searchContacts(name);
        if(contact != null) {
            System.out.println("Found " + contact.getName() + " in contact list");
            printContact(contact);
        } else {
            System.out.println("Contact does not exist");
        }
    }

    private void printContact(Contact contact) {
        System.out.printf("%s\t%s\n", contact.getName(), contact.getNumber());
    }

    private Contact searchContacts(String name) {
        for (Contact contact: contacts) {
            if (contact.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase())) {
                return contact;
            }
        }
        return null;
    }
}
