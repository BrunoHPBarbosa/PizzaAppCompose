package com.example.meuappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meuappcompose.ui.theme.MeuAppComposeTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PizzaScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaScreen() {
    val pizzaImg = listOf(
        R.drawable.salami,
        R.drawable.four_chees,
        R.drawable.capricciosa,
        R.drawable.mozzarella,
        R.drawable.prosciutto
    )
    val initialPage = 2
    val pagerState = rememberPagerState(
        pageCount = { pizzaImg.size },
        initialPage = initialPage

    )

    val imageSize = 250.dp

val rectShape = RoundedCornerShape(
    topStart = CornerSize(0.dp),
    topEnd = CornerSize(0.dp),
    bottomStart = CornerSize(100.dp),
    bottomEnd = CornerSize(100.dp)
)

Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(440.dp),
    contentAlignment = Alignment.TopCenter
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(440.dp)
            .clip(rectShape)
            .background(color = Color.Blue)
    )
}

Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp),
    contentAlignment = Alignment.TopStart
){
    Image(
        painter = painterResource(id = R.drawable.menu),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)



    )
}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            val pageOffset =
                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(imageSize)


            ) {
                Image(
                    painter = painterResource(id = pizzaImg[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .graphicsLayer {
                            val scale = if (pageOffset < 1f) 1f - (0.25f * pageOffset) else 0.75f
                            scaleX = scale
                            scaleY = scale
                            val rotationAngle = pageOffset * 360f
                            rotationZ = -rotationAngle

                        }
                        .shadow(
                            elevation = if (pageOffset < 1f) 5.dp else 0.dp,
                            shape = CircleShape

                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface)
                )
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    MeuAppComposeTheme {
        PizzaScreen()
    }
}
