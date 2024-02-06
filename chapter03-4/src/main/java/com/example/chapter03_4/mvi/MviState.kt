package com.example.chapter03_4.mvi

import com.example.chapter03_4.mvi.model.Image

sealed class MviState {

    object Idle : MviState()
    object Loading : MviState()
    data class LoadedImage(val image: Image, val count: Int) : MviState()
}