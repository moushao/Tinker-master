package com.tinker_master.www;

import android.app.Application;
import android.util.Log;

import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

/**
 * Created by Mou on 2017/7/6.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTinker();
    }
    private void initTinker() {
        if (BuildConfig.TINKER_ENABLE) {
            // 我们可以从这里获得Tinker加载过程的信息
            ApplicationLike tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
            // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
            TinkerPatch.init(tinkerApplicationLike).reflectPatchLibrary().setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true);
            // 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
            new FetchPatchHandler().fetchPatchWithInterval(3);
            Log.i("TAG", "tinker init");
        }
    }
}
