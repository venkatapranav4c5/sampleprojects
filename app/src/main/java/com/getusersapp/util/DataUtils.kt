package com.getusersapp.util

import com.getusersapp.R
import com.getusersapp.data.models.Learnings

object DataUtils {
    fun fetchLearningsList(): List<Learnings> {
        val learningsList = ArrayList<Learnings>()
        learningsList.add(
            Learnings(
                R.drawable.coroutines, "API calling using Kotlin Coroutines"
            )
        )

        learningsList.add(
            Learnings(
                R.drawable.rxjava, "API calling using RxJava"
            )
        )

        return learningsList
    }
}