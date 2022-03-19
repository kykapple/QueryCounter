package com.example.querycounter.handler;

import com.example.querycounter.counter.Counter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class QueryCountHandler implements InvocationHandler {

    private Object target;
    private Counter counter;

    public QueryCountHandler(Object target, Counter counter) {
        this.target = target;
        this.counter = counter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object ret = method.invoke(target, args);
            if (counter.getFlag() && method.getName().startsWith("prepareStatement")) {
                counter.countQuery();
            }
            return ret;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}
