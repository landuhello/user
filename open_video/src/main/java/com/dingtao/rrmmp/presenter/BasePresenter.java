package com.dingtao.rrmmp.presenter;



import java.lang.ref.WeakReference;

/**
 * data:basepresenter基类
 * author:老刘（）
 * function:这是干什么的
 */
public abstract class BasePresenter<M, V> {
    public M model;
    public V view;
    private WeakReference<V> weakReference;

    public abstract M getModel();

    //绑定
    public void attach(V view) {
        this.model = getModel();
        weakReference = new WeakReference<>(view);
        this.view = weakReference.get();//得到view
    }


    //解绑
    public void dettach() {
        if (weakReference != null) {
            weakReference.clear();//清空对象
            weakReference = null;
            this.view = null;
        }
        if (model != null) {
            model = null;
        }
    }
}
