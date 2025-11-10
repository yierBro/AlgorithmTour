package Code06;

import java.lang.reflect.Array;
import java.util.Stack;

public class headHuiWenNode {
    public static void main(String[] args) {
        Node node=new Node(1);
        Node node1=new Node(2);
        Node node2=new Node(3);
        Node node3=new Node(2);
        Node node4=new Node(1);
        node.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(isHuiWen(node));
        }
        long l2 =System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(isHuiWen2(node));
        }
        long l3= System.currentTimeMillis();
        //压栈法在时间上略微的慢于指针法
        //但是随着数组的变多，空间的占用也变多了
        //System.out.println(l);
        System.out.println(l2-l);
        System.out.println(l3-l2);

    }
    public static class Node{
        int value;
        Node next;
        public Node(int data){
            this.value=data;
        }
    }
    //12321 abcba
    //是否为回文的链表
    //笔试的时候快点做出来就好
    //你压入栈，然后再从头开始出栈
    //palindrome
    //这个要耗费空间的
    public static boolean isHuiWen(Node head){
        Stack<Node> stack=new Stack<Node>();
        Node cur=head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        cur=head;
        while (!stack.empty()){
            //cur.equals()
            if (stack.pop().value!=cur.value){
                return false;
            }
            cur=cur.next;
        }
        return true;
    }
    //或者你指针走到一半，中点的位置
    //然后创栈

    //一点空间也不用的话是到中点，然后把后边的链表反转
    //再比，再转回来记得
    public static boolean isHuiWen2(Node head){
        if (head==null||head.next==null){
            return true;
        }
        Node slow=head,fast=head;
        while (fast.next!=null &&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        Node pre=slow;
        Node cur=slow.next;
        Node next=null;
        pre.next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        boolean ans=true;
        Node left=head;
        Node right=pre;
        while (left!=null &&right!=null){
            if (left.value!=right.value){
                ans=false;
                break;
            }
            left=left.next;
            right=right.next;
        }
        cur=pre.next;
        pre.next=null;
        while (cur!=null){
            next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }

        return ans;
    }

}
