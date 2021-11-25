package ru.netology;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

public class MessageSenderImplTest {
    @Test
    void sendEng() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("USA")).thenReturn(new Location("", Country.USA, "", 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("hello");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "USA");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        assertThat(messageSender.send(headers), is("hello"));
    }

    @Test
    void sendRus() {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("RUSSIA")).thenReturn(new Location("", Country.RUSSIA, "", 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("привет");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "RUSSIA");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        assertThat(messageSender.send(headers), is("привет"));
    }
}
