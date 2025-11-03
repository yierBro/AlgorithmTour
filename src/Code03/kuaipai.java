package Code03;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class kuaipai {
    public static void main(String[] args) {
        int[] ints = generateRandomArray(10, 10);
        int[] clone = ints.clone();
        partionSort(ints);
        Arrays.sort(clone);
        boolean equal = isEqual(clone, ints);
        if (equal){
            System.out.println("okokok");

        }
        System.out.println(Arrays.toString(clone));
        System.out.println(Arrays.toString(ints));
    }
    //随机产生数组
    public static int []generateRandomArray(int maxSize,int maxValue){
        Random random=new Random();
        int size=random.nextInt(maxSize+1);
        int []arr=new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=random.nextInt(maxValue+1);
        }
        return arr;
    }
    //复制数组
    public static int[] copy(int []arr1){
        return arr1==null?null:arr1.clone();
    }
    //判断是否相等
    //都为null true 一个null false length不同false 每一位对比
    public static boolean isEqual(int []arr1,int[]arr2){
        if (arr1==null&&arr2==null)return true;
        if (arr1==null||arr2==null)return false;
        if (arr1.length!=arr2.length)return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i]!=arr2[i])return false;
        }
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        return true;
    }

    //快排1.0 <=区 >区，然后i<大于区 让最右边的掉回来，
    public static void partion1(int[] arr, int L, int R){
        if (arr==null|| arr.length<2){
            return;
        }
        if (L>=R) {
            return;
        }
        int eqIndex=quickSort(arr,L,R);
        partion1(arr,L,eqIndex-1);
        partion1(arr,eqIndex+1,R);
    }
    public static int quickSort(int[]arr,int L,int R){
        int num=arr[R];
        //int Lp=L-1;
        int Rp=R;
        int i=L;
        while (i<Rp){
            if (arr[i]<=num)i++;
            else {
                int temp=arr[i];
                arr[i]=arr[Rp-1];
                arr[Rp-1]=temp;
                Rp--;
            }
        }
        arr[R]=arr[i];
        arr[i]=num;
        return i;
    }


    //2.0 等于区
    //以最右边为界限去选一个数来进行分区
    // 之后返回等于区间的第一个，和最后一个
    // 因为可能大于小于区的指针是-1或者超length
    //特殊情况呢是L>R 和L=R
    //相等时，返回的是L R 大于时返回的是-1 -1
    public static void possess2(int []arr,int L,int R){
        if (L>=R)return;
        int []equalnums=equalflag(arr,L,R);
        possess2(arr,L,equalnums[0]-1);
        possess2(arr,equalnums[1]+1,R);
    }
    public static int[] equalflag(int[] arr,int L,int R){
        if (L>R){
            return new int[]{-1,-1};
        }
        if (L==R){
            return new int[]{L,R};
        }
        int less=L-1;
        //以右边的数为交换的基准
        int more=R;
        int i=L;
        while (i<more){
            //如果是=那就直接右移指针
            //如果是小于那就变化less指针，交换less下一个与i i位置肯定是小的
            // less下一个可能是等于区，也可能是i 同时i++这样就肯定正确了
            //如果是大于，那就变换与more指针前面数的位置，同时i不++因为传过来的数
            if (arr[i]==arr[R])i++;
            else if (arr[i]<arr[R]){
                int temp=arr[i];
                arr[i]=arr[less+1];
                arr[less+1]=temp;
                less++;
                i++;
            }
            else {
                int temp=arr[i];
                arr[i]=arr[more-1];
                arr[more-1]=temp;
               more--;
            }
        }
        //more区有一个是=的
        int temp=arr[R];
        arr[R]=arr[i];
        arr[i]=temp;
        //more肯定不会越界，less可能会越界
        return new int[]{less+1,more};
    }
    //3.0 随机选一个数
    public static void possess3(int arr[],int L,int R){
        if (L>=R)return;
        int []equationNum=randomEquateSession(arr,L,R);
        possess3(arr,L,equationNum[0]-1);
        possess3(arr,equationNum[0]+1,R);
    }
    public static int [] randomEquateSession(int arr[],int L,int R){
        //随机选一个是为了制造随机，给的数组可能不是随机的，给的数组如果是最差的
        //每一次分的两半极不均匀，那么复杂度就不能是NlogN了
        //那么我们如果固定选R，时间复杂度就不能是NlogN了，但是如果是随机的那么数学最后算的就是NlogN
        if (L>R){return new int[]{-1,-1};}
        if (L==R){return new int[]{L,R};}
        int randomIndex= L+(int) (Math.random()*(R-L+1));
        int compareNum=arr[randomIndex];
        int less=L-1;
        int more=R+1;
        int i=L;
        while (i<more){
            if (arr[i]==compareNum)i++;
            else if (arr[i]<compareNum){
                int temp=arr[i];
                arr[i]=arr[less+1];
                arr[less+1]=temp;
                i++;
                less++;
            }else {
                int temp=arr[i];
                arr[i]=arr[more-1];
                arr[more-1]=temp;
                more--;
            }
        }
        return new int[]{less+1,more-1};
    }


    //用模拟栈实现 非递归形式
    //我们刚才的缺点是什么？一直在递归函数，会导致内存溢出
    //如果我们手动模拟，然后每次是选择循环判断进行操作
    //那么内存耗费就会大大减少
    //我们只需要用栈模拟每次返回的R L即可
    //不需要开辟新的空间去耗费内存
    public static void partionSort(int arr[]){
        if (arr==null||arr.length<2)return;
        Stack<int[]> stack=new Stack();
        stack.push(new int[]{0,arr.length-1});
        while (!stack.empty()){
            int[] pop = stack.pop();
            if (pop[0]>=pop[1])continue;
            //左右边界是pop范围的+1 -1
            int randomIndex=pop[0]+(int) (Math.random()*(pop[1]-pop[0]+1));
            int less=pop[0]-1;
            int more=pop[1]+1;
            int i=less+1;

            int compareNum=arr[randomIndex];
            while (i<more){
                if (arr[i]==compareNum)i++;
                else if (arr[i]<compareNum){
                    int temp=arr[i];
                    arr[i]=arr[less+1];
                    arr[less+1]=temp;

                    less++;
                    i++;
                }else {
                    int temp=arr[i];
                    arr[i]=arr[more-1];
                    arr[more-1]=temp;
                    more--;
                }
            }
            if (less-pop[0]>pop[1]-more){
                stack.push(new int[]{pop[0],less});
                stack.push(new int[]{more,pop[1]});
            }else {
                stack.push(new int[]{more,pop[1]});
                stack.push(new int[]{pop[0],less});
            }
        }
    }
}
