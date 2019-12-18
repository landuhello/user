package com.example.patientscircle.bean;

/*
 *@Auther:陈浩
 *@Date: 2019/8/23
 *@Time:21:02
 *@Description:${DESCRIPTION}
 * */public class DrugDetailsEntity {

    /**
     * result : {"approvalNumber":" 国药准字Z42021662 ","component":" 党参、黄芪、山麦冬、醋龟甲、炒白术、山药、醋南五味子、龙骨、煅牡蛎、茯苓、大枣、甘草、乳酸钙、炒鸡内金、维生素D2、葡萄糖酸钙。辅料为蔗糖、菠萝萃取液。 ","createTime":1547709517000,"drugsCategoryId":2,"effect":" 强筋壮骨，和胃健脾。用于治疗和预防小儿佝偻病、软骨病；对小儿多汗、夜惊、食欲不振、消化不良、发育迟缓也有治疗作用。 ","id":55,"mindMatter":" 忌辛辣、生冷、油腻食物。详见说明书。 ","name":" 龙牡壮骨颗粒 ","packing":" 5gx15袋 ","picture":"https://imgq.ddky.com/c/product/531795/big/z_1.jpg?t=1506407609253&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100","shape":" 本品为淡黄色至黄棕色的颗粒；气香，味甜。 ","sideEffects":" 尚不明确。 ","storage":" 密封。 ","taboo":" 尚不明确。 ","usage":" 开水冲服。二岁以下一次5克，二岁至七岁一次7.5克，七岁以上一次10克，一日3次。 "}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * approvalNumber :  国药准字Z42021662
         * component :  党参、黄芪、山麦冬、醋龟甲、炒白术、山药、醋南五味子、龙骨、煅牡蛎、茯苓、大枣、甘草、乳酸钙、炒鸡内金、维生素D2、葡萄糖酸钙。辅料为蔗糖、菠萝萃取液。
         * createTime : 1547709517000
         * drugsCategoryId : 2
         * effect :  强筋壮骨，和胃健脾。用于治疗和预防小儿佝偻病、软骨病；对小儿多汗、夜惊、食欲不振、消化不良、发育迟缓也有治疗作用。
         * id : 55
         * mindMatter :  忌辛辣、生冷、油腻食物。详见说明书。
         * name :  龙牡壮骨颗粒
         * packing :  5gx15袋
         * picture : https://imgq.ddky.com/c/product/531795/big/z_1.jpg?t=1506407609253&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100
         * shape :  本品为淡黄色至黄棕色的颗粒；气香，味甜。
         * sideEffects :  尚不明确。
         * storage :  密封。
         * taboo :  尚不明确。
         * usage :  开水冲服。二岁以下一次5克，二岁至七岁一次7.5克，七岁以上一次10克，一日3次。
         */

        private String approvalNumber;
        private String component;
        private long createTime;
        private int drugsCategoryId;
        private String effect;
        private int id;
        private String mindMatter;
        private String name;
        private String packing;
        private String picture;
        private String shape;
        private String sideEffects;
        private String storage;
        private String taboo;
        private String usage;

        public String getApprovalNumber() {
            return approvalNumber;
        }

        public void setApprovalNumber(String approvalNumber) {
            this.approvalNumber = approvalNumber;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDrugsCategoryId() {
            return drugsCategoryId;
        }

        public void setDrugsCategoryId(int drugsCategoryId) {
            this.drugsCategoryId = drugsCategoryId;
        }

        public String getEffect() {
            return effect;
        }

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMindMatter() {
            return mindMatter;
        }

        public void setMindMatter(String mindMatter) {
            this.mindMatter = mindMatter;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPacking() {
            return packing;
        }

        public void setPacking(String packing) {
            this.packing = packing;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getSideEffects() {
            return sideEffects;
        }

        public void setSideEffects(String sideEffects) {
            this.sideEffects = sideEffects;
        }

        public String getStorage() {
            return storage;
        }

        public void setStorage(String storage) {
            this.storage = storage;
        }

        public String getTaboo() {
            return taboo;
        }

        public void setTaboo(String taboo) {
            this.taboo = taboo;
        }

        public String getUsage() {
            return usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }
    }
}
