package com.wt.wtplayer.remote;


import com.wt.wtplayer.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络请求帮助类
 * @author whs on 2018/11/28
 */
public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    private ServiceApi serviceApi;
    private Retrofit mRetrofit = null;
    private static RetrofitHelper Instance = null;

    private RetrofitHelper() {
        init();
    }

    public static RetrofitHelper getInstance() {
        if (Instance == null) {
            synchronized (RetrofitHelper.class) {
                if (Instance == null) {
                    Instance = new RetrofitHelper();
                }
            }
        }
        return Instance;
    }

    public ServiceApi getServiceApi() {
        if (serviceApi == null) {
            serviceApi = mRetrofit.create(ServiceApi.class);
        }
        return mRetrofit.create(ServiceApi.class);
    }

    private void init() {
        resetApp();
    }

    public void resetApp() {
       // Log.e(TAG,"resetApp:"+ GsonUtil.toJson(user));
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //LoginExpiredInterceptor loginExpiredInterceptor = new LoginExpiredInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        okHttpClient.addInterceptor(httpLoggingInterceptor);
        //登录过期拦截器
        //okHttpClient.addNetworkInterceptor(loginExpiredInterceptor);
        okHttpClient.connectTimeout(5, TimeUnit.SECONDS);
        okHttpClient.readTimeout(2, TimeUnit.MINUTES);
        okHttpClient.writeTimeout(2, TimeUnit.MINUTES);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();
    }
}
