package com.dev.caiovinicius.sorteadordenumeros

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.FractionRes
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.dev.caiovinicius.sorteadordenumeros.databinding.FragmentResultadoSorteioBinding
import kotlinx.coroutines.launch
import kotlin.getValue
import kotlin.random.Random

class ResultadoSorteioFragment : Fragment() {

    private val viewModel: SorteioViewModel by activityViewModels()

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
            lifecycleScope.launch {
                viewModel.uiState.collect { uiState ->
                    tvDrawNumber.text = getString(
                        R.string.numero_do_sorteio,
                        uiState.currentDrawNumber.toString()
                    )

                    clearLastDrewNumbers()
                    uiState.drawNumbers.forEach { drawNumber ->
                        generateDrawNumberTextView(drawNumber)
                    }
                }
            }
        }
    }

    private fun FragmentResultadoSorteioBinding.generateDrawNumberTextView(drawNumber: Int) {
        val drawNumberTextView = TextView(requireContext()).apply {
            id = View.generateViewId()
            text = drawNumber.toString()
            setTextAppearance(R.style.TextAppearance_RobotoMono_Overline)
            textSize = 48f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.content_brand))
        }

        root.addView(drawNumberTextView)
        flowResultNumbersHelper.referencedIds =
            flowResultNumbersHelper.referencedIds.plus(drawNumberTextView.id)
    }

    private fun FragmentResultadoSorteioBinding.clearLastDrewNumbers() {
        flowResultNumbersHelper.referencedIds.forEach {
            root.removeView(root.findViewById(it))
        }
    }
}