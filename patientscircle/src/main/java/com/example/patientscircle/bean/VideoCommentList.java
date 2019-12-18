package com.example.patientscircle.bean;

import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/8/13
 *@Time:21:09
 *@Description:${DESCRIPTION}
 * */public class VideoCommentList {
    /**
     * result : [{"content":"这个医生讲解的很专业","id":1},{"content":"这个医生讲解的很专业","id":2},{"content":"这个医生讲解的很专业","id":3},{"content":"gfdg","id":4},{"content":"卡卡","id":16},{"content":"bbbbbbbbbbbbbbbbb","id":19},{"content":"你好帅","id":20},{"content":"123123","id":21},{"content":"我想说我就fuck了","id":22},{"content":"真心fuck","id":23},{"content":"王家瑞","id":24},{"content":"张莉宏是傻逼 ok?","id":26},{"content":"不能那些年","id":29},{"content":"不像你那些年","id":30},{"content":"不像你那些年","id":31},{"content":"好久休假很喜欢","id":32},{"content":"回到家就到家","id":33},{"content":"很喜欢很喜欢","id":34},{"content":"更丰富","id":35},{"content":"重新","id":36},{"content":"肥嘟嘟","id":37},{"content":"吃吃吃","id":38},{"content":"废话呵呵","id":39},{"content":"返回回到家","id":40},{"content":"返回回到家","id":41},{"content":"挂电话电话","id":42},{"content":"查查","id":43},{"content":"或长或短","id":49},{"content":"离你那","id":58},{"content":"付都觉得","id":61},{"content":"1611b","id":62},{"content":"1611b","id":63},{"content":"1611b","id":64},{"content":"网红","id":65},{"content":"啦咯啦咯啦咯啦啦啦","id":66},{"content":"参加都不想","id":69},{"content":"骨灰盒","id":76},{"content":"骨灰盒","id":77},{"content":"骨灰盒","id":78},{"content":"骨灰盒","id":79},{"content":"骨灰盒","id":80},{"content":"骨灰盒","id":81},{"content":"骨灰盒","id":82},{"content":"还能","id":87},{"content":"说你呢说你呢","id":94},{"content":"长长的","id":95},{"content":"长长的","id":96},{"content":"才吃饭","id":97},{"content":"才吃饭","id":98},{"content":"刚刚","id":99},{"content":"刚刚","id":100},{"content":"叭叭叭","id":102},{"content":"叭叭叭","id":103},{"content":"黄金季节","id":106},{"content":"哦哟自己","id":111},{"content":"11111111111111111111111111111111111111111111111111","id":112},{"content":"姑姑家我记忆","id":113},{"content":"哈哈哈","id":114},{"content":"奋斗的","id":115},{"content":"摸摸你","id":116},{"content":"很专业","id":117},{"content":"好几回","id":118},{"content":"vvv","id":129},{"content":"撒啊啊","id":148},{"content":"88","id":151},{"content":"88","id":152},{"content":"88","id":153},{"content":"0","id":154},{"content":"7777","id":157},{"content":"9","id":158},{"content":"让人","id":163},{"content":"让人","id":164},{"content":"9","id":170},{"content":"一级","id":172},{"content":"一级","id":173},{"content":"一级","id":174},{"content":"回家","id":175},{"content":"辣鸡","id":177},{"content":"辣鸡","id":178},{"content":"一句一句一句一句也我1了","id":179},{"content":"好吃刚吃过","id":180},{"content":"胡","id":184},{"content":"宿舍","id":191},{"content":"肺结核","id":192},{"content":"肺结核","id":193},{"content":"肺结核","id":194},{"content":"金口水巾","id":195},{"content":"婆婆乒乒乓乓乒乒乓乓","id":199},{"content":"黄近近景近景近近景近景","id":200},{"content":"实时刷新","id":207},{"content":"会员1就要嚣张一点","id":208},{"content":"3","id":217},{"content":"hfhfb","id":218},{"content":"大舅哥啊啊啊啊啊啊啊啊啊吧把啊啊啊啊","id":219},{"content":"无情二七","id":223},{"content":"撒大声地所","id":224},{"content":"说说","id":225},{"content":"我说","id":226},{"content":"好像奖学金","id":234}]
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
         * content : 这个医生讲解的很专业
         * id : 1
         */

        private String content;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
