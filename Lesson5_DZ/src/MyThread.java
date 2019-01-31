import java.util.ArrayList;

public class MyThread extends Thread{

    private float[] arr;

    public MyThread(int h, float[] arr){
        this.arr = new float[h];
        System.arraycopy(arr,0,this.arr,0,h);
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
