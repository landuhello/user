package com.dingtao.rrmmp.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dingtao.common.bean.DepartListBean;
import com.dingtao.common.bean.PlateList;
import com.dingtao.rrmmp.login.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/5<p>
 * <p>更改时间：2019/12/5<p>
 */
public class HomeListOneAdapter extends BaseAdapter {

    List<PlateList> list = new ArrayList<>();

    public void OnAddAll(List<PlateList> li) {
        if (li != null) {
            list.addAll(li);
        }
    }

    public void onClearData() {
        list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        String thumbnail = list.get(position).thumbnail;
        String[] split = thumbnail.split(";");
        if (split.length == 0) {
            if (convertView == null) {
                convertView = View.inflate(parent.getContext(), R.layout.adapter_home_list_one, null);
                holder = new ViewHolder();
                holder.onename = convertView.findViewById(R.id.onename);
                holder.onetime = convertView.findViewById(R.id.onetime);
                holder.onedoctor = convertView.findViewById(R.id.onedoctor);

                //绑定
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.onename.setText(list.get(position).title);
            holder.onedoctor.setText(list.get(position).source);
            Date date=new Date(list.get(position).releaseTime);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            holder.onetime.setText(simpleDateFormat.format(date));

            return convertView;


        } else {
            if (split.length == 1) {
                if (convertView == null) {
                    convertView = View.inflate(parent.getContext(), R.layout.adapter_home_list_two, null);
                    holder = new ViewHolder();
                    holder.onename = convertView.findViewById(R.id.onename);
                    holder.onetime = convertView.findViewById(R.id.onetime);
                    holder.onedoctor = convertView.findViewById(R.id.onedoctor);
                    holder.twosimple = convertView.findViewById(R.id.twosimple);

                    //绑定
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.onename.setText(list.get(position).title);
                holder.onedoctor.setText(list.get(position).source);
                Date date=new Date(list.get(position).releaseTime);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                holder.onetime.setText(simpleDateFormat.format(date));
                Uri uri=Uri.parse(thumbnail);
                holder.twosimple.setImageURI(uri);

                return convertView;

            } else {
                if (convertView == null) {
                    convertView = View.inflate(parent.getContext(), R.layout.adapter_home_list_three, null);
                    holder = new ViewHolder();
                    holder.onename = convertView.findViewById(R.id.onename);
                    holder.onetime = convertView.findViewById(R.id.onetime);
                    holder.onedoctor = convertView.findViewById(R.id.onedoctor);
                    holder.threeimg1 = convertView.findViewById(R.id.threeimg1);
                    holder.threeimg2 = convertView.findViewById(R.id.threeimg2);
                    holder.threeimg3 = convertView.findViewById(R.id.threeimg3);

                    //绑定
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.onename.setText(list.get(position).title);
                holder.onedoctor.setText(list.get(position).source);
                Date date=new Date(list.get(position).releaseTime);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                holder.onetime.setText(simpleDateFormat.format(date));

                String thumbnail1 = list.get(position).thumbnail;
                String[] split1 = thumbnail1.split(";");

                Uri uri=Uri.parse(split1[0]);
                holder.threeimg1.setImageURI(uri);
                Uri uri1=Uri.parse(split1[1]);
                holder.threeimg2.setImageURI(uri1);
                Uri uri2=Uri.parse(split1[2]);
                holder.threeimg3.setImageURI(uri2);

                return convertView;
            }
        }
    }

    class ViewHolder {
        TextView onename, onedoctor, onetime;
        SimpleDraweeView twosimple,threeimg1,threeimg2,threeimg3;
    }
}
