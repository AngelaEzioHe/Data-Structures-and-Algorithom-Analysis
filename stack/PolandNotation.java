package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: EzioHe
 * @Date: 2023/3/29 14:09
 */
public class PolandNotation {
    public static void main(String[] args) {
        /*
            将一个中缀表达式转成后缀表达式
            1.1+((2+3)*4)-5 -> 1 2 3 + 4 * + 5 -
            2.因为直接对字符串操作不方便。先将 "1+((2+3)*4)-5" -> 中缀的表达式对应的List
                即： "1+((2+3)*4)-5" -> [1,+,(,(,2,+,3,),*,4,),-,5]
            3.将得到的中缀表达式对应的List -> 后缀表达式对应的List
              [1,+,(,(,2,+,3,),*,4,),-,5] -> [1,2,3,+,4,*,+,5,-]
         */
        String expression = "1+((2+3)*4)-5";
        List<String> toInfixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List" + toInfixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(toInfixExpressionList);
        System.out.println("后缀表达式对应的List"+suffixExpressionList);
        System.out.printf("expression=%d",calculate(suffixExpressionList));
        //先定义一个逆波兰表达式
        //(3+4)*5-6 -> 3 4 + 5 * 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "3 4 + 5 * 6 -";
//
//        /*
//            思路：
//            1.先将 "3 4 + 5 * 6 -" 装入 ArrayList 中
//            2.将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈完成计算
//         */
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList=" + rpnList);
//        int res = calculate(rpnList);
//        System.out.println("计算的结果是 " + res);
    }

    //将得到的中缀表达式对应的List -> 后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //因为 s2 这个栈，在整个转换过程中，没有pop操作，而且后面需要逆序输出
        //比较麻烦，这里我们不用 Stack<String> 直接用 List<String> s2
        List<String> s2 = new ArrayList<String>(); //存储中间结果的栈

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入到 s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号")",则依次弹出 s1 栈顶的运算符，并压入 s2,直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将 ( 弹出 s1 栈，消除小括号
            } else {
                //当 item 的优先级 <= 栈顶运算符优先级
                //缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将 item 压入栈
                s1.push(item);
            }
        }

        //将 s1 中剩余的运算符依次弹出并加入 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //因为是存放到List，因此按顺序输出就是对应的后缀表达式对应的List
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //先定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //相当于一个指针，用于遍历中缀表达式字符串
        String str; //对多位数的拼接工作
        char c; //每遍历到一个字符，就放入到c
        do {
            //如果 c 是一个非数字，就需要加入到 ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else { //如果是一个数，需要考虑多位数的问题
                str = ""; //将 str 制成空串
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到 ArrayList 中
    public static List<String> getListString(String suffixExpression) {
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建给栈，只需一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历 ls
        for (String item : ls) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) { //代表匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把 res 入栈
                stack.push(res + ""); //整数转成字符串
            }
        }
        //最后留在 stack 中的数据是结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类，可以返回一个对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}