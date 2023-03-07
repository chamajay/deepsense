package com.codestack.deepsense.presentation.suggestions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.suggestionspage.*

@Preview(showBackground = true)
@Composable
fun SuggestionScreen() {
    Surface {
        Column(
            modifier = Modifier
//            .background(color = MaterialTheme.colors.background)
                .fillMaxSize()
        ) {


            FilterBoxes()
            HorizontalBoxes()
            VerticalBoxes(verticalContent = VerticalContent("Mood video","heythere","3 mins","Play now",
                Color.Green, Color.Black)
            )



        }
    }
}


@Composable
fun FilterBoxes() {
    val filterOptions = FILTER_CONTENT_LIST
    LazyRow(
        Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(filterOptions.size) {
            ChipComponent(filter = filterOptions[it])

        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipComponent(filter: FilterContent) {
    val contentColor = filter.contentColor
    val chipBackground = filter.backgroundColor
    val filterText = filter.filterText

    FilterChip(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(2.dp),
        colors = FilterChipDefaults.filterChipColors(contentColor),
        border = null,
        selected = true,
        shape = RoundedCornerShape(16.dp),
        label = {
            Text(text = filterText)

        }

    )
}

@Composable
fun HorizontalBoxes() {
    val horizontalOptions = HORIZONTAL_CONTENT_LIST
    LazyRow(
        Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(horizontalOptions.size) {
            HorizontalChipComponent(horizontalContent = horizontalOptions[it])

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalChipComponent(horizontalContent: HorizontalContent) {
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.primary
        )


    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp)
        ) {


            Text(
                text = horizontalContent.title,
                style = MaterialTheme.typography.h6,
                color = Color.White,


                )

            Text(
                text = horizontalContent.description,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 8.dp)

            )

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalBoxes(verticalContent: VerticalContent) {
    val verticalOptions = VERTICAL_CONTENT_LIST
    
    Column(

        modifier = Modifier.padding( start = 32.dp, end = 32.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
    //            .padding( bottom = 8.dp)
                .height(150.dp),
//            verticalAlignment = Alignment.Center
        ) {
            verticalOptions.forEach { item ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier

                        .width(160.dp)
                        .padding(end=16.dp,)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = verticalContent.title,
                            style = MaterialTheme.typography.h6,
                        )

                        Text(
                            text = verticalContent.description,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
//                .padding( top = 8.dp)
                .height(150.dp)
        ) {
            verticalOptions.forEach { item ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .width(160.dp)
                        .padding(end=16.dp, top = 8.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = verticalContent.title,
                            style = MaterialTheme.typography.h6,
                        )

                        Text(
                            text = verticalContent.description,
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
        
    }


}


