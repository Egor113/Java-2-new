import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/*
2. Написать простой класс ТелефонныйСправочник, который хранит в себе
список фамилий и телефонных номеров. В этот телефонный справочник
с помощью метода add() можно добавлять записи. С помощью метода get() искать
номер телефона по фамилии. Следует учесть, что под одной фамилией
может быть несколько телефонов (в случае однофамильцев),
тогда при запросе такой фамилии должны выводиться все телефоны.

Желательно как можно меньше добавлять своего, чего нет в задании
(т.е. не надо в телефонную запись добавлять еще дополнительные поля
(имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д.).
 Консоль желательно не использовать (в том числе Scanner),
 тестировать просто из метода main() прописывая add() и get().
 */
public class TelephoneBook {
    private TreeMap<String,String> book = new TreeMap<>();;

    public TreeMap<String, String> getBook() {
        return book;
    }

    public void add(String telephone, String surname){
        book.put(telephone,surname);
    }

    public void get(String surname){
        System.out.println("Телефон " + surname + "(a):");
        boolean find = false;
        for (Map.Entry<String,String> pair: book.entrySet()
                ) {
            if (pair.getValue().equals(surname)){
                find = true;
                System.out.println(pair.getValue() + " " + pair.getKey());
            }
        }
        if (!find) System.out.println("Телефон не найден!");
    }
}
