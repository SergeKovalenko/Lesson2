package Lesson2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

class Inv implements InvocationHandler {
    private Object obj;
    private boolean cached = false;
    private double doubleValueCached;

   public Inv(Object obj) {
        this.obj = obj;
    }
    @Override

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m  = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (m.isAnnotationPresent(Mutator.class)) {
            cached = false;
            method.invoke(obj, args);
            return doubleValueCached;
        }
        if (m.isAnnotationPresent(Cache.class)) {
            if (cached == true) return doubleValueCached;
            else {
                cached = true;
                doubleValueCached = (double) method.invoke(obj, args);
                return doubleValueCached;
            }
        }
        return method.invoke(obj, args);
    }
}