import java.util.HashMap;
import java.util.Map.Entry;

public class Go {
    public static void main(String[] args) {
        String[] names = {"John", "Marta", "Carol", "Marta", "Din", "Sam", "Sam", "Mike",
                "Jake", "Kain", "Mikel", "Alex", "Nik", "Zane", "Marta"};

        HashMap<String, Integer> namesHash = new HashMap<>();

        for (String n : names) {
            namesHash.put(n, namesHash.getOrDefault(n, 0) + 1);
        }

        System.out.println("Уникальные элементы массива:");

        int value;

        for (Entry<String, Integer> entry : namesHash.entrySet()) {
            value = entry.getValue();
            if (value == 1) {
                System.out.println(entry.getKey());
            }
        }

        System.out.println();
        System.out.println("Каждое слово встречается раз: ");
        System.out.println(namesHash);

        PhoneBook book = new PhoneBook();

        createPhoneBook(book);
        System.out.println();
        findPhoneInBook(book, "Ivan Ivanov");
    }

    public static void createPhoneBook(PhoneBook book) {
        book.addContact("Marta Collins", "1235456512");
        book.addContact("Jake Blake", "1235465411231");
        book.addContact("Ivan Ivanov", "12354565456");
        book.addContact("Ivan Ivanov", "1235453654");
        book.addContact("Jane Anderson", "4561231546");
        book.addContact("Leo Dicaprio", "457621354");
        book.addContact("Ms. Smith", "1111120332347");
    }

    public static void findPhoneInBook(PhoneBook book, String name) {
        book.getContact(name);
    }

}
