package pl.multishop.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.multishop.model.OrderStatus;
import pl.multishop.service.OrderStatusService;

@Component
public class StatusConverter implements Converter<Object, OrderStatus> {

    static final Logger logger = LoggerFactory.getLogger(StatusConverter.class);

    @Autowired
    OrderStatusService orderStatusService;

    /**
     * This method Gets UserStatus by Id
     * @see org.springframework.core.convert.converter.Converter#convert(Object)
     */
    public OrderStatus convert(Object element) {
        Long id = Long.parseLong((String)element);
        OrderStatus status = orderStatusService.findById(id);
        logger.info("User status: {}", status);
        return status;
    }
}
