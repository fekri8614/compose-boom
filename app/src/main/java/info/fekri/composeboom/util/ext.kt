package info.fekri.composeboom.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.Serializable

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.e("coroutineExceptionHandler", throwable.message ?: "null-message", throwable)
}

data class ThreePair<A, B, C>(
    val first: A, val second: B, val third: C
) : Serializable {
    override fun toString(): String = "$first, $second, $third"
}

fun textLengthStyle(txt: String, length: Int): String {
    if (txt.length > length) return txt.substring(0, length) + "..."
    return txt
}

fun textIdStyle(txt: String): String {
    if (txt.lowercase().contains(" ")) {
        txt.replace(txt, "_")
    }
    return txt
}
