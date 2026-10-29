package sa.factory.ecommerce_basket.data

import sa.factory.ecommerce_basket.model.Product
import sa.factory.ecommerce_basket.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ProductRepository {
    fun getAvailableCatalog(): Flow<List<Item>>
    fun getActiveRequest(): Flow<Product?>
    suspend fun submitRequest(request: Product): Result<String>
}

class InMemoryProductRepository : ProductRepository {
    private val _catalog = MutableStateFlow<List<Item>>(listOf(
        Item("1", "هاتف ذكي", "هاتف ذكي متطور مع أحدث المواصفات", 5000L, "verified"),
        Item("2", "لابتوب للألعاب", "لابتوب قوي للألعاب والتصميم", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<Product?>(null)

    override fun getAvailableCatalog(): Flow<List<Item>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<Product?> = _active.asStateFlow()

    override suspend fun submitRequest(request: Product): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
