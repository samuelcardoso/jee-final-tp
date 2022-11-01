package br.puc.tp_final.track.repositories

import br.puc.tp_final.track.domains.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Int> {
}