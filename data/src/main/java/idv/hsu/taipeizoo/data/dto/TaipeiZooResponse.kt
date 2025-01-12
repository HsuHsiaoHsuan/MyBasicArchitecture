package idv.hsu.taipeizoo.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaipeiZooResponse(
    val result: Result
)

@JsonClass(generateAdapter = true)
data class Result(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<Animal>
)

@JsonClass(generateAdapter = true)
data class Animal(
    @Json(name = "_id") val id: Int,
    @Json(name = "_importdate") val importDate: ImportDate,
    @Json(name = "a_name_ch") val nameChinese: String,
    @Json(name = "a_summary") val summary: String,
    @Json(name = "a_keywords") val keywords: String,
    @Json(name = "a_alsoknown") val alsoKnown: String,
    @Json(name = "a_geo") val geo: String,
    @Json(name = "a_location") val location: String,
    @Json(name = "a_poigroup") val poiGroup: String,
    @Json(name = "a_name_en") val nameEnglish: String,
    @Json(name = "a_name_latin") val nameLatin: String,
    @Json(name = "a_phylum") val phylum: String,
    @Json(name = "a_class") val `class`: String,
    @Json(name = "a_order") val order: String,
    @Json(name = "a_family") val family: String,
    @Json(name = "a_conservation") val conservation: String,
    @Json(name = "a_distribution") val distribution: String,
    @Json(name = "a_habitat") val habitat: String,
    @Json(name = "a_feature") val feature: String,
    @Json(name = "a_behavior") val behavior: String,
    @Json(name = "a_diet") val diet: String,
    @Json(name = "a_crisis") val crisis: String,
    @Json(name = "a_interpretation") val interpretation: String,
    @Json(name = "a_theme_name") val themeName: String,
    @Json(name = "a_theme_url") val themeUrl: String,
    @Json(name = "a_adopt") val adopt: String,
    @Json(name = "a_code") val code: String,
    @Json(name = "a_pic01_alt") val pic01Alt: String,
    @Json(name = "a_pic01_url") val pic01Url: String,
    @Json(name = "a_pic02_alt") val pic02Alt: String,
    @Json(name = "a_pic02_url") val pic02Url: String,
    @Json(name = "a_pic03_alt") val pic03Alt: String,
    @Json(name = "a_pic03_url") val pic03Url: String,
    @Json(name = "a_pic04_alt") val pic04Alt: String,
    @Json(name = "a_pic04_url") val pic04Url: String,
    @Json(name = "a_pdf01_alt") val pdf01Alt: String,
    @Json(name = "a_pdf01_url") val pdf01Url: String,
    @Json(name = "a_pdf02_alt") val pdf02Alt: String,
    @Json(name = "a_pdf02_url") val pdf02Url: String,
    @Json(name = "a_voice01_alt") val voice01Alt: String,
    @Json(name = "a_voice01_url") val voice01Url: String,
    @Json(name = "a_voice02_alt") val voice02Alt: String,
    @Json(name = "a_voice02_url") val voice02Url: String,
    @Json(name = "a_voice03_alt") val voice03Alt: String,
    @Json(name = "a_voice03_url") val voice03Url: String,
    @Json(name = "a_vedio_url") val vedioUrl: String,
    @Json(name = "a_update") val update: String,
    @Json(name = "a_cid") val cid: String
)

@JsonClass(generateAdapter = true)
data class ImportDate(
    val date: String,
    @Json(name = "timezone_type") val timezoneType: Int,
    val timezone: String
)