package idv.hsu.taipeizoo.data

import idv.hsu.taipeizoo.data.dto.Animal
import idv.hsu.taipeizoo.data.dto.ImportDate
import idv.hsu.taipeizoo.data.dto.Result
import idv.hsu.taipeizoo.data.dto.TaipeiZooResponse
import io.mockk.mockk

val mockResponse = mockk<TaipeiZooResponse>()

val mockResponse2 = TaipeiZooResponse(
    result = Result(
        limit = 0,
        offset = 0,
        count = 0,
        sort = "",
        results = listOf<Animal>(
            Animal(
                id = 123,
                importDate = ImportDate(
                    date = "",
                    timezone_type = 0,
                    timezone = ""
                ),
                a_name_ch = "",
                a_summary = "",
                a_keywords = "",
                a_alsoknown = "",
                a_geo = "",
                a_location = "",
                a_poigroup = "",
                a_name_en = "",
                a_name_latin = "",
                a_phylum = "",
                a_class = "",
                a_order = "",
                a_family = "",
                a_conservation = "",
                a_distribution = "",
                a_habitat = "",
                a_feature = "",
                a_behavior = "",
                a_diet = "",
                a_crisis = "",
                a_interpretation = "",
                a_theme_name = "",
                a_theme_url = "",
                a_adopt = "",
                a_code = "",
                a_pic01_alt = "",
                a_pic01_url = "",
                a_pic02_alt = "",
                a_pic02_url = "",
                a_pic03_alt = "",
                a_pic03_url = "",
                a_pic04_alt = "",
                a_pic04_url = "",
                a_pdf01_alt = "",
                a_pdf01_url = "",
                a_pdf02_alt = "",
                a_pdf02_url = "",
                a_voice01_alt = "",
                a_voice01_url = "",
                a_voice02_alt = "",
                a_voice02_url = "",
                a_voice03_alt = "",
                a_voice03_url = "",
                a_vedio_url = "",
                a_update = "",
                a_cid = ""
            )
        )
    )
)