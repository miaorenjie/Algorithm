package acm;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Main {


    public static void println(Object s){
        System.out.println(s);
    }
    public static void print(Object s){
        System.out.print(s);
    }
    public static String aaa(String a)
    {
        a=a.replace("q","a");
        return a;
    }
     public static class ListNode {
            int val;
            ListNode next = null;
            ListNode(int val) {
            this.val = val;
         }
     }

    /*
    输入一个链表，从尾到头打印链表每个节点的值。
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer>arrayList=new ArrayList<>();
        Stack<Integer>stack=new Stack<>();
        while (listNode.next!=null)
        {
            stack.push(listNode.val);
            listNode=listNode.next;
        }
        while (!stack.isEmpty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

    /*
    请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.
    则经过替换之后的字符串为We%20Are%20Happy
     */
    public static String replaceSpace(StringBuffer str) {
        for (int i=0;i<str.length();i++)
        {
            if (str.charAt(i)==' ')
            {
                str.delete(i,i+1);
                str.insert(i,"%20");
                print(str.charAt(i));
            }
        }
        return str.toString();
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     * @param
     */

    static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root=reConstructBinaryTree(pre, in,0,pre.length-1,0,in.length-1);
        return root;
    }
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in, int pStart, int pEnd, int iStart, int iEnd) {
        if(pStart>pEnd||iStart>iEnd)
            return null;
        TreeNode root;
        root=new TreeNode(pre[pStart]);
//        print(root.val+"--");
        int index = 0;
//        System.out.println(pStart+"---"+pEnd+"------"+iStart+"--------"+iEnd);
        for(int i=pStart,j=iStart;i<=pEnd;i++,j++){
            if (pre[pStart]==in[j]){
//                System.out.println(i+"---"+j);
//                if((pStart+(j-iStart)+1)==3)
//                    print("asd");
                root.left=reConstructBinaryTree(pre, in,pStart+1,pStart+(j-iStart),iStart,j-1);
                root.right=reConstructBinaryTree(pre,in, pStart+(j-iStart)+1,pEnd,j+1,iEnd);
                break;
            }
        }

        return root;
    }
    private static void travelTree(TreeNode root){
        if (root==null)
            return;
        if(root.val==4){
            print("asd"+root.right);
        }
        print(root.val+"  ");
        travelTree(root.left);
        travelTree(root.right);
    }

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(!stack2.isEmpty())
            stack1.push(stack2.pop());
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()&&!stack1.isEmpty()){
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
            return stack2.pop();
        }
        if (!stack2.isEmpty()&&stack1.isEmpty())
            return stack2.pop();
        return Integer.parseInt(null);
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int [] array) {
        int pre=0,cur=1;
        if (array.length==0)
            return 0;
        if (array.length==1)
            return array[0];
//        if (array[pre]<array[cur])
//            return array[pre];
        for(int i=0;i<array.length-1;i++,pre++,cur++){
            if(array[pre]>array[cur])
                return array[cur];
        }
        return 0;
    }

    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
     n<=39
     * @param n
     * @return
     */
    public int Fibonacci(int n) {
        int pre=1,next=1,sum=0;
        if (n==0)
            return 0;
        if(n<3)
            return 1;
        for (int i=1;i<n;i++)
        {
            sum=pre+next;
            pre=next;
            next=sum;
        }
        return next;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target
     * @return
     * 本质斐波那契
     */

    public int JumpFloor(int target) {//此题为规律求解，f(n)=f(n-1)+f(n-2)本质斐波那契
        if (target==1)
            return 1;
        if (target==2)
            return 2;
        return JumpFloor(target-1)+JumpFloor(target-2);
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     * @param target
     * @return
     */
    public int RectCover(int target) {//此题为规律求解，f(n)=f(n-1)+f(n-2)本质斐波那契
        if (target==0)
            return 0;
        if (target==1)
            return 1;
        if (target==2)
            return 2;
        return RectCover(target-1)+RectCover(target-2);
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * @param target
     * @return
     */

    public int JumpFloorII(int target) {//动态规划法
        if (target==1)
            return 1;
        if (target==2)
            return 2;
        return 2*JumpFloorII(target-1);

    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * @param n
     * @return
     */
    public static int NumberOf1(int n) {
        int count=0;
        if(n<0){
            n=n&0x7fffffff;//7的二进制是0111，f的二进制是1111
            ++count;
        }
        while (n>0)
        {
            if((n&1)==1)
                count++;
            n>>>=1;
        }
        return count;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * @param base
     * @param exponent
     * @return
     */

    public double Power(double base, int exponent) {
        double mBase=base;
        if(exponent==0)
            return 1;
        while (exponent>1){
            mBase*=base;
            exponent--;
        }
        if (exponent<0)
        {
            while (exponent<-1){
                mBase*=base;
                exponent++;
            }
            return 1/mBase;
        }
        return mBase;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * @param array
     */
    public void reOrderArray(int [] array) {
        int temp;
        int evenNumberCount=0;
        for (int i = 0; i < array.length; i++) {
            if(array[i]%2==0){
                evenNumberCount++;
            }else {
                moveArray(array,i-evenNumberCount,evenNumberCount);
            }
        }
    }
    public void moveArray(int []array,int start,int count){
        int targetNumber=array[start+count];
        for(int i=start+count;i>start;i--){
            array[i]=array[i-1];
        }
        array[start]=targetNumber;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode temp=head;
        int count=0;
        while (temp!=null){
            temp=temp.next;
            count++;
        }
        for(int i=0;i<count-k;i++){
            head=head.next;
        }
        if (k>count)
            return null;
        return head;
    }

    /**
     * 输入一个链表，反转链表后，输出链表的所有元素。
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        ListNode pre=head;
        if (head==null){
            return null;
        }
        ListNode cur=head.next;

        if (cur==null)
            return head;
        ListNode newHead=null;
        while (cur!=null){
            ListNode next=cur.next;
            if(next==null)
                newHead=cur;
            cur.next=pre;
            if(pre==head)
                pre.next=null;
            pre=cur;
            cur=next;
        }
        return newHead;
    }
    private static ListNode createList(int []array){
        ListNode root=new ListNode(array[0]);
        ListNode temp=root;
        for(int i=1;i<array.length;i++){
            ListNode node=new ListNode(array[i]);
            temp.next=node;
            temp=node;
        }
        return root;
    }
    private static void traverseList(ListNode head){
        while (head!=null){
            print(head.val+" ");
            head=head.next;
        }
    }

    public static void main(String[] args) {
        int []array={1};
        ListNode head=createList(array);
        head=ReverseList(head);
        traverseList(head);
    }

}
