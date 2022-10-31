package br.puc.tp_final.purchase.model

import javax.persistence.*

@Entity
@Table(name = "tb_deliver")
data class Deliver(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deliverId")
    var deliverId: Long? = null,

    @Column(name = "addressNumber")
    var addressNumber: Int,

    @Column(name = "addressComplement")
    var addressComplement: String,

    @Column(name = "streetName")
    var streetName: String,

    @Column(name = "city")
    var city: String,

    @Column(name = "isHomeAddress")
    var isHomeAddress: Boolean,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deliver", cascade = [CascadeType.ALL])
    var purchases: List<Purchase>? = null
)