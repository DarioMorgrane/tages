package dariomodule.web.endpoint.v1;

import dariomodule.TagesSpringBootApplication;
import dariomodule.model.File;
import dariomodule.service.interfaces.FileService;
import info.magnolia.rest.AbstractEndpoint;
import info.magnolia.rest.EndpointDefinition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

@Path("/files/v1")
public class GetFileEndpoint<D extends EndpointDefinition> extends AbstractEndpoint<D> {

    private FileService service;

    public GetFileEndpoint(D endpointDefinition) {
        super(endpointDefinition);
        service = TagesSpringBootApplication.applicationContext.getBean("fileServiceImpl", FileService.class);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("id") Long id) {
        File file = service.findFileById(id);
        StreamingOutput stream = output -> {
            try {
                output.write(file.getContent());
            } catch (Exception e) {
                throw new WebApplicationException(e);
            }
        };
        String contentDispositionValue = "attachment; filename=\"replace\"".replace("replace", file.getName());
        return Response
                .ok(stream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", contentDispositionValue)
                .build();
    }

}
