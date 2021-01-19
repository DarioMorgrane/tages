package dariomodule.web.endpoint.v1;

import dariomodule.TagesSpringBootApplication;
import dariomodule.model.Order;
import dariomodule.service.interfaces.OrderService;
import info.magnolia.rest.AbstractEndpoint;
import info.magnolia.rest.EndpointDefinition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("admin/orders/v1")
public class AdminEndpoint<D extends EndpointDefinition> extends AbstractEndpoint<D> {

    private final OrderService orderService;

    public AdminEndpoint(D endpointDefinition) {
        super(endpointDefinition);
        orderService = TagesSpringBootApplication.applicationContext.getBean("orderServiceImpl", OrderService.class);
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder(@PathParam("id") Long id) {
        return orderService.findOrderById(id);
    }

    @Path("/{id}/changeStatus")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Order changeStatus(@PathParam("id") Long id) {
        Order order = orderService.findOrderById(id);
        orderService.changeStatus(order);
        return orderService.saveOrder(order);
    }


}
