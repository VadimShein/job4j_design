package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char charType = 'f';
        boolean booleanType = true;
        byte byteType = 8;
        short shortType = 16;
        int intType = 32;
        long longType = 64L;
        float floatType = 32.32f;
        double doubleType = 64.0;

        LOG.debug("char symbol : {}, boolean value : {}", charType, booleanType);
        LOG.debug("byte value : {}, short value : {}, int value : {}", byteType, shortType, intType);
        LOG.debug("long value: {}", longType);
        LOG.debug("float value: {}, double value: {}", floatType, doubleType);
    }
}