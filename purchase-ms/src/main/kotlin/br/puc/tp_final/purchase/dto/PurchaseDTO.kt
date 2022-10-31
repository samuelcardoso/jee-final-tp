package br.puc.tp_final.purchase.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PurchaseDTO(

        @field:Valid
        @field:Schema(description = "Lista de produtos a serem comprados.", type = "List")
        @field:NotEmpty(message = "A lista de produtos não pode ser vazia.")
        @field:NotNull(message = "Informe um produto para a lista. Não pode ser composta de elementos nulos.")
        @JsonProperty("products")
        val products: List<ProductDTO>,

        @field:Valid
        @field:Schema(description = "Id do tipo de pagamento.", example = "1")
        @field:NotNull(message = "O id do type de pagamento não pode ser nulo.")
        @field:Positive(message = "O id do tipo de pagamento não pode ser negativo.")
        @JsonProperty("paymentTypeId")
        val paymentTypeId: Long,

        @field:Valid
        @field:Schema(description = "Id do registro da entrega.", example = "1")
        @field:NotNull(message = "O id do registro de entrega não pode ser nulo.")
        @field:Positive(message = "O id do registro de entrega não pode ser negativo.")
        @JsonProperty("deliverId")
        val deliverId: Long
)