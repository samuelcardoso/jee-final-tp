package br.puc.tp_final.purchase.service

import br.puc.tp_final.purchase.event.EventProducer
import br.puc.tp_final.purchase.dto.PurchaseDTO
import org.springframework.stereotype.Service

@Service
class PurchaseService(val eventProducer:  EventProducer) {

    fun buy(purchaseDTO: PurchaseDTO): Boolean {
        return eventProducer.makePurchase(purchaseDTO)
    }
}