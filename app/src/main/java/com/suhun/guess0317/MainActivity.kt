package com.suhun.guess0317

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.suhun.guess0317.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val tag = MainActivity::class.simpleName
    val secretNumber:SecretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure")
                .setPositiveButton("Ok", {dialog, which->
                    secretNumber.resetAll()
                    binding.contentLayout.userInput.text = null
                    binding.contentLayout.count.text = "0"
                    Toast.makeText(this, "Reset Success", Toast.LENGTH_LONG).show()
                })
                .setNeutralButton("Cancel", null)
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun checkGuess(view: View){
        var userInput:Int = binding.contentLayout.userInput.text.toString().toInt()
        var message:String = secretNumber.verifyResult(resources, userInput)
        var bingo = if(secretNumber.verify(userInput)==0) true else false

        binding.contentLayout.count.text = secretNumber.playCount.toString()
        AlertDialog.Builder(this)
            .setTitle("Guess Result")
            .setMessage(message)
            .setPositiveButton("ok", {dialog, which->
                if(bingo){
                    val intent:Intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNT", secretNumber.playCount.toInt())
                    intent.putExtra("NAME", binding.contentLayout.player.text.toString())
                    startActivity(intent)

                }else{
                    binding.contentLayout.userInput.text = null
                }
            })
            .show()
    }
}