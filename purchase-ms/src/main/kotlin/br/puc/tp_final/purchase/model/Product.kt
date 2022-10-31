package br.puc.tp_final.purchase.model

import javax.persistence.*

@Entity
@Table(name = "tb_product")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    var productId: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "value")
    var value: Double,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = [CascadeType.ALL])
    var productsPurchases: List<ProductPurchase>? = null,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = [CascadeType.ALL])
    var productsInventories: List<ProductInventory>? = null
)