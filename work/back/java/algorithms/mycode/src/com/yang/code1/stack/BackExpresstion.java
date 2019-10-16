package com.yang.code1.stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/10/10
 */
public class BackExpresstion {

    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        //中缀表达式
        ArrayList<String> mid = getMidExpress(expression);
        System.out.println("mid = " + mid);
        //后缀表达式
        ArrayList<String> back = getBackExpress(mid);
        System.out.println("back = " + back);
        //计算结果
        int result = calculate(back);
        System.out.println("result = " + result);
    }

    private static int calculate(ArrayList<String> back) {

        Stack<String> stack = new Stack<>();
        int front = 0;
        int last = 0;
        int result = 0;
        for (String str : back) {
            //数字进stack
            if(str.matches("\\d")){
                stack.add(str);
            }else{
            // 符号操作
                last = Integer.parseInt(stack.pop());
                front = Integer.parseInt(stack.pop());
                if(str.equals("+")){
                    result  = last + front;
                }else if(str.equals("-")){
                    result  = front - last;
                }else if(str.equals("*")){
                    result  = last * front;
                }else if(str.equals("/")){
                    result  = front / last;
                }
                stack.add(result+"");
            }

        }



        return Integer.parseInt(stack.pop());
    }

    private static ArrayList<String> getBackExpress(ArrayList<String> mid) {
        //两个栈，s1最终，s2放符号
        ArrayList<String> s1 = new ArrayList<>(20);
        Stack<String> s2 = new Stack<>();
        for (String str:mid) {
            //对栈进行操作
            //数值直接放s1
            if(str.matches("\\d+")){
                s1.add(str);
            }else {
            //操作符若s2为空或栈顶为做括号直接进入s2
                if(s2.isEmpty() || s2.peek().equals("(")){
                    s2.add(str);
                }else {
                //若是右括号，弹出s2到左括号
                    if(str.equals(")")){
                        String tmp = "";
                        while (!"(".equals(tmp=s2.pop())){
                            s1.add(tmp);
                        }
                    }else {
                    //否则判断与s2栈顶相对应的优先级，若高于s2栈顶的符号则直接入栈，否则弹出s2栈顶符号到s1，此str入栈
                        if(priority(str) > priority(s2.peek())){
                            s2.add(str);
                        }else {
                            s1.add(s2.pop());
                            s2.add(str);
                        }
                    }


                }

            }

        }

        for (String str: s2) {
            s1.add(str);
        }


        return s1;
    }

    private static int priority(String option){
        if(option.equals("+") || option.equals("-"))
            return 1;
        else if(option.equals("("))
            return 3;
        else
            return 2;
    }

    private static ArrayList<String> getMidExpress(String expression) {
        ArrayList<String> list = new ArrayList<>(20);
        //遍历表达式
        //判断是否继续
        int lenth = expression.length();
        int i = 0;
        char cur = ' ';
        char next = ' ';
        String target ="";
        while(true){
            if(i == lenth)break;
            cur = expression.charAt(i);
            //若是符号则直接进入集合
            if(cur <48 || cur > 57){
                list.add(cur+"");
            }else {
                //若是数字则判断下一个是否为数字
                while(true){
                target+=cur;
                if(i == lenth-1) break;
                next=expression.charAt(i+1);
                if(next < 48 || next > 57){
                    break;
                }
                i++;
                cur = expression.charAt(i);
                }
                list.add(target);
                target="";
            }
            i++;
        }

        return list;
    }

}
