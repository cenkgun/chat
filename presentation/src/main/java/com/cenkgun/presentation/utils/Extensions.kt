package com.cenkgun.presentation.utils

import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.bumptech.glide.Glide
import com.cenkgun.presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Hours
import org.joda.time.format.DateTimeFormat
import kotlin.math.absoluteValue

private const val LONG_TIME_STAMP_FORMAT = "HH:mm - dd.MM.yyyy"
private const val SHORT_TIME_STAMP_FORMAT = "HH:mm"

fun <T> SavedStateHandle.stateFlow(
    scope: CoroutineScope,
    key: String,
    initialValue: T
): MutableStateFlow<T> {
    val liveData = getLiveData(key, initialValue)
    val stateFlow = MutableStateFlow(initialValue)

    val observer = Observer<T> { value ->
        if (value != stateFlow.value) {
            stateFlow.value = value
        }
    }
    liveData.observeForever(observer)

    stateFlow.onCompletion {
        withContext(Dispatchers.Main.immediate) {
            liveData.removeObserver(observer)
        }
    }.onEach { value ->
        withContext(Dispatchers.Main.immediate) {
            if (liveData.value != value) {
                liveData.value = value
            }
        }
    }.launchIn(scope)
    return stateFlow
}

fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun ImageView.load(avatarUrl: String?) {
    Glide.with(this.context)
        .load(avatarUrl)
        .circleCrop()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .error(R.drawable.ic_avatar_placeholder)
        .into(this)
}

fun EditText.clear() {
    this.setText("")
}

fun Long.getHumanReadableDate(): String {
    val timeZone = DateTimeZone.getDefault()
    val startOfToday = DateTime.now().withTimeAtStartOfDay()
    val timeAtStartOfDay = DateTime(this, timeZone).withTimeAtStartOfDay()
    val hours = Hours.hoursBetween(startOfToday, timeAtStartOfDay).hours.absoluteValue
    if (hours < 24) {
        return DateTimeFormat.forPattern(SHORT_TIME_STAMP_FORMAT).print(DateTime(this, timeZone))
    }
    return DateTimeFormat.forPattern(LONG_TIME_STAMP_FORMAT).print(DateTime(this, timeZone))
}