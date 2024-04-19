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
        if (Arrays.stream(m.getAnnotationsByType(Mutator.class)).count()>0) {
            cached = false;
            method.invoke(obj, args);
            return doubleValueCached;
        }

        if (Arrays.stream(m.getAnnotationsByType(Cache.class)).count() > 0 )
                if (cached == true)return doubleValueCached;
                else {
                    cached = true;
                    doubleValueCached = (double) method.invoke(obj, args);
                    return doubleValueCached;
                }
        return method.invoke(obj, args);
    }
}