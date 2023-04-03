package hashtable;

import java.util.Scanner;

/**
 * @Author: EzioHe
 * @Date: 2023/4/3 20:37
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("exit:退出系统");
            System.out.println("find:查找雇员");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id:");
                    int id = scanner.nextInt();
                    System.out.println("输入名字:");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                case "find":
                    System.out.println("请输入要查找的id：");
                    id=scanner.nextInt();
                    hashTab.findEmpById(id);
            }
        }
    }
}

//创建 HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //编写散列函数，（取模法）
    public int hashFun(int id) {
        return id % size;
    }

    //遍历所有链表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到雇员 id=%d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; //next 默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建 EmpLinkedList，表示链表
class EmpLinkedList {
    //头指针，指向第一个 Emp 节点
    private Emp head;

    //添加雇员
    /*
        说明:
        1.假定，当添加雇员时，id是自增长，即 id 的分配
     */
    public void add(Emp emp) {
        //如果添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) { //说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }
        curEmp.next = emp;
    }

    //遍历雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            System.out.println();
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    //根据id查找雇员(如果找到，返回Emp；如果没有找到，返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp cueEmp = head;
        while (true) {
            if (cueEmp.id == id) {
                break; //这时curEmpty就指向要查找的雇员
            }
            if (cueEmp.next == null) { //遍历链表没有当前雇员
                cueEmp = null;
                break;
            }
            cueEmp = cueEmp.next;
        }
        return cueEmp;
    }
}