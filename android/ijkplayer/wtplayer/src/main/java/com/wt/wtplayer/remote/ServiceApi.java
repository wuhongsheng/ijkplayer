package com.wt.wtplayer.remote;


import com.wt.wtplayer.model.VideoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * api
 *
 * @author whs
 * @date 2019-10-25
 */
public interface ServiceApi {

    /**
     * 取流接口
     * @param deviceId 设备ID
     * @param streamType 流类型 0
     * @param protocol 视频流协议 flv rtmp
     * @return
     */
    @GET("api/v1/gateway/openstream")
    Call<VideoResult> getVideoStream(@Query("deviceId") String deviceId, @Query("streamType") String streamType, @Query("protocol") String protocol);

    /**
     * 动态加载授权树
     * @param posCode 位置code
     * @return
     */
   /* @GET("app/user/device/permit/next-level-list")
    Call<CommonResponse<Object>> getDeviceTree(@Query("posCode") String posCode);*/

}