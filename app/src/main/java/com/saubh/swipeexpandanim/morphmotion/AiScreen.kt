package com.saubh.swipeexpandanim.morphmotion

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AiScreen(
    modifier: Modifier = Modifier,
    greeting: String = "Effortless sign-in with Ohhpro AI",
    topCardAlpha: Float = 1f,
    onSwipeUp: () -> Unit,
    onSwipeDown: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient_animation")

    // Animate the rotation angle
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(24.dp))
//            .drawBehind { drawMovingGradientBorder(rotationAngle) }
    ) {
        AiScreenTopCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.TopCenter)
                .alpha(topCardAlpha)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onSwipeUp = onSwipeUp
        )
        Column(
            modifier = Modifier.statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = greeting,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    )
                ),
                //color = MaterialTheme.colorScheme.onBackground, Not needed as we are using brush
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(300.dp))
            Text(
                text = "What's up! Need any help?",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AiScreenBottomCard(
            modifier = Modifier.align(Alignment.BottomCenter),
            onSwipeDown = onSwipeDown
        )
    }
}

@Composable
fun AiScreenBottomCard(
    modifier: Modifier = Modifier,
    onSwipeDown: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    )

    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left side text
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "MANUAL SIGN-UP",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Prefer to sign up manually?\nSwipe Up to disable AI assistance.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )
            }

            Column(
                modifier = modifier.clickable(onClick = onSwipeDown),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {
                Icon(
                    imageVector = Icons.Default.KeyboardDoubleArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "SWIPE DOWN",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun AiScreenTopCard(
    modifier: Modifier = Modifier,
    onSwipeUp : () -> Unit
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(80.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello Saubhagya!!",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.tertiary
                            ))
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "How can I help you?",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Right side - Swipe indicator
            Column(
                modifier = Modifier.clickable(
                    onClick = onSwipeUp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardDoubleArrowUp,
                    contentDescription = "Swipe up indicator",
                    tint = Color.White,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "SWIPE UP",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = Color.White.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

//fun DrawScope.drawMovingGradientBorder(rotationAngle: Float) {
//    val borderWidth = 2.dp.toPx()
//    val radius = 24.dp.toPx()
//
//    val centerX = size.width / 2
//    val centerY = size.height / 2
//
//    val colors = listOf(
//        Color(0xFFedbcdc),
//        Color(0xFFc8a8f3),
//        Color(0xFF7C3AED),
//        Color(0xFFafdfdd)
//    )
//
//    // Calculate gradient positions based on rotation
//    val angleRad = Math.toRadians(rotationAngle.toDouble())
//    val gradientRadius = maxOf(size.width, size.height) * 0.7f
//
//    val startX = centerX + (gradientRadius * cos(angleRad)).toFloat()
//    val startY = centerY + (gradientRadius * sin(angleRad)).toFloat()
//    val endX = centerX - (gradientRadius * cos(angleRad)).toFloat()
//    val endY = centerY - (gradientRadius * sin(angleRad)).toFloat()
//
//    val brush = Brush.linearGradient(
//        colors = colors,
//        start = Offset(startX, startY),
//        end = Offset(endX, endY)
//    )
//
//    // Draw the gradient border
//    drawRoundRect(
//        brush = brush,
//        topLeft = Offset.Zero,
//        size = size,
//        cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius)
//    )
//
//    // Draw inner black rectangle to create border effect
//    drawRoundRect(
//        color = Color(0xff000000),
//        topLeft = Offset(borderWidth, borderWidth),
//        size = androidx.compose.ui.geometry.Size(
//            size.width - borderWidth * 2,
//            size.height - borderWidth * 2
//        ),
//        cornerRadius = CornerRadius(radius - borderWidth)
//    )
//}

