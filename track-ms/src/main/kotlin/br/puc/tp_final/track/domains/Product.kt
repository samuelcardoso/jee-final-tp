package br.puc.tp_final.track.domains

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "Products")
data class Product (
    @get:Id
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false, updatable = false)
    var id: Int? = null,

    @get:Basic
    @get:Column(name = "name", nullable = false)
    var name: String? = null,

    @get:ManyToMany(mappedBy="productsOrder")
    @JsonIgnore
    var ordersProduct: MutableList<Order>? = null

)
