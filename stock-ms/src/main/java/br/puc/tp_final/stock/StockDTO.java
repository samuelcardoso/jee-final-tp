package br.puc.tp_final.stock;

import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class StockDTO {

    private long id;

    @NotEmpty(message = "Name cannot be empty.")
    private String name;

    private List<@NotEmpty(message = "Item cannot be empty.")String> items = new ArrayList<>();

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
}
