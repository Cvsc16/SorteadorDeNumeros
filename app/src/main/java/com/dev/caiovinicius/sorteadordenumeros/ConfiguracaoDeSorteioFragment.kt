package com.dev.caiovinicius.sorteadordenumeros

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dev.caiovinicius.sorteadordenumeros.databinding.FragmentConfiguracaoDeSorteioBinding
import kotlin.getValue

class ConfiguracaoDeSorteioFragment : Fragment() {

    private val viewModel: SorteioViewModel by activityViewModels()

    private var _binding: FragmentConfiguracaoDeSorteioBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracaoDeSorteioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            swtNotRepeatNumbers.setOnCheckedChangeListener { _, isChecked ->
                swtNotRepeatNumbers.trackTintList = getColorStateList(
                    requireContext(),
                    if (isChecked) R.color.background_brand else R.color.content_tertiary
                )
                viewModel.setShouldRepeatNumbers(shouldRepeatNumbers = !isChecked)
            }

            etAmountNumbers.addTextChangedListener { text ->
                viewModel.setDrawAmountNumber(drawAmountNumber = text.toString().toIntOrNull() ?: 0)
            }

            etInitialLimit.addTextChangedListener { text ->
                viewModel.setInitialLimit(initialLimit = text.toString().toIntOrNull() ?: 1)
            }

            etFinalLimit.addTextChangedListener { text ->
                viewModel.setFinalLimit(finalLimit = text.toString().toIntOrNull() ?: 0)
            }
        }
    }
}