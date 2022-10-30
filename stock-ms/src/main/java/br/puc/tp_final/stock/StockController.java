package br.puc.tp_final.stock;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;


@Path("stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockController {

    @EJB
    private StockService stockService;

    // http://localhost:8080/stock-ms/rest/stock
    @POST
    @Path("")
    public Response createStock(StockDTO stockDTO, @Context UriInfo uriInfo) {
        try {
            Stock stock = stockService.createStock(stockDTO);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(stock.getId())).build();
            return Response.created(uri).entity(stock).build();
        } catch (Exception e) {
            throw e;
        }
    }

    // http://localhost:8080/stock-ms/rest/stock/{id}/item/{item}/write_off
    @PATCH
    @Path("/{id}/items/{item}/write_off")
    @Consumes("application/json")
    public Response downStock(@PathParam("id") Long id, @PathParam("item") String item, @Context UriInfo uriInfo) {
        try {
            Stock stock = stockService.downStock(id, item);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(stock.getId())).build();
            return Response.created(uri).entity(stock).build();
        } catch (Exception e) {
            throw e;
        }
    }

    // http://localhost:8080/stock-ms/rest/stock/status/{id}
    @GET
    @Path("/status/{id}")
    @Consumes("application/json")
    public Response status(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        try {
            List<String> status = stockService.status(id);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id)).build();
            return Response.created(uri).entity(status).build();
        } catch (Exception e) {
            throw e;
        }
    }

}
