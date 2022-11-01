package br.puc.tp_final.track.domains

import javax.persistence.*

@Entity
@Table(name = "Orders")
data class Order (
    @get:Id
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false, updatable = false)
    var id: Int? = null,

    @get:ManyToOne
    @get:JoinColumn(name = "clientId", referencedColumnName = "id")
    var client: Client? = null,

    @get:Enumerated(EnumType.STRING)
    @get:Column(name = "status", nullable = false)
    var status: Status? = null,

    @get:ManyToMany
    @get:JoinTable(
        name = "ProcuctsOrder",
        joinColumns = [JoinColumn(name = "orderId", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "productId", referencedColumnName = "id")]
    )
    var productsOrder: MutableList<Product>? = null

)