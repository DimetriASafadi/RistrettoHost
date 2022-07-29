package com.dimetris.ristrettohost.HostSection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dimetris.ristrettohost.databinding.RisScreenItemBinding

class ItemScreen : AppCompatActivity() {

    lateinit var binding:RisScreenItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RisScreenItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



    }
}