package com.acet.custompullrefreshfrombottom.post_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(PostDetailUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onTopPullRefresh() {
        viewModelScope.launch {
            setTopPullLoading(true)
            delay(1500L)
            setTopPullLoading(false)
        }
    }

    fun onBottomPullRefresh() {
        viewModelScope.launch {
            setBottomPullLoading(true)
            delay(1500L)
            setBottomPullLoading(false)
        }
    }

    fun setTopPullLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(
                isTopPullRefreshLoading = isLoading
            )
        }
    }

    fun setBottomPullLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(
                isBottomPullRefreshLoading = isLoading
            )
        }
    }
}

data class PostDetailUiState(
    val isBottomPullRefreshLoading: Boolean = false,
    val isTopPullRefreshLoading: Boolean = false,
)

data class PostDetailUiEvent(
    val onBackPress: () -> Unit = {},
    val onTopPullRefresh: () -> Unit = {},
    val onBottomPullRefresh: () -> Unit = {}
)

enum class PullBottomState(val offset: Int) {
    IDLE(330),
    ACTIVE(240),
    REFRESHING(140),
}
