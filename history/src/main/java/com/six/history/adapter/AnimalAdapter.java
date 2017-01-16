package com.six.history.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.six.history.R;
import com.six.history.bean.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15/015.
 */

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> implements View.OnClickListener
{

    private Context mContext;
    ArrayList<Status.ResultsBean> bean;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public AnimalAdapter(Context mContext, ArrayList<Status.ResultsBean> bean) {
        this.mContext = mContext;
        this.bean = bean;
    }

    //define interface
    public static interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view , String data);
     }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(mContext!=null)
        {
            mContext=parent.getContext();
        }

        View view= LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false);
        //将创建的View注册点击事件
       view.setOnClickListener(this);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {

        //将数据保存在itemView的Tag中，以便点击时进行获取
       holder.itemView.setTag(bean.get(position).getUrl());

        Glide.with(mContext).load(bean.get(position).getUrl()).into(holder.animalImage);
    }

    @Override
    public int getItemCount()
    {
        return bean.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView animalImage;

        public MyViewHolder(View itemView)
        {
            super(itemView);


            cardView=(CardView)itemView;

            animalImage= (ImageView) itemView.findViewById(R.id.recycler_image);

        }
    }


    public void onClick(View v) {
            if (mOnItemClickListener != null) {
                 //注意这里使用getTag方法获取数据
                 mOnItemClickListener.onItemClick(v, (String) v.getTag());
      }
 }

}
