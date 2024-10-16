package arol.dev.coroutinescodelab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeStoreViewModel(
    private val fakeStoreRepository: FakeStoreRepository
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun fetchProducts() {
        _loading.value = true
        val result = fakeStoreRepository.getProducts()
        if (result.isSuccess) {
            _products.value = result.getOrDefault(emptyList())
        }
        _loading.value = false
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val httpClient = HttClientImpl()
                val fakeStoreRepository = FakeStoreRepositoryImpl(httpClient)
                FakeStoreViewModel(fakeStoreRepository)
            }
        }
    }
}