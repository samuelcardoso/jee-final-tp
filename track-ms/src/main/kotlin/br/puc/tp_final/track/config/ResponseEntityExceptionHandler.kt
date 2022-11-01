package br.puc.tp_final.track.config

import br.puc.tp_final.track.domains.Order
import br.puc.tp_final.track.exception.BadRequestException
import br.puc.tp_final.track.exception.NotFoundException
import br.puc.tp_final.track.exception.ServiceUnavailableException
import br.puc.tp_final.track.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceUnavailableException::class)
    fun handleServiceUnavailableException(e:ServiceUnavailableException) : ResponseEntity<Response<Void>> {
        val response: Response<Void> = Response()
        response.erros.add("Ops! Something wrong! Try Again Later")
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e:NotFoundException) : ResponseEntity<Void> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(e:BadRequestException) : ResponseEntity<Void> {
        return ResponseEntity.notFound().build()
    }

}