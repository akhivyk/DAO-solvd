package com.solvd.farm.parsing;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

;

public class JacksonParser {
    private static final Logger logger = LogManager.getLogger();
    ObjectMapper objectMapper = new ObjectMapper();

    public <T> List<T> readEntityFromFile(String fileName, Class<T> name) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, name);
            return objectMapper.readValue(new File(fileName), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeUsers(List<User> users) throws IOException {
        File result = new File("user.json");
        if (!result.exists()) {
            result.createNewFile();
        }
        objectMapper.writeValue(result, users);
    }

    public <T> void writeValue(T entity) throws IOException {
        File result = new File("entity.json");
        if (!result.exists()) {
            result.createNewFile();
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(result, entity);
    }
}
