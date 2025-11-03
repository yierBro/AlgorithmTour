package Code04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSort {
    //0为起点的话父节点是(i-1)/2
    //子节点为2*i+1 2*i+2
    //向0取整 所以我们 index>(index-1)/2
    //判断大根堆是否要移动
    //树的高度是logN级别
    //每一次添加数字 调整的代价是logN

    //pop 提取顶部怎么去调整根堆
    //取最大的子孩子，换
    //再换，直到index<size或者不小于两个孩子了
    //1.取最大的一个孩子
    //2.与跟比较大小 换的话 换完index要*2+1 以判断下一个小堆
    //最多下沉几次？logN次



    public static void main(String[] args) {
        //系统实现的堆
        //整型默认小根堆
        PriorityQueue<Integer> heap=new PriorityQueue<>();
        heap.add(5);
        heap.add(7);
        heap.add(3);
        heap.add(0);
        heap.add(5);
        heap.add(2);
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
        //堆有关的题目
        //第k小  第k大(用比较器试试)
        //k范围内有答案
    }
    public static void heapInsert(ArrayList<Integer> arrayList,int index){
        //父节点是 （i-1）/2
        while (index>0){
            //不断的去找父节点
            if (arrayList.get((index-1)/2)<arrayList.get(index)){
                Integer swap=arrayList.get((index-1)/2);
                arrayList.set((index-1)/2,arrayList.get(index));
                arrayList.set(index,swap);
                index=(index-1)/2;
            }else break;

        }
    }
    //因为呢我们是为了排出数，有的同学问了，为什么不是0而是index
    //你如果学到图论会用到，你就假设index是0，好吧，index是0，然后不断的看这个子节点

    //子节点会怎么怎么样
    public static void heapify(ArrayList<Integer> arrayList,int index,int size){
        int left=index*2+1;
        //int right=left+1;
        //终止条件是什么 要么 该位置以及很大了
        //要么没有子孩子了
        while (left<size){
            //什么时候右孩子最大?
            //有右孩子，同时右孩子比左孩子大
            //查询这三者里面最大的数的下标
            int lagestindex=left+1<size && arrayList.get(left)<arrayList.get(left+1)?
                        left+1:left;
            lagestindex=arrayList.get(index)>arrayList.get(lagestindex)?index:lagestindex;
            if (lagestindex==index){
                break;
            }
            Integer swap=arrayList.get(lagestindex);
            arrayList.set(lagestindex,arrayList.get(index));
            arrayList.set(index,swap);
            index=lagestindex;
            left=index*2+1;
        }
    }
    public static Integer pop(ArrayList<Integer> arrayList){
        Integer pop=arrayList.get(0);
        //把最后一个数换到前面
        Integer swap=arrayList.get(arrayList.size()-1);
        arrayList.set(0,swap);
        arrayList.remove(arrayList.size()-1);
        heapify(arrayList,0,arrayList.size());
        return pop;
    }
    //堆排序是将一个数组排成堆，然后从头开始提取，然后提取n次
    public static void heapSort(ArrayList<Integer> arrayList){
        //这是一个无序的数组，我们可以从0开始到尾部去insert
        for (int i=0;i<arrayList.size();i++){//要计算N次
            heapInsert(arrayList,i);//每次是logN
            //排序成根堆时间复杂度是NlogN
        }
//        for (int i = arrayList.size()-1; i>=0 ; i--) {
//            heapify(arrayList,i,arrayList.size());
//            //如果从底部开始进行排序
//            //最后一排的时间复杂度是O 1
//            //如果你认为最后一排没满，那么倒数第二排右边部分肯定是单个的
//            //这些大概是一半只需要进行一次运算
//            //其余的最大是logN
//            //这个时间复杂度要比刚才的低很多
//        }
        int size=arrayList.size();
        while (size>0){
            //会循环N次 每次的heapify平均是logN
            heapify(arrayList,0,size);
            //然后交换 让最大的数放前面
            Integer i = arrayList.get(size);
            arrayList.set(0,arrayList.get(size));
            arrayList.set(size,i);
            size--;
        }
        //如果不修改算法，那么上面的总时间复杂度为2NlogN
    }
    public static Integer Kmaxormin(PriorityQueue<Integer> queue,int k){
        //第几个大的就弹几次即可
        Integer result=-1;
        while (k>0){
            result=queue.poll();
            k--;
        }
        return result;
    }
    public static class comparatora implements java.util.Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    }
    public static void kSort(int []arr,int k){
        //我们把它变成大根堆
        //可以看比较器那章节
        PriorityQueue priorityQueue=new PriorityQueue<>(new comparatora());
        //如果长度小于k 那么直接全放大根堆里面，然后提取出来即可
        if (arr.length<k){
            for (int i = 0; i < arr.length; i++) {
                priorityQueue.add(arr[i]);
            }
            for (int i = 0; i < arr.length; i++) {
                while (!priorityQueue.isEmpty()){
                    arr[i]=(int)priorityQueue.poll();
                }
            }
        }

        for (int i = 0; i < k; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            arr[i-k]=(int)priorityQueue.poll();
            priorityQueue.add(arr[i]);
        }
        for (int i = arr.length-k; i < arr.length; i++) {
            arr[i]=(int) priorityQueue.poll();
        }


    }


}
