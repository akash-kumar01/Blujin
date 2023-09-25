package com.example.blujin.Screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.blujin.R

@Composable
fun signin(navController: NavHostController) {
    val nav=navController
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            imgsetup()
            textforimgandsignin()
            textforbox()
            CustomSplitTextField()
            forgottext()
            signinbtn()
            continuetext()
            icons()
            donttext(nav)
            termstext()


        }

    }
}

@Composable
fun imgsetup() {
    Image(
        painter = painterResource(id = R.drawable.blujinlogo),
        contentDescription = null,
        modifier = Modifier
            .scale(1.4f)
            .padding(top = 40.dp)
    )
}

@Composable
fun textforimgandsignin() {
    Text(
        text = "blujin",
        fontSize = 24.sp,
//        fontWeight = FontWeight(500),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 10.dp)
    )
    Text(
        text = "Sign in",
        fontSize = 28.sp,
        fontWeight = FontWeight(700),
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
        color = MaterialTheme.colorScheme.secondary
    )

}

@Composable
fun textforbox() {
    Text(
        text = "Sign in with your email address",
        fontSize = 14.sp, fontWeight = FontWeight(600),
        modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.secondary
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSplitTextField() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .border(
                width = 1.dp, // Border thickness
                color = Color.LightGray, shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            OutlinedTextField(
                value = text1,
                onValueChange = { text1 = it },
                label = {
                    Text(
                        "Email address",
                        color = Color.LightGray,
                        fontWeight = FontWeight(500)
                    )
                }, // Add a label for Text Field 1
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,

                    unfocusedBorderColor = Color.Transparent,
                    unfocusedLabelColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp), color = Color.LightGray, thickness = 2.dp
            )
            OutlinedTextField(
                value = text2,
                onValueChange = { text2 = it },
                label = {
                    Text(
                        "Password",
                        color = Color.LightGray,
                        fontWeight = FontWeight(500)
                    )
                }, // Add a label for Text Field 2
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary,

                    unfocusedBorderColor = Color.Transparent,
                    unfocusedLabelColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp)
            )
        }


    }
}

@Composable
fun forgottext() {
    Text(
        text = "Forgot password?",
        color = MaterialTheme.colorScheme.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight(600),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun signinbtn() {

    Button(onClick = { /*TODO*/ }, modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp))
    {
        Text(text = "Sign in", modifier = Modifier.padding(10.dp), fontSize = 14.sp)

    }

}

@Composable
fun continuetext() {
    Text(
        text = "Or continue with",
        fontSize = 13.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight(600)
    )
}

@Composable
fun icons() {
    Row {
        val context = LocalContext.current
        Image(painter = painterResource(id = R.drawable.linkedinicon),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Hi linked in",
                            Toast.LENGTH_LONG
                        )
                        .show()
                }
        )
        Spacer(modifier = Modifier.width(10.dp))

        Image(painter = painterResource(id = R.drawable.googleicon), contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Hi google",
                            Toast.LENGTH_LONG
                        )
                        .show()
                }

        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(painter = painterResource(id = R.drawable.appleicon),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Hi apple",
                            Toast.LENGTH_LONG
                        )
                        .show()
                }

        )


    }
}

@Composable
fun donttext(navController:NavController) {
    val line1 = "Don’t have an account?"
    val line2 = " Sign up now"

    val anotatedstring = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.secondary,
            )
        ) {
            append(line1)
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
            )
        ) {
            pushStringAnnotation(tag = line2, annotation = line2)
            append(line2)
        }
    }
    ClickableText(
        text = anotatedstring,
        onClick = {navController.navigate(route="setuppage1")},
        style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight(600)),
        modifier = Modifier.padding(top = 20.dp)
    )
    Spacer(modifier = Modifier.height(25.dp))
}

@Composable
fun termstext() {

    val line1 = "By signing up, you agree to our"
    val line2 = " Term. "
    val line3 = " See how we use your data in our "
    val line4 = "Privacy Policy and Cookies Policy"

    val annotatedstring = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Gray, fontWeight = FontWeight(500)

            )
        )
        {
            append(line1)
        }
        withStyle(
            style = SpanStyle(
                color=MaterialTheme.colorScheme.inversePrimary, fontWeight = FontWeight(600),
                textDecoration = TextDecoration.Underline,
            )
        )
        {
            pushStringAnnotation(tag = line2, annotation = line2)
            append(line2)
        }
        withStyle(
            style = SpanStyle(
                color = Color.Gray, fontWeight = FontWeight(500)

            )
        )
        {
            append(line3)
        }
        withStyle(
            style = SpanStyle(
                color=MaterialTheme.colorScheme.inversePrimary, fontWeight = FontWeight(600),
                textDecoration = TextDecoration.Underline,

                )
        )
        {
            pushStringAnnotation(tag = line4, annotation = line4)
            append(line4)
        }
    }
    ClickableText(
        text = annotatedstring,
        onClick = {},
        style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center),
    )


}
