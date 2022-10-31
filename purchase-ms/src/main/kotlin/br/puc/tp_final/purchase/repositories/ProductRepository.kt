package br.puc.tp_final.purchase.repositories

import br.puc.tp_final.purchase.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
}