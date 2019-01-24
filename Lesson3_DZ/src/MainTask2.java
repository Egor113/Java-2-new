import java.util.*;

public class MainTask2 {
    public static void main(String[] args) {

        TelephoneBook telephoneBook = new TelephoneBook();
        telephoneBook.add("81111111111","Иванов");
        telephoneBook.add("82222222222","Петров");
        telephoneBook.add("82223333333","Семенов");
        telephoneBook.add("84444444444","Сидоров");
        telephoneBook.add("85555555555","Петренко");
        telephoneBook.add("86666666666","Иванов");
        telephoneBook.add("87777777777","Петренко");
        telephoneBook.add("88888888888","Сергеев");

        System.out.println("Тедефонный справочник :");
        System.out.println();
        for (Map.Entry<String,String> pair: telephoneBook.getBook().entrySet()
             ) {
            System.out.println(pair.getValue() + " " + pair.getKey());
        }

        System.out.println();
        telephoneBook.get("Иванов");
        System.out.println();
        telephoneBook.get("Сергеев");
        System.out.println();
        telephoneBook.get("Гоглидзе");

    }

}
