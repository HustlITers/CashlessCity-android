package ru.hustliters.cashlesscity

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yandex.mapkit.geometry.Geometry
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.search.*
import com.yandex.mapkit.uri.UriObjectMetadata
import com.yandex.runtime.Error


class MapController(private val searchManager: SearchManager, private val map: Map)
    : ViewModel(), Session.SearchListener, GeoObjectTapListener {

    override fun onSearchResponse(response: Response) {
        for (index in response.collection.children) {
            index.obj?.let { geoObject ->
                val metadata = geoObject.metadataContainer.getItem(BusinessObjectMetadata::class.java)
                Log.i("onSearchResponse", "Category - ${metadata.name}, description - ${geoObject.descriptionText}")
            }
        }
    }

    override fun onSearchError(error: Error) {
        Log.d("onSearchError", error.toString())
    }

    override fun onObjectTap(event: GeoObjectTapEvent): Boolean {
        event.isSelected = true
        val uriMetadata = event.geoObject
            .metadataContainer
            .getItem(UriObjectMetadata::class.java)
        if (uriMetadata != null) {
            searchManager.searchByURI(uriMetadata.uris.first().value, SearchOptions().setSearchTypes(SearchType.BIZ.value), this)
        } else {
            val boundingBox = event.geoObject.boundingBox
            if (boundingBox != null) {
                searchManager.submit("", Geometry.fromBoundingBox(boundingBox), SearchOptions().setSearchTypes(SearchType.BIZ.value), this)
            } else {
                event.geoObject.geometry.first().point?.let {
                    searchManager.submit(
                        it,
                        map.cameraPosition.zoom.toInt() - 1,
                        SearchOptions().setSearchTypes(SearchType.BIZ.value),
                        this
                    )
                }
            }
        }
        return true
    }
}