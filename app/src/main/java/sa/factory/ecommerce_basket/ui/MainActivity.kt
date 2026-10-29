package sa.factory.ecommerce_basket.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import sa.factory.ecommerce_basket.data.InMemoryProductRepository
import sa.factory.ecommerce_basket.model.Item

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = InMemoryProductRepository()
        val viewModel = ProductViewModel(repository)

        setContent {
            var currentScreen by remember { mutableStateOf("home") }
            var selectedItem by remember { mutableStateOf<Item?>(null) }

            when (currentScreen) {
                "details" -> {
                    ProductDetailsScreen(
                        item = selectedItem,
                        onBack = { currentScreen = "home" },
                        onProceedCheckout = { currentScreen = "checkout" }
                    )
                }
                "checkout" -> {
                    ProductCheckoutScreen(
                        onBack = { currentScreen = "details" },
                        onComplete = { currentScreen = "home" }
                    )
                }
                else -> {
                    ProductHomeScreen(
                        viewModel = viewModel,
                        onSelectItem = { item ->
                            selectedItem = item
                            currentScreen = "details"
                        },
                        onQuickCheckout = {
                            currentScreen = "checkout"
                        }
                    )
                }
            }
        }
    }
}
