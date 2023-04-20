package com.m2891.util.id;

public interface IdGenerator
{
    default String getId()
    {
        return getId("");
    }

    default String getId(String route)
    {
        byte random[] = new byte[16];
        int sessionIdLength = 16;
        StringBuilder buffer = new StringBuilder(2 * sessionIdLength + 20);
        int resultLenBytes = 0;
        while (resultLenBytes < sessionIdLength)
        {
            getRandomBytes(random);
            for (int j = 0;
                 j < random.length && resultLenBytes < sessionIdLength;
                 j++)
            {
                byte b1 = (byte) ((random[j] & 0xf0) >> 4);
                byte b2 = (byte) (random[j] & 0x0f);
                if (b1 < 10)
                {
                    buffer.append((char) ('0' + b1));
                } else
                {
                    buffer.append((char) ('A' + (b1 - 10)));
                }
                if (b2 < 10)
                {
                    buffer.append((char) ('0' + b2));
                } else
                {
                    buffer.append((char) ('A' + (b2 - 10)));
                }
                resultLenBytes++;
            }
        }

        if (route != null && route.length() > 0)
        {
            buffer.append('.').append(route);
        }
        return buffer.toString();
    }

    void getRandomBytes(byte bytes[]);
}
