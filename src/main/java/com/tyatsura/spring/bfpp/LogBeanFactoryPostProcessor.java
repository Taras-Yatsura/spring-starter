package com.tyatsura.spring.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

//PriorityOrdered calling before Ordered
//will be called first as here set HIGHEST_PRECEDENCE and in PropertyResourceConfigurer is used LOWEST_PRECEDENCE
//than will be called FirstVerifyBeanFactoryPostProcessor and after VerifyBeanFactoryPostProcessor
// (less order - earlier call)
@Component
@Slf4j
public class LogBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.debug("called");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
