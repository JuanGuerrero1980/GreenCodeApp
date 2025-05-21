package com.jg.greencode.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jg.greencode.R
import com.jg.greencode.ui.util.ConversionList
import com.jg.greencode.ui.util.CurrencyInput
import com.jg.greencode.ui.util.LoadingIndicator
import com.jg.greencode.ui.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
   viewModel: HomeViewModel = hiltViewModel(),
   onConversionClick: (Int) -> Unit,
) {
    val conversionHistory = viewModel.conversion.collectAsState()
    val amount1 = viewModel.amount1
    val amount2 = viewModel.amount2
    val currency1 = viewModel.currency1
    val currency2 = viewModel.currency2
    val currencies = viewModel.currencies
    val loading = viewModel.loading
    val error = viewModel.error

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFF9FAFB))
            ) {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(R.string.notifications),
                            modifier = Modifier.padding(16.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(R.string.profile),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(8.dp)
                            .shadow(elevation = 16.dp, shape = RoundedCornerShape(24.dp))
                            .background(Color.White, RoundedCornerShape(24.dp)),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.currency_converter),
                                fontSize = 32.sp,
                                color = Color(0xFF333333),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            LoadingIndicator(loading)

                            error?.let {
                                Text(
                                    text = it,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .background(Color(0xFFFFEBEE), RoundedCornerShape(8.dp))
                                        .border(1.dp, Color(0xFFEF9A9A), RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CurrencyInput(
                                    amount = amount1,
                                    onAmountChange = viewModel::updateAmount1,
                                    selectedCurrency = currency1,
                                    onCurrencyChange = viewModel::updateCurrency1,
                                    currencies = currencies,
                                    label = stringResource(R.string.amount)
                                )

                                Button(
                                    onClick = viewModel::swapCurrencies,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(32.dp))
                                        .background(Color(0xFF42A5F5), RoundedCornerShape(32.dp)),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(
                                            0xFF42A5F5
                                        )
                                    ),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = stringResource(R.string.swap_currencies),
                                        tint = Color.White,
                                        modifier = Modifier.size(32.dp)
                                    )
                                }

                                CurrencyInput(
                                    amount = amount2,
                                    onAmountChange = viewModel::updateAmount2,
                                    selectedCurrency = currency2,
                                    onCurrencyChange = viewModel::updateCurrency2,
                                    currencies = currencies,
                                    label = stringResource(R.string.converted)
                                )
                            }

                            Button(
                                onClick = viewModel::fetchExchangeRates,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .height(48.dp)
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(32.dp)),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFF42A5F5
                                    )
                                ),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(stringResource(R.string.convert), color = Color.White, fontSize = 16.sp)
                            }

                        }
                    }
                }
                ConversionList(conversionHistory.value, onConversionClick)
            }
        }
    }
}
