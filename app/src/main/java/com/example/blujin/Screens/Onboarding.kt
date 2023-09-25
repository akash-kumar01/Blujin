package com.example.blujin.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.blujin.R
import com.example.blujin.ui.theme.bg
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Onboarding(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize(), color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            semicircle()
            val items = ArrayList<onboardingdata>()

            items.add(
                onboardingdata(
                    R.drawable.onboardingimg1,
                    "PERSONALIZED RECOMMENDATIONS",
                    "Explore jobs hand-picked for you based on your skills, experience, and preferences"
                )
            )

            items.add(
                onboardingdata(
                    R.drawable.onboardingimg1,
                    "HASSLE-FREE APPLICATIONS",
                    "Use the Smart Apply feature to automatically highlight your most relevant experiences in your job application"
                )
            )

            items.add(
                onboardingdata(
                    R.drawable.onboardingimg1,
                    "DEEP INSIGHTS",
                    "Get a 360-degree view of each opportunity without ever leaving the app"
                )
            )
            val pagerState = rememberPagerState(
                pageCount = items.size,//3
                initialOffscreenLimit = 2,//swipe to download next 2 img
                infiniteLoop = true,//
                initialPage = 0,
            )

            Column(modifier = Modifier.fillMaxSize()) {
                OnBoardingPager(
                    item = items, pagerState = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(color = Color.White),
                )


                Button(
                    onClick = { navController.navigate(route = "signin") },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),


                    ) {
                    Text(
                        text = "Let's Get Started",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun semicircle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(shape = RoundedCornerShape(bottomStart = 240.dp, bottomEnd = 240.dp))
            .background(color = bg),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = "blujin",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 26.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(bottom = 80.dp)
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<onboardingdata>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {

    //auto horizontal changer
    // Define a delay between page changes-2 seconds
    val autoScrollDelay = 2000

    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(autoScrollDelay.toLong())
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % item.size)
        }
    }

    Box(modifier = modifier.background(MaterialTheme.colorScheme.surface)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .padding(18.dp)
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .fillMaxSize(0.7f)
                    )

                    Text(
                        text = item[page].title,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = item[page].desc,
                        modifier = Modifier.padding(start = 28.dp, end = 28.dp),
                        color = Color.Gray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 12.sp

                    )
                }
            }

            PagerIndicator(item.size, pagerState.currentPage)
        }
    }
}

@Composable
fun PagerIndicator(size: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 1.dp)
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
                if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.5f)
            )
    )
}






