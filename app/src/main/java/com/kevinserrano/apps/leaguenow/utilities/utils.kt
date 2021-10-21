package com.kevinserrano.apps.leaguenow.utilities
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson


fun String.completeUrl():String{
    return if (contains("https://")
        || contains("http://"))
            this
    else
        ("https://$this")
}

fun Context.openWebPage(url: String){
    if(url.isEmpty())
        return
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    this.startActivity(intent)
}

fun <T> fromJson(json: String, classOfT: Class<T>): T {
    return try {
        Gson().fromJson(json, classOfT)
    } catch (error: Exception) {
        Gson().fromJson("{}", classOfT)
    }
}

fun <T> getExtraCode(classOfT: Class<T>): String = classOfT.name

fun <T> Fragment.get(classOfT: Class<T>): T {
    val extraCode = classOfT.name
    val json = this.arguments?.getString(extraCode, "{}") ?: "{}"
    return fromJson(json, classOfT)
}


fun Fragment.put(any: Any) {
    val arguments = this.arguments ?: Bundle()
    arguments.putString(getExtraCode(any.javaClass), toJson(any))
    this.arguments = arguments
}

fun Intent.put(any: Any) {
    this.putExtra(any::class.java.name, toJson(any))
}
fun toJson(any: Any): String = Gson().toJson(any)

fun <T> Activity.get(classOfT: Class<T>): T {
    val json = this.intent.getStringExtra(classOfT.name) ?: "{}"
    return fromJson(json, classOfT)
}

