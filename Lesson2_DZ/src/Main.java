import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n,// Количество строк в массиве
            m; //Количество столбцов в массиве

    public static void main(String[] args) throws IOException {
        String[][] arr = inputArray();
        printArray(arr);
        try {
            System.out.println("Сумма элементов массива: " + sum(arr));
        }
        catch (MyArraySizeException ex){
            System.out.println(ex.getMessage());
        }
        catch (MyArrayDataException ex){
            System.out.println(ex.getMessage() + ": [" + ex.getN() + "][" + ex.getM() + "]");
        }

    }
    //Метод для посчета суммы элеметов массива
    public static int sum(String[][] arr)throws MyArrayDataException {
        if (n != 4 || m != 4) throw new MyArraySizeException("Неверный размер массива");
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!isInteger(arr[i][j])) throw new MyArrayDataException("Неверный формат данных в ячейке", i,j);
                sum+= Integer.parseInt(arr[i][j]);
            }
        }
        return sum;
    }
    //Метод для ввода массива с клавиатуры
    public static String[][] inputArray() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите количество строк массива");
        int n = Integer.parseInt(reader.readLine());
        System.out.println("Введите количество столбцов массива");
        int m = Integer.parseInt(reader.readLine());
        System.out.println("Введите массив строк, каждый элемент с новой строки");
        String [][] array = new String[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = reader.readLine();
            }
        }
        Main.n = n;
        Main.m = m;
        return array;
    }
    //Метод для вывода массива на экран
    public static void printArray(String[][] array){
        System.out.println();
        System.out.println("Исходный массив строк:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    //Метод проверки, явлется строка целочисленным Количеством или нет
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}


