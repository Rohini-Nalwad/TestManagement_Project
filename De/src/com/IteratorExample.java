package com;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("A");
        myList.add("B");
        myList.add("C");

//        // Using Iterator (forward direction only)
//        Iterator<String> iterator = myList.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

        // Using ListIterator (forward and backward directions)
        ListIterator<String> listIterator = myList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
