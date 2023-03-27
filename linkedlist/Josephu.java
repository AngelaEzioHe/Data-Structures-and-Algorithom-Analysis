package linkedlist;

/**
 * @Author: EzioHe
 * @Date: 2023/3/26 22:28
 */
public class Josephu {
    public static void main(String[] args) {
        //测试看看构建环形链表，和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(125); //加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1,2,125);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建一个环形链表
    public void addBoy(int nums) {
        //nums 做一个数据校验
        if (nums < 2) { //至少2个小孩儿
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建环形链表
        //使用for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); //构成环状
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        //因为first不能动，因此我们用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); //让curBoy后移
        }
    }

    /*
    根据用户的输入，生成一个小孩出圈的顺序
    n=5,即有5个人
    k=1,从第一个人开始报数
    m=2,数两下

    1.创建一个辅助指针helper，事先应该指向环形链表的最后节点
    2.小孩报数前，先让first和helper移动k-1次
    3.当小孩报数时，让first和helper指针同时移动 m-1 次,然后出圈
    4.将first指向的小孩节点出圈
    first=first.next
    helper.next=first
    原来first指向的节点就没有任何引用，就会被回收
    */

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最开始有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建辅助指针，帮助小孩出圈
        Boy helper = first;
        //创建一个辅助指针helper，事先应该指向环形链表的最后节点
        while (true) {
            if (helper.getNext() == first) { //说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动 m-1 次,然后出圈
        //是一个循环操作，直到圈中只有一个节点
        while (true){
            if(helper==first){ //此时圈中只有一人
                break;
            }
            //让first和helper指针同时移动 countNum-1,出圈
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n",first.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy {
    private int no;
    private Boy next; //指向下一个节点，默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
