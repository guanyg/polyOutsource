package me.yung.poly;

public class Util {
    public static byte[] padding(byte[] arr) {
        if (arr.length % 32 == 0) {
            return arr;
        }
        int offset = (-arr.length % 32) + 32;
        byte[] ret = new byte[arr.length + offset];
        System.arraycopy(arr, 0, ret, offset, arr.length);
        return ret;
    }
}
