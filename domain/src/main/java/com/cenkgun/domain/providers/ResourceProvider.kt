package com.cenkgun.domain.providers

interface ResourceProvider {
    fun getString(stringResId: Int): String
}