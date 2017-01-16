package com.example;

/**
 * Created by Administrator on 2017/1/10/010.
 */

public class Student extends Person
{
    public  String name="222我是学生";


    @Override
    public void eat() {
        //super.eat();


        System.out.println("3333学生是个小吃货");
    }

    @Override
    public void run() {
        //super.run();
        System.out.println("4444学生只有跑才能为祖国健康工作50年");

    }
}
