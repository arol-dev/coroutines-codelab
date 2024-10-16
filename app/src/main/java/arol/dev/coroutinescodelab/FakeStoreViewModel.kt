package arol.dev.coroutinescodelab

import androidx.lifecycle.ViewModel

class FakeStoreViewModel(
    private val fakeStoreRepository: FakeStoreRepository
) : ViewModel() {
    fun getProducts(): Result<List<Product>> =
        fakeStoreRepository.getProducts()
}