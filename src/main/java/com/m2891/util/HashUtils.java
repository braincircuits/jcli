package com.m2891.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.zip.CRC32;

public class HashUtils
{
    public String md5(byte[] bytes)
    {
        return DigestUtils.md5Hex(bytes);
    }
    
    public static long calculateCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }
}
