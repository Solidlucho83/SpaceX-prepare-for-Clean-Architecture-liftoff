package prieto.fernando.spacex.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import prieto.fernando.spacex.R

private val SpaceGrotesk = FontFamily(
    Font(R.font.space_grotesk_bold, FontWeight.W700),
    Font(R.font.space_grotesk_semibold, FontWeight.W600),
    Font(R.font.space_grotesk_light, FontWeight.W200),
    Font(R.font.space_grotesk_medium, FontWeight.W500),
    Font(R.font.space_grotesk_regular, FontWeight.W400)
)

val SpaceXTypography = Typography(
    defaultFontFamily = SpaceGrotesk,
    h1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        letterSpacing = 1.25.sp
    )
)