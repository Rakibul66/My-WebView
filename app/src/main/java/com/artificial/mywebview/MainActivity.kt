package com.artificial.mywebview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.airbnb.lottie.model.content.CircleShape
import com.artificial.mywebview.data.LoaderIntro
import com.artificial.mywebview.data.OnBoardingData
import com.artificial.mywebview.ui.theme.Grey300
import com.artificial.mywebview.ui.theme.MyWebViewTheme
import com.artificial.mywebview.ui.theme.RedLight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagerApi::class)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        setContent {
            MyWebViewTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainFunction()
                }
            }
        }
    }

    @ExperimentalPagerApi
    @Preview(showBackground = true)
    @Composable
    fun PreviewFunction() {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainFunction()
        }
    }


    @ExperimentalPagerApi
    @Composable
    fun MainFunction() {
        val items = ArrayList<OnBoardingData>()
        items.add(
            OnBoardingData(
                R.raw.one,
                "Android Development",
                "We provide software solution based on kotlin,java,flutter,swiftui"
            )
        )
        items.add(
            OnBoardingData(
                R.raw.two,
                "Web Development",
                "Dont worry! We have better desktop view web site solution"
            )
        )
        items.add(
            OnBoardingData(
                R.raw.four,
                "You have your order in minutes!",
                "Easy ordering and fast transportation"
            )
        )
        val pagerState = rememberPagerState(
            pageCount = items.size,
            initialOffscreenLimit = 2,
            infiniteLoop = false,
            initialPage = 0
        )

        OnBoardingPager(
            item = items,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        )
    }

    @ExperimentalPagerApi
    @Composable
    fun OnBoardingPager(
        item: List<OnBoardingData>,
        pagerState: PagerState,
        modifier: Modifier = Modifier,
    ) {
        Box(modifier = modifier) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    Column(
                        modifier = Modifier
                            .padding(60.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoaderIntro(
                            modifier = Modifier
                                .size(200.dp)
                                .fillMaxWidth()
                                .align(alignment = Alignment.CenterHorizontally), item[page].image
                        )
                        Text(
                            text = item[page].title,
                            modifier = Modifier.padding(top = 50.dp),
                            color = Color.Black,
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = item[page].desc,
                            modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                            color = Color.Black,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                PagerIndicator(item.size, pagerState.currentPage)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                BottomSection(pagerState.currentPage, onSkipClick = {
                    pagerState.pageCount + 2
                }, onNextClick = {
                    if (pagerState.currentPage == item.size - 1) {
                        exitProcess(0)
                    } else {
                        pagerState.pageCount + 1
                    }
                }, isSkipClicked = true)
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun rememberPagerState(
        @IntRange(from = 0) pageCount: Int,
        @IntRange(from = 0) initialPage: Int = 0,
        @FloatRange(from = 0.0, to = 1.0) initialPageOffset: Float = 0f,
        @IntRange(from = 1) initialOffscreenLimit: Int = 1,
        infiniteLoop: Boolean = false
    ): PagerState = rememberSaveable(
        saver = PagerState.Saver
    ) {
        PagerState(
            pageCount = pageCount,
            currentPage = initialPage,
            currentPageOffset = initialPageOffset,
            offscreenLimit = initialOffscreenLimit,
            infiniteLoop = infiniteLoop
        )
    }

    @Composable
    fun PagerIndicator(
        size: Int,
        currentPage: Int
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 60.dp)
        ) {
            repeat(size) {
                Indicator(isSelected = it == currentPage)
            }
        }
    }

    @Composable
    fun Indicator(isSelected: Boolean) {
        val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

        Box(
            modifier = Modifier
                .padding(1.dp)
                .height(10.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(
                    if (isSelected) RedLight else Grey300.copy(alpha = 0.5f)
                )
        )
    }

    @ExperimentalPagerApi
    @Composable
    fun BottomSection(
        currentPage: Int,
        onSkipClick: () -> Unit,
        onNextClick: () -> Unit,
        isSkipClicked: Boolean,
    ) {
        val context = LocalContext.current
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                .fillMaxWidth()
        ) {
            if (currentPage != 2) {
                if (isSkipClicked) {
                    Text(
                        text = "Skip",
                        color = RedLight,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .clickable(onClick = {
                                val intent = Intent(context, WebviewActivity::class.java).apply {
                                }
                                context.startActivity(intent)
                            })
                    )
                }
            }
            if (currentPage == 2) {
                Text(
                    text = "Done",
                    color = RedLight,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .clickable(onClick = {
                            val intent = Intent(context, WebviewActivity::class.java).apply {
                            }
                            context.startActivity(intent)
                        })
                )
            } else {
                Text(
                    text = "Next",
                    color = RedLight,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .clickable(onClick = {
                            onNextClick()
                        })
                )
            }
        }
    }
}

