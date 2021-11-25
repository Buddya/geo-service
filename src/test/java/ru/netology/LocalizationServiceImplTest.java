package ru.netology;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LocalizationServiceImplTest {
    @ParameterizedTest
    @MethodSource("localeData")
    void localeTest(Country country, String message) {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        assertThat(localizationService.locale(country), is(message));
    }

    static Stream<Arguments> localeData() {
        return Stream.of(
                arguments(Country.RUSSIA, "Добро пожаловать"),
                arguments(Country.USA, "Welcome")
        );
    }
}
