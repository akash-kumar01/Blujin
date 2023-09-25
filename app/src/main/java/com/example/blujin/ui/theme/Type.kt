package com.example.blujin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.blujin.R


val SansPro=FontFamily(
    Font(R.font.source_sans_pro_light,FontWeight.Light),
    Font(R.font.source_sans_pro_regular,FontWeight.Normal),
    Font(R.font.source_sans_pro_italic,FontWeight.Normal,FontStyle.Italic,),
    Font(R.font.source_sans_pro_semibold,FontWeight.Medium),
    Font(R.font.source_sans_pro_bold,FontWeight.Bold),

)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge =  TextStyle(
        fontFamily = SansPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
//        fontStyle = FontStyle.Italic,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)