package com.suhun.guess0317

import android.content.res.Resources
import android.util.Log
import java.util.Random

class SecretNumber {
    var tag = SecretNumber::class.simpleName
    var randomNumber:Int
    var playCount:Int = 0
    init{
        randomNumber = Random().nextInt(100) + 1
        Log.d(tag,"Secret number:$randomNumber")
    }

    fun verify(userInpupt:Int) = randomNumber - userInpupt

    fun verifyResult(r:Resources, userInput:Int):String{
        playCount++
        if(verify(userInput)>0){
            return "Bigger!!!"
        }else if(verify(userInput)<0){
            return "Smaller!!!"
        }else{
            if(playCount<3){
                return "Excellent! The number is $randomNumber"
            }else{
                return "You got it!!!"
            }
        }
    }

    fun resetAll(){
        playCount = 0
        randomNumber = Random().nextInt() + 1
        Log.d(tag,"Reset secret number:$randomNumber")
    }
}