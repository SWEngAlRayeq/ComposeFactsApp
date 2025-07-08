package app.facts.presentation.fact

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.facts.domain.model.Fact
import app.facts.domain.usecase.GetRandomFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(
    private val getRandomFactUseCase: GetRandomFactUseCase
) : ViewModel() {


    var fact by mutableStateOf<Fact?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set


    init {
        getFact()
    }

    fun getFact() {
        viewModelScope.launch {
            isLoading = true
            error = null
            fact = getRandomFactUseCase()
            try {
                isLoading = true
                error = null
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }

}