package br.puc.tp_final.purchase.model

import javax.persistence.*

@Entity
@Table(name = "tb_purchase")
data class Purchase (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseId")
    var purchaseId: Long? = null,

    @Column(name = "value")
    var value: Double,

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "paymentType", referencedColumnName = "paymentTypeId", foreignKey = ForeignKey(name = "fk_purchase_payment"))
    var paymentType: PaymentType,

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "deliver", referencedColumnName = "deliverId", foreignKey = ForeignKey(name = "fk_purchase_deliver"))
    var deliver: Deliver,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchase", cascade = [CascadeType.ALL])
    var products: List<ProductPurchase>? = null
)