package ru.netology;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class GeoServiceImplTest {
    @ParameterizedTest
    @MethodSource("ipSource")
    void byIpTest(String ip, Country country) {
        GeoServiceImpl geoService = new GeoServiceImpl();

        assertThat(geoService.byIp(ip).getCountry(), is(country));
    }

    static Stream<Arguments> ipSource() {
        return Stream.of(
                arguments("172.", Country.RUSSIA),
                arguments("96.", Country.USA)
        );
    }
}
