package br.puc.tp_final.stock;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Objects;
import java.util.Random;

@Stateless
public class StockService {

    @PersistenceContext(unitName = "persistence-unit")
    private EntityManager entityManager;

    public Stock write_off(String item) {
        return new Stock();
    }

    public String status(Long id) {
        if (chanceOfSuccess()) {
            Stock stock = entityManager.find(Stock.class, id);
            if (!stock.getItems().isEmpty()) {
                return String.valueOf(stock.getItems().size());
            }
        }
        return "TODO";
    }


    public boolean chanceOfSuccess() {
        Random random = new Random();
        Integer x = random.nextInt(100);
        return x < 95;
    }

    public String loadItemsToStock() {
        if (chanceOfSuccess()) {
            Stock stock = new Stock();
            stock.setName("test");
            stock.getItems().add("Item-1");
            stock.getItems().add("Item-2");
            stock.getItems().add("Item-3");
            Stock newStock = entityManager.merge(stock);
            if (Objects.nonNull(newStock)) {
                return "OK";
            }
        }
        return "Failure";
    }
}