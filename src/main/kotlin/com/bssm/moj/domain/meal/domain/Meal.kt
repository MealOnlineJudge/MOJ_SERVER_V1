package com.bssm.moj.domain.meal.domain

import com.bssm.moj.domain.meal.domain.type.daily
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class Meal private constructor(
    date: LocalDate,
    dailyType: daily,
    food: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false)
    var date: LocalDate? = date
        protected set

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var dailyType: daily = dailyType
        protected set

    @Column(nullable = false)
    var food: String = food
        protected set

    companion object {
        fun saveMeal(
            date: LocalDate,
            dailyType: daily,
            food: String,
        ): Meal {
            return Meal(
                date = date,
                dailyType = dailyType,
                food = food
            )
        }
    }
}
