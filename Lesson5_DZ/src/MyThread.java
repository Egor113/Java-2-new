import java.util.ArrayList;

public class MyThread extends Thread{

    private int h;

    private float[] arr;

    public MyThread(int h){
        this.h = h;
        this.arr = new float[h];
    }

    public int getH() {
        return h;
    }

    public float[] getArr() {
        return arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)( arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
