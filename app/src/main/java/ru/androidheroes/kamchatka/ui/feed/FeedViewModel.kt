package ru.androidheroes.kamchatka.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.androidheroes.kamchatka.data.MockRepository

class FeedViewModel : ViewModel() {

    val interviewsLiveData: MutableLiveData<List<Interview>> by lazy {
        MutableLiveData<List<Interview>>()
    }

    val eventsLiveData: MutableLiveData<List<RouteDetails>> by lazy {
        MutableLiveData<List<RouteDetails>>()
    }


    val nearEventsLiveData: MutableLiveData<List<RouteDetails>> by lazy {
        MutableLiveData<List<RouteDetails>>()
    }

    val liked: MutableLiveData<List<RouteDetails>> by lazy {
        MutableLiveData<List<RouteDetails>>()
    }




    init {
        interviewsLiveData.value = MockRepository.getInterview()
        eventsLiveData.value = MockRepository.getEvents()
        nearEventsLiveData.value = MockRepository.getEvents()
        liked.value = MockRepository.getEvents()
    }


}