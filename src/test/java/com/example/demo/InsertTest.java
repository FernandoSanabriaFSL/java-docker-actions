package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(ApplicationConfig.class)
public class InsertTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testInsertUser() {
        Event event = new Event();
        event.setId("500");
        Event savedUser = eventRepository.save(event);

        assertNotNull(savedUser.getId());
    }
}
