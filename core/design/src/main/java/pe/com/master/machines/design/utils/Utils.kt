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
import pe.com.master.machines.model.model.DailyRates
import java.math.BigDecimal
import java.math.RoundingMode

object Utils {
    
    val forwardEnterTransition: EnterTransition
        get() = fadeIn(tween(1000))
    val forwardExitTransition: ExitTransition
        get() = fadeOut(tween(1000))
    val backEnterTransition: EnterTransition
        get() {
            val easing = FastOutLinearInEasing
            return slideInHorizontally(
                initialOffsetX = { -600 }, animationSpec = tween(
                    1000, easing = easing
                )
            ) + fadeIn(
                initialAlpha = 0.0f, animationSpec = tween(1000, easing = easing)
            ) + scaleIn(
                initialScale = 0.95f, animationSpec = tween(1000, easing = easing)
            )
        }
    
    val backExitTransition: ExitTransition
        get() {
            val easing = FastOutLinearInEasing
            return slideOutHorizontally(
                targetOffsetX = { 600 }, animationSpec = tween(
                    1000, easing = easing
                )
            ) + fadeOut(
                targetAlpha = 0.0f, animationSpec = tween(1000, easing = easing)
            ) + scaleOut(
                targetScale = 0.95f, animationSpec = tween(1000, easing = easing)
            )
        }
    
    val Map<String, Map<String, BigDecimal>>?.toDailyRatesList: List<DailyRates>
        get() = this?.map { (date, rates) ->
            val usdEur = rates["USDEUR"]
            val usdClp = rates["USDCLP"]
            if (usdEur == null || usdClp == null) {
                null
            } else {
                val one = BigDecimal.ONE
                DailyRates(
                    date = date,
                    usdToEur = usdEur,
                    usdToClp = usdClp,
                    eurToUsd = one.divide(usdEur, 3, RoundingMode.HALF_UP),
                    clpToUsd = one.divide(usdClp, 3, RoundingMode.HALF_UP),
                    eurToClp = usdClp.divide(usdEur, 3, RoundingMode.HALF_UP),
                    clpToEur = one.divide(usdClp, 3, RoundingMode.HALF_UP)
                        .multiply(usdEur).setScale(3, RoundingMode.HALF_UP)
                )
            }
        }?.filterNotNull() ?: listOf()
}