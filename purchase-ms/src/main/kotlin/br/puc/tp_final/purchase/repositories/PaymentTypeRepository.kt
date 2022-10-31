package br.puc.tp_final.purchase.repositories

import br.puc.tp_final.purchase.model.PaymentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentTypeRepository: JpaRepository<PaymentType, Long> {
}