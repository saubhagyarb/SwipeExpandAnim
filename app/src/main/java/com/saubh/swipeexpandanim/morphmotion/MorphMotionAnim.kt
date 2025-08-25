package com.saubh.swipeexpandanim.morphmotion

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.saubh.swipeexpandanim.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMotionApi::class, ExperimentalWearMaterialApi::class)
@Composable
fun MorphMotionAnim(
    viewmodel : UIStateViewmodel = UIStateViewmodel(),
    modifier: Modifier = Modifier,
    bottomBar: @Composable ((Modifier) -> Unit) = {innerModifier ->
        NavigationBar(modifier = innerModifier)
    },
    mainContent: @Composable ((Modifier) -> Unit) = { innerModifier ->
        Surface(modifier = innerModifier) {
            Text("Hello world!!!")
        }
    },
) {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }
    val swipeAreaHeight = screenHeight - 400f

    // Load MotionScene from raw
    val motionSceneContent by viewmodel.motionContentJson.collectAsState()


    viewmodel.getJsonUiData()

    // Use Compose Material swipeable
    val swipeableState = rememberSwipeableState(0)
    val anchors = mapOf(0f to 0, -swipeAreaHeight to 1)

    val swipeProgress = swipeableState.offset.value / -swipeAreaHeight
    val motionProgress = (1f - swipeProgress).coerceIn(0f, 1f)

    val coroutineScope = rememberCoroutineScope()

    if(motionSceneContent.isNotEmpty())
    {
        Box(
            modifier = modifier
                .fillMaxSize()
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    orientation = Orientation.Vertical,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    enabled = true
                )
        )

        {
            MotionLayout(
                motionScene = MotionScene(content =  motionSceneContent),
                progress = motionProgress,
                modifier = Modifier.fillMaxSize()
            ) {
                mainContent(Modifier.layoutId("loginScreen"))

                DrawLottieAnimWithHue(
                    modifier = Modifier.layoutId("mic"),
                    resource = R.raw.ai_anim,
                    hueColor = Color(0xffCB0FFF)
                )

                AiScreen(
                    modifier = Modifier.layoutId("aiScreen"),
                    topCardAlpha = motionProgress,
                    onSwipeUp = {
                        coroutineScope.launch { swipeableState.animateTo(1) }
                    },
                    onSwipeDown = {
                        coroutineScope.launch { swipeableState.animateTo(0) }
                    }
                )

                bottomBar(Modifier.layoutId("navigationBar"))
            }
        }
    }

}

