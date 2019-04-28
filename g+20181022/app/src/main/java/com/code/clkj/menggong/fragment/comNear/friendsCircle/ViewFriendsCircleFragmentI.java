package com.code.clkj.menggong.fragment.comNear.friendsCircle;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespCommentList;
import com.code.clkj.menggong.response.RespGetDynamicLikes;
import com.code.clkj.menggong.response.RespGetynamicPage;
import com.code.clkj.menggong.response.RespSaveDynamicLike;
import com.lf.tempcore.tempResponse.TempResponse;

/**
 * Created by 92573 on 2017/12/26.
 */

public interface ViewFriendsCircleFragmentI extends BaseLViewI {
    void getDynamicPageSuccess(RespGetynamicPage data);
    void saveDynamicLikeSuccess(RespSaveDynamicLike data);
    void getDynamicLikesSuccess(RespGetDynamicLikes data,int position);
    void getDynamicCommentSuccsee(RespCommentList data,int position);

    void saveDynamicCommentSucceees(TempResponse data);
//    , int positon
}
