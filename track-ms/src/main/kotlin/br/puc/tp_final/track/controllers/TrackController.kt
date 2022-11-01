package br.puc.tp_final.track.controllers

import br.puc.tp_final.track.domains.Order
import br.puc.tp_final.track.response.Response
import br.puc.tp_final.track.services.TrackService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/track-ms/rest/track")
class TrackController(
    val trackService: TrackService
) {
    @ApiOperation("Get status of a order")
    @ApiResponses(
        value = [ApiResponse(code = 200, message = "Status of a order"),
            ApiResponse(code = 404, message = "Order not found"),
            ApiResponse(code = 400, message = "Did not pass the id"),
            ApiResponse(code = 503, message = "Service Unavailable")]
    )
    @GetMapping("status/{id}")
    fun status(@PathVariable id: Int?): ResponseEntity<Response<Order>> {
        val response: Response<Order> = Response()

        if (id == null) {
            return ResponseEntity.badRequest().build()
        }

        //AQUI ESTAMOS SIMULANDO QUE O USUARIO PASSOU UM CODIGO DE COMPRA QUE NÃO EXISTE
        if(id != 1) {
            return ResponseEntity.notFound().build()
        }

        val order = trackService.status(id)

        return if(order == null) {
            response.erros.add("Ops! Something wrong! Try Again Later")
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response)
        } else {
            response.data = order
            ResponseEntity.ok(response)
        }

    }
}