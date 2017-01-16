package com.six.history.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.six.history.R;
import com.six.history.bean.CollectionBean;
import com.six.history.bean.ImageUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * autour: 曾光春
 * date: $date$ $time$
 * update: $date$
 */
public class CollectionAdapter  extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {
    private List<CollectionBean> mDatas;
    private Context context;


    public CollectionAdapter(List<CollectionBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
        View v = View.inflate(context, R.layout.sc_item, null);

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_time.setText(mDatas.get(position).getData());
        holder.tv_collection.setText(mDatas.get(position).getTitle());
        //Glide.with(context).load(url).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_collection,tv_time;
        ImageView image;
        public MyViewHolder(View view) {
            super(view);
            tv_collection = (TextView) view.findViewById(R.id.collection_context);
            tv_time = (TextView) view.findViewById(R.id.collection_title);
            image= (ImageView) view.findViewById(R.id.collection_img);
        }
    }
}
