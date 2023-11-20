package org.exampleHotel.domain.guest;

import org.exampleHotel.util.SystemUtils;

public enum Gender {
    FEMALE(SystemUtils.FEMALE),
    MALE(SystemUtils.MALE),
    LGBTQ(SystemUtils.LGBT);

    private String asStr;

    Gender(String asStr) {
        this.asStr = asStr;
    }

    @Override
    public String toString() {
        return this.asStr;
    }
}

