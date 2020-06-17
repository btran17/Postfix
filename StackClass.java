//package edu.belmont.csc.src.stacks;

import java.util.EmptyStackException;
import java.util.LinkedList;
public class StackClass<T> {
    LinkedList<T> list = new LinkedList<T>();
    public StackClass(){
        list = new LinkedList<>();
    }

    public StackClass(T element){
        this();
        list.add(element);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(T elm) {
        list.addLast(elm);
    }

    public T pop() {
        if(isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    public T peek() {
        if(isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    public void clear(){
        while(!list.isEmpty()){
            list.pop();
        }
    }
}
