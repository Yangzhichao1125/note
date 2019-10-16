package com.yang.code1.stack;

/**
 * 数据结构与算法之中缀表达式
 *
 * @author yang
 * @date 2019/09/25
 */
public class MidCalculator {

    public static void main(String[] args) {
        String str = "3-5-8";
        ArrStack numStack = new ArrStack(20);
        ArrStack signStack = new ArrStack(10);

        int curIndex = 0;
        String keepNum = "";
        char curChar ;

        while(true){

            curChar = str.substring(curIndex, curIndex + 1).charAt(0);
            //判断数值还是操作符
            if(isOper(curChar)){
                //栈为空的情况下直接加
                if (signStack.isEmpty()) {
                    signStack.push(curChar);
                }else {
                //优先级后面的小于等于前面则出栈操作否则直接入栈
                if(priority(curChar) <= priority(signStack.peek())){
                    int signPop = signStack.pop();
                    int next = numStack.pop();
                    int pre = numStack.pop();
                    int result = calculate(next, pre, signPop);
                    numStack.push(result);
                    signStack.push(curChar);
                }else{
                    signStack.push(curChar);
                }
                }
            }else{
                keepNum += curChar;
                //若是最后一个直接入栈
                if(curIndex == str.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                //下一个若非操作符则继续加数字长度
                    char next = str.substring(curIndex + 1, curIndex + 2).charAt(0);
                    if (isOper(next)) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum ="";
                    }
                }
            }
            curIndex ++;
            if(curIndex == str.length())break;
        }
        while(true){
            if(signStack.isEmpty())break;
            int sign = signStack.pop();
            int next = numStack.pop();
            int pre = numStack.pop();
            int calculate = calculate(next, pre, sign);
            numStack.push(calculate);
        }
        System.out.println(numStack.arr[0]);


    }
    public static boolean isOper(int val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级越高
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前的表达式只有+，-，*，/
        }
    }

    public static int calculate(int next,int pre,int oper){
        int result = 0;
        switch (oper){
            case '+':
                result = pre+next;
                break;
            case '-':
                result = pre-next;
                break;
            case '*':
                result = pre*+next;
                break;
            case '/':
                result = pre/next;
                break;
            default:
                break;
        }
        return result;
    }
}

class ArrStack{
    int maxSize;
    int [] arr;
    int top =-1;

    public ArrStack(int maxSize){
        this.maxSize=maxSize;
        arr = new int [maxSize];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return maxSize == top +1;
    }

    public void push(int num){
        arr[++top] = num;
    }

    public int pop(){
        int value = arr[top];
        top--;
        return value;
    }



    //显示栈的情况【遍历栈】,遍历时需要从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, arr[i]);
        }
    }

    public int peek(){
        return arr[top];
    }







}
