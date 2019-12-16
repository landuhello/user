package com.dingtao.rrmmp.bean;

import java.util.List;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */public class MyVideoEntity {

    /**
     * result : [{"buyNum":2,"createTime":1565932475000,"duration":273,"id":740,"originalUrl":"http://172.17.8.100/video/health/original/xl/xl7.mp4","price":0,"shearUrl":"http://172.17.8.100/video/health/cut/xl/xl7.mp4","title":"走进心理学 你是否正在被他人控制！","videoId":39,"whetherBuy":2},{"buyNum":4,"createTime":1565870598000,"duration":224,"id":723,"originalUrl":"http://172.17.8.100/video/health/original/js/js4.mp4","price":0,"shearUrl":"http://172.17.8.100/video/health/cut/js/js4.mp4","title":"健身过程中怎么避免身体损伤","videoId":20,"whetherBuy":2},{"buyNum":2,"createTime":1565852029000,"duration":103,"id":709,"originalUrl":"http://172.17.8.100/video/health/original/ys/ys1.mp4","price":0,"shearUrl":"http://172.17.8.100/video/health/cut/ys/ys1.mp4","title":"3个偏方，教你防治腰间盘突出症！","videoId":41,"whetherBuy":2},{"buyNum":8,"createTime":1565849953000,"duration":78,"id":708,"originalUrl":"http://172.17.8.100/video/health/original/cs/cs3.mp4","price":70,"shearUrl":"http://172.17.8.100/video/health/cut/cs/cs3.mp4","title":"多喝热水真的好吗？","videoId":11,"whetherBuy":2},{"buyNum":3,"createTime":1565849102000,"duration":73,"id":707,"originalUrl":"http://172.17.8.100/video/health/original/mr/mr1.mp4","price":0,"shearUrl":"http://172.17.8.100/video/health/cut/mr/mr1.mp4","title":"玻尿酸微整形的效果怎么样？","videoId":25,"whetherBuy":2},{"buyNum":3,"createTime":1565748599000,"duration":267,"id":631,"originalUrl":"http://172.17.8.100/video/health/original/xl/xl5.mp4","price":0,"shearUrl":"http://172.17.8.100/video/health/cut/xl/xl5.mp4","title":"怎样成为一个自律的人？","videoId":37,"whetherBuy":2}]
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
         * buyNum : 2
         * createTime : 1565932475000
         * duration : 273
         * id : 740
         * originalUrl : http://172.17.8.100/video/health/original/xl/xl7.mp4
         * price : 0
         * shearUrl : http://172.17.8.100/video/health/cut/xl/xl7.mp4
         * title : 走进心理学 你是否正在被他人控制！
         * videoId : 39
         * whetherBuy : 2
         */

        private int buyNum;
        private long createTime;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int videoId;
        private int whetherBuy;

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getShearUrl() {
            return shearUrl;
        }

        public void setShearUrl(String shearUrl) {
            this.shearUrl = shearUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }
    }
}
