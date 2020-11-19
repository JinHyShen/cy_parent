package com.hong.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

import java.util.List;

public class VodTest {
    public static void main(String[] args)  {
        String accessKeyId="LTAI4FzNS75RxNtAmoh7w2xV";
        String accessKeySecret="u5WY3vKRtG9CDxbKpDBk6ewmwxrCru";
        String title="6 - What If I Want to Move Faster-upload by sdk"; //上传之后文件名称
        String fileName="C:/Users/HP/Desktop/guli-online-college-project-master/s/video/6 - What If I Want to Move Faster.mp4"; //本地文件路径
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }

    }

    public static void getPlayAuth() throws ClientException{
        //获取视屏凭证
        //创建对象
        DefaultAcsClient client = InitObject.initVodClient ( "LTAI4FzNS75RxNtAmoh7w2xV", "u5WY3vKRtG9CDxbKpDBk6ewmwxrCru" );
        //创建获取视屏凭证的request,response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest ();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse ();
        //设置视频id
        request.setVideoId ( "8d64239fa05c4e2fb5780943a8595112" );
        //获取凭证
        response = client.getAcsResponse ( request );
        System.out.println ("playAoth: "+response.getPlayAuth ());
    }

    /**
     * 获取视频地址
     * @throws ClientException
     */
    public static void getPlayUrl() throws ClientException {
        //根据id获取视屏地址
        //创建对象
        DefaultAcsClient client = InitObject.initVodClient ( "LTAI4FzNS75RxNtAmoh7w2xV", "u5WY3vKRtG9CDxbKpDBk6ewmwxrCru" );

        GetPlayInfoResponse response = new GetPlayInfoResponse();
        GetPlayInfoRequest request = new GetPlayInfoRequest ();
        //设置视频id
        request.setVideoId ( "8d64239fa05c4e2fb5780943a8595112" );
        //获取数据
        response=client.getAcsResponse ( request );
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

    }
}
