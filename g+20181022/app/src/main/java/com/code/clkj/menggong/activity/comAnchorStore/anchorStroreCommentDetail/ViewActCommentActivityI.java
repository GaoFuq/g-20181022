package com.code.clkj.menggong.activity.comAnchorStore.anchorStroreCommentDetail;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespQueryMgooCommentPage;
import com.code.clkj.menggong.response.RespToConfirmOrder;

/**
 * Created by 92573 on 2018/1/6.
 */

public interface ViewActCommentActivityI extends BaseLViewI{
    void queryMgooCommentPageSuccess(RespQueryMgooCommentPage data);

}
