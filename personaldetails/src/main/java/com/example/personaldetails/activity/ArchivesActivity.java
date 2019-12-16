package com.example.personaldetails.activity;



import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Vibrator;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.bean.AddArchives;
import com.example.personaldetails.activity.bean.ArchivesEntity;
import com.example.personaldetails.activity.bean.MessageEntity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/*档案*/
public class ArchivesActivity extends BaseActivity<ArchivesModelImpl, ArchivesPresenterImpl> implements ArchivesContract.ArchivesView {


    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.a1)
    TextView a1;
    @BindView(R2.id.a2)
    TextView a2;
    @BindView(R2.id.a3)
    TextView a3;
    @BindView(R2.id.a4)
    TextView a4;
    @BindView(R2.id.a5)
    TextView a5;
    @BindView(R2.id.a6)
    TextView a6;
    @BindView(R2.id.archives_img)
    RecyclerView archivesImg;
    @BindView(R2.id.message_show)
    LinearLayout messageShow;
    @BindView(R2.id.b1)
    EditText b1;
    @BindView(R2.id.b2)
    EditText b2;
    @BindView(R2.id.b3)
    EditText b3;
    @BindView(R2.id.b4)
    EditText b4;
    @BindView(R2.id.archives_begin)
    TextView archivesBegin;
    @BindView(R2.id.archives_over)
    TextView archivesOver;
    @BindView(R2.id.b5)
    EditText b5;
    @BindView(R2.id.add_rec)
    RecyclerView addRec;
    @BindView(R2.id.message_up)
    LinearLayout messageUp;
    @BindView(R2.id.no_message)
    RelativeLayout noMessage;
    private int id;

    private int year;
    private int month;
    private int day;

    private PubliscImageAdapter imageAdapter;
    private List<MultipartBody.Part> files = new ArrayList<>();
    private ArrayList images;
    private ArrayList<String> list;


    @Override
    protected void iniData() {
        userTitle.tBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
            }
        });
        userTitle.tName.setText("档案");

        iniImages();

        presenter.findArchives();
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_record_main;
    }

    @Override
    public BasePresenter initPresenter() {
        return new ArchivesPresenterImpl();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    //添加加号的那个
                    for (int i = 0; i < images.size(); i++) {
                        if (images.get(i).toString().equals("2131558400")) {
                            images.remove(i);
                        }
                    }

                    for (int i = 0; i < localMedia.size(); i++) {
                        String path = localMedia.get(i).getPath();
                        File file = new File(path);
                        images.add(file);
                        //上传
                        RequestBody requestBody = MultipartBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part image = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                        files.add(image);
                    }

                    images.add(R.mipmap.add);
                    imageAdapter.notifyDataSetChanged();
                    in111i(addRec, imageAdapter, images);
                    break;
            }
        }
    }


    //=========================================================================================
    private void in111i(RecyclerView mRecyclerView, final RecyclerView.Adapter ap, final ArrayList<String> datas) {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFrlg = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                return makeMovementFlags(dragFrlg, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件  下面注释的代码，滑动后数据和条目错乱，被舍弃
//            Collections.swap(datas,viewHolder.getAdapterPosition(),target.getAdapterPosition());
//            ap.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());

                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(datas, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(datas, i, i - 1);
                    }
                }
                ap.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑删除可以使用；
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            /**
             * 长按选中Item的时候开始调用
             * 长按高亮
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.RED);
                    //获取系统震动服务//震动70毫秒
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(70);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原高亮
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
                ap.notifyDataSetChanged();  //完成拖动后刷新适配器，这样拖动后删除就不会错乱
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }
    //=========================================================================================

    @Override
    public void findArchives(Object obj) {
        ArchivesEntity archivesEntity = (ArchivesEntity) obj;
        ToastUtils.showShort(archivesEntity.getMessage());
        if (archivesEntity.getStatus().equals("0000")) {
            if (!archivesEntity.getMessage().equals("档案为空，快去添加吧！")) {
                messageUp.setVisibility(View.GONE);
                messageShow.setVisibility(View.VISIBLE);
                noMessage.setVisibility(View.GONE);
                id = archivesEntity.getResult().getId();
                a1.setText(archivesEntity.getResult().getDiseaseMain());
                a2.setText(archivesEntity.getResult().getDiseaseNow());
                a3.setText(archivesEntity.getResult().getDiseaseBefore());
                a4.setText(archivesEntity.getResult().getTreatmentHospitalRecent());
                a6.setText(archivesEntity.getResult().getTreatmentProcess());
                String s = TimeUtils.millis2String(archivesEntity.getResult().getTreatmentStartTime());
                String s1 = TimeUtils.millis2String(archivesEntity.getResult().getTreatmentEndTime());
                a5.setText(s + "至" + s1);

                if (archivesEntity.getResult().getPicture()!=null){
                    list = new ArrayList<>();
                    String picture = archivesEntity.getResult().getPicture();
                    archivesImg.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                    String[] split = picture.split(",");
                    for (int i = 0; i < split.length; i++) {
                        list.add(split[i]);
                    }
                    archivesImg.setAdapter(new ArchivesAdapter(this,list));
                }
            } else {
                messageUp.setVisibility(View.GONE);
                messageShow.setVisibility(View.GONE);
                noMessage.setVisibility(View.VISIBLE);
            }
        } else {
            ToastUtils.showShort(archivesEntity.getMessage());
        }
    }

    /**
     * 添加档案
     * @param obj
     */
    @Override
    public void archives(Object obj) {
        MessageEntity messageEntity = (MessageEntity) obj;
        if (messageEntity.getStatus().equals("0000")) {
            ToastUtils.showShort(messageEntity.getMessage());
            if (files.size()!=0) {
                presenter.archivesPic(files);
            }else {
                presenter.findArchives();
            }
        } else {
            ToastUtils.showShort(messageEntity.getMessage());
        }
    }

    @Override
    public void archivesPic(Object obj) {
        MessageEntity messageEntity = (MessageEntity) obj;
        if (messageEntity.getStatus().equals("0000")){
            presenter.findArchives();
            files.clear();
        }else {
            ToastUtils.showShort(messageEntity.getMessage());
        }
    }

    @Override
    public void del(Object obj) {
        MessageEntity messageEntity = (MessageEntity) obj;
        if (messageEntity.getStatus().equals("0000")) {
            presenter.findArchives();
        } else {
            ToastUtils.showShort(messageEntity.getMessage());
        }
    }

    @OnClick({R2.id.archives_delete, R2.id.archives_write, R2.id.rela_begin, R2.id.rela_over, R2.id.archives_bc, R2.id.tj_arc})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.archives_delete) {
            presenter.del(id);
        } else if (viewId == R.id.archives_write) {
        } else if (viewId == R.id.rela_begin) {
            final AddressPopupWindow addressPopupWindow = new AddressPopupWindow(this);
            new XPopup.Builder(this).setPopupCallback(new SimpleCallback() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onCreated() {
                    super.onCreated();
                    DatePicker dpicker = findViewById(R.id.add_datepicker);
                    TextView addBt = findViewById(R.id.add_bt);
                    final TextView addTime = findViewById(R.id.add_time);
                    day = dpicker.getDayOfMonth();
                    month = dpicker.getMonth();
                    year = dpicker.getYear();
                    int am = month + 1;
                    addTime.setText(year + "-" + am + "-" + day);
                    dpicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            int x = monthOfYear + 1;
                            addTime.setText(year + "-" + x + "-" + dayOfMonth);
                        }
                    });
                    //确定按钮
                    addBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            archivesBegin.setText("" + addTime.getText().toString());
                            addressPopupWindow.dismiss();
                        }
                    });
                }
            }).asCustom(addressPopupWindow).show();
        } else if (viewId == R.id.rela_over) {
            final AddressPopupWindow addressPopupWindow2 = new AddressPopupWindow(this);
            new XPopup.Builder(this).setPopupCallback(new SimpleCallback() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onCreated() {
                    super.onCreated();
                    DatePicker dpicker = findViewById(R.id.add_datepicker);
                    TextView addBt = findViewById(R.id.add_bt);
                    final TextView addTime = findViewById(R.id.add_time);
                    day = dpicker.getDayOfMonth();
                    month = dpicker.getMonth();
                    year = dpicker.getYear();
                    int am = month + 1;
                    addTime.setText(year + "-" + am + "-" + day);
                    dpicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
                        @Override
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            int x = monthOfYear + 1;
                            addTime.setText(year + "-" + x + "-" + dayOfMonth);
                        }
                    });
                    //确定按钮
                    addBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            archivesOver.setText("" + addTime.getText().toString());
                            addressPopupWindow2.dismiss();
                        }
                    });
                }
            }).asCustom(addressPopupWindow2).show();
        } else if (viewId == R.id.archives_bc) {
            String msg1 = b1.getText().toString().trim();
            String msg2 = b2.getText().toString().trim();
            String msg3 = b3.getText().toString().trim();
            String msg4 = b4.getText().toString().trim();
            String msg5 = b5.getText().toString().trim();
            String msg6 = archivesBegin.getText().toString().trim();
            String msg7 = archivesOver.getText().toString().trim();

            AddArchives addArchives = new AddArchives();
            addArchives.setDiseaseMain(msg1);
            addArchives.setDiseaseNow(msg2);
            addArchives.setDiseaseBefore(msg3);
            addArchives.setTreatmentHospitalRecent(msg4);
            addArchives.setTreatmentProcess(msg5);
            addArchives.setTreatmentStartTime(msg6);
            addArchives.setTreatmentEndTime(msg7);
            images.clear();
            images.add(R.mipmap.add);
            presenter.archives(addArchives);
        } else if (viewId == R.id.tj_arc) {
            noMessage.setVisibility(View.GONE);
            messageUp.setVisibility(View.VISIBLE);
            messageShow.setVisibility(View.GONE);
        }
    }


    private void iniImages() {
        images = new ArrayList();
        images.add(R.mipmap.add);
        addRec.setNestedScrollingEnabled(false);
        addRec.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        imageAdapter = new PubliscImageAdapter(this, images);
        addRec.setAdapter(imageAdapter);
        imageAdapter.setClick(new PubliscImageAdapter.PuImgClick() {
            @Override
            public void click() {
                if (images.size() == 7) {
                    ToastUtils.showShort("最多6张");
                    return;
                } else {
                    PictureSelector.create(ArchivesActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                            .maxSelectNum(6)// 最大图片选择数量 int
                            .minSelectNum(1)// 最小选择数量 int
                            .imageSpanCount(4)// 每行显示个数 int
                            .selectionMode(PictureConfig.TYPE_ALL)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .previewImage(true)// 是否可预览图片 true or false
                            .forResult(0);//结果回调onActivityResult code
                }
            }
        });

    }
}
