package br.puc.tp_final.purchase.repositories

import br.puc.tp_final.purchase.model.ProductPurchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductPurchaseRepository: JpaRepository<ProductPurchase, Long> {
}