package com.example.selectablebox.ui.theme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun SelectableBox(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    title: String,
    titleColor: Color = if (selected) MaterialTheme.colors.primary
    else MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    titleSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    titleWeight: FontWeight = FontWeight.Medium,
    subtitle: String? = null,
    subtitleColor: Color = if (selected) MaterialTheme.colors.onSurface
    else MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    borderWidth: Dp = 2.dp,
    borderColor: Color = if (selected) Color.Red.copy(alpha = 0.5f)
    else MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    borderShape: Shape = RoundedCornerShape(size = 18.dp),
    icon: ImageVector = if (selected) Icons.Default.CheckCircle
    else Icons.Outlined.CheckCircle,
    iconColor: Color = if (selected) Color.Red.copy(alpha = 0.5f)
    else MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
    onClick: () -> Unit = {}
) {
    val scaleIcon = remember { Animatable(initialValue = 1f) }
    val scaleColumn = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(key1 = selected) {
        if (selected) {
            launch {
                scaleIcon.animateTo(
                    targetValue = 0.3f,
                    animationSpec = tween(durationMillis = 50)
                )
                scaleIcon.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            launch {
                scaleColumn.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(durationMillis = 50)
                )

                scaleColumn.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
        }
    }

    Column(modifier = modifier
        .border(
            width = borderWidth,
            color = borderColor,
            shape = borderShape
        )
        .clip(borderShape)
        .scale(scale = scaleColumn.value)
        .clickable { onClick() })
    {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.weight(9f),
                text = title,
                style = TextStyle(
                    color = titleColor,
                    fontSize = titleSize,
                    fontWeight = titleWeight,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                onClick = onClick,
                modifier = modifier
                    .weight(2f)
                    .scale(scale = scaleIcon.value)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor
                )

            }
        }
        if (subtitle != null) {
            Text(
                text = subtitle,
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 18.dp),
                style = TextStyle(color = subtitleColor),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}