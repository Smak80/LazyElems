package ru.smak.lazyelems

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.smak.lazyelems.ui.theme.LazyElemsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val objects = List(10){
            SomeObject(
                "Заголовок ${it+1}",
                if (it % 2 != 0) "Это текст, расположенный в объекте ${it+1}" else "",
                "Примечание к объекту №${it+1}"
            )
        }
        setContent {
            val testVar = remember { Random.nextBoolean() }
            LazyElemsTheme {
                Column {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(objects){
                            ObjectCard(obj = it, Modifier.fillMaxWidth())
                        }
                    }
                    /*LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        items(objects) {
                            ObjectCard(obj = it, Modifier.fillMaxWidth())
                        }
                        item {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(8.dp)
                            ) {
                                items(objects){
                                    ObjectCard(obj = it, Modifier.fillMaxWidth())
                                }
                            }
                        }
                    }*/
                    if (testVar){
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(200.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(objects){
                                ObjectCard(obj = it, Modifier.fillMaxWidth())
                            }
                        }
                    } else {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Adaptive(200.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalItemSpacing = 8.dp
                        ) {
                            items(objects){
                                ObjectCard(obj = it, Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ObjectCard(
    obj: SomeObject,
    modifier: Modifier = Modifier,
){
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = obj.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
                Divider(modifier = Modifier.padding(bottom = 16.dp, top = 8.dp))
            }
            if (obj.text.isNotBlank()) {
                Text(
                    text = obj.text,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp
                )
            }
            Text(
                text = obj.footnote,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun ObjectCardPreview(){
    val o = SomeObject(
        "Тестовый заголовок",
        "Тестовый текст",
        "Сноска"
    )
    Column() {
        ObjectCard(obj = o, modifier = Modifier.fillMaxWidth())
    }
}