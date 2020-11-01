package com.example.demo.domain.repository;

import com.example.demo.domain.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository // показываем, что это -репозиторий. Репозиторий работает с конкретной таблицей БД. Для работы с кажой таблицей нужно создавать отдельный реп в отдельном классе
public interface TestTableRepository extends JpaRepository<TestTable, UUID> { //TestTable - имя таблицы, UUID - primary key.
    // extends JpaResository - здесь уже есть все методы из JPA репозитория. Чтобы их посмотреть - средней кнопкой мыши по JPARepository
    // можем определить собственный метод или запрос. Здесь Query - показывает что это запрос. Query относится к стркое в скобках (текст запроса) и методу (List...), который и есть выполнение этого запроса
    // здесь TestTable - то, как мы представляем себе эту таблицу в джаве (класс, в котором мы работаем с БД)
    @Query("select tt from TestTable tt where tt.name = :name")
    List<TestTable> getByName(String name);

    // а это нативный запрос на чистом постгресовском языке. Тут уже обращение к таблице идет так, как она зовется в таблице
    @Query(nativeQuery = true, value = "select tt from test_table tt where tt.name = :name")
    List<TestTable> getByNameNative(String name);
}
