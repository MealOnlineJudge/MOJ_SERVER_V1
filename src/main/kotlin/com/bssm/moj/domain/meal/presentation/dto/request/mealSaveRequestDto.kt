import com.bssm.moj.domain.meal.domain.Meal
import com.bssm.moj.domain.meal.domain.type.daily
import java.time.LocalDate

data class mealSaveRequestDto(
    val dailyType: daily,
    val date: LocalDate,
    val food: String,
)
fun mealSaveRequestDto.toEntity(): Meal {
    return Meal.saveMeal(
        date = this.date,
        dailyType = this.dailyType,
        food = this.food
    )
}
