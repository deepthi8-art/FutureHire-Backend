package com.futurehire.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SensitiveFieldSerializationTest {

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @Test
    void sensitiveEntityFieldsAreAcceptedButNeverSerialized() throws Exception {
        User user = objectMapper.readValue("{\"password\":\"encoded-hash\"}", User.class);
        Resume resume = objectMapper.readValue("{\"fileData\":\"AQI=\"}", Resume.class);
        AptitudeQuestion question = objectMapper.readValue("{\"correctOptionIndex\":2}", AptitudeQuestion.class);

        assertFalse(objectMapper.writeValueAsString(user).contains("password"));
        assertFalse(objectMapper.writeValueAsString(resume).contains("fileData"));
        assertFalse(objectMapper.writeValueAsString(question).contains("correctOptionIndex"));
        assertEquals("encoded-hash", user.getPassword());
        assertEquals(2, resume.getFileData().length);
        assertEquals(2, question.getCorrectOptionIndex());
    }
}