package dariomodule.service.interfaces;

import dariomodule.model.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    List<Order> getAllOrders();

    Order findOrderById(Long id);

    void setDate(Order order);

    void setIp(Order order, String clientIpAddress);

    Order changeStatus(Order order);
}
