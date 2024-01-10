package com.plcoding.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.stockmarketapp.domain.model.CompanyListing

/**
 * Composable function [CompanyItem] untuk menampilkan item perusahaan dalam daftar.
 *
 * @param company Instance dari [CompanyListing] yang akan ditampilkan.
 * @param modifier Modifier untuk konfigurasi tata letak dan penampilan item.
 */
@Composable
fun CompanyItem(
    company:CompanyListing,
    modifier:Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Baris pertama dengan nama perusahaan dan bursa.
            Row(
                modifier= Modifier.fillMaxWidth()
            ) {
                Text(
                    text = company.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = company.exchange,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Baris kedua dengan simbol perusahaan dalam tanda kurung.
            Text(
                text = "(${company.symbol})",
                fontStyle= FontStyle.Italic,
                color = MaterialTheme.colors.onBackground
            )

        }
    }
}
