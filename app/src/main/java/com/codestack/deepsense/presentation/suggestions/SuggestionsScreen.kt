package com.codestack.deepsense.presentation.suggestions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.suggestionspage.FILTER_CONTENT_LIST
import com.example.suggestionspage.FilterContent
import com.example.suggestionspage.HORIZONTAL_CONTENT_LIST
import com.example.suggestionspage.HorizontalContent

@Preview(showBackground = true)
@Composable
fun SuggestionScreen(){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.background)
        .fillMaxSize()
    ){
        Column {

            FilterBoxes()
            HorizontalBoxes()
            VerticalBoxes()




        }
    }
}




@Composable
fun FilterBoxes(){
    val filterOptions = FILTER_CONTENT_LIST
    LazyRow(
        Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(filterOptions.size){
            chipComponent(filter = filterOptions[it])

        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun chipComponent(filter: FilterContent) {
    val contentColor = filter.contentColor
    val chipBackground = filter.backgroundColor
    val filterText = filter.filterText

    FilterChip(
        onClick = { /*TODO*/ },
        modifier= Modifier
            .padding(2.dp),
        colors= FilterChipDefaults.filterChipColors(contentColor),
        border = null,
        selected= true,
        shape = RoundedCornerShape(16.dp),
        label = {
            Text(text = filterText)

        }

    )
}

@Composable
fun HorizontalBoxes(){
    val horizontalOptions = HORIZONTAL_CONTENT_LIST
    LazyRow(
        Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(horizontalOptions.size){
            horizontalChipComponent(horizontalContent = horizontalOptions[it])

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun horizontalChipComponent (horizontalContent: HorizontalContent){
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .height(200.dp)
            .width(200.dp),




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

                )

            Text(
                text = horizontalContent.description,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

//            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
//                Card(
//                    colors = ChipDefaults.chipColors(
//                        contentColor = Black,
//                        backgroundColor = meditationTypes.timeBackgroundColor
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(text = meditationTypes.duration, fontFamily = nunitoMedium)
//                }
//                Chip(
//                    onClick = { /*TODO*/ },
//                    colors = ChipDefaults.chipColors(
//                        contentColor = Black,
//                        backgroundColor = Color.White
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(text = meditationTypes.teacher, fontFamily = nunitoMedium)
//                }
        }
    }
}


@Composable
fun VerticalBoxes(){
    val horizontalOptions = HORIZONTAL_CONTENT_LIST
    LazyColumn(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(horizontalOptions.size){
            verticalChipComponent(horizontalContent = horizontalOptions[it])

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun verticalChipComponent (horizontalContent: HorizontalContent){
    Card(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .height(150.dp)
            .width(170.dp),




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

                )

            Text(
                text = horizontalContent.description,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(top = 8.dp)
            )

//            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
//                Card(
//                    colors = ChipDefaults.chipColors(
//                        contentColor = Black,
//                        backgroundColor = meditationTypes.timeBackgroundColor
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(text = meditationTypes.duration, fontFamily = nunitoMedium)
//                }
//                Chip(
//                    onClick = { /*TODO*/ },
//                    colors = ChipDefaults.chipColors(
//                        contentColor = Black,
//                        backgroundColor = Color.White
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(text = meditationTypes.teacher, fontFamily = nunitoMedium)
//                }
        }
    }
}


