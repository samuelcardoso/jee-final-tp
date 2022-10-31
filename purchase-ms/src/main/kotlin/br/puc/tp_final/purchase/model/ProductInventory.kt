package br.puc.tp_final.purchase.model

import javax.persistence.*

@Entity
@Table(name = "tb_product_inventory")
data class ProductInventory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productInventoryId")
    var productInventoryId: Long? = null,

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "productId", foreignKey = ForeignKey(name = "fk_product_inventory_product"))
    var product: Product,

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory", referencedColumnName = "inventoryId", foreignKey = ForeignKey(name = "fk_product_inventory_inventory"))
    var inventory: Inventory
)