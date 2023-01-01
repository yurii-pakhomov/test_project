package com.ipakhomov.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WebDriverMode {
    LOCAL("local"),
    DOCKER("docker");

    private final String name;

    public static WebDriverMode fromString(String name) {
        for (WebDriverMode mode : WebDriverMode.values()) {
            if (mode.getName().equalsIgnoreCase(name)) {
                return mode;
            }
        }
        return null;
    }
}
