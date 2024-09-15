package com.tuxt.mytest.interview;

public class AddTest {


    public static ListNode add(ListNode p,ListNode q){
        //1-3-5
        //2-4-6
        ListNode s=new ListNode();
        ListNode head =s;
        int t=0;
        while (p!=null && q!=null){
            int a=p.val+q.val+t;
            t=a/10;
            s.next=new ListNode(a%10);
            s=s.next;
            p=p.next;
            q=q.next;
        }
        while (p!=null){
            s.next=new ListNode(p.val);
            s=s.next;
            p=p.next;
        }

        while (q!=null){
            s.next=new ListNode(q.val);
            s=s.next;

            q=q.next;
        }
        return head.next;
    }

    public static ListNode reverse(ListNode head){
        ListNode current=head;
        ListNode prev=null;
        while (current!=null){
            ListNode next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode p=new ListNode(1);
        ListNode p3=new ListNode(3);
        ListNode p5=new ListNode(5);
        p.next=p3;
        p3.next=p5;

        ListNode q=new ListNode(2);
        ListNode q4=new ListNode(4);
//        ListNode q6=new ListNode(6);
        q.next=q4;
//        q4.next=q6;
        ListNode reverseP = reverse(p);
        ListNode reverseQ = reverse(q);
        ListNode reverse = reverse(add(reverseP, reverseQ));
        while (reverse!=null){
            System.out.print(reverse.val);
            reverse=reverse.next;
        }
    }
}
