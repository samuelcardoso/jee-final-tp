package br.puc.tp_final.stock;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name = "stock")
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "items", length = 100)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> items = new ArrayList<>();

    public Stock() {
    }

    public Stock(Stock stock) {
        this.id = stock.getId();
        this.name = stock.getName();
        this.items = stock.getItems();
    }

    public Stock(StockDTO stockDTO) {
        this.id = stockDTO.getId();
        this.name = stockDTO.getName();
        this.items = stockDTO.getItems();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void addItem(String item) {
        this.items.add(item);
    }

    public void removeItem(String item) {
        this.items.remove(item);
    }
}
