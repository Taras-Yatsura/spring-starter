package com.tyatsura.spring.bpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OperationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> operationBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // using after initialization because here will be problems with initialization of ConnectionPool (such
        // postprocessor may start after this one and such bean will not be injected because class changed,
        // especially @PostConstruct which will be called last one) all manipulation with types, wrapping them should
        // be used in AfterInitialization (all that be change Annotations, fields, methods)
        if (bean.getClass().isAnnotationPresent(Operation.class)) {
            log.debug("Found operation bean: {}", beanName);
            operationBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = operationBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(),
                                          beanClass.getInterfaces(),
                                          (proxy, method, args) -> {
                                              if (!method.getName().equals("toString")) {
                                                  log.debug("Starting operation");
                                                  try {
                                                      //here will be used maybe already wrapped or proxy class for
                                                      // multiple proxying
                                                      return method.invoke(bean, args);
                                                  }
                                                  catch (Exception e) {
                                                      log.debug("Rollback operation");
                                                      throw e;
                                                  }
                                                  finally {
                                                      log.debug("Closing operation");
                                                  }
                                              }
                                              return method.invoke(bean, args);
                                          });
        }
        return bean;
    }
}
