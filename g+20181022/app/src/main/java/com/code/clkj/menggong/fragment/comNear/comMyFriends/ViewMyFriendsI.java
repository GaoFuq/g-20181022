package com.code.clkj.menggong.fragment.comNear.comMyFriends;

import com.code.clkj.menggong.bean.BaseLViewI;
import com.code.clkj.menggong.response.RespMyFriendsList;
import com.code.clkj.menggong.response.RespMyuserList;

/**
 * Created by clkj on 2017/12/25.
 */

public interface ViewMyFriendsI extends BaseLViewI {
    void getMuseFriendSuccess(RespMyFriendsList data);
    void mySpreadListSuccess(RespMyuserList data);

}
