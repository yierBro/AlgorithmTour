package Code05;


import DuiShuQi.DuiShuQi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//radix是基数的意思
public class Radix {
//桶排序

    public static void main(String[] args) {
        long all1=0;
        long all2=0;
        for (int i = 0; i < 100; i++) {
            int[] arr1 = DuiShuQi.generate(10000, 1000);
            //时长测试 隐式桶 显式桶
            //一万是    37   60      30    57
            //十万是    255  825     282   848
            //一百万是  2296 15352   2288  14526
            //一千万是  23000 208100 36915 262895
            //也就是说一万是差两倍     一万耗时从   0.059   优化到了0.033s 优化了44%
            //十万是三倍多           十万耗时从    0.84s  优化到了0.27s  优化了67.9%
            //一百万是七倍           一百万耗时从  14.9s  优化到了2.3s   优化了86.6%
            //一千万是八倍左右的差距  一千万的耗时从 235.5s 优化到了29.9s  优化了87.3%
            //因为显式桶内存不是连续的
            //内存间的跳跃耗时与数组中连续的空间时间不同
            //同时由于显式桶内存是不连续的，对空间的利用率非常的不友好
            //就像jvm里面已经拒绝了标记清除一样，我们这里需要拒绝这种断断续续的排序方式
            int[] arr2 = arr1.clone();

            //测试排序隐式桶的时间
            long cur=  System.currentTimeMillis();
            //System.out.println("cur:"+cur);
            radixSort(arr1,0,arr1.length-1);
            all1+=System.currentTimeMillis()-cur;
            //System.out.println("all1:"+all1);


            //测试排序显示桶的时间
            cur=  System.currentTimeMillis();
            radixSortQuick(arr2,0,arr2.length-1);
            //System.out.println(cur);
            all2+=System.currentTimeMillis()-cur;
            //System.out.println(all2);

            DuiShuQi.compare(arr1,arr2);
        }
        System.out.println("all1 "+all1);
        System.out.println("all2 "+all2);


    }
    //基数排序
    public static void radixSortQuick(int[] arr,int L,int R){
        int max= (int) Long.MIN_VALUE;
        for (int i = L; i <= R; i++) {
            if (arr[i]>max)max=arr[i];
        }
        //最大数是几位数
        int maxdigit=0;
        while (max>0){
            maxdigit++;
            max/=10;
        }
        Queue<Integer>[] arrayList=new Queue[10];
        for (int i = 0; i < 10; i++) {
            //每一个位置都是null，我们如果不去初始化
            // 那么在后面加数的时候会报错你让null.add
            //null.pull什么的不就会错吗
            arrayList[i]=new LinkedList<>();
        }
        //总循环，每次判断每一个位置上的排序
        for (int i = 1; i < maxdigit + 1; i++) {
            for (int j = L; j < R+1; j++) {
                arrayList[getdigitnum(arr[j],i)].add(arr[j]);
            }
            int index=L;
            //从arr的L开始 替换桶 桶怎么排数字？
            //一个一个排就ok了，空了就break
            //如下
            for (int j = 0; j < 10; j++) {
                while (!arrayList[j].isEmpty()){
                    arr[index++]=arrayList[j].poll();
                }
            }


        }
    }
    //基数排序隐式桶
    public static void radixSort(int [] arr,int L,int R){
        //首先我们算数组的最大位数是多少位
        //其次每次算这个数在这个位上的数字
        int max=(int) Long.MIN_VALUE;
        for (int i = L; i <= R; i++) {
            if (arr[i]>max)max=arr[i];
        }
        int maxdigit=0;
        while (max>0){
            max/=10;
            maxdigit++;
        }

        int[] help=new int[R-L+1];
        //每一位进行一次排序，占比最大的最后排序，比如会员和年龄 会员肯定优先，那么会员是后排
        //会员在maxdigit这里 年龄在1这里 作为第一个进行排序的
        for (int i = 1; i < maxdigit+1; i++) {
            int count[]=new int[10];
            for (int j = L; j < R+1; j++) {
                //在某位上的count++
                count[getdigitnum(arr[j],i)]++;
            }
            for (int j = 1; j < count.length; j++) {
                count[j]=count[j-1]+count[j];
            }
            for (int j = help.length-1; j >=0 ; j--) {
                //从count右到左，算自己位数是几位，然后放在help里面的第x-1处
                //j+R是排序的树所在的位置，i是现在是排的那个位置上的 十位个位百位
                //在help数组上放arr 位置是count数组计算这个数的所在位数上的值
                //在count里面存了要放的位置
                //测试

//                int a=getdigitnum(arr[j+L],i);
//                int b=count[getdigitnum(arr[j+L],i)];
//                int c=j+L;
                help[count[getdigitnum(arr[j+L],i)]-1]=arr[j+L];
                count[getdigitnum(arr[j+L],i)]--;
            }
            for (int j = 0; j < help.length; j++) {
                arr[j+L]=help[j];
            }
        }

    }
    //得到数字在d位上的值
    public static int getdigitnum(int num,int d){
        //pow是10的d-1次方
        return ((num/((int)Math.pow(10,d-1)))%10);
    }


}
