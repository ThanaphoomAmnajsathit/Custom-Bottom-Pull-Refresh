package com.acet.custompullrefreshfrombottom.base_ui.image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

private const val IMAGE_GROUP_ONE = 1
private const val IMAGE_GROUP_TWO = 2
private const val IMAGE_GROUP_THREE = 3
private const val IMAGE_GROUP_FOUR = 4
private const val IMAGE_GROUP_FIVE = 5

private const val IMAGE_INDEX_ZERO = 0
private const val IMAGE_INDEX_ONE = 1
private const val IMAGE_INDEX_TWO = 2
private const val IMAGE_INDEX_THREE = 3
private const val IMAGE_INDEX_FOUR = 4

@Composable
fun ImageGroupPreviewer(
    modifier: Modifier = Modifier,
    isEditMode: Boolean = false,
    imageList: List<String>,
    onClicked: (imageIndex: Int) -> Unit = {},
    onDeleteImageClick: (imageIndex: Int) -> Unit = {},
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
    ) {
        when (imageList.size) {
            IMAGE_GROUP_ONE -> OneImageGroup(
                image = imageList[IMAGE_INDEX_ZERO],
                onClicked = onClicked,
            )

            IMAGE_GROUP_TWO -> TwoImageGroup(
                images = imageList,
                onClicked = onClicked,
            )

            IMAGE_GROUP_THREE -> ThreeImageGroup(
                images = imageList,
                isEditMode = isEditMode,
                onClicked = onClicked,
                onDeleteImageClick = onDeleteImageClick,
            )

            IMAGE_GROUP_FOUR -> FourImageGroup(
                images = imageList,
                isEditMode = isEditMode,
                onClicked = onClicked,
                onDeleteImageClick = onDeleteImageClick,
            )

            IMAGE_GROUP_FIVE -> FiveImageGroup(
                images = imageList,
                isEditMode = isEditMode,
                onClicked = onClicked,
                onDeleteImageClick = onDeleteImageClick,
            )
        }
    }
}

@Composable
private fun OneImageGroup(
    modifier: Modifier = Modifier,
    image: String,
    onClicked: (imageIndex: Int) -> Unit,
) {
    Surface(
        modifier = modifier.aspectRatio(2 / 1f),
        onClick = { onClicked(IMAGE_INDEX_ZERO) },
    ) {
        AsyncImage(
            model = image,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Composable
private fun TwoImageGroup(
    modifier: Modifier = Modifier,
    images: List<String>,
    onClicked: (imageIndex: Int) -> Unit,
) {
    Row(
        modifier = modifier.aspectRatio(2 / 1f),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            onClick = { onClicked(IMAGE_INDEX_ZERO) },
        ) {
            AsyncImage(
                model = images[IMAGE_INDEX_ZERO],
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            onClick = { onClicked(IMAGE_INDEX_ONE) },
        ) {
            AsyncImage(
                model = images[IMAGE_INDEX_ONE],
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun ThreeImageGroup(
    modifier: Modifier = Modifier,
    isEditMode: Boolean = false,
    images: List<String>,
    onClicked: (imageIndex: Int) -> Unit,
    onDeleteImageClick: (imageIndex: Int) -> Unit,
) {
    Column(
        modifier = modifier.aspectRatio(1f),
    ) {
        Surface(
            modifier = Modifier
                .aspectRatio(2 / 1f)
                .padding(bottom = 2.dp),
            onClick = { onClicked(IMAGE_INDEX_ZERO) },
        ) {
            AsyncImage(
                model = images[IMAGE_INDEX_ZERO],
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        Row(
            modifier = Modifier.aspectRatio(2 / 1f),
        ) {
            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, end = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_ONE) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_ONE],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }

            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, start = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_TWO) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_TWO],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun FourImageGroup(
    modifier: Modifier = Modifier,
    isEditMode: Boolean = false,
    images: List<String>,
    onClicked: (imageIndex: Int) -> Unit,
    onDeleteImageClick: (imageIndex: Int) -> Unit,
) {
    Column(
        modifier = modifier.aspectRatio(1f),
    ) {
        Row(
            modifier = Modifier.aspectRatio(2 / 1f),
        ) {
            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(bottom = 2.dp, end = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_ZERO) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_ZERO],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }

            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(bottom = 2.dp, start = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_ONE) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_ONE],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }

        Row(
            modifier = Modifier.aspectRatio(2 / 1f),
        ) {
            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, end = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_TWO) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_TWO],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }

            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, start = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_THREE) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_THREE],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun FiveImageGroup(
    modifier: Modifier = Modifier,
    isEditMode: Boolean = false,
    images: List<String>,
    onClicked: (imageIndex: Int) -> Unit,
    onDeleteImageClick: (imageIndex: Int) -> Unit,
) {
    Column(
        modifier = modifier.aspectRatio(6 / 5f),
    ) {
        Row(
            modifier = Modifier.aspectRatio(2 / 1f),
        ) {
            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(bottom = 2.dp, end = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_ZERO) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_ZERO],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }

            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(bottom = 2.dp, start = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_ONE) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_ONE],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }

        Row(
            modifier = Modifier.aspectRatio(3 / 1f),
        ) {
            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, end = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_TWO) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_TWO],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }

            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, start = 2.dp, end = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_THREE) },
            ) {
                AsyncImage(
                    model = images[IMAGE_INDEX_THREE],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }

            Surface(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(top = 2.dp, start = 2.dp),
                onClick = { onClicked(IMAGE_INDEX_FOUR) },
            ) {
                AsyncImage(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(top = 2.dp, start = 2.dp),
                    model = images[IMAGE_INDEX_FOUR],
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ImageGroupPreviewerPreview(
    @PreviewParameter(ImageGroupPreviewerProvider::class) imageList: List<String>,
) {
    ImageGroupPreviewer(
        imageList = imageList,
    )
}

@Preview
@Composable
private fun ImageGroupPreviewerEditModePreview(
    @PreviewParameter(ImageGroupPreviewerProvider::class) imageList: List<String>,
) {
    ImageGroupPreviewer(
        imageList = imageList,
        isEditMode = true,
    )
}

class ImageGroupPreviewerProvider : PreviewParameterProvider<List<String>> {
    override val values: Sequence<List<String>>
        get() = sequenceOf(
            listOf(""),
            listOf("", ""),
            listOf("", "", ""),
            listOf("", "", "", ""),
            listOf("", "", "", "", ""),
        )
}
