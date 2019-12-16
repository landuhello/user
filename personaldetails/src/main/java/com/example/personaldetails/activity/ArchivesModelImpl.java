package com.example.personaldetails.activity;


import com.example.personaldetails.activity.bean.AddArchives;
import com.example.personaldetails.activity.bean.ArchivesEntity;
import com.example.personaldetails.activity.bean.MessageEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class ArchivesModelImpl implements ArchivesContract.ArchivesModel {
    @Override
    public void dogetFindArchives(final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class).userArchives()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArchivesEntity>() {
                    @Override
                    public void accept(ArchivesEntity archivesEntity) throws Exception {
                        callBackObj.success(archivesEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBackObj.err(throwable.toString());
                    }
                });
    }

    @Override
    public void dopostArchives(AddArchives addArchives, final CallBackObj callBackObj) {
            HttpUntils.getInstance().getcreate(UserApi.class).archives(addArchives)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MessageEntity>() {
                        @Override
                        public void accept(MessageEntity messageEntity) throws Exception {
                            callBackObj.success(messageEntity);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                        }
                    });
    }

    @Override
    public void dopostArchivesPic(List<MultipartBody.Part> picture , final CallBackObj callBackObj) {
            HttpUntils.getInstance().getcreate(UserApi.class).uploadArchivesPicture(picture)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MessageEntity>() {
                        @Override
                        public void accept(MessageEntity messageEntity) throws Exception {
                            callBackObj.success(messageEntity);

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                           throwable.printStackTrace();
                        }
                    });
    }

    @Override
    public void dodeleteArchives(int archivesId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(UserApi.class).delArchives(archivesId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MessageEntity>() {
                    @Override
                    public void accept(MessageEntity messageEntity) throws Exception {
                            callBackObj.success(messageEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }
}
