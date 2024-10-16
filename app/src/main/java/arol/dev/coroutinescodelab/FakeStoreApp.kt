package arol.dev.coroutinescodelab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FakeStoreApp(fakeStoreViewModel: FakeStoreViewModel) {
    val products by fakeStoreViewModel.products.collectAsState()
    val loading by fakeStoreViewModel.loading.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (loading) {
            Box(
                modifier = Modifier.padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (products.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "Products")
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(products) { product ->
                        ListItem(
                            headlineContent = {
                                Text(text = product.title)
                            },
                            supportingContent = {
                                Text(text = product.description)
                            },
                            trailingContent = {
                                Text(text = product.price.toString())
                            },
                        )
                    }
                }
            }
        }
    }
}