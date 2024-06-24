package com.example.meuappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.meuappcompose.ui.theme.MeuAppComposeTheme

class MainActivity : ComponentActivity() {
    private val viewModel :PizzaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PizzaScreen(viewModel = viewModel)

                }
            }
        }
    }
}


// preview para mostrar como esta ficando a ui
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    MeuAppComposeTheme {
        val viewModel = PizzaViewModel()
PizzaScreen(viewModel = viewModel)


    }
}
