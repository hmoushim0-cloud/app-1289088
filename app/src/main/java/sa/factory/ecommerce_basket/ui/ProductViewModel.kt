package sa.factory.ecommerce_basket.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.ecommerce_basket.data.ProductRepository
import sa.factory.ecommerce_basket.model.Product
import sa.factory.ecommerce_basket.model.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    val catalog: StateFlow<List<Item>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<Product?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: Product) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
