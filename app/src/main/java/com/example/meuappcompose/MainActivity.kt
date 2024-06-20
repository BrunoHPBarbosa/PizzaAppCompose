package com.example.meuappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
// lista minhas imagens de pizza
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun PizzaScreen() {
    val pizzaImg = listOf(
        R.drawable.salami,
        R.drawable.four_chees,
        R.drawable.capricciosa,
        R.drawable.mozzarella,
        R.drawable.prosciutto
    )

    val pizzaName = listOf(
       "Salame",
        "Four Chees",
        "Capricciosa",
        "Mozzarella",
        "Prosciutto"
    )
val pizzaValor = listOf(
    "€19,90",
    "€14,90",
    "€16,90",
    "€17,90",
    "€15,90"

)
    // inicia o item central com a pizza capricciosa , assim ja fica os dois itens ao lado
    val initialPage = 2
    val pagerState = rememberPagerState( //guarda a posicao atual do item , mesmo fazndo a rolagem
        pageCount = { pizzaImg.size },
        initialPage = initialPage

    )

    val imageSize = 250.dp

// cia um retangulo de background com 2 bordas arredondadas

val rectShape = RoundedCornerShape(
    topStart = CornerSize(0.dp),
    topEnd = CornerSize(0.dp),
    bottomStart = CornerSize(100.dp),
    bottomEnd = CornerSize(100.dp)
)

Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(360.dp),
    contentAlignment = Alignment.TopCenter
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .clip(rectShape)
            .background(color = Color.Blue)
    )
}
// cria uma box com uma imagem para adicionar o icone ao topo da ui
Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp,)
        .padding(start = 10.dp),
    contentAlignment = Alignment.TopStart
){
    Image(
        painter = painterResource(id = R.drawable.menu),
        contentDescription = null,
        modifier = Modifier
            .size(48.dp)



    )
}


    val pages = pagerState.currentPage
    val currentPages = pizzaName[pages]
    val pizzaPreco = pizzaValor[pages]


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 400.dp)

            ) {
                AnimatedContent(
                    targetState = currentPages,
                    transitionSpec = {
                        if (targetState != initialState) {
                            slideInVertically(tween(durationMillis = 800)) { it } with slideOutVertically { -it }
                        } else {
                            slideInVertically (tween(durationMillis = 800)){ -it }  with slideOutVertically { it }
                        }

                    }
                ) { targetName ->
                    Text(
                        text = targetName,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 250.dp, end = 220.dp)
            ) {
                AnimatedContent(
                    targetState = pizzaPreco,
                    transitionSpec = {
                        if (targetState != initialState) {
                            slideInVertically(tween(durationMillis = 1000)) { it }  with slideOutVertically { -it }
                        } else {
                            slideInVertically (tween(durationMillis = 1000)){ -it }  with slideOutVertically { it }
                        }
                    }
                ) { targetPrice ->
                    Text(
                        text = targetPrice,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Start,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }


// cria o horizontal pager com uma box para posicionar os itens corretamente na ui
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(top = 180.dp),
            contentAlignment = Alignment.TopCenter
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
                Image( // nessa image tem uma pequena animacao que faz a imagem rodar no eixo Z
                    painter = painterResource(id = pizzaImg[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(250.dp)
                        .graphicsLayer { // animacao do eixo Z que gira 360, e faz com que os itens que nao estao ao centro fiquem menores
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

// preview para mostrar como esta ficando a ui
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    MeuAppComposeTheme {
        PizzaScreen()
    }
}
