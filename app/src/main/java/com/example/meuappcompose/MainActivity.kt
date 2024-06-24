package com.example.meuappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meuappcompose.ui.theme.MeuAppComposeTheme
import kotlinx.coroutines.launch
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
        "Four Cheese",
        "Capricciosa",
        "Margherita",
        "Prosciutto"
    )
    val pizzaValor = listOf(
        mapOf("S" to "€19,90", "M" to "€24,90", "L" to "36,90"),
        mapOf("S" to "€18,90", "M" to "€21,90", "L" to "30,90"),
        mapOf("S" to "€16,90", "M" to "€22,90", "L" to "31,90"),
        mapOf("S" to "€17,90", "M" to "€21,90", "L" to "30,90"),
        mapOf("S" to "€25,90", "M" to "€32,90", "L" to "48,90"),


        )
    val pizzaDescricao = listOf(
        "Uma pizza deliciosa que aconpanha: Molho de tomate,queijo , rodelas de salame e azeitonas",
        "Uma pizza deliciosa que aconpanha: Molho de tomate,queijo mussarela,queijo parmezao, queijo cheddar, queijo de cabra e azeitonas",
        "Uma pizza deliciosa que aconpanha: Molho de tomate,queijo ,champignon,cebola roxa e azeitonas",
        "Uma pizza deliciosa que aconpanha: Molho de tomate,queijo mussarela, tomate,margherita e azeitonas",
        "Uma pizza deliciosa que aconpanha: Molho de tomate,queijo e fatias de presunto",


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
// aqui esta a criacao da animacao para os textos de nomePizza e pizzaPreco

    val pages = pagerState.currentPage
    val currentPages = pizzaName[pages]
    var selectedSize by remember { mutableStateOf("S") }
    val pizzaPreco = pizzaValor[pages]
    val pizzaDesc = pizzaDescricao[pages]

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
        modifier = Modifier
            .padding(bottom = 16.dp)
    ) {
        ButtonList(selectedSize = selectedSize) { newSize ->
            selectedSize = newSize
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


@Composable
fun PizzaTamanhoBtn(text : String,isSelected : Boolean, onClick :  () -> Unit) {

    Button(onClick = onClick,
        modifier = Modifier
            .padding(top = 570.dp, start = 50.dp)
            .height(70.dp)
            .width(50.dp)
            .border(
                border = BorderStroke(2.dp, color = colorResource(id = R.color.blueb)),
                shape = RoundedCornerShape(24.dp)
            )
            .background(Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) colorResource(id = R.color.blueb) else Color.Transparent,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues()
    ) {
Text(
    text = text,
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    color = Color.Black
)
        
    }
}
@Composable
fun ButtonList(selectedSize:String,onSizeSelected: (String)-> Unit ){
    val coroutineScope = rememberCoroutineScope()
    Row(modifier = Modifier.padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(-25.dp)){

    PizzaTamanhoBtn(text = "S", isSelected = selectedSize == "S"){
        coroutineScope.launch { onSizeSelected("S") }
    }
        PizzaTamanhoBtn(text = "M", isSelected = selectedSize == "M"){
            coroutineScope.launch { onSizeSelected("M") }
        }
        PizzaTamanhoBtn(text = "L", isSelected = selectedSize == "L"){
            coroutineScope.launch { onSizeSelected("L") }
        }

    }

}

@Composable
fun Checkout(onClick: () -> Unit) {

        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(top = 6.dp, start = 50.dp, end = 50.dp, bottom = 0.dp)
                .height(65.dp)
                .fillMaxWidth(),
                    shape = RoundedCornerShape(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
                )
        ) {
            Text(
                text = "Order Now",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

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
