package com.example.SpringHomeWork12.service;

import com.example.SpringHomeWork12.model.Note;
import com.example.SpringHomeWork12.observer.NoteDeleteEvent;
import com.example.SpringHomeWork12.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    // Создание репозитория
    private final NoteRepository noteRepository;

    // Создание объекта для публикации события
    @Autowired
    private ApplicationEventPublisher publisher;

    // Создать заметку
    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Получить все заметки
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Получить заметку по ID
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    // Обновить заметку
    @Override
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());

        noteById.setTitle(note.getTitle());
        noteById.setContents(note.getContents());
        noteById.setCreationDate(LocalDateTime.now());

        // Публикация события (обновления заметки) в консоль
//        publisher.publishEvent(new NoteUpdatedEvent(this, noteById));

        noteRepository.save(noteById);

        return noteById;
    }

    // Удалить заметку
    @Override
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        // Публикация события (удаления заметки) в консоль
        publisher.publishEvent(new NoteDeleteEvent(this, noteById));
        noteRepository.delete(noteById);
    }

}