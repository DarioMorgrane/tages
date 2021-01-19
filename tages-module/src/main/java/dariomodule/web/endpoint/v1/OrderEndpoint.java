package dariomodule.web.endpoint.v1;

import dariomodule.TagesSpringBootApplication;
import dariomodule.model.File;
import dariomodule.model.Order;
import dariomodule.service.interfaces.FileService;
import dariomodule.service.interfaces.OrderService;
import dariomodule.web.dto.FileJson;
import info.magnolia.rest.AbstractEndpoint;
import info.magnolia.rest.EndpointDefinition;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Path("/orders/v1")
public class OrderEndpoint<D extends EndpointDefinition> extends AbstractEndpoint<D> {

    private final OrderService orderService;
    private final FileService fileService;

    public OrderEndpoint(D endpointDefinition) {
        super(endpointDefinition);
        orderService = TagesSpringBootApplication.applicationContext.getBean("orderServiceImpl", OrderService.class);
        fileService = TagesSpringBootApplication.applicationContext.getBean("fileServiceImpl", FileService.class);
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Order receiveOrderJson(Order order, @Context HttpServletRequest request) {
        orderService.setDate(order);
        orderService.setIp(order, getClientIpAddress(request));
        return orderService.saveOrder(order);
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveOrderFile(FileJson fileJson, @PathParam("id") Long id) {
        File file = fileService.convertToModel(id, fileJson);
        fileService.save(file);
        return Response.ok().build();
    }

    private String getClientIpAddress(HttpServletRequest request) {
        final List<String> IP_HEADERS = Arrays.asList("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");
        return IP_HEADERS.stream()
                .map(request::getHeader)
                .filter(Objects::nonNull)
                .filter(ip -> !ip.isEmpty() && !ip.equalsIgnoreCase("unknown"))
                .findFirst()
                .orElseGet(request::getRemoteAddr);
    }

}
