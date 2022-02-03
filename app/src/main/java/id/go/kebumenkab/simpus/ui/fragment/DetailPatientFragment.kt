package id.go.kebumenkab.simpus.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.go.kebumenkab.simpus.adapter.PatientAdapter.Companion.EXTRA_NAME
import id.go.kebumenkab.simpus.databinding.FragmentDetailPatientBinding

class DetailPatientFragment : Fragment() {
    private var _binding: FragmentDetailPatientBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailPatientBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        val dataName = arguments?.getString(EXTRA_NAME)

        binding.apply {
            toolbar.title = dataName
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }
}