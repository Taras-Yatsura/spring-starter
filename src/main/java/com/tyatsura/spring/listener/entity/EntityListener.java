package com.tyatsura.spring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    //this allow to work first than another event listeners
    @Order(10)
    //works on bean factory post processor so it si allow to use SpEL
    //root.args - arguments of method. also can be used jusr args
    @EventListener(condition = "#root.args[0].getAccessType().name() == 'READ'")
    public void acceptEntityRead(EntityEvent entityEvent) {
        System.out.println("Entity " + entityEvent);
    }
}
