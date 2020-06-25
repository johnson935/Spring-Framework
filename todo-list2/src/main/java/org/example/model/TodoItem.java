package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
public class TodoItem {
    // fields
    private int id;
    private String details;
    private String title;
    private LocalDate deadLine;

    public TodoItem(String details, String title, LocalDate deadLine) {
        this.details = details;
        this.title = title;
        this.deadLine = deadLine;
    }
}
