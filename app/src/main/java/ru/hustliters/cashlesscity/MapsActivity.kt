package ru.hustliters.cashlesscity

import android.app.SearchManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import ru.hustliters.cashlesscity.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this);

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val map = binding.mapview.map
        map.move(
            CameraPosition(Point(59.938703, 30.318006), 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5F),
            null
        )

        val viewModel: MapController by viewModels(factoryProducer = { MapControllerFactory(map) })

        map.addTapListener(viewModel);
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart();
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop();
    }
}