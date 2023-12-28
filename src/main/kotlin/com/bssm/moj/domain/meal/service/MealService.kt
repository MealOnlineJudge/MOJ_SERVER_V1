package com.bssm.moj.domain.meal.service

import com.bssm.moj.domain.meal.domain.repository.MealRepository
import com.bssm.moj.domain.meal.domain.type.daily
import com.bssm.moj.domain.meal.service.properties.MealProperties
import jakarta.transaction.Transactional
import mealSaveRequestDto
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import toEntity
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Service
@Transactional
class MealService(
    private val mealRepository: MealRepository,
    private val mealProperties: MealProperties,
) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    @Scheduled(cron = "0 0 1 * * ?")
    fun create() {
        val yearMonth: YearMonth = YearMonth.now()
        val MLSV_FROM_YMD = yearMonth.atDay(1).format(dateFormatter)
        val MLSV_TO_YMD = yearMonth.atEndOfMonth().format(dateFormatter)

        val url =
            "${mealProperties.mealUrl}&key=${mealProperties.key}&MLSV_TO_YMD=$MLSV_TO_YMD&MLSV_FROM_YMD=$MLSV_FROM_YMD"
        val restTemplate = RestTemplate()

        val jsonString = restTemplate.getForObject(url, String::class.java)!!

        val mealList = mutableListOf<mealSaveRequestDto>()

        val jsonObject = JSONObject(jsonString)
        val mealServiceDietInfo = jsonObject.getJSONArray("mealServiceDietInfo")

        for (i in 0 until mealServiceDietInfo.length()) {
            val row: JSONArray = mealServiceDietInfo.getJSONObject(i).optJSONArray("row")

            for (j in 0 until row.length()) {
                val rowData = row.getJSONObject(j)

                val MMEAL_SC_NM = rowData.getString("MMEAL_SC_NM")
                val MLSV_YMD = rowData.getString("MLSV_YMD")
                val DDISH_NM = rowData.getString("DDISH_NM")

                val localDate = LocalDate.parse(MLSV_YMD, dateFormatter)

                val meal = mealSaveRequestDto(
                    dailyType = daily.entries.find { it.description == MMEAL_SC_NM }!!,
                    date = localDate,
                    food = DDISH_NM
                )

                mealList.add(meal)
            }
        }
        mealRepository.saveAll(mealList.map { it.toEntity() })
    }
}
