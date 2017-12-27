package com.wcc.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Orders createNewOrder(@RequestBody Orders order) {
        return orderRepository.save(order);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Orders getOrderById(@PathVariable Long orderId) {
        return orderRepository.findOne(orderId);
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public Orders updateOrderDetails(@PathVariable Long orderId, @RequestBody Orders orderIn) {
        Orders currOrder = orderRepository.findOne(orderId);
        currOrder.setItemName( (orderIn.getItemName() != null) ?
                orderIn.getItemName() : currOrder.getItemName());
        currOrder.setSpecialRequest( (orderIn.getSpecialRequest() != null) ?
                orderIn.getSpecialRequest() : currOrder.getSpecialRequest());

        //updating fields in the order request
        orderRepository.save(currOrder);
        return currOrder;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Orders searchByUserId(@RequestBody Long userId) {
        return orderRepository.findOrderByUserId(userId);
    }

}
