package br.puc.tp_final.stock.service;

import br.puc.tp_final.stock.dto.StockDTO;
import br.puc.tp_final.stock.model.Stock;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.http.HTTPException;

import java.util.List;
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

    public List<String> status(Long id) {
        if (chanceOfSuccess()) {
            Stock stock = findStockById(id);

            if (!stock.getItems().isEmpty()) {
                return stock.getItems();
            }
        }
        throw new HTTPException(Response.Status.CONFLICT.getStatusCode());
    }

    private Stock findStockById(Long id) {
        Stock stock = entityManager.find(Stock.class, id);

        if (Objects.isNull(stock)) {
            throw new HTTPException(Response.Status.BAD_REQUEST.getStatusCode());
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
