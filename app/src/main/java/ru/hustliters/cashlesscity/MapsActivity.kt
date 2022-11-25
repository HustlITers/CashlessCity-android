package ru.hustliters.cashlesscity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            CameraPosition(Point(62.027221, 129.732178), 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5F),
            null
        )

        val viewModel: MapController by viewModels(factoryProducer = { MapControllerFactory(map) })

//        var retrofit = Retrofit.Builder()
//            .baseUrl("http://cashlesscity.ru/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

        map.addTapListener(viewModel);
//        val callbackOnClickBusiness = fun(sadas: Int, asd: View?) {
//
//        }
        viewModel.selectedBusinesses.observe(this) { selectedBusinesses ->
            if (selectedBusinesses.isEmpty()) return@observe
            val modalBottomSheet = ModalBottomSheet(selectedBusinesses)
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
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