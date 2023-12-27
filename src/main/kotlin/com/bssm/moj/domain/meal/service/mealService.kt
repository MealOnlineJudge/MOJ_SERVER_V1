package com.bssm.moj.domain.meal.service

import com.bssm.moj.domain.meal.domain.repository.mealRepository
import com.bssm.moj.domain.meal.domain.type.daily
import jakarta.transaction.Transactional
import mealSaveRequestDto
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import toEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
@Transactional
class mealService(
    private val mealRepository: mealRepository,
) {
    fun create() {
        val url = System.getenv("URL")
        val restTemplate = RestTemplate()

        val jsonString = restTemplate.getForObject(url, String::class.java)!!

        val mealList = mutableListOf<mealSaveRequestDto>()

        val jsonObject = JSONObject(jsonString)
        val mealServiceDietInfo = jsonObject.getJSONArray("mealServiceDietInfo")

        for (i in 0 until mealServiceDietInfo.length()) {
            val row = mealServiceDietInfo.getJSONObject(i).optJSONArray("row")

            row?.let {
                for (j in 0 until it.length()) {
                    val rowData = it.getJSONObject(j)

                    val MMEAL_SC_NM = rowData.getString("MMEAL_SC_NM")
                    val MLSV_YMD = rowData.getString("MLSV_YMD")
                    val DDISH_NM = rowData.getString("DDISH_NM")

                    val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
                    val localDate = LocalDate.parse(MLSV_YMD, dateFormatter)

                    val meal = mealSaveRequestDto(
                        dailyType = daily.values().find { it.description == MMEAL_SC_NM }!!,
                        date = localDate,
                        food = DDISH_NM
                    )
                    print(MMEAL_SC_NM)

                    mealList.add(meal)
                }
            }
        }
        for(meal in mealList)
            mealRepository.save(meal.toEntity())
    }
}
