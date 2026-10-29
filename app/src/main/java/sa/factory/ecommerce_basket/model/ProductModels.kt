package sa.factory.ecommerce_basket.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class Product(
    val requestId: String,
    val items: List<Item>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: ProductStatus = ProductStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class ProductStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
