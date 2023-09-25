package com.example.blujin

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blujin.Screens.Onboarding
import com.example.blujin.Screens.Profilesetup
import com.example.blujin.Screens.signin

import com.example.blujin.ui.theme.BlujinTheme
import com.example.blujin.ui.theme.bg

import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlujinTheme {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    navigation()
                }
            }
        }
    }

    @Composable
    fun navigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") {
                splashscreen(navController = navController)
            }
            composable("onboarding") {
                Onboarding(navController=navController)
            }
            composable("signin") {
                signin(navController=navController)
            }
            composable("setuppage1") {
                Profilesetup(navController=navController)
            }

        }
    }

    @Composable
    fun splashscreen(navController: NavController) {
        val scale = remember {
            Animatable(0f)//start from 0 it means (blank to img) effect
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(targetValue = 1f,//effect complete and then set to 1
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)//0-2-1
                    }
                )
            )
            delay(2000)//after 2 sec it will navigate
            navController.navigate("onboarding")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = bg),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.splashimg),
                contentDescription = null,
                modifier = Modifier.scale(scale.value)//size changes so using scale.value
            )

        }
    }

}