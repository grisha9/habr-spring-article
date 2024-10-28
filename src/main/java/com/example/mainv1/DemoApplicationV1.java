package com.example.mainv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@SpringBootApplication
public class DemoApplicationV1 {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DemoApplicationV1.class) {
            @Override
            protected ConfigurableApplicationContext createApplicationContext() {
                ConfigurableApplicationContext applicationContext = super.createApplicationContext();
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(applicationContext.getClass());
                enhancer.setCallback(new MethodInterceptorCglib());
                return (ConfigurableApplicationContext) enhancer.create();
            }
        };
        springApplication.run(args);
    }

    static class MethodInterceptorCglib implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (method.getName().equals("onRefresh")) {
                //printBeans((ConfigurableApplicationContext) obj);
                throw new RuntimeException("I am Explyt Plugin!!!");
            } else {
                return proxy.invokeSuper(obj, args);
            }
        }
    }
}

