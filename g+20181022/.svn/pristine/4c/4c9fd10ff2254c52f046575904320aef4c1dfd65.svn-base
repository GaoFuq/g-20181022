package com.code.clkj.menggong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.base.config.BaseUriConfig;
import com.code.clkj.menggong.bean.GiftModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class FaceGVAdapter extends RecyclerView.Adapter<FaceGVAdapter.ViewHodler> {
    private List<GiftModel> list;
    private Context mContext;
    private boolean isNetData;

    private ViewHodler mHolder;
    private int clickTemp = -1;

    //标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }

    public int getSecletion() {
        return clickTemp;
    }

    public FaceGVAdapter(List<GiftModel> list, Context mContext, boolean isNetData) {
        super();
        this.list = list;
        this.mContext = mContext;
        this.isNetData = isNetData;
    }

    public void clear() {
        this.mContext = null;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.face_image, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position) {
        final GiftModel giftModel = list.get(position);
        if (isNetData){
            if(!TextUtils.isEmpty(giftModel.getGiftPic())){
                holder.giftImg.setImageURI(Uri.parse(BaseUriConfig.BASE_IMG_URL+giftModel.getGiftPic()));
            }
        }/*else {
            try {
                Bitmap mBitmap = BitmapFactory.decodeStream(mContext.getAssets().open(giftModel.getGiftName()));
                holder.giftImg.setImageBitmap(mBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        holder.giftName.setText(giftModel.getGiftName());
        holder.giftPrice.setText(giftModel.getGiftPrice()+"金豆");
        holder.llroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, giftModel, position);
                }
            }
        });
        if (clickTemp == position) {
            holder.giftimagview.setBackgroundResource(R.mipmap.change_select);
            mHolder = holder;
        } else {
            holder.giftimagview.setBackgroundResource(R.mipmap.change_press);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearSelection() {
        if (mHolder != null) {
            mHolder.llroot.setBackgroundResource(R.drawable.shape_gift_tran);
            mHolder = null;
        }
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        LinearLayout llroot;
        SimpleDraweeView giftImg;
        ImageView giftimagview;
        TextView giftName;
        TextView giftPrice;

        public ViewHodler(View view) {
            super(view);
            llroot = (LinearLayout) view.findViewById(R.id.ll_gift_root);
            giftImg = (SimpleDraweeView) view.findViewById(R.id.face_img);
            giftName = (TextView) view.findViewById(R.id.face_name);
            giftPrice = (TextView) view.findViewById(R.id.face_price);
            giftimagview = (ImageView) view.findViewById(R.id.change_imagview);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, GiftModel giftModel, int position);
    }

    public OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
