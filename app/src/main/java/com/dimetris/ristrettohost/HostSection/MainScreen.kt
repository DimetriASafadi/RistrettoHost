package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimetris.ristrettohost.databinding.RisScreenMainBinding

class MainScreen : AppCompatActivity() {

    lateinit var binding:RisScreenMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }
}