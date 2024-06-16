package ru.androidheroes.kamchatka.data

import ru.androidheroes.kamchatka.ui.feed.*

object MockRepository {

    fun getInterview(): List<Interview> {
        return listOf(
            Interview(
                "Кирилл ",
                "Кирилл",
                "",
                ""
            )
        )
    }

    fun getPeople(): List<Person> {
        return listOf(
            Person(
                1,
                "Михаил Валуйский",
                20,
                "Опытный походник",
                "Турист",
                "https://sun9-9.userapi.com/impf/c858120/v858120456/78bb/aVmKA4PzZdQ.jpg?size=1080x720&quality=96&sign=f621362e4da05b6e17029ba369a07288&type=album"
            )
        )
    }


    fun findEvents(title: String): List<RouteDetails> {

        val allEvents = ru.androidheroes.kamchatka.data.MockRepository.getEvents()
        val result = allEvents.filter { it.title.contains(title) }
        return result.ifEmpty { emptyList() }

    }

    fun getTasks(): List<EducationDetails> {

        return listOf(
            EducationDetails(
                id = 300,
                title = "Как подготовиться к походу",
                desc = "Базовый курс для начинающих",
                imageUrl = "https://s0.rbk.ru/v6_top_pics/media/img/1/02/346831925047021.webp",
                level = LEVEL.LITE
            )
        )
    }

    fun getMusicTasks(): List<EducationDetails> {

        return listOf(
            EducationDetails(
                id = 300,
                title = "Как подготовиться к походу",
                desc = "Базовый курс для начинающих",
                imageUrl = "https://s0.rbk.ru/v6_top_pics/media/img/1/02/346831925047021.webp",
                level = LEVEL.LITE
            )
        )
    }

    fun getCoursesMusic(): List<EducationDetails> {


        return listOf(
            EducationDetails(
                id = 300,
                title = "Как подготовиться к походу",
                desc = "Базовый курс для начинающих",
                imageUrl = "https://s0.rbk.ru/v6_top_pics/media/img/1/02/346831925047021.webp",
                level = LEVEL.LITE
            )
        )
    }

    fun getCourses(): List<EducationDetails> {

        return listOf(
            EducationDetails(
                id = 300,
                title = "Как подготовиться к походу",
                desc = "Базовый курс для начинающих",
                imageUrl = "https://s0.rbk.ru/v6_top_pics/media/img/1/02/346831925047021.webp",
                level = LEVEL.LITE
            ),
            EducationDetails(
                id = 300,
                title = "Меры предосторожности",
                desc = "Правила поведения с дикими животными",
                imageUrl = "https://65.mchs.gov.ru/uploads/resize_cache/news/2021-03-29/pravila-povedeniya-pri-vstreche-s-burym-medvedem_16169734381246532391__800x800.jpg",
                level = LEVEL.LITE
            ),
            EducationDetails(
                id = 300,
                title = "Экипировка",
                desc = "Необходимая экипировка для похода",
                imageUrl = "https://nordski.ru/images/blog/lichnoe-snaryazhenie-dlya-pokhoda-1.jpg",
                level = LEVEL.LITE
            )
        )
    }

    fun getInteractivTasks(): List<EducationDetails> {

        return listOf(
        )
    }

    fun getEvents(): List<RouteDetails> {

        return listOf(
            RouteDetails(
                full_description = "Символом природного парка «Налычево» являются «домашние» вулканы — Авачинский и Корякский. Они расположены в 30 км от Петропавловска-Камчатского и добраться до них можно всего за час езды от города.\n" +
                        "\n" +
                        "Это лучший способ за 1 день познакомиться с вулканами Камчатки, понаблюдать за евражками или совершить восхождение на Авачинский вулкан.",
                id = 1,
                title = "Природный парк «Налычево»",
                desc = "7 туристических маршрутов",
                imageUrl = "https://kamchatkaland.ru/upload/images/putevoditel/nalychevo/Nalichevo1.jpg",
                webUrl = "https://vulcanikamchatki.ru/territoriya/klaster_nalychevskij/"
            ),
            RouteDetails(
                full_description = "Вулкан Мутновский часто называют «фабрикой облаков» — многочисленные фумаролы на его склонах постоянно «дымят» серными газами.\n" +
                        "\n" +
                        "Вулкан Горелый известен благодаря живописному озеру бирюзового цвета в его кратере и лавовым пещерам у подножия. \n" +
                        "\n" +
                        "Не менее интересна бухта Русская, расположенная в восточной части кластера, — здесь можно наблюдать за китами, косатками и сивучами.\n" +
                        "\n" +
                        "Южный кластер парка более труднодоступен. Как правило, его посещают в рамках вертолетных прогулок, делая высадки в кальдере вулкана Ксудач и на Ходуткинских горячих источниках.\n" +
                        "\n" +
                        "По территории природного парка «Южно-Камчатский» проложено 4 туристических маршрута, доступных в разное время года. Летом здесь можно совершать пешие прогулки налегке или под рюкзаком, ездить на джипах или квадроциклах, летать на вертолете, купаться в термальных источниках и наблюдать за дикими животными. \n" +
                        "\n",
                id = 2,
                title = "Природный парк «Южно-Камчатский»",
                desc = "4 туристических маршрута",
                imageUrl = "https://vulcanikamchatki.ru/images/Territories/ukp/v_kratere_mutnovskogo-1.jpg",
                webUrl = "https://vulcanikamchatki.ru/territoriya/klaster_yuzhnokamchatskij/"
            ),
            RouteDetails(
                full_description = "Природный парк «Ключевской» — это место наиболее активного вулканизма не только на Камчатке, но и в мире. Здесь находятся 13 высочайших вулканов России, причем четыре из них — действующие. Этот парк расположен на расстоянии 500 км от Петропавловска-Камчатского, и потому туристов здесь значительно меньше. В «Ключевском» можно почувствовать себя наедине с вулканами.",
                id = 3,
                title = "Природный парк «Ключевской»",
                desc = "3 туристических маршрута",
                imageUrl = "https://vulcanikamchatki.ru/images/Territories/kluchevskoy/klyuchevskoj_park_mel_nik_roman2.jpg",
                webUrl = "https://vulcanikamchatki.ru/territoriya/klaster_klyuchevskoj/"
            ),
            RouteDetails(
                full_description =
                "Природный парк «Быстринский» интересен ландшафтами, которых нет больше нигде на Камчатке. Помимо вулканов, ледников, фумарол и термальных источников здесь есть альпийские луга, таежные леса, горные озёра, реки, каньоны и водопады.",
                id = 4,
                title = "Природный парк «Быстринский»",
                desc = "14 туристических маршрутов",
                imageUrl = "https://vulcanikamchatki.ru/images/Territories/bystrinsky/rajon_ichinskogo_vulkana_vulkana_cherpuk_-_avgust_2022_foto_natal_i_linder_52.jpg",
                webUrl = "https://vulcanikamchatki.ru/territoriya/klaster_bystrinskij/"
            ),
            RouteDetails(
                full_description = "Жемчужина парка — Вилючинская сопка, привлекает к себе своей близостью (1,5-2ч на автомобиле от г.Елизово), живописными одноименными перевалом, водопадом и источниками. Вилючинский парк — самый молодой и включен в территории КГБУ «Сеть природных парков «Вулканы Камчатки» 28 декабря 2022 г.Путь сюда доступен и зимой, и летом, также, как и в Южно-Камчатский парк, находящийся, буквально, по соседству.",
                id = 5,
                title = "Вилючинский парк",
                desc = "14 туристических маршрутов",
                imageUrl = "https://vulcanikamchatki.ru/images/Territories/viluchinskiy/rassvet_s_vilyuchinskogo_v_spichak.jpg",
                webUrl = "https://vulcanikamchatki.ru/territoriya/klaster_vil/"
            ),
            RouteDetails(
                full_description = "Государственный экспериментальный биологический (лососевый) заказник «Река Коль» создан в целях изучения и охраны состояния биоразнообразия лососевых видов рыб и среды их обитания.",
                id = 6,
                title = "Лососевый заказник «Река Коль»",
                desc = "14 туристических маршрутов",
                imageUrl = "https://www.vulcanikamchatki.ru/images/Territories/kluchevskoy/ris_5.jpg",
                webUrl = "https://www.vulcanikamchatki.ru/territoriya/klaster_kol/"
            )
        )
    }

    // Nalychevo id = 1
    fun getRoutes(): List<RouteDetails> {

        return listOf(
            RouteDetails(
                routeType = "Nalychevo",
                passportUrl = "https://www.vulcanikamchatki.ru/files/2023/doc/pasport/nalych/summer/pasport_8_pinachevo-central_nyj.pdf",
                distance = "37.83 км",
                full_description = "1 день: переход 17,5 км. Маршрут начинается от инспекторского кордона и проходит вдоль реки Пиначевская, через стоянку «Промежуточная», до которой от кордона примерно 12 км, к стоянке кордона «Семеновский» - ещё 5,5 км. Ночевка.\n" +
                        "2 день: переход 21 км. От кордона «Семеновский», по тропе, примерно в 4 км, находится стоянка «Перевальная». От нее ещё около 5 км, тропа по каменистому распадку и горной тундре уходит вверх к Пиначевскому перевалу, абсолютная высота которого составляет 1150 метров. С перевала открывается великолепная панорама Налычевской долины. Отсюда тропа уходит круто вниз, через кедровый и ольховый стланник. Далее, постепенно выполаживаясь, тропа входит в каменноберезовый лес, и вдоль реки Горячей, спустя 12 км от перевала, подходит к кордону «Центральный». На кордоне, по предварительному бронированию, предусмотрено временное проживание в домиках. В случае использования палаток, туристы размещаются в лагере на р. Жёлтая в 1,5 км от кордона.",
                id = 1,
                title = "Туристический маршрут №8 Пиначево-центральный",
                desc = "Пиначево - Центральный",
                imageUrl = "https://idilesom.com/files/photos/places/b/662b28bacf2db_1714104506.jpeg",
                webUrl = "https://idilesom.com/kam/places/3490",
                routeId = 80,
                kmlUrl = "https://trekkingmania.ru/assets/files/kml/nalyuchevo.kml"
            ),
            RouteDetails(
                routeType = "Nalychevo",
                passportUrl = "https://www.vulcanikamchatki.ru/files/2023/doc/marshrut_avachinskij_central_nyj1.pdf",
                distance = "40.94 КМ",
                full_description = "1 день: маршрут начинается от инспекторского кордона, проходит восточнее экструзии «Верблюд», далее через Авачинский перевал в верховья ручья Перевальный с переходом на его левый борт. Через 18 км находится река Правая Седловинская, от нее необходимо держать путь в 4 км на перевал и спуститься к реке Шумная. Ночевка.\n" +
                        "2 день: переход по мосту над р.Шумная. Далее тропа ведет на Аагские нарзаны. От нарзанов тропа спускается в каменноберезовый лес и вдоль реки Ольховой идет к кордону «Центральный». Протяженность данного участка – 15 км.\n" +
                        "Половина пути проходит по шлаковым полям. Тропы как таковой нет. В районе реки Шумной начинаются заросли ольхового странника, через заросли есть тропа. Найти ее может быть проблематично, рекомендуется использовать GPS навигатором с треком маршрута.\n" +
                        "Большая часть маршрута не имеет инфраструктуры и навигации",
                id = 1,
                title = "Туристический маршрут №27 Авачинский — Центральный",
                desc = "Авачинский — Центральный",
                imageUrl = "https://idilesom.com/files/photos/places/b/662b2ed88aca8_1714106072.jpg",
                webUrl = "https://idilesom.com/kam/places/3492",
                routeId = 270,
                kmlUrl = "https://trekkingmania.ru/assets/files/kml/nalyuchevo.kml"
            ),
            RouteDetails(
                routeType = "Nalychevo",
                passportUrl = "https://www.vulcanikamchatki.ru/files/2023/doc/marshrut_avachinskij_central_nyj1.pdf",
                distance = "5 км",
                full_description = "Памятник природы находится в 25 км от г. Петропавловска-Камчатского, на территории природного парка «Вулканы Камчатки», который в 1996 г. был внесен в список Всемирного природного и культурного наследия ЮНЕСКО в номинации «Вулканы Камчатки».\n" +
                        "\n" +
                        "Верблюд - одна из экструзий ( тип и результат извержения вулканов с вязкой лавой, в данном случае состоящей преимущественно из андезитов), которые кольцом окружают камчатские «домашние» вулканы Авачинский и Корякский. Эти экструзии, результат разломов вокруг кальдеры и фундаментов вулканов, сформировалась в 5-15 тысяч лет назад.",
                id = 1,
                title = "На Авачинский перевал и экструзию верблюд",
                desc = "Экструзия Верблюд",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/96/%D0%AD%D0%BA%D1%81%D1%82%D1%80%D1%83%D0%B7%D0%B8%D1%8F_%D0%92%D0%B5%D1%80%D0%B1%D0%BB%D1%8E%D0%B4.jpg",
                webUrl = "https://www.vulcanikamchatki.ru/files/2023/doc/pasport/nalych/summer/pasport_12_ekstruziya_verblyud.pdf",
                routeId = 270,
                kmlUrl = "https://trekkingmania.ru/assets/files/kml/nalyuchevo.kml"
            ),
            RouteDetails(
                routeType = "Nalychevo",
                passportUrl = "https://www.vulcanikamchatki.ru/files/2023/doc/pasport/nalych/summer/pasport_26_central_nyj_-_talovskie_ist_-_dzendzur.pdf",
                distance = "46 км",
                full_description = "1 день: маршрут начинается от инспекторского кордона, проходит восточнее экструзии «Верблюд», далее через Авачинский перевал в верховья ручья Перевальный с переходом на его левый борт. Через 18 км находится река Правая Седловинская, от нее необходимо держать путь в 4 км на перевал и спуститься к реке Шумная. Ночевка.\n" +
                        "2 день: переход по мосту над р.Шумная. Далее тропа ведет на Аагские нарзаны. От нарзанов тропа спускается в каменноберезовый лес и вдоль реки Ольховой идет к кордону «Центральный». Протяженность данного участка – 15 км.\n" +
                        "Половина пути проходит по шлаковым полям. Тропы как таковой нет. В районе реки Шумной начинаются заросли ольхового странника, через заросли есть тропа. Найти ее может быть проблематично, рекомендуется использовать GPS навигатором с треком маршрута.\n" +
                        "Большая часть маршрута не имеет инфраструктуры и навигации",
                id = 1,
                title = "Туристический маршрут №26 Центральный — Таловские источники — вулкан Дзендзур",
                desc = "Центральный — Таловские источники — вулкан Дзендзур",
                imageUrl = "https://cdn.kamchatka-legends.ru/gallery/kamchatka/vulkan-dzenzur/vulkan-dzenzur-1.jpg",
                webUrl = "https://idilesom.com/kam/places/3492",
                routeId = 260,
                kmlUrl = "https://trekkingmania.ru/assets/files/kml/nalyuchevo.kml"
            ),
            RouteDetails(
                routeType = "South Kamchatka",
                passportUrl = "https://www.vulcanikamchatki.ru/files/2024/pasp_zimnie_marshruty/vulkan_gorelyj_zimnij.pdf",
                distance = "10 км",
                full_description = "Горелый — действующий вулкан, расположен на юге Камчатки, относится к Восточно-Камчатскому вулканическому поясу. Имеет крупный долгоживущий щитовой эруптивный центр, сохраняющий эруптивную активность в настоящее время. Состоит из 5 перекрывающихся стратовулканов, которые находятся в кальдере диаметром 10×13 км",
                id = 2,
                title = "Вулкан Горелый",
                desc = "Туристический маршрут №5 Вулкан Горелый",
                imageUrl = "https://cdn.kamchatka-legends.ru/gallery/kamchatka/vulkan-gorely/vulkan-gorely-3b.jpg",
                webUrl = "https://idilesom.com/kam/places/3492",
                routeId = 50,
                kmlUrl = "https://nasledniki.narod.ru/03_GPS/04_Other/Kamchatka_travel.kml",
                level = "Категория 1A"
            )
        )
    }
}