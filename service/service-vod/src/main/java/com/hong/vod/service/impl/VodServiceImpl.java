package com.hong.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.hong.vod.service.VodService;
import com.hong.vod.utils.ConstantVodUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {

        try {

            String fileName=file.getOriginalFilename ();
            //11.mp4
            String title=fileName.substring ( 0,fileName.lastIndexOf ( "." ) );
            InputStream inputStream=file.getInputStream ();
            UploadStreamRequest request = new UploadStreamRequest( ConstantVodUtil.ACCESS_KEY_ID,ConstantVodUtil.ACCESS_KEY_SECRET,title,fileName,inputStream);
            System.out.println ("keyid:"+ConstantVodUtil.ACCESS_KEY_ID);
            UploadVideoImpl uploader = new UploadVideoImpl ();
            UploadStreamResponse response = uploader.uploadStream ( request );

            String videoId = null;

            if(response.isSuccess ()){
                videoId = response.getVideoId ();
                System.out.println ("if_videoId:"+videoId);
            }else {
                videoId = response.getVideoId ();
                System.out.println ("else_videoId:"+videoId);
            }
            return videoId;
        }catch (Exception e){
            return null;
        }
    }
}
