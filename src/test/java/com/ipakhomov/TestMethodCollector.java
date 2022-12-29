package com.ipakhomov;

import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
public class TestMethodCollector {

    public static void main(String[] args) {
        String[] testMethodNames = getTestMethodNames();
        log.info("Found {} test method(s): {}", testMethodNames.length, testMethodNames);
        writeMethodNamesToFile(testMethodNames);
    }

    private static String[] getTestMethodNames() {
        Set<Method> methodsWithTestAnnotation = new Reflections("com.ipakhomov", Scanners.MethodsAnnotated)
                .getMethodsAnnotatedWith(Test.class);
        return methodsWithTestAnnotation.stream()
                .map(Method::getName)
                .toArray(String[]::new);
    }

    @SneakyThrows
    private static void writeMethodNamesToFile(String[] testMethodNames) {
        try (CSVWriter writer = new CSVWriter(new FileWriter("test_methods.csv"))) {
            writer.writeNext(testMethodNames);
        }
    }
}
