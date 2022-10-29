package br.puc.tp_final.stock;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.http.HTTPException;

import java.util.Objects;
import java.util.Random;

@Stateless
public class StockService {

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;

    public Stock downStock(Long id, String item) {
        if (chanceOfSuccess()) {
            Stock stock = findStockById(id);
            stock.removeItem(item);
            return entityManager.merge(stock);
        }
        throw new HTTPException(Response.Status.CONFLICT.getStatusCode());
    }

    public String status(Long id) {
        if (chanceOfSuccess()) {
            Stock stock = findStockById(id);

            if (!stock.getItems().isEmpty()) {
                return String.valueOf(stock.getItems().size());
            }
        }
        throw new HTTPException(Response.Status.CONFLICT.getStatusCode());
    }

    private Stock findStockById(Long id) {
        Stock stock = entityManager.find(Stock.class, id);

        if(Objects.isNull(stock)){
            throw new NotFoundException("Stock not found.");
        }
        return stock;
    }

    public boolean chanceOfSuccess() {
        Random random = new Random();
        int x = random.nextInt(100);
        return x < 95;
    }

    public Stock createStock(StockDTO stockDTO) {
        if (chanceOfSuccess()) {
            Stock stock = new Stock(stockDTO);
            stock = entityManager.merge(stock);

            if (Objects.nonNull(stock)) {
                return stock;
            }
        }
        throw new HTTPException(Response.Status.CONFLICT.getStatusCode());
    }
}
