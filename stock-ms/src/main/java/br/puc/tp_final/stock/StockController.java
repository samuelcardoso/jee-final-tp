package br.puc.tp_final.stock;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


//http://localhost:8080/stock-ms/rest/stock/status/1

@Path("stock")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StockController {

    @EJB
    private StockService stockService;

    @POST
    @Path("")
    public Response createStock(StockDTO stockDTO, @Context UriInfo uriInfo) {
        var stock = stockService.createStock(stockDTO);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(stock.getId()));

        return Response.created(uriBuilder.build()).build();
    }

    @PATCH
    @Path("/{id}/items/{item}")
    @Consumes("application/json")
    public Response downStock(@PathParam("id") Long id, @PathParam("item") String item, @Context UriInfo uriInfo) {
        var stock = stockService.downStock(id, item);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(String.valueOf(stock.getId()));

        return Response.ok(uriBuilder.build()).build();
    }

    @GET
    @Path("/status/{id}")
    @Consumes("application/json")
    public String status(@PathParam("id") Long id) {
        return stockService.status(id);
    }

}
