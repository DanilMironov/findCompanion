package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity //показывает, что это - сущность, которая представляет сущность из бд
@Table(name = "test_table") // показывает, что сущность - таблица
public class TestTable {
    @Id // показывает, что это поле соответсвует primary key из таблицы
    @Column(name = "id", columnDefinition = "uuid") // показывает, что поле соответствует колонке из базы с именем id
    private UUID id;
    @Column(name = "name", columnDefinition = "text") // columnDefinition - это указать, какого типа колонка в БД
    private String name;

    //alt + insert позволяет создать всякую штуку автоматически. Сейчас создали геттеры и сеттеры.

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public TestTable(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
 // пустой конструктор нужен обязатель, потому что jpa не умеет буез них работать, чтобы Entity работало
    public TestTable() {
    }
}
