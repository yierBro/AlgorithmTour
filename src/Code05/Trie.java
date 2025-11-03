package Code05;

import Code02.NodeCode;
import sun.util.resources.cldr.rof.CurrencyNames_rof;

import java.util.HashMap;

public class Trie {

    //26个字母的
    public static class Node1{
        public int pass;
        public int end;
        public Node1[] nexts;
        public Node1(){
            pass=0;
            end=0;
            //0 a
            //1 b
            //2 c
            //next[i]==null i的路不存在
            //next[i]！=null i的路存在
            nexts=new Node1[26];
        }
    }
    public static  class Trie1{
        private Node1 root;
        public Trie1(){
            root=new Node1();
        }
        //插入
        public void insert(String word){
            if (word==null){return;}
            char[] str=word.toCharArray();
            Node1 node=root;
            node.pass++;
            int path=0;
            //从头节点开始，一直放数，放不了了，把node的end++
            for (int i = 0; i < str.length; i++) {
                path=str[i]-'a';
                if (node.nexts[path]==null){
                    node.nexts[path]=new Node1();
                }
                node=node.nexts[path];
                node.pass++;
                //会有环吗？
                //不会。a-d是往后连的，node是不断重置的
                //那环什么时候形成？
                //共享内存，1.7的hashmap 方法区是共享内存的
            }
            node.end++;
        }
        //删除word这个单词 一次
        //去定位是否有word这个单词
        //如果说我们不检查，就是直接删，从头开始删
        //删到后面发现，没有这个数字，咋办?需要roolback
        //其实delete前 search一下就好了
        //如果说p--后 p=0 我们可以干嘛？把该节点置为null
        public void delete(String word){
            //search会帮我们判断word是否为null
            //来一个node 去遍历root 是否含word
            int search = search(word);
            if (search==0) return;
            //word拆开，遍历一条线
            char[] str = word.toCharArray();
            //选哪条路
            int index=0;
            //复制root地址
            Node1 node1=root;
            for (int i = 0; i < str.length; i++) {
                index=str[i]-'a';
                //下面可以省空间
                //比如说有一条线后半部分都是p=0 e=0 是不是占用空间了
                //让p=0的时候直接null即可
                //jvm对没有根节点指向的数据进行自动回收
                //c++需要在设置为null后
                //把后面的东西进行null判定
                if (--node1.nexts[index].pass==0){
                    node1.nexts[index]=null;
                    return;
                }
                //下面这个换为上面是可以减少空间的一个步骤
                //node1.nexts[index].pass--;
                node1=node1.nexts[index];
            }
            //node走到最后了 这时候node 的end--
            //当然了，是指后面还有其它的字符
            node1.end--;

        }
        //查找word这个单词加入过几次
        public int search(String word){
            char[] str=word.toCharArray();
            int path=0;
            Node1 node1=root;
            for (int i = 0; i < str.length; i++) {
                path=str[i]-'a';
                if (node1.nexts[path]==null){
                    return 0;
                }
                node1=node1.nexts[path];
            }
            //为什么是end？ 如果是pass？
            //pass是经过有几个 end是在这里终止
            //在这里终止的是word本身
            //pass过的是前缀
            return node1.end;
        }
        //查找以word为前缀的单词有几个
        public int searchPrex(String word){
            char[] str=word.toCharArray();
            int path=0;
            Node1 node1=root;
            for (int i = 0; i < str.length; i++) {
                path=str[i]-'a';
                if (node1.nexts[path]==null)return 0;
                node1=node1.nexts[path];
            }
            return node1.pass;
        }
    }



    //字符串可能不止26个字母 我们以哈希做存储
    public static class Node2{
        int pass;
        int end;
        HashMap<Integer,Node2> nexts;
        public Node2(){
            pass=0;
            end=0;
            nexts=new HashMap<>();
        }
    }
    //前缀树的实现
    public static class Trie2{
        Node2 root;
        public Trie2(){
            root=new Node2();
        }
        //添加一个字符串
        public void insert(String string){
            Integer index=0;
            char[] str = string.toCharArray();
            Node2 node2=root;
            //在开始的时候加一下pass
            node2.pass++;
            for (int i = 0; i < str.length; i++) {
                index=(int) str[i];
                if (!node2.nexts.containsKey(index)){
                    node2.nexts.put(index,new Node2());
                }
                node2=node2.nexts.get(index);
                node2.pass++;
            }
            node2.end++;
        }

        //查看一个字符串有几个
        public int search(String string){
            //查找字符串 从头节点开始
            //如果下一个字符所对应的node为null return0
            //最后返回pass
            int index=0;
            char[] str = string.toCharArray();
            Node2 node2=root;
            for (int i = 0; i < str.length; i++) {
                index=str[i];
                    if (node2.nexts.get(index)==null){
                        return 0;
                    }
                    node2=node2.nexts.get(index);
            }
            return node2.end;
        }
        //查看前缀有几个
        public int searchPrex(String string){
            //从头结点开始查下一个有的字符
            //如果没查到头就没了，那就是0
            //如果能查到头那就返回end
            int index=0;
            char[] str = string.toCharArray();
            Node2 node2=root;
            for (int i = 0; i < str.length; i++) {
                index=str[i];
                if (node2.nexts.get(index)==null){
                    return 0;
                }
                node2=node2.nexts.get(index);
            }
            return node2.pass;
        }
        //删除一个字符串
        public void delete(String string){
            //提前进行查找是否存在，存在再删
            //不然需要返回操作，很麻烦，不容易实现
            if (search(string)==0)return;

            //存在 从根节点开始，每一个节点的pass都要--
            //最后一个节点的pass以及end都要-- -1噢，是减一个就ok了
            //同时如果--后0 那么这一条路都可以不要了，可以画图嘛
            int index=0;
            char[] str = string.toCharArray();
            Node2 node2=root;
            //根节点的--
            node2.pass--;
            for (int i = 0; i < str.length; i++) {
                index=(int)str[i];
                if (--node2.nexts.get(index).pass==0){
                    node2.nexts.remove(index);
                    return;
                }
                node2=node2.nexts.get(index);
            }
            node2.end--;
        }


    }

    //测试
    public static void main(String[] args) {
        Trie1 trie1=new Trie1();
        Trie2 trie2=new Trie2();
        //添加 abcd abcdd abcdd abcdd abcde abcdf
        String[] strings=new String[6];
        strings[0]="abcd";
        strings[1]="abcdd";
        strings[2]="abcdd";
        strings[3]="abcdd";
        strings[4]="abcde";
        strings[5]="abcdf";
        for (int i = 0; i < strings.length; i++) {
            trie1.insert(strings[i]);
            trie2.insert(strings[i]);
        }

        System.out.println( "t1的abcdd个数查询"+trie1.search("abcdd"));
        System.out.println( "t2的abcdd个数查询"+trie2.search("abcdd"));
        System.out.println( "t2的前缀abcd查询"+trie2.searchPrex("abcd"));
        System.out.println( "t2的前缀abcd查询"+trie2.searchPrex("abcd"));

    }
}
