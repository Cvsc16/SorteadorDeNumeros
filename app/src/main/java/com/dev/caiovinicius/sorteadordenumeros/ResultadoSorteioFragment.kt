package com.dev.caiovinicius.sorteadordenumeros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.dev.caiovinicius.sorteadordenumeros.databinding.FragmentResultadoSorteioBinding

class ResultadoSorteioFragment : Fragment() {

    private var _binding: FragmentResultadoSorteioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultadoSorteioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvInfoTitle
        }
    }
}