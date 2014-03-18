package edu.hm.hafner.util;

/**
 * Konvertiert Geldbeträge nach Euro.
 *
 * @author Ulli Hafner
 */
public class EuroConverter {
    /**
     * Verfügbare Währungen.
     */
    enum Currency {
        /** Euro. */
        EUR,
        /** Schweizer Franken. */
        SFR
    }

    /**
     * Konvertiert den übergebenen Betrag in Euro.
     *
     * @param value
     *            der Betrag
     * @param waehrung
     *            die Währung
     * @return konvertierter Betrag in Euro
     */
    public double converterToEuro(final double value, final Currency waehrung) {
        return value * getWechselkurs(waehrung);
    }

    private double getWechselkurs(final Currency waehrung) {
        if (Currency.SFR == waehrung) {
            return 5.0 / 6;
        }
        else {
            return 1;
        }
    }
}
