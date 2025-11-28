package com.acet.custompullrefreshfrombottom.base_ui.button

import com.acet.custompullrefreshfrombottom.ui.theme.CustomPullRefreshFromBottomTheme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acet.custompullrefreshfrombottom.ui.theme.LinePrimary
import com.acet.custompullrefreshfrombottom.ui.theme.TextPlaceholder
import com.mobile.designsystem.component.text.GCCaption1BoldText

@Composable
fun GCSmallOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    contentDescription: String? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.outlinedShape,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
) {
    val buttonColors = colors.copy(
        disabledContentColor = TextPlaceholder
    )

    OutlinedButton(
        modifier = modifier
            .semantics {
                contentDescription?.let {
                    this.contentDescription = it
                }
            }
            .height(32.dp),
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = buttonColors,
        elevation = elevation,
        border = border ?: BorderStroke(
            width = 1.dp,
            color = LinePrimary
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        interactionSource = interactionSource,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
            GCCaption1BoldText(
                text = text,
                color = if (enabled) buttonColors.contentColor else buttonColors.disabledContentColor
            )
            trailingIcon?.let {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = it),
                    contentDescription = null
                )
            }
        }
    }
}
