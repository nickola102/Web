package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NegativeTests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void fieldNameEmpty() {

        $("[data-test-id='phone'] input").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void fieldPhoneEmpty() {

        $("[data-test-id='name'] input").setValue("Иванов Игорь");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void fieldAgreementEmpty() {

        $("[data-test-id='name'] input").setValue("Иванов Игорь");
        $("[data-test-id='phone'] input").setValue("+79998887766");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void allFieldsEmpty() {

        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void latinName() {

        $("[data-test-id='name'] input").setValue("Ivanov Igor");
        $("[data-test-id='phone'] input").setValue("+79998887766");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void tenSymbolsInPhone() {

        $("[data-test-id='name'] input").setValue("Иванов Игорь");
        $("[data-test-id='phone'] input").setValue("+7999888776");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void twelveSymbolsInPhone() {

        $("[data-test-id='name'] input").setValue("Иванов Игорь");
        $("[data-test-id='phone'] input").setValue("+799988877665");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
