package com.code.clkj.menggong.activity.comChatRoom;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comOtherHomePage.ActOtherHomePage;
import com.code.clkj.menggong.adapter.ChatRoomAdapter;
import com.code.clkj.menggong.adapter.ListBaseAdapter;
import com.code.clkj.menggong.adapter.LiveRoomAdapter;
import com.code.clkj.menggong.base.TempRecyclerView;
import com.code.clkj.menggong.response.RespCahtRoom;
import com.code.clkj.menggong.throwable.ExceptionEngine;
import com.code.clkj.menggong.util.TempDataUtils;
import com.lf.tempcore.tempActivity.TempActivity;
import com.lf.tempcore.tempConfig.TempLoginConfig;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by chenlingkeji on 2018/10/22.
 * for chenlingkeji Company
 * link for more detail www.lingkj.com
 */
public class ActChatRoom extends TempActivity implements ViewActChatRoomI{


    private PreActChatRoomI mChatRoomI;

    @Bind(R.id.mNearUser)
    TempRecyclerView mNearUser;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
    @Bind(R.id.toolbar_title)
    TextView toolbar_title;//显示抬头
    private ChatRoomAdapter mAdapter;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.act_chat_roome);
    }

    @Override
    protected void findViews() {
        toolbar_top.setNavigationIcon(R.mipmap.back);
        toolbar_top.setBackgroundColor(Color.parseColor("#cccccc"));
        toolbar_title.setText("聊天室");
        toolbar_title.setTextSize(18);

        mChatRoomI=new PreActChatRoomImpl(this);
        mAdapter = new ChatRoomAdapter(ActChatRoom.this);
        mNearUser.setLayoutManager(new LinearLayoutManager(ActChatRoom.this));
        mNearUser.setAdapter(mAdapter);
        mNearUser.setRefreshing(new TempRecyclerView.initDataListener() {
            @Override
            public void initDataData(int currentPage, int pageSize) {
                mChatRoomI.queryseletChat(TempLoginConfig.sf_getlng(),TempLoginConfig.sf_getlag(),currentPage+"",pageSize+"");
            }
        });
       mAdapter.setOnItemClickListener(new ListBaseAdapter.OnItemClickListener<RespCahtRoom.ResultEntity.SourceEntity>() {
            @Override
            public void OnItemClick(View itemView, int position, RespCahtRoom.ResultEntity.SourceEntity sourceBean) {
                RongIM.getInstance().startConversation(ActChatRoom.this, Conversation.ConversationType.CHATROOM,  mAdapter.getDataList().get(position).getId(),  mAdapter.getDataList().get(position).getRoomName());
         /*  Intent otherintent = new Intent(ActChatRoom.this, ActOtherHomePage.class);
                otherintent.putExtra("frMuseId", sourceBean.getMuseId());
                startActivity(otherintent);*/
            }
        });
        update();
    }
    public void update() {
        if (mNearUser != null) {
            mNearUser.refreshing();
            mNearUser.forceToRefresh();
        }
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bindValues() {

    }

    @Override
    protected void OnViewClicked(View v) {

    }

    @Override
    public void queryRoomListSuccees(RespCahtRoom data) {
        if (mNearUser.isMore()) {
         mAdapter.addAll(data.getResult().getSource());
        } else {
            mAdapter.setDataList(data.getResult().getSource());
        }
        mNearUser.setTotalCount(data.getResult().getPageSize());
    }

    @Override
    public void onLoadFinish() {
        if (mNearUser != null)
            mNearUser.executeOnLoadFinish();
    }

    @Override
    public void onLoadDataError(ExceptionEngine.ApiException e) {
        if (mNearUser != null)
            mNearUser.executeOnLoadDataError();
    }

    @Override
    public void onLoadDataSuccess() {
        if (mNearUser != null)
            mNearUser.executeOnLoadDataSuccess();
    }

    @Override
    public void toast(String message) {

    }
}
