package app.olauncher

import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.olauncher.data.KeyDownModel
import app.olauncher.helper.SingleLiveEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KeyDownSharedViewModel: ViewModel() {
    private val _onKeyClick = SingleLiveEvent<KeyDownModel>()
    val onKeyClick: LiveData<KeyDownModel> = _onKeyClick
    private val _onKeyDoubleClick = SingleLiveEvent<KeyDownModel>()
    val onKeyDoubleClick: LiveData<KeyDownModel> = _onKeyDoubleClick

    private var clickJob: Job? = null

    fun onKeyDown(keyCode: Int) {
        if (keyCode != 289) return

        if (clickJob?.isActive == true) {
            clickJob?.cancel()
            _onKeyDoubleClick.postValue(KeyDownModel(keyCode))
        } else {
            clickJob = viewModelScope.launch {
                delay(500)
                _onKeyClick.postValue(KeyDownModel(keyCode))
            }
        }
    }
}