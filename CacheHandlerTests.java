package Lesson2;

import org.junit.jupiter.api.*;

public class CacheHandlerTests {
    @Test
    @DisplayName("Должно выводиться 1 раз")
    public void test1_2_1(){
        TestFraction testFr=new TestFraction(1,2);
        Able frProxy=Utils.cache(testFr);
        frProxy.doubleValue();
        frProxy.doubleValue();
        Assertions.assertEquals(testFr.count,1);
    }
    @Test
    @DisplayName("Должно выводиться 2 раза")
    public void test1_2_2(){
        TestFraction testFr=new TestFraction(1,2);
        Able frProxy=Utils.cache(testFr);
        frProxy.doubleValue();
        frProxy.toString();
        frProxy.doubleValue();
        frProxy.toString();
        Assertions.assertEquals(testFr.count,2);
    }
    @Test
    @DisplayName("Должно выводиться 1 раз, поскольку кэшируется только 1 операция")
    public void test1_2_3(){
        TestFraction fraction=new TestFraction(1,2);
        fraction.doubleValue();
        fraction.setNum(3);
        Assertions.assertEquals(fraction.count,1);
    }
    @Test
    @DisplayName("Проверка изменения кэшируемого значения при вызове обнуления кэша")
    public void test2_1_1(){
        TestFraction testFr=new TestFraction(1,2);
        Able frProxy=Utils.cache(testFr);
        double d=frProxy.doubleValue();
        frProxy.setDenum(5);
        double f=frProxy.doubleValue();
        Assertions.assertEquals(f,0.2);
    }
}
