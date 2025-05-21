package com.jg.greencode.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jg.greencode.domain.model.Conversion
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun ConversionItem(
    conversion: Conversion,
    onClick: (Int) -> Unit
) {
    val amountColor = Color(0xFF357a38)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick(conversion.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Check,
            contentDescription = null,
            tint = amountColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(conversion.fromCurrency, fontWeight = FontWeight.SemiBold)
            Text(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(conversion.date), fontSize = 12.sp, color = Color.Gray)
        }

        Text(
            text = "${conversion.result} ${conversion.toCurrency}",
            color = amountColor,
            fontWeight = FontWeight.Medium
        )
    }
}