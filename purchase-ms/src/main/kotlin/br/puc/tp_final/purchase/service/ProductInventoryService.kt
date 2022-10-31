package br.puc.tp_final.purchase.service

import br.puc.tp_final.purchase.dto.ProductDTO
import br.puc.tp_final.purchase.exception.BusinessException
import br.puc.tp_final.purchase.repositories.ProductInventoryRepository
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductInventoryService {

    val logger = KotlinLogging.logger { }

    @Autowired
    lateinit var productInventoryRepository: ProductInventoryRepository;

    fun verifyQuantityProductInventory(products: List<ProductDTO>): Boolean {
        logger.info("Verificando estoque...")

        products.forEach{(productId, quantity) ->
            run {
                val productInventory = productInventoryRepository.findByProduct(productId).orElseThrow { BusinessException("Produto não localizado.") };

                if (productInventory.inventory.quantity < quantity) {
                    throw BusinessException("O estoque não possui a quantidade solicitada para o produto $productId.");
                }
            }
        }
        return true;
    }
}