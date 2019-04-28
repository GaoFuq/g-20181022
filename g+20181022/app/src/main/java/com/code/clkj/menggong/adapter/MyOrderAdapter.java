package com.code.clkj.menggong.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comLiveMyOrder.ActMyOrderEvaluateActivity;
import com.code.clkj.menggong.activity.comLiveMyOrder.ActMyOrderdetailsActivity;

/**
 * Created by Administrator on 2017/9/11.
 */

/**
 * 弃用
 */
public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_my_order_item, parent, false);
        final MyOrderAdapter.ViewHolder viewHolder = new MyOrderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView order_commodity_picture;
        private TextView order_id;
        private TextView order_state;
        private Button evaluate_btn;
        private TextView order_title;
        private Button paymen_btn;

        public ViewHolder(View itemView) {
            super(itemView);
            order_commodity_picture = (ImageView) itemView.findViewById(R.id.order_commodity_picture);
            order_id = (TextView) itemView.findViewById(R.id.order_id);
            order_state = (TextView) itemView.findViewById(R.id.order_state);
            evaluate_btn = (Button) itemView.findViewById(R.id.evaluate_btn);
            order_title = (TextView) itemView.findViewById(R.id.order_title);
            paymen_btn = (Button) itemView.findViewById(R.id.paymen_btn);

            order_commodity_picture.setOnClickListener(this);
            order_commodity_picture.setTag(this);

            evaluate_btn.setOnClickListener(this);
            evaluate_btn.setTag(this);
        }

        @Override
        public void onClick(View v) {
            ViewHolder tag;
            switch (v.getId()) {
                case R.id.order_commodity_picture:
                    tag = (ViewHolder) v.getTag();
                    Intent intent_order_commodity_picture = new Intent(v.getContext(), ActMyOrderdetailsActivity.class);
                    v.getContext().startActivity(intent_order_commodity_picture);
                    break;
                case R.id.evaluate_btn:
                    tag = (ViewHolder) v.getTag();
                    Intent intent_evaluate_btn = new Intent(v.getContext(),ActMyOrderEvaluateActivity.class);
                    v.getContext().startActivity(intent_evaluate_btn);
                    break;
            }

        }
    }
}
