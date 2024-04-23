package Lesson2;

public class TestFraction implements Able{
    int num,denum;
    int count;

    public TestFraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Override   @Cache
    public double doubleValue() {
        count++;
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

    @Override  @Cache
    public String toString() {
        count++;
        return "TestFraction{" +
                "num=" + num +
                ", denum=" + denum +
                ", count=" + count +
                '}';
    }
}
