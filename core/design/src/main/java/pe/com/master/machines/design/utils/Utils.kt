package pe.com.master.machines.design.utils

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object Utils {

    val forwardEnterTransition: EnterTransition
        get() = fadeIn(tween(1000))
    val forwardExitTransition: ExitTransition
        get() = fadeOut(tween(1000))
    val backEnterTransition: EnterTransition
        get() {
            val easing = FastOutLinearInEasing 
            return slideInHorizontally(
                initialOffsetX = { -600 },
                animationSpec = tween(
                    1000,
                    easing = easing
                ) 
            ) + fadeIn(
                initialAlpha = 0.0f, 
                animationSpec = tween(1000, easing = easing)
            ) + scaleIn(
                initialScale = 0.95f, 
                animationSpec = tween(1000, easing = easing)
            )
        }

    val backExitTransition: ExitTransition
        get() {
            val easing = FastOutLinearInEasing 
            return slideOutHorizontally(
                targetOffsetX = { 600 },
                animationSpec = tween(
                    1000,
                    easing = easing
                ) 
            ) + fadeOut(
                targetAlpha = 0.0f, 
                animationSpec = tween(1000, easing = easing)
            ) + scaleOut(
                targetScale = 0.95f, 
                animationSpec = tween(1000, easing = easing)
            )
        }
}