package Code05;

//桶排序
public class Count {
    //计数排序 0~200
    public static void countSort(int[] arr){
        if (arr==null||arr.length<2)return;
        //寻找数组最大值，然后设桶
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int []bucker=new int[max+1];
        for (int i = 0; i < arr.length; i++) {
            bucker[arr[i]]++;
        }
        int i=0;
        for (int j = 0; j < arr.length; j++) {
            while (bucker[j]>0){
                arr[i++]=j;
                bucker[j]--;
            }
        }
        
    }
}
