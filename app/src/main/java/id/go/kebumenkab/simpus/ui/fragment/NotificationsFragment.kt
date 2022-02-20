package id.go.kebumenkab.simpus.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.go.kebumenkab.simpus.databinding.FragmentNotificationsBinding
import id.go.kebumenkab.simpus.hawkstorage.HawkStorage

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = HawkStorage.instance(context).getUser()
        binding.textName.text = user.token
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}