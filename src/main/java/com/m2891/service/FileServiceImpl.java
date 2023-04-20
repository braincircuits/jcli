package com.m2891.service;

import com.m2891.constant.FileConstant;
import com.m2891.pojo.entity.FileInfo;
import com.m2891.util.HashUtils;
import com.m2891.util.HibernateSession;
import com.m2891.util.id.IDUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.m2891.util.Convert.toObjectByList;

@Service
public class FileServiceImpl implements FileService
{
    @SneakyThrows
    @Override
    public String upload(MultipartFile file)
    {
        FileInfo fileInfo = new FileInfo();
        byte[] bytes = file.getBytes();
        long hash = HashUtils.calculateCRC32(bytes);
        fileInfo.setHash(hash);
        FileInfo hasFileInfo = HibernateSession.transaction(session -> {
            List<FileInfo> fileInfos = session.createQuery("from FileInfo fi where fi.hash=:hash", FileInfo.class)
                    .setParameter("hash", hash)
                    .getResultList();
            if (fileInfos.isEmpty())
            {
                String uuid = IDUtils.getUUID();
                fileInfo.setUuid(uuid);
                session.persist(fileInfo);
                return null;
            }
            return fileInfos.get(0);
        });

        if (hasFileInfo == null)
        {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(FileConstant.path + fileInfo.getUuid()));
            return "http://127.0.0.1/file/" + fileInfo.getUuid();
        }
        return "http://127.0.0.1/file/" + hasFileInfo.getUuid();
    }

    @Override
    public FileInfo byUuid(String uuid)
    {
        List<FileInfo> fileInfoList = HibernateSession.transaction(session -> {
            return session.createQuery("from FileInfo fi where fi.uuid=:uuid", FileInfo.class)
                    .setParameter("uuid", uuid)
                    .setReadOnly(true)
                    .getResultList();
        });
        return toObjectByList(fileInfoList);
    }

    public static void main(String[] args) throws IOException
    {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setHash(245245L);
        HibernateSession.transaction(session -> {
            int i = session.createMutationQuery("update FileInfo fi set fi.createBy=:jj")
                    .setParameter("jj", 222)
                    .executeUpdate();
            System.out.println(i);
        });
    }
}
