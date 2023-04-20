package com.m2891.util.id;

import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SecureRandomIdGenerator implements IdGenerator
{
    private final Queue<SecureRandom> randoms = new ConcurrentLinkedQueue<>();

    @SneakyThrows
    public void getRandomBytes(byte[] bytes) {

        SecureRandom random = randoms.poll();
        if (random == null) {
            random =  SecureRandom.getInstance("SHA1PRNG");
            //强制种子生成
            random.nextInt();
        }
        random.nextBytes(bytes);
        randoms.add(random);
    }

}
