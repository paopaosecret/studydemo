package com.example.thinkinjava.innerclass.iterator;

/**
 * Created by bing.zhao on 2017/1/10.
 *
 * 使用内部类模拟迭代器模式
 */
public class Sequence {
    private Object[] data;
    private int index = 0;
    public Sequence(int size){
        data = new Object[size];
    }

    public void add(Object object){
        if(index< data.length){
            data[index] = object;
            index ++;
        }
    }

    public Iterator getIterator(){
        return new SequenceIterator();
    }
    private class SequenceIterator implements Iterator{

        public int i = 0;

        @Override
        public boolean hasNext() {
            if(i < data.length){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public Object getCurrent() {
            return data[i];
        }

        @Override
        public void next() {
            i++;
        }
    }

    public static void main(String[] arg){
        Sequence sequence = new Sequence(10);
        for(int i = 0; i< 10; i++){
            sequence.add(Integer.toString(i));
        }


        Iterator iterator = sequence.getIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.getCurrent()+ "");
            iterator.next();
        }
    }
}
