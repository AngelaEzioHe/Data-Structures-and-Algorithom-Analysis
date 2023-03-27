package linkedlist;

/**
 * @Author: EzioHe
 * @Date: 2023/3/16 13:16
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法

    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时。temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点内容，双向链表的节点内容修改和单向链表一样，仅仅节点类型改变
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //先定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //链表遍历结束
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据 flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到这个节点
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //从双向链表中删除节点
    /*
        1.对于双向链表，我们可以直接找到要删除的这个节点
        2.找到后，自我删除即可
     */
    public void delete(int no) {
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false; //标志是否找到对待删除节点的
        while (true) {
            if (temp == null) { //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
            //判断flag
            if (flag) { //找到
                //可以删除
                temp.pre.next=temp.next;
                temp.next.pre=temp.pre;
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", no);
            }
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点，默认为null
    public HeroNode2 pre;  //指向前一个节点，默认为null

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {

        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
/*
    构建一个单向的环形链表思路
    1.先构建第一个节点，让first指向该节点，并形成环形
    2.后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可

    遍历环形链表：
    1.先让一个辅助变量指向first节点
    2.然后通过一个while循环遍历该环形链表即可(curBoy.next==first结束)
 */