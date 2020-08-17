package com.nanourl.util;

public class BaseConverter {
    private String dictionaryString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private char[] dictionaryChar = dictionaryString.toCharArray();
    private static final int BASE = 62;

    /**
     *
     * Encode ID and use it as nano URL
     *
     * @param num
     * @return (String) generated nano URL
     */
    public String numToUrl(long num) {
        StringBuilder url = new StringBuilder();

        if (num == 0) return "0";

        while (num > 0) {
            url.append(dictionaryChar[(int) (num % BASE)]);
            num = num / BASE;
        }

        return url.reverse().toString();
    }

    /**
     *
     * Decode nano URL and convert back to ID
     *
     * @param url
     * @return (long) URL record ID
     */
    public long urlToNum(String url) {
        char[] characters = url.toCharArray();
        int length = characters.length;

        long num = 0;

        for (int i = 0; i < length; i++) {
            num = num * 62 + (dictionaryString.indexOf(characters[i]));
        }

        return num;
    }
}
