package nextop.asia.pratise.demo;

import android.util.Log;

/**
 * Created by QuyDV on 6/15/17.
 */

public class TestDemo {
    private int[] numbers;

    public void sort(int[] numbers){
        this.numbers = numbers;
        if(numbers.length <= 0) return;
        quickSort(0,numbers.length-1);
        printf();
    }

    private void quickSort(int low, int high){
        int i = low, j = high;
        int center = numbers[(i+j)/2];
        while (i<=j){
            while (numbers[i] < center) i++;
            while (numbers[j] > center) j--;
            if(i<=j) {
                swap(i,j);
                i++;
                j--;
            }
            if(i<high) quickSort(i,high);
            if(j>low) quickSort(low,j);
        }
    }

    private void printf(){
        for(int i = 0; i < numbers.length; i++){
            Log.d("QuickSort",String.valueOf(numbers[i]));
        }
    }

    private void swap(int i, int j){
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
