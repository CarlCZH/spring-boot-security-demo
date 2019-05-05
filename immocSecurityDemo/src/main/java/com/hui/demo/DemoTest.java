package com.hui.demo;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 21:30 2019\3\11 0011
 */
public class DemoTest {

    public static void main(String[] args){

        for (int i=5; i < 5000; i++){

            String s = "INSERT INTO user_info VALUES (" + i + ", 'zhangsan"+i+"', 'F', '2300', '123456');";

            System.out.println(s);
        }

    }

}
