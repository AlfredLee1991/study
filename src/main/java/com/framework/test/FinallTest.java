package com.framework.test;

class Student{

    int a = 1;

    @Override
    public String toString() {
        return "Student [a=" + a + "]";
    }

    public Student(int a) {
        this.a = a;
    }

}

public class FinallTest{
    private int a = 1;
    private Integer b = 200;
    private Double d = 20.2;
    private Boolean bool = true;
    private Student s = new Student(1);

    // 传入的是:基本类型
    public void show(int n) {
        System.out.println("基本类型修改前:" + n);
        n = 3;
        System.out.println("基本类型修改后:" + n);
    }

    // 传入的是:包装类Integer
    public void show(Integer n) {
        System.out.println("包装类修改前:" + n);
        n = 300;
        System.out.println("包装类修改后:" + n);
    }

    // 传入的是:包装类Double
    public void show(Double n) {
        System.out.println("包装类修改前:" + n);
        n = 30.3;
        System.out.println("包装类修改后:" + n);
    }

    // 传入的是:包装类Boolean
    public void show(Boolean n) {
        System.out.println("包装类修改前:" + n);
        n = false;
        System.out.println("包装类修改后:" + n);
    }

    // 传入的是:对象
    public void show(Student s) {
        System.out.println("对象修改前:" + s.a);
        s.a = 3;
        System.out.println("对象修改后:" + s.a);
    }

    public void test() {
        // 基本类型int，值传递，所以最终值没有改变
        System.out.println("传入一个基本类型数字:" + a);
        show(a);
        System.out.println("基本类型最终值:" + a);
        System.out.println("------------------");

        // 包装类Integer
        System.out.println("传入一个包装类数字:" + b);
        show(b);
        System.out.println("包装类最终值:" + b);
        System.out.println("------------------");

        // 包装类Double
        System.out.println("传入一个包装类数字:" + d);
        show(d);
        System.out.println("包装类最终值:" + d);
        System.out.println("------------------");

        // 包装类Boolean
        System.out.println("传入一个包装类数字:" + bool);
        show(bool);
        System.out.println("包装类最终值:" + bool);
        System.out.println("------------------");

        // 对象Student
        System.out.println("传入一个对象数字:" + s);
        show(s);
        System.out.println("包装类最终值:" + s);

    }

    public static void main(String[] args) {
        new FinallTest().test();
    }
}
