package com.jg.greencode.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jg.greencode.R
import com.jg.greencode.ui.util.DetailPropertyItem
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    conversionId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val conversionState = viewModel.getConversionById(conversionId).collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.conversion_detail)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Outlined.Close, contentDescription = stringResource(R.string.close))
                    }
                }
            )
        }
    ) { padding ->
        conversionState.value?.let { conversion ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                DetailPropertyItem(label = stringResource(R.string.from), value = conversion.fromCurrency)
                DetailPropertyItem(label = stringResource(R.string.to), value = conversion.toCurrency)
                DetailPropertyItem(label = stringResource(R.string.amount_detail), value = conversion.amount.toString())
                DetailPropertyItem(label = stringResource(R.string.result), value = conversion.result.toString())
                DetailPropertyItem(label = stringResource(R.string.rate), value = conversion.rate.toString())
                DetailPropertyItem(label = stringResource(R.string.date), value = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(conversion.date))
            }
        } ?: Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}