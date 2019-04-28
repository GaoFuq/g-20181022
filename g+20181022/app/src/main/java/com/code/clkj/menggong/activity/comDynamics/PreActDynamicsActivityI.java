package com.code.clkj.menggong.activity.comDynamics;

import retrofit.http.Field;

/**
 * Created by 92573 on 2017/12/25.
 */

public interface PreActDynamicsActivityI {
    void saveDynamic(String museId,
                     String musePassword,
                     String dyContent,
                      String dyImage);
}
