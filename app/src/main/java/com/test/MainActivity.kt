package com.test

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var networkCallback : ConnectivityManager.NetworkCallback
    private val TAG : String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkCallback = getNetworkCallback()

        binding.btSendSms.setOnClickListener {
            Toast.makeText(this,Utility.connectionType(this),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        unRegisterNetworkCallback()
    }

    override fun onResume() {
        super.onResume()
        registerNetworkCallback()
    }

    private fun getNetworkCallback() : ConnectivityManager.NetworkCallback{

        return object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                //Toast.makeText(this@MainActivity,"back online",Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onAvailable: "+Thread.currentThread().name)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                //Toast.makeText(this@MainActivity,"Connection Lost",Toast.LENGTH_SHORT).show()
                Log.i(TAG, "onLost: "+Thread.currentThread().name)
            }
        }
    }

    private fun registerNetworkCallback(){
        Utility.getConnectivityManager(this).registerNetworkCallback(Utility.getNetworkRequest(),networkCallback)
    }

    private fun unRegisterNetworkCallback() {
        Utility.getConnectivityManager(this).unregisterNetworkCallback(networkCallback)
    }
}