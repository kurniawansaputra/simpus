package id.go.kebumenkab.simpus.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import id.go.kebumenkab.simpus.R
import id.go.kebumenkab.simpus.adapter.PatientAdapter
import id.go.kebumenkab.simpus.databinding.FragmentPatientBinding
import id.go.kebumenkab.simpus.model.patient.dummy.Patient
import id.go.kebumenkab.simpus.model.patient.dummy.PatientsData

class PatientFragment : Fragment() {
    private var patientList: ArrayList<Patient> = arrayListOf()

    private var _binding: FragmentPatientBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPatientBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()

        binding.rvPatient.setHasFixedSize(true)
        patientList.clear()
        patientList.addAll(PatientsData.patientList)
        setPatientList()
    }

    private fun setListener() {
        binding.apply {
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun setPatientList() {
        val patientAdapter = PatientAdapter(patientList)
        binding.rvPatient.adapter = patientAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.patient_menu, menu)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.findViewById<View>(androidx.appcompat.R.id.search_plate)?.setBackgroundColor(
            Color.TRANSPARENT)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}