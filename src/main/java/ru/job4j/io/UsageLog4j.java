package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int birthDate = 24012010;
        boolean isWomen = true;
        byte classNumber = 9;
        char classLetter = 'b';
        float grade = 4.13F;
        short hours = 564;

        LOG.debug("Student info birthDate : {}, isWomen : {}, classNumber : {},"
                + "classLetter : {}, grade : {}, hours : {}", birthDate, isWomen, classNumber,
                classLetter, grade, hours);
    }
}