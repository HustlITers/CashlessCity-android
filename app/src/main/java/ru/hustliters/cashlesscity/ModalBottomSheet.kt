package ru.hustliters.cashlesscity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(private val selectedBusinesses: List<Business>) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.select_business, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.selectedBusinesses)
        recycler.adapter = SelectedBusinessesAdapter(selectedBusinesses) { _, _ -> }
        return view
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
