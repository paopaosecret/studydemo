package com.example.suanfa;

/**
 * Created by Administrator on 2018/2/26.
 */

public class QuickSort {

    static void quickSort(int targetArr[],int start,int end)
    {
        int i=start,j=end;
        int key=targetArr[start];
        while(i<j)
        {
            while(j>i && targetArr[j]>=key)
            {
                j--;
            }
            if(i<j)
            {
                /*targetArr[i]已经保存在key中，可将后面的数填入*/
                targetArr[i]=targetArr[j];
                i++;
            }
            /*按i++方向遍历目标数组，直到比key大的值为止*/
            while(i<j&&targetArr[i]<=  key)
            /*此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次。*/
            {
                i++;
            }
            if(i<j)
            {
                /*targetArr[j]已保存在targetArr[i]中，可将前面的值填入*/
                targetArr[j]=targetArr[i];
                j--;
            }
        }
        /*此时i==j*/
        targetArr[i]=key;

        /*递归调用，把key前面的完成排序*/
        if(start == i-1){
            return;
        }else{
            quickSort(targetArr,start,i-1);
        }

        /*递归调用，把key后面的完成排序*/
        if(j+1 == end){
            return;
        }else{
            quickSort(targetArr,j+1,end);
        }


    }

    public static void main(String args[]){

        int array[] = {15,5,6,59,15,45,78};
        quickSort(array,0,6);

        for(int i =0;i<array.length;i++){
            System.out.println(array[i]);
        }

    }
}
