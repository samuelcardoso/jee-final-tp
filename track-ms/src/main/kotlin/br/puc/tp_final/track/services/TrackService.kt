package br.puc.tp_final.track.services

import br.puc.tp_final.track.domains.Client
import br.puc.tp_final.track.domains.Order
import br.puc.tp_final.track.domains.Product
import br.puc.tp_final.track.domains.Status
import br.puc.tp_final.track.exception.BadRequestException
import br.puc.tp_final.track.exception.NotFoundException
import br.puc.tp_final.track.exception.ServiceUnavailableException
import br.puc.tp_final.track.repositories.ClientRepository
import br.puc.tp_final.track.repositories.OrderRepository
import br.puc.tp_final.track.repositories.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Random
import kotlin.collections.ArrayList

@Service
class TrackService(
    val orderRepository: OrderRepository,
    val clientRepository: ClientRepository,
    val productRepository: ProductRepository
    ) {

    fun status(id: Int?): Order? {

        if(id==null) {
            throw BadRequestException()
        }

        //AQUI ESTAMOS SIMULANDO QUE O USUARIO PASSOU UM CODIGO DE COMPRA QUE NÃO EXISTE
        if(id != 1) {
            throw NotFoundException()
        }

        val order: Order

        val success: Int = Random().nextInt(100) + 1

        if(success in 1..90) {
             order = simulateProductTrack()
        } else {
            throw ServiceUnavailableException()
        }

        return order

    }

    @Transactional
    private fun simulateProductTrack(): Order {

        val listProducts = ArrayList<Product>()

        val client = Client(1,"Mateus")
        clientRepository.save(client)

        var product = Product(1,"Computer")
        productRepository.save(product)
        listProducts.add(product)

        product = Product(2,"Keyboard")
        productRepository.save(product)
        listProducts.add(product)

        val randomStatus: Int = Random().nextInt(2) + 1

        var order : Order = if(randomStatus == 1) {
            Order(1,client,Status.DELIVERED,listProducts)
        } else {
            Order(1,client,Status.ONCOURSE,listProducts)
        }

        order = orderRepository.save(order)

        return order

    }

}