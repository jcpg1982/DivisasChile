package pe.com.master.machines.design.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val ColorWhite = Color(0xFFFFFFFF)
val ColorBlack = Color(0xFF000000)

// ----------------- LIGHT -----------------
val BackgroundLight = Color(0xFFF5F7FA)
val SurfaceLight = Color(0xFFFFFFFF)
val TextPrimaryLight = Color(0xFF2C3E50)
val TextSecondaryLight = Color(0xFF7F8C8D)

val PrimaryLight = Color(0xFF4A90E2)
val OnPrimaryLight = ColorWhite
val PrimaryContainerLight = Color(0xFFD6E3FF)
val OnPrimaryContainerLight = Color(0xFF001C3F)

val SecondaryLight = Color(0xFF50E3C2)
val OnSecondaryLight = ColorBlack
val SecondaryContainerLight = Color(0xFFB1FFF0)
val OnSecondaryContainerLight = Color(0xFF00201A)

val TertiaryLight = Color(0xFF7D5260)
val OnTertiaryLight = ColorWhite
val TertiaryContainerLight = Color(0xFFFFD8E4)
val OnTertiaryContainerLight = Color(0xFF31111D)

val SurfaceVariantLight = Color(0xFFE0E2EC)
val OnSurfaceVariantLight = Color(0xFF44474F)
val BorderLight = Color(0xFFDCE1E7)

val ErrorLight = Color(0xFFE74C3C)
val OnErrorLight = ColorWhite
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF410002)

val SuccessLight = Color(0xFF27AE60)

val BackgroundDark = Color(0xFF1C1C1E)
val SurfaceDark = Color(0xFF2C2C2E)
val TextPrimaryDark = ColorWhite
val TextSecondaryDark = Color(0xFFB0B3B8)

val PrimaryDark = Color(0xFFADC6FF)
val OnPrimaryDark = Color(0xFF002F65)
val PrimaryContainerDark = Color(0xFF00458E)
val OnPrimaryContainerDark = Color(0xFFD6E3FF)

val SecondaryDark = Color(0xFF79DCC8)
val OnSecondaryDark = Color(0xFF00372E)
val SecondaryContainerDark = Color(0xFF005043)
val OnSecondaryContainerDark = Color(0xFFB1FFF0)

val TertiaryDark = Color(0xFFEFB8C8)
val OnTertiaryDark = Color(0xFF492532)
val TertiaryContainerDark = Color(0xFF633B48)
val OnTertiaryContainerDark = Color(0xFFFFD8E4)

val SurfaceVariantDark = Color(0xFF44474F)
val OnSurfaceVariantDark = Color(0xFFC4C6CF)
val BorderDark = Color(0xFF8E9099)

val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

val SuccessDark = Color(0xFF27AE60)

val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = BorderLight,
    surfaceTint = PrimaryLight,
    scrim = Color(0x66000000),
    inversePrimary = OnPrimaryContainerLight,
    inverseSurface = TextPrimaryLight,
    inverseOnSurface = SurfaceLight
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = BorderDark,
    surfaceTint = PrimaryDark,
    scrim = Color(0x66FFFFFF),
    inversePrimary = OnPrimaryContainerDark,
    inverseSurface = TextPrimaryDark,
    inverseOnSurface = SurfaceDark
)