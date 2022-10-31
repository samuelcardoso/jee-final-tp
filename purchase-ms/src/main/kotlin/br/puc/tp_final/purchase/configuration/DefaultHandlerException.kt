package br.puc.tp_final.purchase.configuration

import br.puc.tp_final.purchase.exception.BusinessException
import br.puc.tp_final.purchase.exception.StandardError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException

@ControllerAdvice
class DefaultHandlerException {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<StandardError?>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                createResponseError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Falha na requisição.", e.message)
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ResponseEntity<StandardError?>? {
        val errors: List<String> = e.bindingResult.allErrors.map { error -> error.defaultMessage.toString() };
        val error: String? = errors?.reduce { total, string -> total + string }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                createResponseError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Falha na validação.", error)
        );
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<StandardError?>? {
        val errors: List<String> = e.constraintViolations.map { error -> error.messageTemplate }
        val error: String? = errors?.reduce { total, string -> total + string }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                createResponseError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Falha na validação.", error)
        );
    }

    private fun createResponseError(timestamp: Long, status: Int, error: String, message: String?): StandardError? {
        return StandardError(timestamp, status, error, message);
    }
}