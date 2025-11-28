package com.acet.custompullrefreshfrombottom.post_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.acet.custompullrefreshfrombottom.extension.ColorExtension.parseColor
import com.acet.custompullrefreshfrombottom.post_detail.components.CommentSection
import com.acet.custompullrefreshfrombottom.post_detail.components.PostSection
import com.acet.custompullrefreshfrombottom.ui.theme.BackgroundPrimary
import com.acet.custompullrefreshfrombottom.ui.theme.BackgroundSecondary
import com.mobile.designsystem.component.text.GCHeadlineBoldText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostDetailRoot(
    modifier: Modifier = Modifier,
    viewModel: PostDetailViewModel = koinViewModel(),
    uiEvent: PostDetailUiEvent,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PostDetailScreen(
        modifier = modifier,
        uiState = uiState,
        uiEvent = uiEvent.copy(
            onTopPullRefresh = viewModel::onTopPullRefresh,
            onBottomPullRefresh = viewModel::onBottomPullRefresh
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostDetailScreen(
    modifier: Modifier = Modifier,
    uiState: PostDetailUiState,
    uiEvent: PostDetailUiEvent,
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Surface(modifier) {
        Scaffold(
            modifier = Modifier
                .onGloballyPositioned {},
            containerColor = BackgroundSecondary,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundPrimary),
                    title = {
                        GCHeadlineBoldText(text = "Post Detail")
                    },
                )
            },
            content = { paddingValues ->
                PullToRefreshBox(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    isRefreshing = uiState.isTopPullRefreshLoading,
                    onRefresh = {
                        uiEvent.onTopPullRefresh()
                    },
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(
                                state = scrollState,
                            ),
                    ) {
                        Column {
                            Card(
                                modifier = Modifier
                                    .padding(vertical = 16.dp, horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .onGloballyPositioned {},
                                colors = CardDefaults.cardColors(containerColor = BackgroundPrimary),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    PostSection(
                                        modifier = Modifier.fillMaxWidth(),
                                        postPublisherImg = "",
                                        postPublisher = "Mackey Mackenzie",
                                        postDate = "1 Day ago",
                                        postContent = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
                                        postLikeCount = 32,
                                        imageList = listOf(
                                            "https://picsum.photos/200/300.jpg",
                                            "https://picsum.photos/seed/picsum/200/300",
                                            "https://picsum.photos/id/237/200/300",
                                            "https://picsum.photos/id/222/200/300"
                                        ),
                                        isCommentLoading = uiState.isBottomPullRefreshLoading,
                                        onLikeClicked = {},
                                        onCommentClicked = {},
                                        onLikeHistoriesClicked = {},
                                        onOpenImagesClicked = { _,_ -> },
                                    )

                                    Spacer(Modifier.height(16.dp))

                                    CommentSection(
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            this@Column.PullBottomToRefreshIndicator(
                                isLoading = uiState.isBottomPullRefreshLoading,
                                scrollState = scrollState,
                                coroutineScope = coroutineScope,
                                onRefresh = {
                                    uiEvent.onBottomPullRefresh()
                                },
                            )
                        }
                    }
                }
            },
        )
    }
}

@Composable
private fun ColumnScope.PullBottomToRefreshIndicator(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    scrollState: ScrollState,
    coroutineScope: CoroutineScope,
    onRefresh: () -> Unit = {},
) {
    var pullState by remember {
        mutableStateOf(PullBottomState.IDLE)
    }

    val hasReachedBottom by remember {
        derivedStateOf { scrollState.value == scrollState.maxValue && !scrollState.isScrollInProgress }
    }

    fun animateTo(stateOffset: Int) {
        coroutineScope.launch {
            scrollState.animateScrollTo(scrollState.maxValue - stateOffset)
        }
    }

    LaunchedEffect(scrollState.value) {
        // step 1: IDLE state
        if (pullState == PullBottomState.IDLE) {
            if (scrollState.value >= scrollState.maxValue - PullBottomState.IDLE.offset) {
                // when scrollState.value > IDLE.offset, it going to step2: ACTIVE state.
                if (isLoading) {
                    pullState = PullBottomState.ACTIVE
                } else {
                    // to stop scroll at IDLE offset when scrolling to bottom.
                    pullState = PullBottomState.ACTIVE
                    scrollState.scrollTo(scrollState.maxValue - PullBottomState.IDLE.offset)
                }
            }
        }

        // step 2: ACTIVE state
        if (pullState == PullBottomState.ACTIVE) {
            // when scrollState.value < ACTIVE.offset, it going back to step1: IDLE state
            if (scrollState.value <= scrollState.maxValue - PullBottomState.ACTIVE.offset) {
                pullState = PullBottomState.IDLE
            }
        }
    }

    LaunchedEffect(scrollState.isScrollInProgress) {
        if (pullState == PullBottomState.ACTIVE) {
            // when pulling and release finger from the screen but is NOT ReachedBottom, it going back to step1: IDLE state.
            if (!scrollState.isScrollInProgress && !isLoading) {
                if (!hasReachedBottom && scrollState.value in (scrollState.maxValue - PullBottomState.IDLE.offset)..scrollState.maxValue) {
                    animateTo(PullBottomState.IDLE.offset)
                }
            }
        }
    }

    LaunchedEffect(hasReachedBottom) {
        if (pullState == PullBottomState.ACTIVE) {
            if (isLoading && scrollState.value == PullBottomState.REFRESHING.offset) {
                // posting comment || delete comment.
                onRefresh()
            } else if (hasReachedBottom) {
                // pulling bottom refresh.
                onRefresh()
                animateTo(PullBottomState.REFRESHING.offset)
            }
        }
    }

    LaunchedEffect(isLoading) {
        if (pullState == PullBottomState.ACTIVE) {
            if (!isLoading && !scrollState.isScrollInProgress) {
                // when refresh successfully, going back to IDLE state
                animateTo(PullBottomState.IDLE.offset)
            }
        }
    }

    this@PullBottomToRefreshIndicator.AnimatedVisibility(
        modifier = modifier,
        visible = true,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(bottom = 60.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (pullState == PullBottomState.ACTIVE) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(34.dp),
                        color = "#FF9D0D".parseColor(),
                        strokeCap = StrokeCap.Round,
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .border(
                                width = 3.5.dp,
                                color = "#FF9D0D".parseColor(),
                                shape = CircleShape,
                            ),
                    )
                }
            } else {
                Box(modifier = Modifier.size(24.dp)) {}
            }
        }
    }
}
