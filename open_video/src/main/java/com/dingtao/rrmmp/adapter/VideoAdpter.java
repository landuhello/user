package com.dingtao.rrmmp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dingtao.common.bean.video.VideovolBean;
import com.dingtao.common.util.DateUtils;
import com.dingtao.rrmmp.login.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.listener.GSYVideoShotListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class VideoAdpter extends RecyclerView.Adapter<VideoAdpter.VideoViewHolder> {
    private List<VideovolBean> mData1 = new ArrayList<>();
    public Boolean miscollect = false;
    public Boolean misdammu = false;
    private VideovolBean mBean;
    public Bitmap fengmian;
    private Context mContext;

    public VideoAdpter(List<VideovolBean> data1, Context context) {
        mData1 = data1;
        mContext = context;
    }
    public Boolean getMiscollect() {
        return miscollect;
    }

    public void setMiscollect(Boolean miscollect) {
        this.miscollect = miscollect;
    }
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(inflate);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {
        RelativeLayout thumbImageViewLayout = holder.gsyVideoPlayer.getThumbImageViewLayout();
        Log.e("12", thumbImageViewLayout + "");
        TextView textView = new TextView(holder.itemView.getContext());
        textView.setTextColor(Color.BLUE);
        textView.setText("试看结束，充值购买观看完整视频");
        textView.setTextSize(20);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) thumbImageViewLayout.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        thumbImageViewLayout.addView(textView);

        //设置全屏按键功能
        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(holder.gsyVideoPlayer);
            }
        });
        Bitmap videoThumbnail = getVideoThumbnail(mData1.get(position).getOriginalUrl(), 2,500,500);
        //增加封面视频
        ImageView imageView = new ImageView(holder.itemView.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (imageView.getParent() != null) {
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            viewGroup.removeView(imageView);
        }


        //防止错位，离开释放
        //gsyVideoPlayer.initUIState();
        GSYVideoOptionBuilder gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        gsyVideoOptionBuilder.setIsTouchWiget(false)
                //设置封面
                .setThumbImageView(imageView)
                .setUrl(mData1.get(position).originalUrl)
                .setVideoTitle("000")
                .setCacheWithPlay(false)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setPlayPosition(position)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        if (!holder.gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                            //静音
                            GSYVideoManager.instance().setNeedMute(true);
                        }

                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        //全屏不静音
                        GSYVideoManager.instance().setNeedMute(true);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        GSYVideoManager.instance().setNeedMute(false);
                        holder.gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                    }
                }).build(holder.gsyVideoPlayer);


        //videoViewHolder.gsyVideoPlayer.setThumbImageView();
        //设置返回键
        holder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        holder.gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置全屏按键功能
        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.gsyVideoPlayer.startWindowFullscreen(holder.itemView.getContext(), false, true);
            }
        });
        //videoViewHolder.gsyVideoPlayer;
        mBean = mData1.get(position);
        holder.videoItemName.setText(mBean.title);
        holder.videoItemNeirong.setText(mBean.abstracts);
        holder.YiBuy.setText(mBean.buyNum + "万人已购买");
        //是否购买
        if (mBean.whetherBuy == 1) {
            //购买
            holder.gsyVideoPlayer.setUpLazy(mData1.get(position).originalUrl, true, null, null, "视频播放标题");
            holder.YiBuy.setVisibility(View.GONE);
            holder.shikanlinner.setVisibility(View.GONE);
            holder.videoItemCoin.setImageResource(R.mipmap.common_icon_comment_samll_s);
        } else {
            holder.gsyVideoPlayer.setUpLazy(mData1.get(position).shearUrl, true, null, null, "视频播放标题");
            holder.videoItemCoin.setImageResource(R.mipmap.common_icon_toll_n);
            holder.YiBuy.setVisibility(View.VISIBLE);
            holder.shikanlinner.setVisibility(View.VISIBLE);
        }

        //是否收藏
        if (mBean.whetherCollection == 1) {
            miscollect = true;
            //收藏
        } else {
            miscollect = false;
        }

        if (miscollect) {
            mBean.setWhetherCollection(1);
            holder.videoItemCollection.setImageResource(R.mipmap.common_button_collection_small_s);
        } else {
            mBean.setWhetherCollection(2);
            holder.videoItemCollection.setImageResource(R.mipmap.common_button_collection_small_n);
        }

        //覅及去收藏
        //   Gocollect(videoViewHolder, resultBeans.get(i).getId() + "");
        //点击收藏
        holder.videoItemCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(mBean.id);
                integers.add(position);
               EventBus.getDefault().post(integers);
            }
        });


        //播放进度监听
        holder.gsyVideoPlayer.setGSYVideoProgressListener(new GSYVideoProgressListener() {

            private Date mDatetime;

            @Override
            public void onProgress(int progress, int secProgress, int currentPosition, int duration) {

                //设置可观看时间倒计时
                mDatetime = new Date(duration - currentPosition);

                String s = null;
                try {
                    s = DateUtils.dateFormat(mDatetime, "yyyy-MM-dd");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                holder.shikan.setText(s.substring(s.length() - 2) + "s");
            }
        });
        // EventBus.getDefault().post(bean.getId());

        if (misdammu) {
            //传值Id
//            EventBus.getDefault().post(resultBeans.get(i).getId());
            holder.videoItemBarrage.setImageResource(R.mipmap.common_icon_open_live_commenting_n);
        } else {
            holder.videoItemBarrage.setImageResource(R.mipmap.common_icon_close_live_commenting_n);
        }

        //点击弹幕
        holder.videoItemBarrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(mBean.getId());
                misdammu = !misdammu;
                if (misdammu) {
                    //添加弹幕
                    holder.videoItemBarrage.setImageResource(R.mipmap.common_icon_open_live_commenting_n);
                } else {
                    holder.videoItemBarrage.setImageResource(R.mipmap.common_icon_close_live_commenting_n);
                }
            }
        });


        //点击购买
        holder.videoItemCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取截图
                holder.gsyVideoPlayer.taskShotPic(new GSYVideoShotListener() {
                    @Override
                    public void getBitmap(Bitmap bitmap) {
                        fengmian = bitmap;
                    }
                });
                Integer[] ints = new Integer[3];
                ints[0] = mBean.getId();
                ints[1] = mBean.getPrice();
                ints[2] = position;
                EventBus.getDefault().post(ints);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData1.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView videoItemCoin;

        private ImageView videoItemCollection;

        private ImageView videoItemBarrage;

        private TextView YiBuy;

        private TextView shikan;

        private TextView videoItemName;


        private TextView videoItemNeirong;
        private LinearLayout shikanlinner;

        private ImageView shangla;
        private StandardGSYVideoPlayer gsyVideoPlayer;

        public VideoViewHolder(View itemView) {
            super(itemView);
            gsyVideoPlayer = itemView.findViewById(R.id.detail_item_player);
            videoItemCoin = itemView.findViewById(R.id.video_item_coin);
            videoItemCollection = itemView.findViewById(R.id.video_item_collection);
            videoItemBarrage = itemView.findViewById(R.id.video_item_barrage);
            YiBuy = itemView.findViewById(R.id.Yi_buy);
            shikan = itemView.findViewById(R.id.shikan);
            videoItemName = itemView.findViewById(R.id.video_item_name);
            videoItemNeirong = itemView.findViewById(R.id.video_item_neirong);
            shangla = itemView.findViewById(R.id.shangla);
            shikanlinner = itemView.findViewById(R.id.shikanlinner);
        }
    }
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(mContext, true, true);
    }
    public static Bitmap getVideoThumbnail(String videoPath, int kind, int width, int height) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        if (width > 0 && height > 0) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }

        return bitmap;
    }
    //接口回调
    public interface Onclick{
        void success(VideovolBean bean);
    }
    private Onclick mOnclick;

    public void setOnclick(Onclick onclick) {
        mOnclick = onclick;
    }
}
