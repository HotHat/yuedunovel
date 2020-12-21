package com.lyhux.yuedunovel;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void json_sign() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String json = "{\n" +
                      "    \"b\": 12,\n" +
                      "    \"c\": 23,\n" +
                      "    \"a\": \"82\",\n" +
                      "    \"a1\": \"88\"\n" +
                      "}";
        JsonParser p = new JsonParser();

        JsonObject j = (JsonObject)p.parse(json);

        Set<String> s = j.keySet();

        String[] arr = (String[]) s.toArray(new String[0]);
        Arrays.sort(arr);
        for (String s1: arr) {
            System.out.println(s1);
        }

        StringBuilder sb = new StringBuilder();

        int idx = 0;
        for (String k : arr) {
            if (idx == 0) {
                sb.append(k).append("=").append(j.get(k).toString());
            } else {
                sb.append("&")
                        .append(k)
                        .append("=")
                        .append(j.get(k).toString());
            }

            ++idx;
        }

        String appSecret = "123456";

        sb.append("&").append(appSecret);

        String ns = sb.toString().replace("\"", "");

        System.out.println(ns);

        String f = sha1(ns, "123456");


        System.out.println(f);




    }
    public static String sha1(String s, String keyString) throws
            UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {

        SecretKeySpec key = new SecretKeySpec((keyString).getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);

        byte[] bytes = mac.doFinal(s.getBytes(StandardCharsets.UTF_8));

        for (byte b : bytes) {
            System.out.println(b);
        }

        System.out.println(bytes.length);


        return ByteToHex(bytes);
    }

    // btye转换hex函数
    public static String ByteToHex(byte[] byteArray) {
        StringBuffer StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return StrBuff.toString();
    }

    @Test
    public void libraryPortalAsync() {
        System.out.println("Hello world");
    }
}