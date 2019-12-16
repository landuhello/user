package com.dingtao.rrmmp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingtao.rrmmp.login.R;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class HistryFlowAdapter extends BaseAdapter {

    List<String> list=new ArrayList<>();

    public void AddAlla(List<String> li){
        if (li != null) {
            list.addAll(li);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(parent.getContext(), R.layout.adapter_histry_seach_item, null);
                    holder = new ViewHolder();
                    holder.name=convertView.findViewById(R.id.name);
                    holder.histry_close=convertView.findViewById(R.id.histry_close);

                    //绑定
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.name.setText(list.get(position));
                holder.histry_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                return convertView;
    }

    class ViewHolder{
        TextView name;
        ImageView histry_close;
    }

}
