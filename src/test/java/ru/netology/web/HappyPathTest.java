package ru.netology.web;

import org.junit.jupiter.api.Test;

public class HappyPathTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenadeElement form = $("[data-test-id=callback-form]");
    }
}
