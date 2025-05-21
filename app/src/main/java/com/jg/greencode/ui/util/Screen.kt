package com.jg.greencode.ui.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jg.greencode.ui.theme.GreenCodeAppTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    GreenCodeAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}