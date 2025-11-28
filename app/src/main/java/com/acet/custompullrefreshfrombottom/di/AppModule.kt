package com.acet.custompullrefreshfrombottom.di

import com.acet.custompullrefreshfrombottom.post_detail.PostDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::PostDetailViewModel)
}