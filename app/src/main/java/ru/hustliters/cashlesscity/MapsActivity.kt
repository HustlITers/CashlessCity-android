package ru.hustliters.cashlesscity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.selectedBusinesses.adapter = SelectedBusinessesAdapter(arrayListOf())
        binding.selectedBusinesses.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)

        val map = binding.mapview.map
        map.move(
            CameraPosition(Point(62.027221, 129.732178), 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5F),
            null
        )

        val viewModel: MapController by viewModels(factoryProducer = { MapControllerFactory(map) })

        map.addTapListener(viewModel);
        viewModel.selectedBusinesses.observe(this) { selectedBusinesses ->
            binding.selectedBusinesses.adapter = SelectedBusinessesAdapter(selectedBusinesses)
        }
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