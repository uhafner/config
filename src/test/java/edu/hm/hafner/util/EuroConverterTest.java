package edu.hm.hafner.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.hm.hafner.util.EuroConverter.Currency;

/**
 * Testet die Klasse EuroConverter.
 *
 * @author Ulli Hafner
 */
public class EuroConverterTest {
    /** Delta für Währungsumrechnung. */
    private static final double DELTA = 0.0001;

    /**
     *  Testet die Euro nach Euro Konversion mit verschiedenen Werten.
     */
    @Test
    public void testeEuroNachEuroKonversion() {
        // Given
        EuroConverter converter = new EuroConverter();
        verifyEuroNachEuro(converter, 1);
        verifyEuroNachEuro(converter, 15);
        verifyEuroNachEuro(converter, 150);
    }

    private void verifyEuroNachEuro(final EuroConverter converter, final double value) {
        // When
        double resultat = converter.converterToEuro(value, Currency.EUR);
        // Then
        assertEquals("Euro nach Euro Konversion fehlgeschlagen", value, resultat, DELTA);
    }

    /**
     *  Testet die Franken nach Euro Konversion mit verschiedenen Werten.
     */
    @Test
    public void testeFrankenNachEuroKonversion() {
        // Given
        EuroConverter converter = new EuroConverter();
        // When
        double ergebnis = converter.converterToEuro(6, Currency.SFR);
        // Then
        assertEquals("Franken nach Euro Konversion fehlgeschlagen", 5, ergebnis, DELTA);
    }
}

