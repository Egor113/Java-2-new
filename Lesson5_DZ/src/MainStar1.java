import java.util.ArrayList;

public class MainStar1 {
    public static void main(String[] args) {
        method2(2);
    }
    public static void method1(){
        final int size = 10_000_000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void method2(int threadNumber){
        final int size = 10_000_000;
        final int h = size / threadNumber;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        ArrayList<float[]> listArray = new ArrayList<>();
        for (int i = 0; i < threadNumber; i++) {
            listArray.add(new float[h]);
        }

        for (int i = 0; i < threadNumber; i++) {
            System.arraycopy(arr,0,listArray.get(i),0,h);
        }

        ArrayList<MyThread> listThread = new ArrayList<>();

        for (int i = 0; i < threadNumber; i++) {
            listThread.add(new MyThread(h));
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < threadNumber; i++) {
            listThread.get(i).start();
        }

        for (int i = 0; i < threadNumber; i++) {
            try{
                listThread.get(i).join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        for (int i = 0; i < threadNumber; i++) {
            System.arraycopy(listArray.get(i),0,arr,0,h);
        }

        System.out.println(System.currentTimeMillis() - a);
    }
}
