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

    private int charToNumber(char c){
        return c - '0';
    }

    private char numberToChar(int number){
        return (char)(number-48);
    }

    private void normarlize(String a, String b){
        if(a.length() >= b.length()){

        }
    }

    private void quickSort(int low, int high){
        int i = low, j = high;
        int center = numbers[(i+j)/2];
        while(i<=j){
            while(numbers[i]<center) i++;
            while(numbers[j]>center) j--;
            if(i<=j){
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

    private String sum(String a, String b){
        String s = "";
        int temp = 0;

        return s;
    }

    // Xau doi xung dai nhat
    public String xauDoiXung(String ss){
        String s1 = "", s2 = "";
        char[] listChar = ss.toCharArray();
        int[][] arr = new int[listChar.length][listChar.length];
        for(int i = 0; i < arr.length; i++)
            for(int j = 0; j < arr.length; j++) arr[i][j] = 1;

        for(int i = listChar.length - 2; i >= 0; i--){

            for(int j = i+1; j < listChar.length; j++){
                if(listChar[i] == listChar[j]){
                    arr[i][j] = arr[i+1][j-1] + 2;
                }else{
                    arr[i][j] = Math.max(arr[i+1][j],arr[i][j-1]);
                }
            }
        }

        int i=0, j=listChar.length-1;
        while(i<=j){
            if(listChar[i] == listChar[j]){
                s1 = s1.concat(String.valueOf(listChar[i]));
                s2 = String.valueOf(listChar[j]).concat(s2);
                i++;
                j--;
            }else{
                if(arr[i+1][j] == arr[i][j]) i++;
                else j--;
            }
        }
        if(arr[1][listChar.length-1] %2 == 1) s2 = s2.substring(1,s2.length());

        s1 = s1.concat(s2);

        return s1;
    }


}
