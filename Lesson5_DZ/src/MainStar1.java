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
        final int size = 10;
        final int h = size / threadNumber;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
            System.out.println(arr[i]);
        }

        ArrayList<MyThread> listThread = new ArrayList<>();
        int firstIndex = 0;
        for (int i = 0; i < threadNumber; i++) {
            float[] a = new float[h];
            System.arraycopy(arr,firstIndex,a,0,h);
            listThread.add(new MyThread(h,a));
            firstIndex += h;
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
        firstIndex = 0;
        for (int i = 0; i < threadNumber; i++) {
            System.arraycopy(listThread.get(i).getArr(),0,arr,firstIndex,h);
            firstIndex += h;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println(System.currentTimeMillis() - a);
    }
}
