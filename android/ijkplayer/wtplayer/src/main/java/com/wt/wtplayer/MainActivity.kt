package com.wt.wtplayer

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.wt.wtplayer.model.VideoResult
import com.wt.wtplayer.remote.RetrofitHelper
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback

/**
 * 主页面
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)
        var tvPlay: TextView = findViewById(R.id.tv_play)
        tvPlay.setOnClickListener(View.OnClickListener {
            RetrofitHelper.getInstance().serviceApi
                    .getVideoStream("43100111011320000002", "0", "rtmp")
                    .enqueue(object : Callback<VideoResult> {
                        override fun onFailure(call: Call<VideoResult>, t: Throwable) {
                            ToastUtils.showShort("获取视频流失败");
                        }

                        override fun onResponse(call: Call<VideoResult>, response: Response<VideoResult>) =
                                try {
                                    if (response.body()?.result?.errorNum == 200) {
                                        var path: String = response.body()?.result!!.url
                                        var name: String = "RTMP";
                                        VideoPlayActivity.intentTo(this@MainActivity, path, name,true);
                                    }else{
                                        ToastUtils.showShort("获取视频流失败");
                                    }

                                } catch (e: Exception) {
                                    ToastUtils.showShort("获取视频流异常:$e");
                                }

                    })
        })
    }
}
