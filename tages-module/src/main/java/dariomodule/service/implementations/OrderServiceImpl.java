package dariomodule.service.implementations;

import dariomodule.model.Order;
import dariomodule.repository.OrderRepository;
import dariomodule.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order saveOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public void setDate(Order order) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy hh:mm a"));
        order.setDate(date);
    }

    @Override
    public void setIp(Order order, String clientIpAddress) {
        order.setIp(clientIpAddress);
    }

    @Override
    public Order changeStatus(Order order) {
        order.setProcessed(!order.isProcessed());
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No orders with provided id exist!"));
    }
}
