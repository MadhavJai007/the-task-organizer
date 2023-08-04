package com.example.taskorganizaer.ui.presentation.homeScreen

import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun MoveableFAB(
    onFabClicked: () -> Unit,
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val displayMetrics = DisplayMetrics()
    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels
    Log.e("ERMM",width.toString())
    Log.e("ERMM", height.toString())
    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//            .size(50.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    offsetX += dragAmount.x
                    offsetY += dragAmount.y
                    Log.e("ERMM", offsetX.toString())
                    Log.e("ERMM", offsetY.toString())
                }

            }
    ) {
        FloatingActionButton(
            onClick = { onFabClicked() },
            modifier = Modifier
                .padding(0.dp, 0.dp, 20.dp, 32.dp),
//                .size(56.dp)

        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "add")
        }
    }
}