public class MyArrayDataException extends NumberFormatException { //Исключение о неправильности данных в ячейке массива
    private int n;//Количество строк в массиве
    private int m;//Количество столбцов в массиве

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public MyArrayDataException(String s, int n, int m) {
        super(s);
        this.n = n;
        this.m = m;
    }

}
