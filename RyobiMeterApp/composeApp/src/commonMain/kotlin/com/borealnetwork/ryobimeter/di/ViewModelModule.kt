package com.borealnetwork.ryobimeter.di

import com.borealnetwork.kmmuicore.viewModelDefinition
import com.borealnetwork.ryobimeter.ui.presentation.home.HomeViewModel
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModelDefinition {
        HomeViewModel()
    }
}