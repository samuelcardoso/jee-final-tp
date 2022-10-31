package br.puc.tp_final.purchase.event

import br.puc.tp_final.purchase.configuration.RabbitPurchaseConstants
import br.puc.tp_final.purchase.dto.PurchaseDTO
import br.puc.tp_final.purchase.exception.BusinessException
import br.puc.tp_final.purchase.model.*
import br.puc.tp_final.purchase.repositories.*
import mu.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class EventConsumer {

    private val logger = KotlinLogging.logger { }

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var paymentTypeRepository: PaymentTypeRepository

    @Autowired
    lateinit var deliverRepository: DeliverRepository

    @Autowired
    lateinit var purchaseRepository: PurchaseRepository

    @Autowired
    lateinit var productInventoryRepository: ProductInventoryRepository;

    @Autowired
    lateinit var productPurchaseRepository: ProductPurchaseRepository

    @Transactional
    @RabbitListener(queues = [RabbitPurchaseConstants.PURCHASE_QUEUE])
    fun receiveNewPurchase(purchaseDTO: PurchaseDTO) {
        logger.info("Compra {} recebida", purchaseDTO)

        val paymentType: PaymentType = paymentTypeRepository.findById(purchaseDTO.paymentTypeId).orElseThrow{ BusinessException("Tipo de pagamento não encontrado.") }
        val deliver: Deliver = deliverRepository.findById(purchaseDTO.deliverId).orElseThrow{ BusinessException("Informações de entrega não encontradas.") }
        purchaseDTO.products.forEach { (productId, quantity) ->
            run {
                val product: Product = productRepository.findById(productId).orElseThrow{ BusinessException("Produto não localizado.") }
                val productInventory: ProductInventory = productInventoryRepository.findByProduct(productId).orElseThrow { BusinessException("Produto não localizado.") };

                productInventory.inventory.quantity = productInventory.inventory.quantity - quantity;
                productInventoryRepository.save(productInventory);

                val purchase: Purchase = Purchase(value = quantity * product.value, paymentType = paymentType, deliver = deliver)
                purchaseRepository.save(purchase)

                val productPurchase: ProductPurchase = ProductPurchase(product = product, purchase = purchase, productQuantity = quantity)
                productPurchaseRepository.save(productPurchase)
            }
        }
    }
}