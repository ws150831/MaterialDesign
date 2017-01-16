package com.six.history.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.six.history.DetailsActivity;
import com.six.history.R;
import com.six.history.adapter.CollectionAdapter;
import com.six.history.bean.CollectionBean;
import com.six.history.dao.CollectionDao;
import com.six.history.dao.ScUser;
import com.six.history.dao.ScUserDao;
import com.six.history.utils.RecyclerViewClickListener;
import com.six.history.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16/016.
 */

public class CollectFragment extends Fragment
{

    // 获取控件
    private RecyclerView screcyclerview;
    private List<CollectionBean> sqlList;
    private ScUserDao dao;
    private View view;
    private CollectionDao collectionDao;
    private CollectionAdapter adapter;
    /********************************************/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.collect_fragment, null);
        screcyclerview = (RecyclerView)view.findViewById(R.id.screcyclerview);
        collectionDao = new CollectionDao(getActivity());
//设置布局管理
        LayoutManager();

        init();
        //设置设配器
        setAdapter();

        //RecyclerView的监听
        recyClickListener();
        return view;
    }

    private void recyClickListener() {
        //设置RecyclerView条目的间距
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        screcyclerview.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        //
        screcyclerview.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), screcyclerview, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("eid",sqlList.get(position).getHistory_id());
                intent.putExtra("title",sqlList.get(position).getTitle());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, final int position) {

                Snackbar.make(view,"是否取消收藏",Snackbar.LENGTH_SHORT).setAction("Yes", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {

                        //删除
                        collectionDao.delete(sqlList.get(position).getHistory_id());
                        // onMessageEvent(new CollectionBean());
                        collectionDao.select();
                        init();
                        setAdapter();
                    }
                }).show();
//                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//                builder.setTitle("系统提示");
//                builder.setMessage("确定取消收藏么?");
//                //确定删除收藏
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //删除
//                        collectionDao.delete(sqlList.get(position).getSc());
//                        // onMessageEvent(new CollectionBean());
//                        collectionDao.select();
//                        init();
//                        setAdapter();
//                    }
//                });
//                //取消删除收藏
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                builder.show();
            }
        }));
    }

    private void setAdapter() {
        adapter = new CollectionAdapter(sqlList,getActivity());
        screcyclerview.setAdapter(adapter);
    }

    private void init() {
        sqlList = new ArrayList<>();
        sqlList = collectionDao.select();
    }

    private void LayoutManager() {
        //布局管理
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity());
        screcyclerview.setLayoutManager(gridLayoutManager);
    }
    @Override
    public void onStart() {
        super.onStart();
        init();
        //设置设配器
        setAdapter();
    }

}
