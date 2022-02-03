package id.go.kebumenkab.simpus.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.go.kebumenkab.simpus.R
import id.go.kebumenkab.simpus.databinding.ItemRowPatientBinding
import id.go.kebumenkab.simpus.model.patient.dummy.Patient

class PatientAdapter(private val patientList: List<Patient>):
    RecyclerView.Adapter<PatientAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRowPatientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(patientList[position]) {
                binding.apply {
                    textName.text = name
                    textIdNumber.text = idNumber

                    val bundle = Bundle()
                    bundle.putString(EXTRA_NAME, name)
                    cardPatient.setOnClickListener {
                        Navigation.findNavController(it).navigate(R.id.action_patientFragment_to_detailPatientFragment, bundle)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = patientList.size

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}