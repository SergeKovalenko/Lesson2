package Lesson2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class Inv implements InvocationHandler {
    private Object currentObj;
    private Map<Method,Object> results=new HashMap();

   public Inv(Object currentObj) {
        this.currentObj = currentObj;
    }
    @Override

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method currentMethod  = currentObj.getClass().getMethod(method.getName(), method.getParameterTypes());
        Object objectResult;


        if (currentMethod.isAnnotationPresent(Cache.class)) {
            if (results.containsKey(currentMethod)){
               return results.get(currentMethod);
            }
            objectResult=method.invoke(currentObj,args);
            results.put(currentMethod,objectResult);
            return objectResult;
        }

        if (currentMethod.isAnnotationPresent(Mutator.class)) {
            results.clear();
        }

        return method.invoke(currentObj, args);
    }
}