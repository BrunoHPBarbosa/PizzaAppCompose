package com.example.meuappcompose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

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
fun ButtonList(selectedSize: String, onSizeSelected: (String) -> Unit){
    val coroutineScope = rememberCoroutineScope()
    Row(modifier = Modifier.padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(-25.dp)){

        PizzaTamanhoBtn(
            text = "S",
            isSelected = selectedSize == "S"
        ){
            coroutineScope.launch { onSizeSelected("S") }
        }
        PizzaTamanhoBtn(
            text = "M",
            isSelected = selectedSize == "M"
        ){
            coroutineScope.launch { onSizeSelected("M") }
        }
        PizzaTamanhoBtn(
            text = "L",
            isSelected = selectedSize == "L"
        ){
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
            containerColor = colorResource(id = R.color.laranja)
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
