package com.bssm.moj.domain.review

import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.user.User
import jakarta.persistence.*

@Entity
class Review(
    content: String,
    rating: Double,
    user: User,
    meal: Meal,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    val content: String = content

    val rating: Double = rating

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_id",
        foreignKey = ForeignKey(name = "FK_REVIEW_USER_ID")
    )
    val user: User = user

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "meal_id",
        foreignKey = ForeignKey(name = "FK_REVIEW_MEAL_ID")
    )
    val meal: Meal = meal
}
