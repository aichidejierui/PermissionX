package com.example.permissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jemo.PermissionX
import com.example.permissionx.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activityMainBinding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.makeCallBtn.setOnClickListener {
            PermissionX.request(this,
                Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}