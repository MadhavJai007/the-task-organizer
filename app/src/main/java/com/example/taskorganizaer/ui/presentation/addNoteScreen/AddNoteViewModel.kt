package com.exmaple.taskorganizaer.ui.presentation.addNoteScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizaer.data.models.NoteModel
import com.example.taskorganizaer.domain.NoteRepository
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepo = NoteRepository(application)

    fun insertNote(noteModel: NoteModel) {
        viewModelScope.launch {
            noteRepo.insertNoteToRoom(noteModel)
        }
    }
}