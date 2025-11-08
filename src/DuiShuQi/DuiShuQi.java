package DuiShuQi;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class DuiShuQi {
    //测试多少次自己去设置即可，在自己的类里面的main里面for一个就ok

    //生成数组
    //size是长度，max是大小的范围
    public static int[] generate(int size,int max){
        Random random=new Random();
        int []arr=new int[size];
        for (int i = 0; i < arr.length; i++) {
            //0-x左闭右开 然后外部+1是1-max 内部正好是0-x
            arr[i]=random.nextInt(max+1);
        }
        return arr;
    }
    //比较数组
    public static boolean compare(int[] arr1,int[]arr2){
        if (arr1==null||arr2==null)return false;
        if (arr1.length!=arr2.length)return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i]!=arr2[i]){
                System.out.println("这次失败了");
                System.out.println("arr1:");
                System.out.println(Arrays.toString(arr1));
                System.out.println("arr2:");
                System.out.println(Arrays.toString(arr2));

                return false;}
        }
        System.out.println("这次成功了");
        System.out.println("arr1:");
        System.out.println(Arrays.toString(arr1));
        System.out.println("arr2:");
        System.out.println(Arrays.toString(arr2));
        return true;
    }
    //排序数组直接在相关的类里面自己调就ok
}
