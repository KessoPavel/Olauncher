package app.olauncher

import android.view.KeyEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.olauncher.data.KeyDownModel

class KeyDownSharedViewModel: ViewModel() {
    private val _onKeyDown = MutableLiveData<KeyDownModel>()
    val onKeyDown: LiveData<KeyDownModel> = _onKeyDown

    fun onKeyDown(keyCode: Int, event: KeyEvent?) {
        _onKeyDown.postValue(KeyDownModel(keyCode, event))
    }
}