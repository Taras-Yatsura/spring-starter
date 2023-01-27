package com.tyatsura.spring.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;

@Component
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getDeclaredFields())
              .filter(field -> field.isAnnotationPresent(InjectBean.class))
              .forEach(field -> {
                  //get all beans for type
                  var beansForSuchField = context.getBeansOfType(field.getType());
                  var beanToInject = beansForSuchField.entrySet().stream().findAny();
                  if (beanToInject.isEmpty()) {
                      throw new RuntimeException("Check application context for such bean type");
                  }

                  ReflectionUtils.makeAccessible(field);
                  //set any of the beans with such type
                  ReflectionUtils.setField(field, bean, beanToInject.get().getValue());
              });

        return bean;
    }

    //calling before bean post processing
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
