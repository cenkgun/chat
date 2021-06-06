package com.cenkgun.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageModel(
    var text: String,
    var timestamp: Long,
    var user: UserModel
) : Parcelable