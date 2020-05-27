package com.example.thinkinjava.innerclass;

/**
 * Created by bing.zhao on 2017/1/10.
 *
 * 内部类：
 * 成员内部类 - > 局部内部类 - > 匿名内部类 —>静态内部类（嵌套类）
 *
 * 内部类的应用：迭代器模式 {@link com.example.thinkinjava.innerclass.iterator.Sequence}
 * 内部类的作用：解决多重继承问题
 */

public class Goods {

    private String name;

    /**
     * public 可以在外部类之外使用new 创建对象
     */
    public class MyContent implements Content{
        int value = 100;
        @Override
        public int value() {
            return value;
        }

        /**
         * 可以访问外部类所有的属性
         * @return
         */
        public String getGoodsName(){
            return name;
        }

        /**
         * 获取拥有的外部类对象,由于内部类对象持有外部类的this对象所以可以访问外部类的成员
         */
        public Goods getOutClass(){
            return Goods.this;
        }
    }

    /**
     * private不能使用new 在外部类之外创建对象
     */
    private class MyDestination implements Destination{
        private MyDestination(String label){
            this.label = label;
        }
        private String label;
        @Override
        public String readLabel() {
            return label;
        }
    }

    public Content getContent(){
        return new MyContent();
    }

    public Destination getDestination(String label){
        return new MyDestination(label);
    }

    class Fextends extends F{

    }
    class F1extends extends F1{

    }
    private F f;
    private F1 f1;     //通过这样的变形方式实现多继承


}

class Test{

}
