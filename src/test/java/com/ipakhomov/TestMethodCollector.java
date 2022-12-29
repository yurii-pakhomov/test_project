package com.ipakhomov;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
public class TestMethodCollector {

    @SneakyThrows
    public static void main(String[] args) {
        Set<Method> methodsWithTestAnnotation = new Reflections("com.ipakhomov", Scanners.MethodsAnnotated)
                .getMethodsAnnotatedWith(Test.class);
        String[] methods = methodsWithTestAnnotation.stream()
                .map(Method::getName)
                .toArray(String[]::new);
        log.info("Found {} test method(s): {}", methods.length, methods);
        try (CSVWriter writer = new CSVWriter(new FileWriter("test_methods.csv"))) {
            writer.writeNext(methods);
        }
    }
}
