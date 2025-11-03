package Code02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class HashCode {
    public static void main(String[] args) {
        HashMap<Integer,String> map=new HashMap<>();
        HashMap<Integer,String> map2=new HashMap<>();
        map2.put(1,"我是3");
        map.put(1,"我是1");
        //Java里面
        //int double float是基础数据类型
        //Integer Double Float是包装数据类型

        //HashMap里面Integer是基础数据类型，同时不能用int这种
        //自定义类是包装数据类型
        //Integer自己是-128-127按址，但是hash里面都按值传递
        System.out.println(map.get(1)+" "+map2.get(1));
        map.put(2,"我是2");
        map.put(3,"我是3");
        map.put(4,"我是4");
        map.put(5,"我是5");
        map.put(6,"我是6");
        System.out.println(map.containsKey(1));
        System.out.println(map.containsKey(10));
        System.out.println(map.get(4));
        //没有记录的时候会返回空
        System.out.println(map.get(10));
        //没有key是创建 有是更新
        map.put(4,"他是4");

        map.remove(4);
        System.out.println(map.get(4));


        //只存key
        //哈希表的增删改查 使用时均是，O(1)
        //同时哈希表和有序表是一样的底层实现
        HashSet<String> set=new HashSet<>();
        set.add("abc");
        set.contains("abc");
        set.remove("abc");

        //有序表的每个操作的时间复杂度是O(logN)

        TreeMap<Integer,String> treeMap=new TreeMap<>();
    }
}
