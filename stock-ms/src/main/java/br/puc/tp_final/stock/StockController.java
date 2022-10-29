package br.puc.tp_final.stock;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;

//http://localhost:8080/stock-ms/rest/stock/status/1

@Path("stock")
@Produces({"application/json"})
public class StockController {

    @EJB
    private StockService stockService;

    @POST
    @Path("write_off")
    @Consumes("application/json")
    public Stock pay(String item) {
        return stockService.write_off(item);
    }

    @GET
    @Path("/status/{id}")
    @Consumes("application/json")
    public String status(@PathParam("id") Long id) {
        return stockService.status(id);
    }

    @POST
    @Path("load")
    public String load() {
       return stockService.loadItemsToStock();
    }
}