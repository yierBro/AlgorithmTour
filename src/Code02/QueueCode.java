package Code02;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class QueueCode { //用数组模拟队列 环形数组
    public static class arrayStack{
        //添加的数字位置
        int push=0;
        //推出的数字位置
        int poll=0;
        int size=0;
        //假设可以容纳7个 数字
        int limit=7;
        int [] arr=new int[7];
        //如果符合条件，那就可以让poll和push+1 进行添加或者提取操作
        public int nextIndex(int index){
            //如果这个index在最高位，那么下一次就该是0了
            return index<limit-1 ? index+1:0;
        }
        public boolean isEmpty(){
            return size==0;
        }
        public void push(int value){
            if (size==limit){
                throw new RuntimeException("栈满了，无法加入数据");
            }
            size++;
            arr[push]=value;
            push=nextIndex(push);
        }
        public int pop(){
            if (size==0){
                throw new RuntimeException("栈为空，无法再拿了");
            }
            size--;
            int num=arr[poll];
            poll=nextIndex(poll);
            return num;
        }
        public int findtop(int value){
            if (size==0){
                throw new RuntimeException("栈为空，无数据");
            }
            return arr[poll];
        }
    }
    //可以查询最小数的栈
    public static class minStack{
        Stack<Integer> stack=new Stack<>();
        Stack<Integer> minstack=new Stack<>();
        //查找top
        public int findtop(){
            return stack.peek();
        }
        //查找大小
        public int findSize(){
            return stack.size();
        }
        //添加 添加的时候如果是第一个数，那么两个栈都添加
        //如果是第二个数，看min里面的peek是否<该数，如果不小于，两个都+
        //如果不大于，就加在stack里面
        public void push(int data){
            if (stack.size()==0){
                stack.push(data);
                minstack.push(data);
            }else {
                if (minstack.peek() < data){
                    stack.push(data);
                }else {
                    stack.push(data);
                    minstack.push(data);
                }
            }
        }
        //查找最小
        public int findmin(){
            if (stack.size()==0){
                throw new RuntimeException("无数据");
            }
            return minstack.peek();
        }
        //删除
        public int delete(){
            //Integer是包装类，在比较-128-127时，引用的地址值相同
            //但是在比较超出这个范围的数时候
            //就算值相同，但是地址值不同，二者==的时候，虽然值相同，但是比较的是地址，因此会出错
            //所以需要用equals
            if (stack.peek().equals(minstack.peek())){
                stack.pop();
                return minstack.pop();
            }else return stack.pop();
        }
    }
    //用队列模拟栈
    //数据全存放在一个队列里面，当要查看数据时，所有数据倒入另一个队列，同时剩一个
    //之后如果是要删，就删，同时返回数据
    //如果是查看，那就返回数据同时把这个数据也移动到queue里面

    public static class queueToStack{
        Queue<Integer> queue1=new ArrayDeque<>();
        Queue<Integer> queue2=new ArrayDeque<>();
        //是否为空
        public  Boolean isEmpty(){
            //看二者是否均为空，如果不是，那么就是一个为空一个不为空
            if (queue1.isEmpty()&&queue2.isEmpty()){
                return true;
            }else return false;
        }
        //数量
        public  int Size(){
            //看二者是否均为空，如果不是，那么就是一个为空一个不为空
            if (queue1.isEmpty()&&queue2.isEmpty()){
                return 0;
            }
            if (queue1.isEmpty()){
                return queue2.size();
            }else{ return queue1.size();}
        }
        //添加
        public  void push(Integer data){
            //看二者是否均为空，如果不是，那么就是一个为空一个不为空
            if (queue1.isEmpty()&&queue2.isEmpty()){
                queue1.add(data);
            }
            if (queue1.isEmpty()){
                queue2.add(data);
            }else{ queue1.add(data);}
        }
        //查看
        public  Integer peek(Integer data){
            //看二者是否均为空，如果不是，那么就是一个为空一个不为空
            if (queue1.isEmpty()&&queue2.isEmpty()){
                throw new RuntimeException("没有数据");
            }
            if (queue1.isEmpty()){
                while (queue2.size()!=1){
                    queue1.add(queue2.poll());
                }
                Integer num=queue2.poll();
                queue1.add(num);
                return num;
            }else{
                while (queue1.size()!=1){
                    queue2.add(queue1.poll());
                }
                Integer num=queue1.poll();
                queue2.add(num);
                return num;

            }
        }
        //删除
        public  Integer pop(Integer data){
            //看二者是否均为空，如果不是，那么就是一个为空一个不为空
            if (queue1.isEmpty()&&queue2.isEmpty()){
                throw new RuntimeException("没有数据");
            }
            if (queue1.isEmpty()){
                while (queue2.size()!=1){
                    queue1.add(queue2.poll());
                }
                Integer num=queue2.poll();
                //queue1.add(num);
                return num;
            }else{
                while (queue1.size()!=1){
                    queue2.add(queue1.poll());
                }
                Integer num=queue1.poll();
                //queue2.add(num);
                return num;

            }
        }

    }
}
