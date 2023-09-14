package com.dive.backend.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {
    @Autowired
    private AmazonS3 s3;

    public String uploadFile(MultipartFile file) throws IOException{
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        ObjectMetadata metadata = new ObjectMetadata();
        InputStream is;

        if(file!= null){
            
           metadata.setContentType(file.getContentType());
           metadata.setContentLength(file.getSize());
           is = file.getInputStream();
    
            } else {
            byte[] emptyBytes = new byte[0];
           metadata.setContentType("null");
           metadata.setContentLength(0);
           is = new ByteArrayInputStream(emptyBytes);
            }
    
           PutObjectRequest putReq = new PutObjectRequest("ceemj", uuid, is, metadata);
           putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
    
           s3.putObject(putReq);
           return uuid;
    }

}
