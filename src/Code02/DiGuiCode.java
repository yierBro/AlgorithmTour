package Code02;

public class DiGuiCode {
    public static int pocess(int[] arr,int left,int right){
        if (left==right){
            return arr[left];
        }
        //中点可以是(left+right)/2
        //也就是left +二者差的一半
        int mid=left+((right-left)>>1);
        int leftnum=pocess(arr,left,mid);
        int rightnum=pocess(arr,mid+1,right);
        return Math.max(leftnum,rightnum);
    }
}
