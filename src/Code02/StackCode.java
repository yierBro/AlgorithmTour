package Code02;

import java.util.Stack;

public class StackCode { public static class arrayStack{
    int index=0;
    int [] arr=new int[7];
    //假设可以容纳7个 数字
    //栈很好模拟从0开始往上加
    //要提取的时候可以通过index看最顶部的值即可
    //添加数据
    public void adddata(int data){
        //这里面index在没数字的时候是0
        //最大为七个数字对应的是7 也就是<7的时候可以添加数
        if (index<arr.length){
            arr[index]=data;
            index++;
        }else {
            System.out.println("数量大于7无法添加");
        }
    }
    //删除数据
    public void deletedata(){
        if (index==0){
            //index为0的时候是没数据的，其余情况是可以删的
            System.out.println("没有数据可以删除");
        }else {
            //直接减index即可
            index--;
        }
    }
    //查看顶部
    public void findtop(){
        if (index==0){
            //index为0的时候是没数据的，其余情况是可以删的
            System.out.println("没有数据可以查看");
        }else {
            //直接减index即可
            System.out.println("顶部为"+arr[index]);
        }
    }
    //查看大小
    public void size(){
        System.out.println("总共有:"+index+"个数");
    }

}
    //用栈模拟队列
    public static class stackToqueue{
        Stack<Integer> pushStack=new Stack();
        Stack<Integer> popStack=new Stack();
        //在查数的时候要把push栈 里的数，全放在pop栈里面，之后pop栈里面的peek就是目标值
        //但是当pop栈里面有数的时候是无法push的，想要push就要把数全倒pop里面
        //同时如果想要往pop里面放东西需要pop栈为空

        //查找
        public Integer FindNum(){
            //如果二者里面都没数据，那就没数据，报错即可
            //如果pop里面有数据那就直接排
            //如果pop里面没了,注意是没了，才能从push里面倒同时要倒完
            if (popStack.empty()&&pushStack.empty()){
                throw  new RuntimeException("数据为空");
            }
            if (!popStack.empty()){
                return popStack.peek();
            }else {
                //pop为空，那就可以倒了，但是要全倒
                pushAllToPop();
                return popStack.peek();
            }
        }
        //增加
        public void addToPop(Integer data){
            pushStack.push(data);
        }
        //排出
        public Integer PopNum(){
            //如果二者里面都没数据，那就没数据，报错即可
            //如果pop里面有数据那就直接排
            //如果pop里面没了,注意是没了，才能从push里面倒同时要倒完
            if (popStack.empty()&&pushStack.empty()){
                throw  new RuntimeException("数据为空");
            }
            if (!popStack.empty()){
                return popStack.pop();
            }else {
                //pop为空，那就可以倒了，但是要全倒
                pushAllToPop();
                return popStack.pop();
            }
        }
        //所有东西导入pop里
        public void pushAllToPop(){
            //如果pop里面为空就执行这个函数
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
    }
}
