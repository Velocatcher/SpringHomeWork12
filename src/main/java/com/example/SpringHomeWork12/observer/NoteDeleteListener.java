package com.example.SpringHomeWork12.observer;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// Создание слушателя события (обновления заметки)
@Component
@AllArgsConstructor
public class NoteDeleteListener implements ApplicationListener<NoteDeleteEvent> {

    // Вывод сообщения в консоль при генерации события
    @Override
    public void onApplicationEvent(NoteDeleteEvent event) {
        System.out.println("Новое событие:");
        System.out.println("Заметка удалена: " + event.getNote().toString());

    }
}