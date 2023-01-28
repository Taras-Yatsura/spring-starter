package com.tyatsura.spring.listener;

import com.tyatsura.spring.listener.entity.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityListener {

    //this allow to work first than another event listeners
    @Order(10)
    //works on bean factory post processor so it si allow to use SpEL
    //root.args - arguments of method. also can be used jusr args
    @EventListener(condition = "#root.args[0].getAccessType().name() == 'READ'")
    public void acceptEntityRead(EntityEvent entityEvent) {
        log.debug("Entity {} consumed", entityEvent);
    }
}
