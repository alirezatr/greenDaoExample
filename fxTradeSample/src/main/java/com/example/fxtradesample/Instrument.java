package com.example.fxtradesample;

import java.math.BigDecimal;

/**
 * An item to be traded.  Typically a currency pair (such as "EUR/USD"), CFD (such as "US SPX 500"),
 * or a commodity (such as "Gold").
 */
public final class Instrument {
    private final String mInstrument;
    private final String mDisplayName;
    private final BigDecimal mPip;
    private final BigDecimal mPrecision;
    private final int mMaxTradeUnits;

    /**
     * Create a new Instrument
     */
    public Instrument(String instrument, String displayName, BigDecimal pip, BigDecimal precision, int maxTradeUnits) {
        mInstrument = instrument;
        mDisplayName = displayName;
        mPip = pip;
        mPrecision = precision;
        mMaxTradeUnits = maxTradeUnits;
    }

    /** The non-localized identifying string of the instrument. Eg, "XAU_USD". */
    public String instrument() { return mInstrument; }

    /** The localized (translated) human readable name of the instrument.  Eg, "Gold". */
    public String displayName() { return mDisplayName; }

    /** The fraction of a price of the instrument that represents on pip. */
    public BigDecimal pip() { return mPip; }

    /** The decimal places of which prices for the instrument are accurate to. */
    public BigDecimal precision() { return mPrecision; }

    /** The maximum number of units that can be traded at one time of the instrument. */
    public int maxTradeUnits() { return mMaxTradeUnits; }

    /**
     * Returns a string representation of this @{code Instrument}.
     *
     * @return a string representation of {@code this}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(mInstrument.length() + 3 + mDisplayName.length());
        sb.append(mInstrument);
        if (!mInstrument.equals(mDisplayName)) {
            sb.append(" ("); sb.append(mDisplayName); sb.append(")");
        }
        return sb.toString();
    }

    /**
     * Checks the two object for equality by instrument name.
     *
     * @param o the {@link Instrument} to which this one is to be checked for equality
     * @return true if the objects are both considered equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instrument)) {
            return false;
        }

        Instrument i = (Instrument) o;
        return mInstrument.equals(i.mInstrument);
    }

    /**
     * Returns a hash code for this {@code Instrument}.
     *
     * @return a hash code for {@code this}.
     */
    @Override
    public int hashCode() {
        /* should use the same fields that are used to determine equality */
        return mInstrument.hashCode();
    }
}