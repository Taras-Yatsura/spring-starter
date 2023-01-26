package com.tyatsura.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class AuditingBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> auditingBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // using after initialization because here will be problems with initialization of ConnectionPool (such
        // postprocessor may start after this one and such bean will not be injected because class changed,
        // especially @PostConstruct which will be called last one) all manipulation with types, wrapping them should
        // be used in AfterInitialization (all that be change Annotations, fields, methods)
        if (bean.getClass().isAnnotationPresent(Auditing.class)) {
            auditingBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditingBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                                          beanClass.getInterfaces(),
                                          (proxy, method, args) -> {
                                              System.out.println("Starting audit of method: " + method.getName());
                                              var startTime = System.nanoTime();
                                              try {
                                                  //here will be used maybe already wrapped or proxy class for
                                                  // multiple proxying
                                                  return method.invoke(bean, args);
                                              } finally {
                                                  System.out.println("Execution time: " + (System.nanoTime() - startTime) + " nanoseconds");
                                              }
                                          });
        }
        return bean;
    }
}
