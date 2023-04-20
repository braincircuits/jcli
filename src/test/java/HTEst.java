import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2891.util.HibernateSession;
import org.apache.catalina.util.StandardSessionIdGenerator;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

public class HTEst
{
    static SecureRandom randoma;

    static
    {
        try
        {
            randoma = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        HibernateSession.transaction(session -> {
            int i = session.createMutationQuery("update FileInfo fi set fi.hash=:hash")
                    .setParameter("hash", "xxxxxxx")
                    .executeUpdate();
            
        });
    }

    @Test
    public void a() throws JsonProcessingException
    {
        StandardSessionIdGenerator standardSessionIdGenerator = new StandardSessionIdGenerator();
        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        map.put("bbb", "ccc");
        hashMap.put("aaa", map);
        System.out.println(objectMapper.writeValueAsString(hashMap));
    }


    public static String j()
    {

        byte random[] = new byte[16];
        int sessionIdLength = 16;

        // Render the result as a String of hexadecimal digits
        // Start with enough space for sessionIdLength and medium route size
        StringBuilder buffer = new StringBuilder(2 * sessionIdLength + 20);

        int resultLenBytes = 0;

        while (resultLenBytes < sessionIdLength)
        {
            //=====
            randoma.nextBytes(random);
            //====
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
        return buffer.toString();
    }
}
