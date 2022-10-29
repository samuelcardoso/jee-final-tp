package br.puc.tp_final.stock;

import jakarta.ejb.Stateless;

import java.util.Random;

@Stateless
public class StockService {


    public void write_off() {
    }

    public String status() {
        return "TODO";
    }

    public boolean PedidoAceito(){
        Random random = new Random();
        int x = random.nextInt(10);

        return x < 8;
    }
}