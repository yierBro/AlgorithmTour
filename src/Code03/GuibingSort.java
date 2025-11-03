package Code03;

import java.util.ArrayList;

public class GuibingSort {
    public static void pocess(int[] arr,int left,int right){
        if (left==right){
            return ;
        }
        //中点可以是(left+right)/2
        //也就是left +二者差的一半
        //master公式的值是
        //T(N)=2T(N/2)+O(N);
        //logb a=1=d->O(Nd *logN)=O(N*logN)
        int mid=left+((right-left)>>1);
        pocess(arr,left,mid);
        pocess(arr,mid+1,right);
        merge(arr,left,mid,right);
    }
    //递归的归并排序
    public static void merge(int[] arr,int L,int M,int R){
        //把两部分归在一起排序
        //就好比打牌，两部分，然后排序 牌
        // 3 2 1  5 2 9
        //3 2    1这两个部分排序
        // 3 2 排序 2 3
        // 2 3 1排序
        //1 2 3
        //最后
        // 1 2 3   2 5 9
        // 1 左2 右2 3 5 9
        int [] help=new int[R-L+1];
        int i=0;
        int p1=L;
        int p2=M+1;
        while (p1<=M&&p2<=R){
            //注意那个等号，在改计算方法时等号有时候可能省去
            help[i++]=arr[p1]<=arr[p2]?arr[p1++] :arr[p2++];
        }
        while (p1<=M){
            help[i++]=arr[p1++];
        }
        while (p2<=R){
            help[i++]=arr[p2++];
        }
        for ( i = 0; i < help.length; i++) {
            arr[i]=help[i];
        }
    }
    //非递归的
    //一次排序是O(N)
    //排了多少次 x 2的x次方=N
    // 也就是排了logN次
    public static void mergeSort2(int []arr){
        //一整块数组需要排序
        //我们以1为起点当merge归并排序的最小半单位
        //如果m>arr的右侧那就不用排了
        //或者这个一半大于N/2
        //不然就接着排序
        //L M R
        //R是m+siz或者N的最小值
        //4 4 4 4 4 2
        //4 4 4 4 2如果只有一组M大于边界 就已经排序完毕了
        //4 3如果右边这次排序的mergesize是>N/2的话，那就不用让mergesize*2了
        if (arr==null||arr.length<2){
            return;
        }
        int N=arr.length;
        int mergeSize=1;
        //当mergesize小于N时候
        while (mergeSize<N){
            int L=0;
            //从1开始慢慢的一次一次排序
            while (L<N){
                int M=L+mergeSize;
                if (M>=N){
                    break;
                }
                int R=Math.min(M+mergeSize,L);
                merge(arr,L,M,R);
                L=R+1;
            }
            //如果max是边界值，mergesize再*2就溢出了
            if (mergeSize>N/2)break;
            mergeSize<<=1;
        }
    }
    //最小和 数组中，求每个数的左边的小值和
    //累计和
    //先干流程再给知识
    //查一个数右边有几个比它大，那就是几个数的小和 这是归并的本质
    //想要求一个数左边的最小和
    public static int sumpocess(int[] arr,int left,int right){
        if (left==right){
            return 0;
        }
        //中点可以是(left+right)/2
        //也就是left +二者差的一半
        //master公式的值是
        //T(N)=2T(N/2)+O(N);
        //logb a=1=d->O(Nd *logN)=O(N*logN)
        int mid=left+((right-left)>>1);
        return  sumpocess(arr,left,mid)
                +
                sumpocess(arr,mid+1,right)
                +
                summerge(arr,left,mid,right);
    }
    //递归的归并排序
    public static int summerge(int[] arr,int L,int M,int R){
        //把两部分归在一起排序
        //就好比打牌，两部分，然后排序 牌
        // 3 2 1  5 2 9
        //3 2    1这两个部分排序
        // 3 2 排序 2 3
        // 2 3 1排序
        //1 2 3
        //最后
        // 1 2 3   2 5 9
        // 1 左2 右2 3 5 9
        int [] help=new int[R-L+1];
        int i=0;
        int p1=L;
        int p2=M+1;
        int res=0;
        while (p1<=M&&p2<=R){
            //左边的数比右边的数小吗？小的话res+ 这个数*右边有几个比这个数大
            //然后这个右组比完了，等下一个右组的比
            //如果相等，先放右边的数，右边指针右移，找比这个数大的数 的数量
            //如果左边数比右边数大，ok，右组指针右移，找比左边数大的数字数量

            res+=arr[p1]<arr[p2]?(R-p2+1)*arr[p1]:0;
            help[i++]=arr[p1]<arr[p2]?arr[p1++] :arr[p2++];
        }
        while (p1<=M){
            help[i++]=arr[p1++];
        }
        while (p2<=R){
            help[i++]=arr[p2++];
        }
        for ( i = 0; i < help.length; i++) {
            arr[i]=help[i];
        }

        return res;
    }
    public int dispairguibing(int [] arr,int L,int R){
        if (L==R){
            return 0;
        }
        //防止溢出
        int mid=L+R>>1+1;
        return
                dispairguibing(arr,L,mid)+
                dispairguibing(arr,mid+1,R)
                +dispairmerge(arr,L,L+R>>1+1,R);
    }
    //求降序对
    public int dispairmerge(int[] arr,int L,int M,int R){
        int p1=L;
        int p2=M+1;
        int [] help=new int[R-L+1];
        int index=0;
        int count=0;
        while (p1<=M&&p2<=R) {
            //右边比左边小的数算的是右边的个数,相等的时候删左边
            count+=arr[p1]>arr[p2]? M-p1+1:0;
            //然后在相等的时候删左边的
            help[index++]=arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
            while (p1<=M){
                help[index++]=arr[p1++];
            }
            while (p2<=R){
                help[index++]=arr[p2++];
            }

        for ( int i = 0; i < help.length; i++) {
            arr[i]=help[i];
        }
        return count;
    }
    //纠结每一个数右边有多少个比你大 小
    //纠结每一个数左边有多少个数比你 小 的时候用归并排序

}
