package com.example.gratitudeassignment.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.gratitudeassignment.R
import com.example.gratitudeassignment.model.data.local.DailyZenItemEntity
import com.example.gratitudeassignment.viewmodel.MainViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun MainContent(mainViewModel: MainViewModel = hiltViewModel()){

    val dateId = remember {
        mutableIntStateOf(0)
    }

    val date =  remember {
        mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")))
    }

    val day = remember {
        mutableStateOf("Today")
    }

    val dailyZenData = mainViewModel.data.value
    val dailyZenDataItems = mainViewModel.dataFromDb.collectAsState().value

    if (dailyZenData.loading == true){
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
    else if(dailyZenDataItems.isNotEmpty()){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) Color(0xFF201A1B) else Color(0xFFFFFFFF)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                IconButton(
                    onClick = {
                        dateId.intValue += 1
                        date.value = LocalDate
                            .now()
                            .minusDays(dateId.intValue.toLong())
                            .format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                            .toString()
                        day.value = currentDay(date = date.value, dateId = dateId.intValue)
                        mainViewModel.getCustomDailyZen(customDate = date.value)
                    },
                    enabled = (dateId.intValue < 7)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Arrow Left",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Text(
                    text = day.value,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically),
                    style = TextStyle(
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                    )
                )
                IconButton(
                    onClick = {
                        dateId.intValue -= 1
                        date.value = LocalDate
                            .now()
                            .minusDays(dateId.intValue.toLong())
                            .format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                            .toString()
                        day.value = currentDay(date = date.value, dateId = dateId.intValue)
                        mainViewModel.getCustomDailyZen(customDate = date.value)
                    },
                    enabled = (dateId.intValue > 0)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Arrow Right",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(state = ScrollState(0), true),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                dailyZenDataItems.forEach {
                    CustomizedCard(data = it)
                }

                Image(
                    painter = painterResource(id = R.drawable.frame),
                    contentDescription = "frame",
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .size(125.dp)
                        .aspectRatio(127f / 128f)
                )
                Text(
                    text = "That’s the Zen for today!\n See you tomorrow :)",
                    modifier = Modifier
                        .padding(bottom = 75.dp),
                    fontSize = 12.sp
                )
            }
        }
    }
    else{
        Column(modifier = Modifier.
        fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Something went wrong!\nPlease try again.",
                textAlign = TextAlign.Center)

            CustomIconButton(imageVector = Icons.Outlined.Refresh, contentDescription = "Refresh") {
                mainViewModel.getCustomDailyZen(customDate = date.value)
            }
        }
    }
}

fun currentDay(date: String, dateId: Int): String {
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString()
    val yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString()

    return when (date) {
        today -> "Today"
        yesterday -> "Yesterday"
        else -> {
            val currentDate = LocalDate.now().minusDays(dateId.toLong())
            "${currentDate.month}, ${currentDate.dayOfMonth}"
        }
    }
}

@Composable
fun CustomizedCard(data: DailyZenItemEntity){

    val showBottomSheet = remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        .border(
            width = 1.dp,
            color = if (isSystemInDarkTheme()) Color(0xFF524345) else Color(
                0xFFD7C1C3
            ),
            shape = RoundedCornerShape(size = 12.dp)
        )
        .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF181212) else Color(
            0xFFFFF8F7
        )
        )
    ) {
        Row(modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start) {
            Text(
                text = data.themeTitle,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(600)
                )
            )
        }

        val context = LocalContext.current
        val imageRequest = ImageRequest.Builder(context)
            .data(data.dzImageUrl)
            .diskCacheKey(data.dzImageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()

        AsyncImage(model = imageRequest,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1011f / 1011f))

        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically) {

            if (data.primaryCTAText == "Read Full Post"){

                val uriHandler = LocalUriHandler.current

                Card(modifier = Modifier
                    .clickable(onClick = {
                        uriHandler.openUri(data.articleUrl)
                    }),
                    shape = RoundedCornerShape(corner = CornerSize(100.dp)),
                    colors = CardDefaults.cardColors(containerColor = if (isSystemInDarkTheme()) Color(0xFF5C3F43) else Color(
                        0xFFFFD9DD
                    ),
                        contentColor = if (isSystemInDarkTheme()) Color(0xFFFFD9DD) else Color(
                            0xFF2C1518
                        ))
                ) {
                    Row(modifier = Modifier
                        .padding(10.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Article, contentDescription = "Read Full Post")
                        Text(
                            text = "Read Full Post",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(600)
                            )
                        )
                    }
                }
            }

            IconButton(onClick = {
                showBottomSheet.value = true
            },
                colors  = IconButtonDefaults
                    .iconButtonColors(
                        containerColor = if (isSystemInDarkTheme()) Color(0xFF5C3F43) else Color(
                            0xFFFFD9DD
                        ),
                        contentColor = if (isSystemInDarkTheme()) Color(0xFFFFD9DD) else Color(
                            0xFF2C1518
                        )
                    )) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
            }

            CustomIconButton(imageVector = Icons.Outlined.BookmarkBorder, contentDescription = "Bookmark"){

            }
        }
        CustomizedBottomSheet(showBottomSheet = showBottomSheet, data = data)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizedBottomSheet(showBottomSheet: MutableState<Boolean>, data: DailyZenItemEntity){

    val sheetState = rememberModalBottomSheetState()

    val context = LocalContext.current
    val packageManager = context.packageManager

    if (showBottomSheet.value){
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            containerColor = if(isSystemInDarkTheme()) Color(0xFF201A1B) else Color(
                0xFFFEF1F1
            ),
            dragHandle = {}
        ) {
            // Sheet content
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Row(modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Text(text = data.primaryCTAText,
                        fontWeight = FontWeight(600))
                    IconButton(onClick = {
                        showBottomSheet.value = false
                    }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                AsyncImage(model = data.dzImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .aspectRatio(1011f / 1011f)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFD7C1C3),
                            shape = RoundedCornerShape(size = 12.dp)
                        ))

                if (data.text.isNotBlank()){
                    Row(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50.dp))
                        .background(color = Color(0xFFFFD9DD)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {

                        val cardColour = remember {
                            mutableLongStateOf(0xFFFFD9DD)
                        }

                        val textColour = remember {
                            mutableLongStateOf(0xFFEA436B)
                        }

                        val isCopied = remember {
                            mutableStateOf("Copy")
                        }

                        Text(text = data.text+" - "+data.author,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                                .weight(0.8F)
                                .wrapContentWidth(align = Alignment.Start),
                            color = Color(0xFF201A1B),
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(400),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1)

                        Card(modifier = Modifier
                            .padding(5.dp)
                            .clickable(onClick = {
                                cardColour.longValue = 0xFFEA436B
                                textColour.longValue = 0xFFFFFFFF
                                isCopied.value = "Copied"

                                val clipboardManager =
                                    context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                                val clipData: ClipData = ClipData.newPlainText(
                                    "text",
                                    if (data.author.isBlank()) data.text else data.text + " - " + data.author
                                )
                                clipboardManager.setPrimaryClip(clipData)

                            }),
                            shape = RoundedCornerShape(corner = CornerSize(100.dp)),
                            colors = CardDefaults.cardColors(containerColor = Color(cardColour.longValue)),
                            border = BorderStroke(width = 1.dp, color = Color(0xFF847374))
                        ) {
                            Text(text = isCopied.value,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .wrapContentWidth(align = Alignment.End),
                                color = Color(textColour.longValue),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                }
                Divider(modifier = Modifier.padding(5.dp),
                    color = Color(0xFFD7C1C3)
                )
                
                Text(text = "Share",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start)

                Row(modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start) {

                    if (isWhatsAppInstalled(packageManager)){
                        ShareIconButton(modifier = Modifier
                            .padding(16.dp)
                            .size(50.dp),id = R.drawable.whatsapp, contentDescription = "whatsapp") {
                            val url = "https://api.whatsapp.com/send"
                            val intent: Intent = try {
                                Intent(Intent.ACTION_SEND).apply {
                                    putExtra(Intent.EXTRA_TEXT, data.sharePrefix)
                                    type = "text/plain"
                                    `package` = "com.whatsapp"
                                }
                            } catch (e: PackageManager.NameNotFoundException) {
                                Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            }
                            context.startActivity(intent)
                        }
                    }

                    if(isInstagramInstalled(packageManager)){
                        ShareIconButton(modifier = Modifier
                            .padding(16.dp)
                            .size(50.dp), id = R.drawable.insta, contentDescription = "instagram") {
                            val url = "https://www.instagram.com/"
                            val intent: Intent = try {
                                Intent(Intent.ACTION_SEND).apply {
                                    putExtra(Intent.EXTRA_TEXT, data.sharePrefix)
                                    type = "text/plain"
                                    `package` = "com.instagram.android"
                                }
                            } catch (e: PackageManager.NameNotFoundException) {
                                Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            }
                            context.startActivity(intent)
                        }
                    }

                    if (isFacebookInstalled(packageManager)){
                        ShareIconButton(modifier = Modifier
                            .padding(16.dp)
                            .size(50.dp), id = R.drawable.fb, contentDescription = "facebook") {
                            val url = "https://www.facebook.com/"
                            val intent: Intent = try {
                                Intent(Intent.ACTION_SEND).apply {
                                    putExtra(Intent.EXTRA_TEXT, data.sharePrefix)
                                    type = "text/plain"
                                    `package` = "com.facebook.katana"
                                }
                            } catch (e: PackageManager.NameNotFoundException) {
                                Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            }
                            context.startActivity(intent)
                        }
                    }

                    CustomIconButton(modifier = Modifier
                        .padding(16.dp)
                        .size(50.dp), imageVector = Icons.Outlined.Download, contentDescription = "Download"){

                    }

                    CustomIconButton(modifier = Modifier
                        .padding(16.dp)
                        .size(50.dp), imageVector = Icons.Outlined.MoreHoriz, contentDescription = "More"){
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, data.sharePrefix)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
                }
            }
        }
    }
}



fun isWhatsAppInstalled(packageManager: PackageManager): Boolean {
    return try {
        packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
        true // WhatsApp is installed
    } catch (e: PackageManager.NameNotFoundException) {
        false // WhatsApp is not installed
    }
}

fun isInstagramInstalled(packageManager: PackageManager): Boolean {
    return try {
        packageManager.getPackageInfo("com.instagram.android", PackageManager.GET_ACTIVITIES)
        true // Instagram is installed
    } catch (e: PackageManager.NameNotFoundException) {
        false // Instagram is not installed
    }
}

fun isFacebookInstalled(packageManager: PackageManager): Boolean {
    return try {
        packageManager.getPackageInfo("com.facebook.katana", PackageManager.GET_ACTIVITIES)
        true // Facebook is installed
    } catch (e: PackageManager.NameNotFoundException) {
        false // Facebook is not installed
    }
}

@Composable
fun ShareIconButton(modifier: Modifier = Modifier, id: Int, contentDescription: String, onClicked: () -> Unit){
    IconButton(onClick = {
        onClicked()
    },
        modifier = modifier) {
        Image(painter = painterResource(id = id),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit)
    }
}

@Composable
fun CustomIconButton(modifier: Modifier = Modifier, imageVector: ImageVector, contentDescription: String, onClicked: () -> Unit){
    IconButton(onClick = {
                         onClicked()
                         },
        modifier = modifier,
        colors  = IconButtonDefaults
            .iconButtonColors(
                containerColor = if (isSystemInDarkTheme()) Color(0xFF5C3F43) else Color(
                    0xFFFFD9DD
                ),
                contentColor = if (isSystemInDarkTheme()) Color(0xFFFFD9DD) else Color(
                    0xFF2C1518
                )
            )) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}
