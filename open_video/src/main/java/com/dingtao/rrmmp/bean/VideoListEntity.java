package com.dingtao.rrmmp.bean;

import java.util.List;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */public class VideoListEntity {
    /**
     * result : [{"abstracts":"小孩贫血的检查方法，应该是根据孩子贫血的.分类来进行的","buyNum":26,"categoryId":1,"duration":95,"id":1,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek1.mp4","price":100,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek1.mp4","title":"小儿贫血的检查方法有哪些","whetherBuy":2,"whetherCollection":2},{"abstracts":"在哺乳的时候一定要采取小孩在左弯45度的哺乳姿势，而不应该是小孩平躺哺乳","buyNum":14,"categoryId":1,"duration":98,"id":2,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek2.mp4","price":50,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek2.mp4","title":"儿童错颌畸形如何预防","whetherBuy":2,"whetherCollection":2},{"abstracts":"我们知道发热是儿科临床，一个非常常见的症状，那么发热之后怎么办","buyNum":14,"categoryId":1,"duration":75,"id":6,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek6.mp4","price":60,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek6.mp4","title":"小儿发热如何正确使用退烧药","whetherBuy":2,"whetherCollection":2},{"abstracts":"因为多种原因可以引起黄疸，而且胆红素是人体正常的代谢产物，所以不能预防，只能对这些孩子进行严密的监测","buyNum":8,"categoryId":1,"duration":128,"id":4,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek4.mp4","price":80,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek4.mp4","title":"新生儿黄疸能预防吗","whetherBuy":2,"whetherCollection":2},{"abstracts":"儿童肥胖并不是一个简单的、体型肥胖的问题，可引起很多类似于成人的代谢问题","buyNum":10,"categoryId":1,"duration":65,"id":5,"originalUrl":"http://172.17.8.100/video/health/original/ek/ek5.mp4","price":50,"shearUrl":"http://172.17.8.100/video/health/cut/ek/ek5.mp4","title":"儿童肥胖会给孩子带来哪些代谢上的影响","whetherBuy":2,"whetherCollection":2}]
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
         * abstracts : 小孩贫血的检查方法，应该是根据孩子贫血的.分类来进行的
         * buyNum : 26
         * categoryId : 1
         * duration : 95
         * id : 1
         * originalUrl : http://172.17.8.100/video/health/original/ek/ek1.mp4
         * price : 100
         * shearUrl : http://172.17.8.100/video/health/cut/ek/ek1.mp4
         * title : 小儿贫血的检查方法有哪些
         * whetherBuy : 2
         * whetherCollection : 2
         */

        private String abstracts;
        private int buyNum;
        private int categoryId;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int whetherBuy;
        private int whetherCollection;

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
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

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }
    }
}
