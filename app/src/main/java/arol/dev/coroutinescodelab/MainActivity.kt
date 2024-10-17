package arol.dev.coroutinescodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import arol.dev.coroutinescodelab.ui.theme.CoroutinesCodeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val fakeStoreViewModel: FakeStoreViewModel by viewModels {
            FakeStoreViewModel.Factory
        }
        setContent {
            CoroutinesCodeLabTheme {
                FakeStoreApp(fakeStoreViewModel)
            }

            LaunchedEffect(Unit) {
                fakeStoreViewModel.fetchProducts()
            }
        }
    }
}
