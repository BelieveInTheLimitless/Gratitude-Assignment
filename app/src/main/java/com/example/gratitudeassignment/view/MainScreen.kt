package com.example.gratitudeassignment.view

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratitudeassignment.R

@Composable
fun MainContent(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Arrow Left")
            }
            Text(
                text = "Today",
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                )
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "Arrow Right")
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(state = ScrollState(0), true),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF524345),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFF181212), shape = RoundedCornerShape(size = 12.dp))
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start, ) {
                    IconButton(onClick = { /*TODO*/ }, colors  = IconButtonDefaults.iconButtonColors(containerColor = Color(
                        0xFF5C3F43
                    ), contentColor = Color(0xFFFFD9DD)
                    )) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }, colors  = IconButtonDefaults.iconButtonColors(containerColor = Color(
                        0xFF5C3F43
                    ),
                        contentColor = Color(0xFFFFD9DD)
                    )) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }

            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD7C1C3),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFFFF8F7), shape = RoundedCornerShape(size = 12.dp))
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }

            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD7C1C3),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFFFF8F7), shape = RoundedCornerShape(size = 12.dp))
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }

            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD7C1C3),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFFFF8F7), shape = RoundedCornerShape(size = 12.dp))
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }

            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD7C1C3),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFFFF8F7), shape = RoundedCornerShape(size = 12.dp))
                    ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }

            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD7C1C3),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFFFF8F7), shape = RoundedCornerShape(size = 12.dp))
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }

            Card(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD7C1C3),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .fillMaxWidth()
                .background(color = Color(0xFFFFF8F7), shape = RoundedCornerShape(size = 12.dp))
            ) {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "QUOTE OF THE DAY",
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600)
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.media),
                    contentDescription = "image description",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.statelayer), contentDescription = "Bookmark",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp))
                    }
                }
            }
        }
    }

}