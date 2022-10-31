package br.puc.tp_final.purchase.model

import javax.persistence.*

@Entity
@Table(name = "tb_payment_type")
data class PaymentType (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentTypeId")
    var paymentTypeId: Long? = null,

    @Column(name = "type")
    var type: String,

    @Column(name = "description")
    var description: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentType", cascade = [CascadeType.ALL])
    var purchases: List<Purchase>? = null
)