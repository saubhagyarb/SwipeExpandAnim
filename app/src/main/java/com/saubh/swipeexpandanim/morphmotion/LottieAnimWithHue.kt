package com.saubh.swipeexpandanim.morphmotion

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DrawLottieAnimWithHue(
    modifier: Modifier = Modifier,
    @RawRes resource: Int,
    hueColor: Color
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    LottieAnimation(
        modifier = modifier
            .layoutId("mic")
            .drawBehind {
                val radius = size.minDimension / 2f
                val gradient = Brush.radialGradient(
                    colors = listOf(
                        hueColor,
                        Color.Transparent
                    ),
                    center = Offset(size.width / 2, size.height / 2),
                    radius = radius
                )
                drawCircle(brush = gradient, radius = radius)
            },
        composition = composition,
        progress = { progress }
    )
}