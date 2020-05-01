package com.getusersapp.util

import com.getusersapp.BuildConfig
import com.getusersapp.R
import com.getusersapp.data.models.Learnings

object DataUtils {

    fun fetchLearningsList(): List<Learnings> {
        val learningsList = ArrayList<Learnings>()
        if(BuildConfig.FLAVOR.equals("users", true)){
            learningsList.add(Learnings(R.drawable.coroutines, "API calling using Kotlin Coroutines"))
            learningsList.add(Learnings(R.drawable.rxjava, "API calling using RxJava"))
            learningsList.add(Learnings(R.drawable.kotlin, "Show middle element of a list using extension function"))
            learningsList.add(Learnings(R.drawable.rxjava, "Usage Of FlatMap Operator in RxJava"))
        } else {
            learningsList.add(Learnings(R.drawable.kotlin, "Launch News Search View"))
        }
        return learningsList
    }
}