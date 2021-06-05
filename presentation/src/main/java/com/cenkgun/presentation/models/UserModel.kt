package com.cenkgun.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var id: String,
    var nickname: String,
    var avatarURL: String?
) : Parcelable
