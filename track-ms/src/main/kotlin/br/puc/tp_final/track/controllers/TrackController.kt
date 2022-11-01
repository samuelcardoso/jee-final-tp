package br.puc.tp_final.track.controllers

import br.puc.tp_final.track.domains.Order
import br.puc.tp_final.track.response.Response
import br.puc.tp_final.track.services.TrackService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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
        response.data = trackService.status(id)
        return ResponseEntity.ok(response)

    }
}