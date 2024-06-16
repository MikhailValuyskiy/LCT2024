package ru.androidheroes.kamchatka.data

object AddressRepository {

    fun getWarnings(): List<Warning> {

        return listOf(
            Warning(
                id = 1,
                title = "Свалка мусора",
                location = Pair(
                    158.38896,
                    53.351978
                ),
                routeName = "Маршрут №7"
            )
        )
    }
}

data class Warning(
    var id: Int,
    var title: String,
    var routeName: String,
    var location: Pair<Double, Double>,
    var district: District = District.BAO,
    var area: String = "",
    var status:String="На рассмотрении"
)

enum class District {
    CAO,
    SAO,
    SBAO,
    BAO,
    YBAO,
    YAO,
    YZAO,
    ZAO,
    SZAO,
    ZelAO,
    TinAO
}