package br.puc.tp_final.track.domains

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "Clients")
data class Client (
    @get:Id
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    @get:Column(name = "id", nullable = false, updatable = false)
    var id: Int? = null,

    @get:Basic
    @get:Column(name = "name", nullable = false)
    var name: String? = null,

    @JsonIgnore
    @get:OneToMany(mappedBy = "client")
    var orders: List<Order>? = null

)