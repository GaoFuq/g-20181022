package com.code.clkj.menggong.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.code.clkj.menggong.R;
import com.code.clkj.menggong.activity.comDynamics.ActDynamicsActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private ArrayList<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private boolean isAdded;   //是否额外添加了最后一个图片

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
        void ondeteleChange(ArrayList<ImageItem> images);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;

    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.camer_gongyong, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, final int position) {
        holder.bind(position);
        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(position);
                mNewData= mData;

                if (listener != null) listener.ondeteleChange(mNewData);
            }
        });
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin_horizontal = mContext.getResources().getDimensionPixelSize(R.dimen.padding_size_default);
        if(position % 4 == 0) {
            lp.setMargins(margin_horizontal/4, margin_horizontal/4, margin_horizontal/4, margin_horizontal/4);
        } else {
            lp.setMargins(margin_horizontal/4, margin_horizontal/4, margin_horizontal/4, margin_horizontal/4);
        }
        holder.itemView.setLayoutParams(lp);

     //   int margin_horizontal = mContext.getResources().getDimensionPixelSize(R.dimen.padding_size_default);
     /*   if(position % 1 == 0) {
            lp.setMargins(margin_horizontal/3,margin_horizontal/3, margin_horizontal/3, margin_horizontal/3);
        } else {
            lp.setMargins(margin_horizontal/3, margin_horizontal/3, margin_horizontal/3, margin_horizontal/3);
        }*/
        holder.itemView.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView_delete;
        private SimpleDraweeView iv_img;
        private int clickPosition;
      //  private TextView frag_up_video_mine_add_video_text;
        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = (SimpleDraweeView) itemView.findViewById(R.id.frag_up_video_mine_add_video_iv);
            imageView_delete = (ImageView) itemView.findViewById(R.id.imageView_delete);
        }

        public void bind( int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
              //  iv_img.setImageResource(R.drawable.selector_image_add);
                clickPosition = ActDynamicsActivity.IMAGE_ITEM_ADD;
                ImagePicker.getInstance().getImageLoader().fresscoImage((Activity) mContext,"", iv_img, 400, 400);
                imageView_delete.setVisibility(View.GONE);
            } else {
                ImagePicker.getInstance().getImageLoader().fresscoImage((Activity) mContext, item.path, iv_img, 400, 400);
                clickPosition = position;
                imageView_delete.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onItemClick(v, clickPosition);
        }
    }
    private ArrayList<ImageItem> mNewData=new ArrayList<>();
}