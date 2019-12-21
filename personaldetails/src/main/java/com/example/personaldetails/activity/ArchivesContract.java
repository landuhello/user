package com.example.personaldetails.activity;



import com.dingtao.common.core.hello.IBaseModel;
import com.example.personaldetails.activity.bean.AddArchives;

import java.util.List;

import okhttp3.MultipartBody;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public interface ArchivesContract {
    interface ArchivesView extends IBaseView{
        void findArchives(Object obj);
        void archives(Object obj);
        void archivesPic(Object obj);
        void del(Object obj);
    }
    interface ArchivesModel extends IBaseModel {
        void dogetFindArchives(CallBackObj callBackObj);
        void dopostArchives(AddArchives addArchives, CallBackObj callBackObj);
        void dopostArchivesPic(List<MultipartBody.Part> picture, CallBackObj callBackObj);
        void dodeleteArchives(int archivesId, CallBackObj callBackObj);
    }
    abstract class ArchivesPresenter extends BasePresenter<ArchivesModel,ArchivesView>{
        public abstract void findArchives();
        public abstract void archives(AddArchives addArchives);
        public abstract void archivesPic(List<MultipartBody.Part> picture);
        public abstract void del(int archivesId);

        @Override
        public ArchivesModel getModel() {
            return new ArchivesModelImpl();
        }
    }
}
