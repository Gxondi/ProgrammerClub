package com.hyh.club.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class DruidCryptUtil {
    private static String publicKey;
    private static String privetKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privetKey = keyPair[0];
            publicKey = keyPair[1];
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String text) throws Exception {
        String encrypt = ConfigTools.encrypt(privetKey, text);
        return encrypt;
    }

    public static String decrypt(String text) throws Exception {
        String encrypt = ConfigTools.decrypt(publicKey, text);
        return encrypt;
    }
}
