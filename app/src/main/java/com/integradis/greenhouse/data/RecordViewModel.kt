package com.integradis.greenhouse.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.integradis.greenhouse.data.local.AppDatabase
import com.integradis.greenhouse.data.local.RecentRecordDao
import com.integradis.greenhouse.data.model.RecentRecord
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {
    private val recordDao: RecentRecordDao = AppDatabase.getDatabase(application).recentRecordDao()
    private val _record = MutableLiveData<RecentRecord>()
    val record: LiveData<RecentRecord> get() = _record

    val defaultRecord = RecentRecord("cc2c4c87-c511-243a-a45c-99a02fa112d1", "Formula", "cc7c6c19-c416-453a-a93b-99a02fa136d0")

    init {
        // Inicializar el ViewModel con el registro por defecto
        _record.value = defaultRecord
    }

    fun loadMostRecentRecord(cropId: String, phase: String) {
        recordDao.getMostRecentRecord(cropId, phase).observeForever { record ->
            _record.value = record ?:defaultRecord
        }
    }

    fun addNewRecord(newRecord: RecentRecord) {
        viewModelScope.launch {
            // Eliminar el registro anterior
            recordDao.deletePreviousRecords(newRecord.cropId, newRecord.phase)
            // Insertar el nuevo registro
            recordDao.insertRecord(newRecord)
        }
    }
}

class RecordViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecordViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}