package com.acet.custompullrefreshfrombottom.post_detail.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acet.custompullrefreshfrombottom.R
import com.acet.custompullrefreshfrombottom.base_ui.button.GCSmallOutlinedButton
import com.acet.custompullrefreshfrombottom.base_ui.image.ImageGroupPreviewer
import com.acet.custompullrefreshfrombottom.ui.theme.TextPrimary
import com.acet.custompullrefreshfrombottom.ui.theme.TextSecondary
import com.mobile.designsystem.component.text.GCFootnoteRegularText
import com.mobile.designsystem.component.text.GCSubHeadBoldText
import com.mobile.designsystem.component.text.GCSubHeadRegularText

@Composable
fun PostSection(
    modifier: Modifier = Modifier,
    postPublisherImg: String,
    postPublisher: String,
    postDate: String,
    postContent: String,
    postLikeCount: Int,
    imageList: List<String>,
    isCommentLoading: Boolean = false,
    onLikeClicked: () -> Unit = {},
    onCommentClicked: () -> Unit = {},
    onLikeHistoriesClicked: () -> Unit = {},
    onOpenImagesClicked: (index: Int, images: List<String>) -> Unit = { _, _ -> },
) {
    var isShowReadMore by remember {
        mutableStateOf(false)
    }
    var lastCharIndex by remember { mutableIntStateOf(0) }
    val textMaxLine by remember {
        mutableIntStateOf(3)
    }
    var isExpand by remember {
        mutableStateOf(false)
    }

    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = postPublisherImg,
                placeholder = painterResource(id = R.drawable.ic_default_circle_avatar),
                error = painterResource(id = R.drawable.ic_default_circle_avatar),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            Column {
                GCSubHeadBoldText(
                    text = postPublisher,
                )
                GCFootnoteRegularText(
                    text = postDate,
                    color = TextSecondary,
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (postContent.isNotEmpty()) {
                GCSubHeadRegularText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .clickable {
                            isExpand = true
                        },
                    text = postContent,
                    textAlign = TextAlign.Start,
                    maxLines = Int.MAX_VALUE,
                    onTextLayout = {
                        if (!isExpand && it.hasVisualOverflow) {
                            isShowReadMore = true
                            lastCharIndex = it.getLineEnd(textMaxLine - 1, visibleEnd = true)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (imageList.isNotEmpty()) {
                ImageGroupPreviewer(
                    imageList = imageList,
                    onClicked = { imageIndex ->
                        onOpenImagesClicked(imageIndex, imageList)
                    },
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (postLikeCount != 0) {
                GCFootnoteRegularText(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable { onLikeHistoriesClicked() },
                    text = buildString {
                        append("Like")
                        append(postLikeCount)
                    },
                    color = TextSecondary,
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            ) {
                GCSmallOutlinedButton(
                    leadingIcon = R.drawable.ic_bold_like,
                    text = "Like",
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextPrimary,
                    ),
                    onClick = onLikeClicked,
                )

                GCSmallOutlinedButton(
                    leadingIcon = R.drawable.ic_linear_message,
                    text = "Comment",
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TextPrimary,
                    ),
                    onClick = {
                        if (!isCommentLoading) onCommentClicked()
                    },
                )
            }
        }
    }
}