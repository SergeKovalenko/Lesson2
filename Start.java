package Lesson2;
import lombok.ToString;

import java.lang.reflect.Proxy;
import java.util.*;
public class Start {
    public static void main(String... args) {
        Able cmd = new A(1,2);
        cmd=Utils.cache(cmd);
        cmd.doubleValue();
        cmd.doubleValue();
        cmd.setDenum(5);
        cmd.doubleValue();
        cmd.doubleValue();
        cmd.setNum(2);
        cmd.doubleValue();
        cmd.doubleValue();

    }
}

@ToString
class A implements Able{
    private int num;
    private int denum;

    public A(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Override  @Cache
    public double doubleValue() {
        System.out.println((double) num/denum);
        return (double) num/denum;
    }

    @Override        @Mutator
    public void setNum(int num) {
        this.num = num;
    }

    @Override        @Mutator
    public void setDenum(int denum) {
        this.denum = denum;
    }
}