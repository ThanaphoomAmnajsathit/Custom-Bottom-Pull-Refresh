package com.acet.custompullrefreshfrombottom.extension

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

object ColorExtension {
    fun String.parseColor(): Color =
        try {
            Color(this.toColorInt())
        } catch (exception: Exception) {
            Color("#$this".toColorInt())
        } catch (exception: Exception) {
            Color.Unspecified
        }
}