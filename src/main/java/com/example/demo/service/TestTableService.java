package com.example.demo.service;

import com.example.demo.domain.TestTable;
import com.example.demo.domain.repository.TestTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service // сервис. В данном случае для работы с БД. Вообще сервисом можно дергать классы из другого места, не обязательно из БД
public class TestTableService {
    // Тянем логер из slf4j
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTableService.class);

    // создаем поле компонента, который нужен в этом компоненте
    private TestTableRepository testTableRepository;
    // после поля создаем конструктор, в котором указываем как входные паарметры имена компонентов (бины), которые тут нуэжно подключить. В данном случае- TestTableRepository
    public TestTableService(TestTableRepository testTableRepository) {
        this.testTableRepository = testTableRepository;
    }

    // перед любым методом, которе изменяют БД, нужно прописать транзакционность. Это делается с помощью @Transactionsl
    // propagation - характер транзакционности
    // основные виды транзикционности:
    // REQUIRED - если уже есть транзакция, которая была запущена в метоюе выше. он будет использовать ее. Если нет - он сохдаст новую.
    // если сломается чето здесь, то откатится всто что было ранее в этой транзакции
    // REQUIRES_NEW - просто создает новую транзакцию. Есди чето упадет откатит только изменения в рамкахмэтого метода
    // MANDATORY - пытается взять транзацкию, которая была выше. Если такой нет - кинет ошибку. Если чето упадет, то все транзакция сверху
    // save - просто название метода, т.к. мы вызываем save из репозитория
    //Transactional нужно взять спринговый, а не javax
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TestTable save(final String name) {
        TestTable testTable = new TestTable(UUID.randomUUID(), name);
        LOGGER.info("Created TestTable {}", testTable); // testTable можем использовать, потмоу что в нем мы определили метод toString()
        return testTableRepository.save(testTable);
    }
}
