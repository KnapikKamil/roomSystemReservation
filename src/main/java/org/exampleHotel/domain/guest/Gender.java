package org.exampleHotel.domain.guest;

import org.exampleHotel.util.Properties;

public enum Gender {
    FEMALE(Properties.FEMALE),
    MALE(Properties.MALE),
    LGBTQ(Properties.LGBT);

    private String asStr;

    Gender(String asStr) {
        this.asStr = asStr;
    }

    @Override
    public String toString() {
        return this.asStr;
    }
}

