package arol.dev.coroutinescodelab

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

interface FakeStoreRepository {
    suspend fun getProducts(): Result<List<Product>>
}

class FakeStoreRepositoryImpl(
    private val httpClient: HttpClient
) : FakeStoreRepository {
    override suspend fun getProducts(): Result<List<Product>> = withContext(Dispatchers.IO) {
        runCatching {
            with(httpClient.openConnection(URL_PRODUCTS)) {
                requestMethod = "GET"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Accept", "application/json")
                val jsonAsText = inputStream.bufferedReader().readText()
                val productsAsJSONArray = JSONArray(jsonAsText)
                val products = mutableListOf<Product>()
                for (i in 0 until productsAsJSONArray.length()) {
                    val productAsJSONObject = productsAsJSONArray.getJSONObject(i)
                    products.add(Product.fromJSONObject(productAsJSONObject))
                }
                products
            }
        }
    }

    companion object {
        const val URL_PRODUCTS = "https://fakestoreapi.com/products"
    }
}