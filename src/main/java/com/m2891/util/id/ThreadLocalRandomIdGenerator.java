package com.m2891.util.id;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomIdGenerator implements IdGenerator
{
    public void getRandomBytes(byte[] bytes) {
        ThreadLocalRandom.current().nextBytes(bytes);
    }
}
