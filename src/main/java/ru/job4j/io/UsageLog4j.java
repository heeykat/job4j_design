package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int birthDate = 24012010;
        boolean isWomen = true;
        long phone = 8903678456L;
        byte classNumber = 9;
        char classLetter = 'b';
        float grade = 4.13F;
        double fullGrade = 4.1284543509;
        short hours = 564;

        LOG.debug("Student info birthDate : {}, isWomen : {}, phone : {}, classNumber : {}, "
                + "classLetter : {}, grade : {}, fullGrade : {}, hours : {}", birthDate, isWomen, phone, classNumber,
                classLetter, grade, fullGrade, hours);
    }
}