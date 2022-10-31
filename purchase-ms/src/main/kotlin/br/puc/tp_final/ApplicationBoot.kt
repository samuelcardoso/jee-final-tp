package br.puc.tp_final

import br.puc.tp_final.purchase.model.*
import br.puc.tp_final.purchase.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ApplicationBoot {

    @Autowired
    lateinit var deliverRepository: DeliverRepository

    @Autowired
    lateinit var inventoryRepository: InventoryRepository

    @Autowired
    lateinit var paymentTypeRepository: PaymentTypeRepository

    @Autowired
    lateinit var productInventoryRepository: ProductInventoryRepository

    @Autowired
    lateinit var productPurchaseRepository: ProductPurchaseRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var purchaseRepository: ProductPurchaseRepository

    @Bean
    fun init() = CommandLineRunner {
        val deliveries: List<Deliver> = deliverRepository.saveAll(listOf(
            Deliver(addressNumber = 1, addressComplement = "A", streetName = "Street Name 1", city = "City 1", isHomeAddress = false),
            Deliver(addressNumber = 2, addressComplement = "B", streetName = "Street Name 2", city = "City 2", isHomeAddress = false),
            Deliver(addressNumber = 3, addressComplement = "C", streetName = "Street Name 3", city = "City 3", isHomeAddress = true),
            Deliver(addressNumber = 4, addressComplement = "D", streetName = "Street Name 4", city = "City 4", isHomeAddress = true),
            Deliver(addressNumber = 5, addressComplement = "E", streetName = "Street Name 5", city = "City 5", isHomeAddress = true)
        ))

        val inventories: List<Inventory> = inventoryRepository.saveAll(listOf(
            Inventory(name = "Name 1", description = "Descrition 1", quantity = 100, maxQuantity = 100),
            Inventory(name = "Name 2", description = "Descrition 2", quantity = 200, maxQuantity = 200),
            Inventory(name = "Name 3", description = "Descrition 3", quantity = 300, maxQuantity = 300),
            Inventory(name = "Name 4", description = "Descrition 4", quantity = 400, maxQuantity = 400),
            Inventory(name = "Name 5", description = "Descrition 5", quantity = 500, maxQuantity = 500)
        ))

        val paymentTypes: List<PaymentType> = paymentTypeRepository.saveAll(listOf(
            PaymentType(type = "Type 1", description = "Description 1"),
            PaymentType(type = "Type 2", description = "Description 2"),
            PaymentType(type = "Type 3", description = "Description 3"),
            PaymentType(type = "Type 4", description = "Description 4"),
            PaymentType(type = "Type 5", description = "Description 5")
        ))

        val products: List<Product> = productRepository.saveAll(listOf(
            Product(name = "Product 1", description = "Description 1", value = 10.0),
            Product(name = "Product 2", description = "Description 2", value = 20.0),
            Product(name = "Product 3", description = "Description 3", value = 30.0),
            Product(name = "Product 4", description = "Description 4", value = 40.0),
            Product(name = "Product 5", description = "Description 5", value = 50.0),
            Product(name = "Product 6", description = "Description 6", value = 60.0),
            Product(name = "Product 7", description = "Description 7", value = 70.0),
            Product(name = "Product 8", description = "Description 8", value = 80.0),
            Product(name = "Product 9", description = "Description 9", value = 90.0),
            Product(name = "Product 10", description = "Description 10", value = 100.0),
            Product(name = "Product 11", description = "Description 11", value = 110.0),
            Product(name = "Product 12", description = "Description 12", value = 120.0),
            Product(name = "Product 13", description = "Description 13", value = 130.0),
            Product(name = "Product 14", description = "Description 14", value = 140.0),
            Product(name = "Product 15", description = "Description 15", value = 150.0),
            Product(name = "Product 16", description = "Description 16", value = 160.0),
            Product(name = "Product 17", description = "Description 17", value = 170.0),
            Product(name = "Product 18", description = "Description 18", value = 180.0),
            Product(name = "Product 19", description = "Description 19", value = 190.0),
            Product(name = "Product 20", description = "Description 20", value = 200.0),
            Product(name = "Product 21", description = "Description 21", value = 210.0),
            Product(name = "Product 22", description = "Description 22", value = 220.0),
            Product(name = "Product 23", description = "Description 23", value = 230.0),
            Product(name = "Product 24", description = "Description 24", value = 240.0),
            Product(name = "Product 25", description = "Description 25", value = 250.0)
        ))

        val productsInventories: List<ProductInventory> = productInventoryRepository.saveAll(listOf(
            ProductInventory(product = products[0], inventory = inventories[0]),
            ProductInventory(product = products[1], inventory = inventories[0]),
            ProductInventory(product = products[2], inventory = inventories[0]),
            ProductInventory(product = products[3], inventory = inventories[0]),
            ProductInventory(product = products[4], inventory = inventories[0]),
            ProductInventory(product = products[5], inventory = inventories[1]),
            ProductInventory(product = products[6], inventory = inventories[1]),
            ProductInventory(product = products[7], inventory = inventories[1]),
            ProductInventory(product = products[8], inventory = inventories[1]),
            ProductInventory(product = products[9], inventory = inventories[1]),
            ProductInventory(product = products[10], inventory = inventories[2]),
            ProductInventory(product = products[11], inventory = inventories[2]),
            ProductInventory(product = products[12], inventory = inventories[2]),
            ProductInventory(product = products[13], inventory = inventories[2]),
            ProductInventory(product = products[14], inventory = inventories[2]),
            ProductInventory(product = products[15], inventory = inventories[3]),
            ProductInventory(product = products[16], inventory = inventories[3]),
            ProductInventory(product = products[17], inventory = inventories[3]),
            ProductInventory(product = products[18], inventory = inventories[3]),
            ProductInventory(product = products[19], inventory = inventories[3]),
            ProductInventory(product = products[20], inventory = inventories[4]),
            ProductInventory(product = products[21], inventory = inventories[4]),
            ProductInventory(product = products[22], inventory = inventories[4]),
            ProductInventory(product = products[23], inventory = inventories[4]),
            ProductInventory(product = products[24], inventory = inventories[4])
        ))
    }
}

/*
H2 Console URL: http://localhost:8080/h2 (login: sa - password: )
select * from tb_deliver;
select * from tb_inventory;
select * from tb_payment_type;
select * from tb_product;
select * from tb_product_inventory;
select * from tb_product_purchase;
select * from tb_purchase;
*/
// Swagger URL: http://localhost:8080/swagger-ui/index.html
// RabbitMQ URL: http://localhost:15672 (login: guest - password: guest)
fun main(args: Array<String>) {
    runApplication<ApplicationBoot>(*args)
}