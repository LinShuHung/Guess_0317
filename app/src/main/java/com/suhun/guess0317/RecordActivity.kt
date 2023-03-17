package com.suhun.guess0317

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhun.guess0317.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name:String? = intent.getStringExtra("NAME")
        val count:Int = intent.getIntExtra("COUNT", -1)
        var information:String = "$name \t $count times"
        binding.playerInformation.text = information
    }
}