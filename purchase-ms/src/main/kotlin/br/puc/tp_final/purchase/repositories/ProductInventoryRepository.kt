package br.puc.tp_final.purchase.repositories

import br.puc.tp_final.purchase.model.ProductInventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProductInventoryRepository : JpaRepository<ProductInventory, Long> {

    @Query("SELECT pi FROM ProductInventory pi WHERE pi.product.productId = :productId")
    fun findByProduct(@Param("productId") productId: Long): Optional<ProductInventory>;
}