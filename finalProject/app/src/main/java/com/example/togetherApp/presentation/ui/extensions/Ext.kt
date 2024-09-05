package com.example.togetherApp.presentation.ui.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.security.MessageDigest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toNormalDate(): String {
    val dateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return dateTime.atOffset(ZoneOffset.UTC).format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toSmallDate(): String {
    val dateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM")
    return dateTime.atOffset(ZoneOffset.UTC).format(formatter)
}

fun String.formatPhoneNumber(): String {
    val cleanedInput = this.removePrefix("8")
    return "+7 (${cleanedInput.substring(0, 3)}) ${cleanedInput.substring(3, 6)}-${cleanedInput.substring(6, 8)}-${cleanedInput.substring(8, 10)}"
}


fun String.toChatDate(): String {
    return try {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        originalFormat.timeZone = TimeZone.getTimeZone("UTC")
        val targetFormat = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
        val date = originalFormat.parse(this)
        targetFormat.format(date)
    } catch (e: ParseException) {
        "???"
    }
}

fun Int.countRole(): String = when(this) {
    0 -> "Студент"
    2 -> "Админ"
    else -> ""
}