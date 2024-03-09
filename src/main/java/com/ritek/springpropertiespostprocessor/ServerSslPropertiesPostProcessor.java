package com.ritek.springpropertiespostprocessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ResourceUtils;

public class ServerSslPropertiesPostProcessor implements EnvironmentPostProcessor {
    private static final Map<String, String> propertiesToAdd = Map.of(
            "path.to.file1", "file1PropertyName",
            "path.to.file2", "file2PropertyName"
    );

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final Properties newProperties = new Properties();

        setKeyStoreProperties(environment, newProperties);

        if (!newProperties.isEmpty()) {
            environment.getPropertySources().addLast(
                    new PropertiesPropertySource(ServerSslPropertiesPostProcessor.class.getName(), newProperties));
        }
    }

    private static void setKeyStoreProperties(ConfigurableEnvironment environment, Properties properties) {
        propertiesToAdd.forEach(
                (key, value) -> {
                    final String path = environment.getProperty(key);
                    if (path != null && !path.isBlank()) {
                        properties.setProperty(value, readValueFromFile(path));
                    }
                });

    }

    private static String readValueFromFile(String filePath) {
        try {
            final Path path = ResourceUtils.getFile(filePath).toPath();
            return Files.lines(path)
                    .findFirst()
                    .map(String::trim)
                    .orElseThrow(
                            () -> new RuntimeException("Can not find any value in file: " + filePath));
        } catch (IOException exception) {
            throw new IllegalStateException("An error occurred when reading a file: " + filePath, exception);
        }
    }

}
