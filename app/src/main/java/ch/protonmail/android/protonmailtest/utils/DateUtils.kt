package ch.protonmail.android.protonmailtest.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    const val INPUT_FORMAT = "yyyy-mm-dd'T'HH:mm:ss.SSS"
    const val OUTPUT_FORMAT = "yyyy-MM-dd • HH:mm"

    fun String.getDateInFormat(inputFormat: String, outputFormat: String): String =
        SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
            ?.let { SimpleDateFormat(outputFormat, Locale.getDefault()).format(it) } ?: ""
}