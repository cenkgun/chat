package com.cenkgun.presentation.impl

import android.content.Context
import com.cenkgun.domain.providers.ResourceProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {
    override fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }
}