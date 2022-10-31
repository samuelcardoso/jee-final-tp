package br.puc.tp_final.purchase.model

import javax.persistence.*

@Entity
@Table(name = "tb_inventory")
data class Inventory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventoryId")
    var inventoryId: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "quantity")
    var quantity: Long,

    @Column(name = "maxQuantity")
    var maxQuantity: Long,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory", cascade = [CascadeType.ALL])
    var productsInventories: List<ProductInventory>? = null
)