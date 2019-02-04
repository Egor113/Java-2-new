package lesson6.lesson5.hw;

//import lesson6.lesson5.hw.ru.geekbrains.Jawa2.Lesson5.Array;

import java.util.Arrays;

public class Main {

    private static void writeInArrayOneThread(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void writeInArrayManyThread(int countOfThread, float[] arr) throws InterruptedException {
        int lengthArray = arr.length / countOfThread;
        int lengthLastArray = (arr.length / countOfThread != 0) ? arr.length - lengthArray * (countOfThread - 1) : lengthArray;
        float[][] bufferArray = new float[countOfThread][];

        for (int i = 0; i < bufferArray.length; i++) {
            bufferArray[i] = (i != bufferArray.length - 1) ? new float[lengthArray] : new float[lengthLastArray];
            System.arraycopy(arr, lengthArray * i, bufferArray[i], 0, bufferArray[i].length);
        }

        Thread[] thread = new Thread[countOfThread];
        for (int i = 0; i < thread.length; i++) {
            int I = i;
            thread[i] = new Thread(() -> {
                for (int J = 0; J < bufferArray[I].length; J++) {
                    bufferArray[I][J] = veryHardEquation(bufferArray, I, J, lengthArray);
                }
            });
            thread[i].start();
        }
        for (int i = 0; i < thread.length; i++) {
            thread[i].join();
        }

        for (int i = 0; i < bufferArray.length; i++) {
            System.arraycopy(bufferArray[i], 0, arr, lengthArray * i, bufferArray[i].length);
        }
    }

    private static float veryHardEquation(float arr[][], int i, int j, int lengthArrays) {
        return (float) (arr[i][j] * Math.sin(0.2f + (i * lengthArrays + j) / 5)
                * Math.cos(0.2f + (i * lengthArrays + j) / 5) * Math.cos(0.4f + (i * lengthArrays + j) / 2));
    }

    private static float[] test(int countFlow, int size) throws InterruptedException {
        float[] arr = new float[size];
        Arrays.fill(arr, 1f);
        long start = System.currentTimeMillis();

        writeInArrayManyThread(countFlow, arr);

        long finish = System.currentTimeMillis();
        System.out.println("Массив размером: " + size + " был обработан в " + countFlow +
                " потоках(потоке) за " + (finish - start) + " миллисекунд");

        return arr;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        float[] idol = new float[size];
        Arrays.fill(idol, 1f);
        writeInArrayOneThread(idol);

        boolean valid = true;
        for (int i = 1; i < 30; i++) {
            if (!Arrays.equals(idol, test(i, size))) valid = false;
        }

        System.out.println(valid ? "валидный" : "не валидный");
    }
}
