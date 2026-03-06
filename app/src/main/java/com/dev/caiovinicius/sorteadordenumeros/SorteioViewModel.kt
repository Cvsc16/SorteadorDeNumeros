package com.dev.caiovinicius.sorteadordenumeros

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random

@Serializable
data class UiState(
    val drawAmountNumber: Int = 2,
    val initialLimit: Int = 1,
    val finalLimit: Int = 100,
    val shouldRepeatNumbers: Boolean = true,
    val currentDrawNumber: Int = 0,
    val drawNumbers: List<Int> = emptyList()
)

class SorteioViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setDrawAmountNumber(drawAmountNumber: Int) {
        _uiState.value = _uiState.value.copy(drawAmountNumber = drawAmountNumber)

    }

    fun setInitialLimit(initialLimit: Int) {
        _uiState.value = _uiState.value.copy(initialLimit = initialLimit)

    }

    fun setFinalLimit(finalLimit: Int) {
        _uiState.value = _uiState.value.copy(finalLimit = finalLimit)
    }

    fun setShouldRepeatNumbers(shouldRepeatNumbers: Boolean) {
        _uiState.value = _uiState.value.copy(shouldRepeatNumbers = shouldRepeatNumbers)
    }

    fun drawNumbers() {
        val drawNumbers = mutableListOf<Int>()

        repeat(_uiState.value.drawAmountNumber) {
            var number = Random.nextInt(_uiState.value.initialLimit, _uiState.value.finalLimit)
            while (drawNumbers.contains(number) && !_uiState.value.shouldRepeatNumbers) {
                number = Random.nextInt(_uiState.value.initialLimit, _uiState.value.finalLimit)
            }
            drawNumbers.add(number)
        }

        _uiState.value =
            _uiState.value.copy(
                currentDrawNumber = _uiState.value.currentDrawNumber + 1,
                drawNumbers = drawNumbers.toList()
            )

        val gsonString = GsonBuilder().setPrettyPrinting().create().toJson(_uiState.value)
        val serializationString = Json { encodeDefaults = true }.encodeToString(_uiState.value)

        Log.d("SorteioViewModel", "[Gson] UiState serializado: $gsonString")
        Log.d("SorteioViewModel", "[Serialization] UiState serializado: $serializationString")

        Log.d("SorteioViewModel", "[Gson] UiState desserializado: ${GsonBuilder().setPrettyPrinting().create().fromJson(gsonString, UiState::class.java)}")
        Log.d("SorteioViewModel", "[Serialization] UiState desserializado: ${Json { encodeDefaults = true }.decodeFromString<UiState>(serializationString)}")
    }


}