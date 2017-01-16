package com.example.test1;

/**
 * Created by Administrator on 2017/1/11/011.
 */

public class Test
{

    public static void main(String[]args)
    {

        Person p=new Person("zhangsan",18);
        Person p1=new Person("lisi",19);
        Person p2=new Person("wangwu",20);

        p1=p;
        p2=p1;

        System.out.println(p.getName());
        System.out.println(p1.getName());
        System.out.println(p2.getName());

    }
}
