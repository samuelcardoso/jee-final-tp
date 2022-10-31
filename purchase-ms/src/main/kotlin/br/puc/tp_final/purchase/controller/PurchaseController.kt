package br.puc.tp_final.purchase.controller

import br.puc.tp_final.purchase.dto.PurchaseDTO
import br.puc.tp_final.purchase.service.PurchaseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import net.minidev.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/purchase-ms/rest/purchase"], consumes = ["application/json"])
@Tag(name = "Endpoints para realização de compras")
@Validated
class PurchaseController(val purchaseService: PurchaseService) {

    @PostMapping("/buy")
    @ApiResponse(responseCode = "200", description = "OK", content = [Content(mediaType = "application/json", schema = Schema(implementation = PurchaseDTO::class))])
    @Operation(summary = "Envia requisição de compra", description = "Envia requisição de compra.")
    fun buy(@Valid @RequestBody purchaseDTO:  PurchaseDTO): ResponseEntity<JSONObject> {
        return if (purchaseService.buy(purchaseDTO)) ResponseEntity(JSONObject(mapOf("message" to "Compra aguardando na fila para ser concluída.")), HttpStatus.OK) else ResponseEntity(JSONObject(mapOf("message" to "Erro ao processar a compra.")), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}