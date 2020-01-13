import java.util.HashMap;
import java.util.HashSet;

public class PhoneBook {
    HashMap<String, HashSet<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void addContact(String name, String phone) {
        HashSet<String> book = phoneBook.getOrDefault(name, new HashSet<>());
        book.add(phone);
        phoneBook.put(name, book);
    }

    public void getContact(String name) {
        if (phoneBook.containsValue(name)) {
            System.out.println("Контакт " + name + " / Номер(а): " + phoneBook.getOrDefault(name, new HashSet<>()));
        }
        else
        {
            System.out.println("Такого контакта в телефонной книге нет.");
        }
    }
}
