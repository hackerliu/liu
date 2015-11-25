package com.xmpp.Chat.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Person> persons=new ArrayList<Person>();
        for (int i=0;i<10;i++){
            persons.add(new Person("person"+i,i));
        }
    }

    public static class Person{
        String name;
        int age;

        public Person(String name,int age){
            this.name=name;
            this.age=age;
        }
    }
}
