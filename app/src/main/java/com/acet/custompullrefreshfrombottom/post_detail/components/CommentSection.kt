package com.acet.custompullrefreshfrombottom.post_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acet.custompullrefreshfrombottom.R
import com.acet.custompullrefreshfrombottom.ui.theme.TextSecondary
import com.mobile.designsystem.component.text.GCFootnoteRegularText
import com.mobile.designsystem.component.text.GCSubHeadBoldText
import com.mobile.designsystem.component.text.GCSubHeadRegularText

@Composable
fun CommentSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HorizontalDivider()

        GCFootnoteRegularText(
            modifier = Modifier.align(Alignment.Start),
            text = "6 Comments",
            color = TextSecondary,
        )

        repeat(6) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    model = "",
                    placeholder = painterResource(id = R.drawable.ic_default_circle_avatar),
                    error = painterResource(id = R.drawable.ic_default_circle_avatar),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )

                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        GCSubHeadBoldText(
                            modifier = Modifier.weight(1f),
                            text = "Lorem Ipsum",
                        )

                        IconButton(
                            modifier = Modifier.size(30.dp, 20.dp),
                            onClick = {},
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_linear_share),
                                contentDescription = null,
                            )
                        }
                    }

                    GCFootnoteRegularText(
                        text = "2hour",
                        color = TextSecondary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    GCSubHeadRegularText(
                        text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                        maxLines = Int.MAX_VALUE,
                    )
                }
            }
        }
    }
}