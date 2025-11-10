package Code06;

import java.util.ArrayList;

public class QuickOrSlowNode {

    public static class Node{
        public int value;
        public Node next;
        public Node(int v){
            value=v;
        }
    }
    //奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMidNode(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return head;
        }
        Node slow=head.next;
        Node fast=head.next.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;

    }
    public static Node right1(Node head){
        if (head==null){
            return null;
        }
        Node cur=head;
        ArrayList<Node> arr=new ArrayList<>();
        while (cur!=null){
            arr.add(cur);
            cur=cur.next;
        }
        return arr.get((arr.size()-1)/2);
    }
    //奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMidNode(Node node){
        if (node==null||node.next==null){
            return node;
        }
        Node slow=node.next;
        Node fast=node.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public static Node right2(Node head){
        if (head==null){
            return null;
        }
        Node cur=head;
        ArrayList<Node> arr=new ArrayList<>();
        while (cur!=null){
            arr.add(cur);
            cur=cur.next;
        }
        return arr.get(arr.size()/2);
    }

    //奇数长度返回中点前一个，偶数长度返回上中点前一个1
    public static Node midOrUpMidPreNode(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return null;
        }
        Node slow=head;
        Node fast=head.next.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public static Node right3(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return null;
        }
        Node cur=head;
        ArrayList<Node> arr=new ArrayList<>();
        while (cur!=null){
            arr.add(cur);
            cur=cur.next;
        }
        return arr.get((arr.size()-3)/2);
    }
    //奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node midOrDownMidPreNode(Node head){
        if (head==null||head.next==null){
            return null;
        }
        Node slow=head;
        Node fast=head.next;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public static Node right4(Node head){
        if (head==null||head.next==null||head.next.next==null){
            return null;
        }
        Node cur=head;
        ArrayList<Node> arr=new ArrayList<>();
        while (cur!=null){
            arr.add(cur);
            cur=cur.next;
        }
        return arr.get((arr.size()-2)/2);
    }





}
