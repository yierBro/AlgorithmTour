package Code01;

public class YiHuo {
    public static void main(String[] args) {
//        int[] arr={1,1};
//        arr[0]=arr[0]^arr[1];
//        arr[1]=arr[0]^arr[1];
//        arr[0]=arr[0]^arr[1];
//        System.out.println(arr[0]+"+"+arr[1]);
    }
    //异或交换
    public static void exchange(int a,int b){
        System.out.println("交换前：a:"+a+" b:"+b);
        //a^b
        a=a^b;
        //b=a^b ^b=a^0=a
        b=a^b;
        //a=a^b ^a=b^0=b
        a=a^b;
        System.out.println("交换后：a:"+a+" b:"+b);
        //如果交换的两个数是同一个位置的数，那么该交换失效
        //常见于数组中同一个下标以及定义a=1，让a与a进行交换
        //我们进行刚才的流程 第一步就终止了 a=a^a=0 之后再进行a^a还是0
        //根本换不了，这个异或交换是省空间，但是没必要，用temp也是可以的，虽然费空间
        //但是现在不缺那一点空间，我们需要知道，但是我们不一定要用到这个
        //我们只需要在别人问的时候会即可
    }
    //拿一个最小点，然后一直选最小的
    public static int count(int n){
        int count=0;
        //当n不是0000..0000时,肯定有位为1
        while(n!=0){
            //算出最右边的1所在的位置
            int rightone=n & ((~n)+1);
            count++;
            //n与该数进行异或，除去这个1部分，其它部分原来是1的依然是1，0依然是0
            //而1这里与rightone的1异或后会变成0
            //n少了一个1，之后继续判断是否成立
            //有人会想用减法，但是这时候你看-2 -(-1） 我们本来是想让数变小的，但是这样会适得其反
            //这时候用异或运算计算即可，不要过多的考虑
            n=n^rightone;
        }
        return count;
    }
    public static int rightone(int n){
        //n是00011000.1000
        //我们取一下反 11100111.0111这时候二者如果& 结果将会是0
        //但是如果取反后+1,那么就11100111.1000 再与它与你会发现
        //结果是我们想要的目标位1
        int rightone=n & ((~n)+1);
        //题目传进来的数需要是右侧有一个1，如果传进来0就没意思了,结果依然是0
        return rightone;

    }
    public static void oneJishu(int[] arr){
        //我们先把所有的数异或，设置目标数为x y
        //那么下面for循环出的num=x^y
        int num=0;
        for (int i=0;i<arr.length;i++){
            num^=arr[i];
            //num=x^y
        }
        //我们知道xy两个数是不一样的，那么二者的二进制肯定有一位是一个为0一个为1
        //二者异或后，二进制为1的部分为二者一个1 一个0的部分，最少是有一个的，我们找到最右边的一
        int rightone = num & ((~num) + 1);
        //所有的数x在这一位是1，y在这一位是0，其余的数在这一位要么为0要么为1
        //不管结果怎样，都是偶数，异或后均会为0只剩x，y
        //我们只需要经过rightone筛选出一种，得出x，另一个y就好得出了
        //先得出一种
        int x=0;
        for (int i=0;i<arr.length;i++){
            //看arr[i]这个地方的值是否在rightone位上有1
            //结果为00001...000 或者00000...000
            if ( (rightone & (arr[i]&((~arr[i])+1))) !=0 ){
                x=x^arr[i];
            }
        }
        //num是 x^y 而x的值我们已经得出，那么y怎么求？
        //请回顾异或位的加法，就会得出结论了
        //y=x^y ^x =y^x^x=y^0=y
        int y=num^x;
        System.out.println(x+"  "+y);
    }
}
