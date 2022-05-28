package com.amosh.common

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.view.View
import java.io.UnsupportedEncodingException
import java.math.RoundingMode
import java.net.URLEncoder
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


fun <T> MutableList<T>.addIfNotExist(item: T?) {
    if (item != null && !this.contains(item)) this.add(item)
}

fun <T> MutableList<T>.addIfNotExistAndNotNull(item: T?) {
    if (!this.contains(item) && item != null) this.add(item)
}

@Suppress("DEPRECATION") // Fixed for higher versions
fun Context?.isOffline(): Boolean {
    val connectivityManager =
        this?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            ?: return true
    val nw = connectivityManager.activeNetwork ?: return true
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return true
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> false
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> false
        //for other device how are able to connect with Ethernet
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> false
        //for check internet over Bluetooth
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> false
        else -> true
    }
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.setIsVisible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun Context.openMapLocation(lat: Double, lng: Double, label: String) {
    var locationLabel = label
    locationLabel = try {
        "(" + URLEncoder.encode(locationLabel, "utf-8") + ")"
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        ""
    }
    val geoUri = "geo:0,0?q=$lat,$lng$locationLabel&z=11"
    val gmmIntentUri = Uri.parse(geoUri)
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    this.startActivity(mapIntent)
}

fun Context.callPhoneNumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    this.startActivity(intent)
}

fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val theta = lon1 - lon2
    var dist = (sin(deg2rad(lat1))
        * sin(deg2rad(lat2))
        + (cos(deg2rad(lat1))
        * cos(deg2rad(lat2))
        * cos(deg2rad(theta))))
    dist = acos(dist)
    dist = rad2deg(dist)
    dist *= 60 * 1.1515
    return (dist / 1000).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
}

private fun deg2rad(deg: Double): Double {
    return deg * Math.PI / 180.0
}

private fun rad2deg(rad: Double): Double {
    return rad * 180.0 / Math.PI
}
