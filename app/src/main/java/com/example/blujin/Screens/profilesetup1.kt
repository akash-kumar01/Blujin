package com.example.blujin.Screens


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.blujin.R

@Composable
fun Profilesetup(
    desc: String = "Resume",
    value: String = "1",
    size: Dp = 70.dp,//size of circle
    foregroundIndicatorColor: Color = Color(0xFF35898f),//color of foreground circle
    shadowColor: Color = Color.Black,//color of shadow
    indicatorThickness: Dp = 10.dp,//circle thickness
    progress: Float = 1f,//initial
    animationDuration: Int = 1000,
    navController: NavHostController
) {

    var progressRemember by remember {
        mutableFloatStateOf(-1f)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(30.dp)
                    )
                    .border(width = 2.dp, color = Color.LightGray),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Column(
                    Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .clip(
                                shape = RoundedCornerShape(30.dp)
                            )
                            .border(width = 2.dp, color = Color.LightGray),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Blujintext()
                            Profiletext()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // It remembers the data usage value

                                // This is to animate the foreground indicator
                                val dataUsageAnimate = animateFloatAsState(
                                    targetValue = progressRemember,//final state
                                    animationSpec = tween(
                                        durationMillis = animationDuration
                                    ), label = ""
                                )
                                // This is to start the animation when the activity is opened
                                LaunchedEffect(Unit) {
                                    progressRemember = progress
                                }//here progress remember = 1f


                                Column(
                                    modifier = Modifier.wrapContentSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(size),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Canvas(
                                            modifier = Modifier
                                                .size(size)
                                        ) {
                                            // For shadow
                                            drawCircle(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(shadowColor, Color.White),
                                                    center = Offset(
                                                        x = this.size.width / 2,
                                                        y = this.size.height / 2
                                                    ),//finding center of circle using box size
                                                    radius = this.size.height / 2//given
                                                ),
                                                radius = this.size.height / 2,
                                                center = Offset(
                                                    x = this.size.width / 2,
                                                    y = this.size.height / 2
                                                )
                                            )

                                            // This is the white circle that appears on the top of the shadow circle
                                            drawCircle(
                                                color = Color.White,
                                                radius = (size / 2 - indicatorThickness).toPx(),
                                                center = Offset(
                                                    x = this.size.width / 2,
                                                    y = this.size.height / 2
                                                )
                                            )

                                            // Convert the dataUsage to angle
                                            val sweepAngle =
                                                (dataUsageAnimate.value) * 360 / 100//100->out of 100

                                            // Foreground indicator
                                            drawArc(
                                                color = foregroundIndicatorColor,
                                                startAngle = 90f,//start from
                                                sweepAngle = sweepAngle,//make the arc to move by progress
                                                useCenter = false,//cover color from center -> true or false
                                                style = Stroke(
                                                    width = indicatorThickness.toPx(),
                                                    cap = StrokeCap.Round
                                                ),//denote the shape of arc ex-circle,square
                                                size = Size(
                                                    width = (size - indicatorThickness).toPx(),
                                                    height = (size - indicatorThickness).toPx()
                                                ),
                                                topLeft = Offset(
                                                    x = (indicatorThickness / 2).toPx(),
                                                    y = (indicatorThickness / 2).toPx()
                                                )
                                            )
                                        }

                                        // Display the data usage value
                                        DisplayText(value)

                                    }
                                    Text(text = desc, fontSize = 10.sp)


                                }
                                Divider1()
                                CircularProgressbar1(value = "2", desc = "Link profile")
                                Divider1()
                                CircularProgressbar1(value = "3", desc = "Preference")
                            }
                            Spacer(modifier = Modifier.padding(bottom = 35.dp))


                        }
                    }
                    EditableText()
                }
            }
            Resumetext()
            Resumedesc()
            Selectfile()
            Files()
        }
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Button(
                onClick = {
                    progressRemember = 100f
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = "Continue",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun Blujintext() {
    Text(text = "blujin", fontSize = 26.sp)
}

@Composable
fun Profiletext() {
    Text(text = "Setup your profile", fontSize = 28.sp, fontWeight = FontWeight(700))
}


@Composable
fun CircularProgressbar1(
    desc: String,
    value: String,
    size: Dp = 70.dp,//size of circle
    foregroundIndicatorColor: Color = Color(0xFF35898f),//color of foreground circle
    shadowColor: Color = Color.Black,//color of shadow
    indicatorThickness: Dp = 10.dp,//circle thickness
    progress: Float = 1f,//initial
    animationDuration: Int = 1000
) {
    // It remembers the data usage value
    var progressRemember by remember {
        mutableFloatStateOf(-1f)
    }
    // This is to animate the foreground indicator
    val dataUsageAnimate = animateFloatAsState(
        targetValue = progressRemember,//final state
        animationSpec = tween(
            durationMillis = animationDuration
        ), label = ""
    )
    // This is to start the animation when the activity is opened
    LaunchedEffect(Unit) {
        progressRemember = progress
    }//here progress remember = 1f


    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(size),
            contentAlignment = Alignment.Center,
        ) {
            Canvas(
                modifier = Modifier
                    .size(size)
            ) {
                // For shadow
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(shadowColor, Color.White),
                        center = Offset(
                            x = this.size.width / 2,
                            y = this.size.height / 2
                        ),//finding center of circle using box size
                        radius = this.size.height / 2//given
                    ),
                    radius = this.size.height / 2,
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2)
                )

                // This is the white circle that appears on the top of the shadow circle
                drawCircle(
                    color = Color.White,
                    radius = (size / 2 - indicatorThickness).toPx(),
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2)
                )

                // Convert the dataUsage to angle
                val sweepAngle = (dataUsageAnimate.value) * 360 / 100//100->out of 100

                // Foreground indicator
                drawArc(
                    color = foregroundIndicatorColor,
                    startAngle = 90f,//start from
                    sweepAngle = sweepAngle,//make the arc to move by progress
                    useCenter = false,//cover color from center -> true or false
                    style = Stroke(
                        width = indicatorThickness.toPx(),
                        cap = StrokeCap.Round
                    ),//denote the shape of arc ex-circle,square
                    size = Size(
                        width = (size - indicatorThickness).toPx(),
                        height = (size - indicatorThickness).toPx()
                    ),
                    topLeft = Offset(
                        x = (indicatorThickness / 2).toPx(),
                        y = (indicatorThickness / 2).toPx()
                    )
                )
            }

            // Display the data usage value
            DisplayText(value)

        }
        Text(text = desc, fontSize = 10.sp)


//        ButtonProgressbar {
//            progressRemember = 100f
//        }

    }

}

@Composable
fun DisplayText(value: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value, fontSize = 18.35.sp
        )
    }
}

@Composable
fun Divider1() {
    Divider(modifier = Modifier.width(50.dp), thickness = 2.dp)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditableText() {
    var isEditMode by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("Akash kumar jenishvar") }


    if (isEditMode) {
        // Display an editable text field
        TextField(
            value = text,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.Sentences
            ),
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Provide a "Done" button to save changes
        Button(
            onClick = {

                if (text.isNotBlank()) { // Check if text is not empty
                    isEditMode = false
                }

            }
        ) {
            Text("Done")
        }
    } else {
        // Display the text and an edit icon (pen)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(text)
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                modifier = Modifier.clickable {
                    isEditMode = true
                }
            )
        }
    }
}

@Composable
fun Resumetext() {
    Text(
        text = "Upload resume",
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(15.dp)
    )
}

@Composable
fun Resumedesc() {
    Text(
        text = "We will use your resume to create your profile and customise your job recommendations.",
        fontSize = 18.sp,
        fontWeight = FontWeight(400), textAlign = TextAlign.Center
    )
}

@Composable
fun Selectfile() {
    Image(
        modifier = Modifier.fillMaxWidth(),
        painter = painterResource(id = R.drawable.selectfile),
        contentDescription = null, contentScale = ContentScale.FillWidth
    )
}

@Composable
fun Files() {
    Box(modifier = Modifier.wrapContentSize())
    {

    }

}







