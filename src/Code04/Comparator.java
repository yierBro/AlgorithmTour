package Code04;

import java.util.Arrays;

public class Comparator {
    public static class IdComparator implements java.util.Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            //我想升序
            //返回0的时候二者一样 那么我们就可以统一为下面的return
            //            if (o1.getId()<o2.getId())return -1;
            //            else if (o1.getId()>o2.getId())return 1;
            //            else return 0;
            return o1.getId()-o2.getId();
            //而这年龄谁小谁考前 一样的话id升序
            //return o1.getAge()!=o2.getAge()?
            //        o1.getAge()-o2.getAge():o1.getId()-o2.getId();
        }
    }

    public static void main(String[] args) {
        Student student1=new Student();
        Student student2=new Student();
        Student student3=new Student();
        student1.setId(1);
        student2.setId(5);
        student3.setId(3);

        Student[] students=new Student[]{student1,student2,student3};
        System.out.println("排序前：");
        for (int i = 0; i <students.length ; i++) {
            System.out.println(students[i]);
        }

        Arrays.sort(students,new IdComparator());
        System.out.println("排序后：");
        for (int i = 0; i <students.length ; i++) {
            System.out.println(students[i]);
        }
    }
}
