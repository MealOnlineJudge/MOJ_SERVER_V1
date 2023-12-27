package com.bssm.moj.domain.meal.service

import com.bssm.moj.domain.meal.domain.repository.mealRepository
import com.bssm.moj.domain.meal.domain.type.daily
import jakarta.transaction.Transactional
import mealSaveRequestDto
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
@Transactional
class mealService(
    private val mealRepository: mealRepository,
) {
    fun create() {
        val jsonString = """
        {
           "mealServiceDietInfo":[
              {
                 "head":[
                    {
                       "list_total_count":3
                    },
                    {
                       "RESULT":{
                          "CODE":"INFO-000",
                          "MESSAGE":"정상 처리되었습니다."
                       }
                    }
                 ]
              },
              {
                 "row":[
                    {
                       "ATPT_OFCDC_SC_CODE":"C10",
                       "ATPT_OFCDC_SC_NM":"부산광역시교육청",
                       "SD_SCHUL_CODE":"7150658",
                       "SCHUL_NM":"부산소프트웨어마이스터고등학교",
                       "MMEAL_SC_CODE":"1",
                       "MMEAL_SC_NM":"조식",
                       "MLSV_YMD":"20231227",
                       "MLSV_FGR":141.00,
                       "DDISH_NM":"검정쌀밥 모닝빵.딸기쨈(소마) (1.2.5.6.13) 시금치된장국 (5.6.9) 과일샐러드 (1.2.5.12) 치즈스테이크 (5.6.13.18) 배추김치 (9) 블루베리요거트 (2)",
                       "ORPLC_INFO":"쇠고기(종류) : 국내산(한우) ... (중략) ... 부세 : 수입산 부세 수산가공품 : 수입산",
                       "CAL_INFO":"878.3 Kcal",
                       "NTR_INFO":"탄수화물(g) : 145.2 ... (중략) ... 철분(mg) : 3.8",
                       "MLSV_FROM_YMD":"20231227",
                       "MLSV_TO_YMD":"20231227",
                       "LOAD_DTM":"20231227"
                    },
                    {
                       "ATPT_OFCDC_SC_CODE":"C10",
                       "ATPT_OFCDC_SC_NM":"부산광역시교육청",
                       "SD_SCHUL_CODE":"7150658",
                       "SCHUL_NM":"부산소프트웨어마이스터고등학교",
                       "MMEAL_SC_CODE":"2",
                       "MMEAL_SC_NM":"중식",
                       "MLSV_YMD":"20231227",
                       "MLSV_FGR":184.00,
                       "DDISH_NM":"찰옥수수쌀밥 한우갈비탕 (1.16) 시금치나물 (5.6) 순살삼치데리야끼구이(산고) (5.6.13) 두부구이(산고) (5.6.13) 깍두기(소마) (9) 낙안매실요구르트 (2)",
                       "ORPLC_INFO":"쇠고기(종류) : 국내산(한우) ... (중략) ... 부세 : 수입산 부세 수산가공품 : 수입산",
                       "CAL_INFO":"878.2 Kcal",
                       "NTR_INFO":"탄수화물(g) : 118.7 ... (중략) ... 철분(mg) : 5.5",
                       "MLSV_FROM_YMD":"20231227",
                       "MLSV_TO_YMD":"20231227",
                       "LOAD_DTM":"20231227"
                    },
                    {
                       "ATPT_OFCDC_SC_CODE":"C10",
                       "ATPT_OFCDC_SC_NM":"부산광역시교육청",
                       "SD_SCHUL_CODE":"7150658",
                       "SCHUL_NM":"부산소프트웨어마이스터고등학교",
                       "MMEAL_SC_CODE":"3",
                       "MMEAL_SC_NM":"석식",
                       "MLSV_YMD":"20231227",
                       "MLSV_FGR":144.00,
                       "DDISH_NM":"찰보리밥 조랭이떡국 (1.5.6) 양배추사과샐러드(산고) (1.5.12) 메이플피칸파이(생지)(산고) (1.2.5.6.14) 깍두기(소마) (9) 수제김치함박스테이크/소스 (1.2.5.6.9.10.12.13.16) 스테비아방울토마토 (12)",
                       "ORPLC_INFO":"쇠고기(종류) : 국내산(한우) ... (중략) ... 부세 : 수입산 부세 수산가공품 : 수입산",
                       "CAL_INFO":"969.7 Kcal",
                       "NTR_INFO":"탄수화물(g) : 141.4 ... (중략) ... 철분(mg) : 6.1",
                       "MLSV_FROM_YMD":"20231227",
                       "MLSV_TO_YMD":"20231227",
                       "LOAD_DTM":"20231227"
                    }
                 ]
              }
           ]
        }
    """.trimIndent()

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

                    mealList.add(meal)

                    println("Meal Type: $MMEAL_SC_NM")
                    println("Date: $MLSV_YMD")
                    println("Menu: $DDISH_NM")
                    println()
                }
            }
        }
    }
}
