package com.tanriverdi.diceroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tanriverdi.diceroll.ui.theme.DiceRollTheme

// MainActivity, uygulamanın başlangıç noktasıdır
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Uygulamanın içerik düzenini ayarlamak için setContent kullanılır
        setContent {
            // Tema ayarları
            DiceRollTheme {
                // Uygulama için bir Surface bileşeni oluşturuyoruz
                Surface(
                    modifier = Modifier.fillMaxSize(), // Tüm ekranı kapla
                    color = MaterialTheme.colorScheme.background // Tema arka plan rengi
                ) {
                    // DiceRollerApp bileşenini yerleştiriyoruz
                    DiceRollerApp()
                }
            }
        }
    }
}

// Uygulamanın önizlemesi için kullanılan bileşen
@Preview
@Composable
fun DiceRollerApp() {
    // Ana bileşeni oluştur
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize() // Tüm alanı kapla
        .wrapContentSize(Alignment.Center) // İçeriği ortala
    )
}

// Zar görüntüleme ve buton bileşeni
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // Zar sonucunu tutmak için bir durum (state) değişkeni tanımlıyoruz
    var result by remember { mutableStateOf(1) } // Başlangıç değeri 1

    // Zar sonucuna göre hangi resmin gösterileceğini belirliyoruz
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6 // 6 durumu
    }

    // Dikey bir sütun düzeni oluşturuyoruz
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        // Zar resmini görüntüle
        Image(painter = painterResource(imageResource), contentDescription = result.toString())

        // Zarı atmak için bir buton oluşturuyoruz
        Button(
            onClick = {
                // Butona tıklandığında 1 ile 6 arasında rastgele bir sayı ata
                result = (1..6).random()
            },
        ) {
            // Buton üzerindeki metin
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }
    }
}
