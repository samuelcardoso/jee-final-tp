package br.puc.tp_final.track.repositories

import br.puc.tp_final.track.domains.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Int> {
}