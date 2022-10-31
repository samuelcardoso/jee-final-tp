package br.puc.tp_final.purchase.repositories

import br.puc.tp_final.purchase.model.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository: JpaRepository<Inventory, Long> {
}