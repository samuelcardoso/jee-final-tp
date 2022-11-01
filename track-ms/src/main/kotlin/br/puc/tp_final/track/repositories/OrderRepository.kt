package br.puc.tp_final.track.repositories

import br.puc.tp_final.track.domains.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Int> {
}