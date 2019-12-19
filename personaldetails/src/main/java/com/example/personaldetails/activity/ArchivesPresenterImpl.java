package com.example.personaldetails.activity;



import com.example.personaldetails.activity.bean.AddArchives;

import java.util.List;

import okhttp3.MultipartBody;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class ArchivesPresenterImpl extends ArchivesContract.ArchivesPresenter {
    /**
     * 查找档案
     */
    @Override
    public void findArchives() {
        model.dogetFindArchives(new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.findArchives(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 添加档案
     * @param addArchives
     */
    @Override
    public void archives(AddArchives addArchives) {
        model.dopostArchives(addArchives, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.archives(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 添加档案图片
     * @param picture
     */
    @Override
    public void archivesPic(List<MultipartBody.Part> picture) {
        model.dopostArchivesPic(picture, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.archivesPic(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 删除档案
     * @param archivesId
     */
    @Override
    public void del(int archivesId) {
        model.dodeleteArchives(archivesId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.del(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }


}
