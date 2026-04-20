package com.schmitttech.ingresso.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.schmitttech.ingresso.domain.model.Rating
import androidx.core.graphics.toColorInt

/**
 * A compact rating badge displaying the rating label with the API-provided background color.
 * Falls back to a neutral gray if the color cannot be parsed.
 */
@Composable
fun RatingBadge(
    rating: Rating?,
    modifier: Modifier = Modifier
) {
    if (rating == null) return

    val backgroundColor = try {
        Color(rating.color.toColorInt())
    } catch (_: Exception) {
        Color(0xFF666666)
    }

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .sizeIn(minWidth = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = rating.label,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 14.sp
        )
    }
}
