package com.test

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService


object Utility {

    fun isConnectionAvailable(context: Context) : Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }

    fun connectionType(context: Context) : String{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) != true){
            return "connection is not available"
        }
        else{
            if (connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                return "Connected to mobile Network"
            }
            else if (connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                return "Connected to wifi"
            }
            return "connection is not available"
        }
    }

    fun getConnectivityManager(context: Context) : ConnectivityManager{
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    fun getNetworkRequest() : NetworkRequest{
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }
}