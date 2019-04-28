package com.code.clkj.menggong.fragment.comNear;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.lf.tempcore.tempFragment.TempFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * 消息的Fragment
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends TempFragment {

    @Bind(R.id.toolbar_title)
    TextView toolbar_title;
    @Bind(R.id.toolbar_top)
    Toolbar toolbar_top;
//    @Bind(R.id.nearby_message)
//    RecyclerView nearby_message;
    View v;
    public InfoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_info,null);
        ConversationListFragment fragment = (ConversationListFragment) getChildFragmentManager().findFragmentById(R.id.cool_fragment);
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();

        fragment.setUri(uri);
        return v;
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void bundleValues() {
//        toolbar_title.setText("消息");
//        toolbar_title.setTextSize(20);
//        toolbar_top.setNavigationIcon(R.mipmap.back);
//        toolbar_top.setBackgroundColor(Color.parseColor("#D3D3D3"));
//        nearby_message.setLayoutManager(new LinearLayoutManager(getContext()));
//        nearby_message.setAdapter(new NearbyMessageAdapter());
    }

    @OnClick(R.id.toolbar_top)
    @Override
    protected void OnViewClicked(View v) {
        switch (v.getId()) {
            case R.id.toolbar_top:
                getActivity().finish();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
