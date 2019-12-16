package com.example.personaldetails.activity;

import java.util.List;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class WalletRecEntity {
    /**
     * result : [{"changeNum":-500,"createTime":1566196103000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566196102000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566196099000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566180669000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566180667000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566180665000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566180632000,"direction":2,"type":12},{"changeNum":-500,"createTime":1566126520000,"direction":2,"type":9},{"changeNum":-500,"createTime":1566010815000,"direction":2,"type":9},{"changeNum":-500,"createTime":1566010672000,"direction":2,"type":9}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * changeNum : -500
         * createTime : 1566196103000
         * direction : 2
         * type : 12
         */

        private int changeNum;
        private long createTime;
        private int direction;
        private int type;

        public int getChangeNum() {
            return changeNum;
        }

        public void setChangeNum(int changeNum) {
            this.changeNum = changeNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "changeNum=" + changeNum +
                    ", createTime=" + createTime +
                    ", direction=" + direction +
                    ", type=" + type +
                    '}';
        }
    }
}
