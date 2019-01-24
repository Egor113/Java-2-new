import java.util.*;

public class Main {
    public static void main(String[] args) {
        task1();
    }

    /*
    1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    Найти и вывести список уникальных слов, из которых состоит массив
    (дубликаты не считаем). Посчитать сколько раз встречается каждое слово.
    */
    public static void task1(){

        ArrayList<String> listOfWords = new ArrayList<>();
        listOfWords.add("AA");
        listOfWords.add("BB");
        listOfWords.add("CC");
        listOfWords.add("DA");
        listOfWords.add("AC");
        listOfWords.add("AC");
        listOfWords.add("AA");
        listOfWords.add("AC");
        listOfWords.add("AC");
        listOfWords.add("AB");
        listOfWords.add("AA");
        listOfWords.add("BB");
        listOfWords.add("DA");
        listOfWords.add("DD");

        HashMap<String,Integer> mapOfWords = new HashMap<>();
        for (int i = 0; i < listOfWords.size(); i++) {
            mapOfWords.put(listOfWords.get(i),0);
        }

        Iterator<Map.Entry<String,Integer>> iterator = mapOfWords.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> pair = iterator.next();
            for (int i = 0; i < listOfWords.size(); i++) {
                if (pair.getKey().equals(listOfWords.get(i)))
                    pair.setValue(pair.getValue() + 1);
            }
        }

        for (Map.Entry<String,Integer> pair: mapOfWords.entrySet()
                ) {
            System.out.println("Слово " + pair.getKey() + " встречается " + pair.getValue() + " раз(а)");
        }
    }

    /*

     */
    public static void task2(){

    }
}
