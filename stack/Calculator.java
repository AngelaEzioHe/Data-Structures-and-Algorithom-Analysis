/*
    使用栈完成表达式的计算思路
    1.通过一个index值（索引），来遍历表达式
    2.如果是数字，h直接进数栈
    3.如果是符号，分如下情况：
        (1)如果当前符号栈为空，慧直接入栈
        (2)如果符号栈有操作符，先进行比较优先级：
            ①如果当前操作符的优先级小于或者等于栈中的操作符，
              需要从栈中pop出两个数，从符号栈中pop出一个符号，
              进行运算，得到的结果入数栈、将当前操作符入符号栈；
            ②如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
    4.当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
    5.最后数栈只有一个数字，就是表达式的结果
 */
package stack;

/**
 * @Author: EzioHe
 * @Date: 2023/3/28 21:03
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "70*2*2-5+1-5+3-4";

        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量(无需初始化，默认为0）
        int index = 0; //用于扫描
        int num1;
        int num2;
        int oper;
        int res;
        char ch = ' '; //将每次扫描得到的符号保存到ch
        String keepNum = ""; //用于拼接多位数
        //开始while循环地扫描expression
        while (true) {
            //依次得到 expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断 ch 是什么，然后做相应地处理
            if (operStack.isOperator(ch)) { //如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) { //如果不为空
                    //如果当前操作符的优先级小于或者等于栈中的操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //运算结果入数栈
                        numStack.push(res);
                        //当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前操作符的优先级大于栈中的操作符，直接入符号栈
                        operStack.push(ch);
                    }
                } else { //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            } else { //如果是数，则直接入数栈

                //numStack.push(ch-48); //'1'对应的ASCA码是49，应转换
                //分析思路
                //1.当处理多位数时，不能发现一个数就立即入栈，因为它可能是多位数
                //2.在处理数，需要向 expression 的表达式的 index 后再看一位，如果是数就进行扫描；如果是符号才入符号栈
                //3.因此我们需要定义一个字符串变量吗，用于拼接

                //处理多位数
                keepNum += ch;

                //如果 ch 已经是 expression 的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，则继续扫描；如果是运算符，则入栈
                    if (operStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //把 keepNum 清空
                        keepNum = "";
                    }
                }

            }
            //让 index+1 ，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); //入栈
        }

        //将最后数栈的数pop出，就是结果
        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }
}

//先创建一个栈，用之前创建好的,（但是需要扩展功能）
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack; //数组模拟栈，数据存放在该数组
    private int top = -1; //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回当前栈顶的值，但是不出栈
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断栈空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
            //不需要有return
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况（遍历栈）,遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~");
            System.out.println("------------");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员定的，优先级使用数字表示。数字越大，优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1; //假定目前的表达式只有 + , - , * , /
        }
    }

    //判断是不是一个运算符
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; //存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '、':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}