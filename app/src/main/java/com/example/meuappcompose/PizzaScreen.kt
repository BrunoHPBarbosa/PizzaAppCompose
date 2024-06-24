package com.example.meuappcompose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meuappcompose.ListaItens.pizzaDescricao
import com.example.meuappcompose.ListaItens.pizzaImg
import com.example.meuappcompose.ListaItens.pizzaName
import com.example.meuappcompose.ListaItens.pizzaValor
import kotlin.math.absoluteValue

// lista minhas imagens de pizza
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun PizzaScreen(viewModel: PizzaViewModel) {

    val pizzaLista = ListaItens
    val initialPage = 2
    val pagerState = rememberPagerState( //guarda a posicao atual do item , mesmo fazndo a rolagem
        pageCount = { pizzaImg.size },
        initialPage = initialPage

    )
    var selectedSize by remember { mutableStateOf("S") }
    val coroutineScope = rememberCoroutineScope()
    val imageSize = 250.dp


    val pages = pagerState.currentPage
    val currentPages = pizzaName[pages]
    val pizzaPreco = pizzaValor[pages]
    val pizzaDesc = pizzaDescricao[pages]

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
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(rectShape)
                .background(color = colorResource(id = R.color.blueP))
        )
    }
// cria uma box com uma imagem para adicionar o icone ao topo da ui
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp,)
            .padding(start = 30.dp),
        contentAlignment = Alignment.TopStart
    ) {
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
        Box(
            modifier = Modifier
                .padding(bottom = 540.dp)
        ) {

            AnimatedContent(
                targetState = currentPages,
                transitionSpec = {
                    if (targetState != initialState) {
                        slideInVertically(tween(durationMillis = 1000)) { it } with slideOutVertically { -it }
                    } else {
                        slideInVertically(tween(durationMillis = 1000)) { -it } with slideOutVertically { it }
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
    }
//conjunto para o PizzaPrice

    Box(
        modifier = Modifier
            .padding(top = 450.dp, end = 50.dp, start = 50.dp, bottom = 10.dp)
    ) {
        Column {
            AnimatedContent(
                targetState = pizzaPreco[selectedSize] ?: "",
                transitionSpec = {
                    if (targetState != initialState) {
                        slideInVertically(tween(durationMillis = 1000)) { it } with slideOutVertically { -it }
                    } else {
                        slideInVertically(tween(durationMillis = 1000)) { -it } with slideOutVertically { it }
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

            //conjunto do DescricaoPizza

            AnimatedContent(
                targetState = pizzaDesc,
                transitionSpec = {
                    if (targetState != initialState) {
                        slideInVertically(tween(durationMillis = 1000)) { it } with slideOutVertically { -it }
                    } else {
                        slideInVertically(tween(durationMillis = 1000)) { -it } with slideOutVertically { it }
                    }
                }
            ) { targetDes ->
                Text(
                    text = targetDes,
                    fontSize = 15.sp,
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


// cria o horizontal pager com uma box para posicionar os itens corretamente na ui
    Box(
        modifier = Modifier
            .fillMaxSize()
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
    Box(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        ButtonList(selectedSize = selectedSize) { newSize ->
            selectedSize = newSize
            viewModel.selectSize(newSize)
        }
    }



    // Botão de checkout
    Box(
        modifier = Modifier
            .padding(bottom = 6.dp, top = 700.dp)
    ) {
        Checkout(onClick = { })
    }
}
