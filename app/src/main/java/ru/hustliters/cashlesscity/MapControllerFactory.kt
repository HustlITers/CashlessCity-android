package ru.hustliters.cashlesscity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType

class MapControllerFactory(private val map: com.yandex.mapkit.map.Map): ViewModelProvider.Factory {
    private val search: SearchManager = SearchFactory.getInstance().createSearchManager(
        SearchManagerType.ONLINE
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapController(search, map) as T
    }
}