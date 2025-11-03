package Code02;

import java.util.OptionalInt;
import java.util.Stack;

public class NodeCode {
    //链表
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value=data;
        }
    }
    //反转链表
    public static Node reverseLinkedList(Node head){

        //1->2->3->4->5->6
        //简化的说
        //  pre  head
        //  pre->1    2 3 4 5 6

        //       pre head
        //要变成<-1    2 3 4 5 6 hread
        //null<-1
        //null<-1<-2  <-3...
        //从头开始 这个1与2先断，
        Node pre=null;
        Node next=null;
        while (head!=null){
            //1.保存一下head的next
            //2.head的next应该变成pre
            //3.pre和head 先后换新值
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }
    //删除链表中某个值
    public static Node removeValue(Node head,int num){
        while (head!=null){
            if (num!= head.value){
                break;
            }
            head=head.next;
        }
        //head就是要返回的head了，但是后面的相关value还没有处理
        Node pre=head;
        Node cur=head;
        //从头开始
        //1->2->3->4->5
        //如果符合，那么pre=cur cur=cur.next
        //如果不符合，那么pre.next=cur.next,cur=cur.next
        while (cur!=null){
            if (cur.value==num){
                pre.next=cur.next;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }
        return head;
    }
    //双向链表
    public static class DoubleNode{
        public int value;

        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            value=data;
        }
    }
    //反转双向链表
    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre=null;
        DoubleNode next=null;

        while (head!=null){
            // pre  head
            // null-  1-2-3-4
            //要变成 pre head
            //保存head的next head的前后换一下，pre换成head head换成next
            next=head.next;
            head.last=pre;
            head.next=next;
            pre=head;
            head=next;

        }
        return pre;
    }
    //栈 队列 把双向链表复写左进出 右进出，封掉API即可
}
