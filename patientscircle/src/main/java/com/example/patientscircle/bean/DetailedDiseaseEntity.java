package com.example.patientscircle.bean;

/*
 *@Auther:陈浩
 *@Date: 2019/8/23
 *@Time:20:58
 *@Description:${DESCRIPTION}
 * */public class DetailedDiseaseEntity {

    /**
     * result : {"benefitTaboo":" 1．急性胆囊炎病人，应卧床休息，轻者可吃半流质饮食，重者则应禁食并予以静脉输液。 2．如果你的胆囊炎病情特别严重，可考虑进医院动手术，但最好等发炎消退后再动手术。 3．注意卫生，吃生菜、水果时必须先洗干净，饭前便后必须洗手，以防止蛔虫感染，有蛔虫者应先驱虫。 4．如果你有胆囊疾病，应立即去看医生。 5．一切酒类及刺激性食物或浓烈的调味品均可能导致胆囊炎的急性发作，宜慎之。","chineseMedicineTreatment":" 1．胸胁胀满，嗳气频作，恶心呕逆，口苦纳呆，大便不调，右上腹时有隐痛，每遇情志不遂则诸证加重。舌淡红苔薄白，脉弦。可服逍遥丸、四逆散、保和丸、木香顺气丸等。 2．右上腹间歇性闷痛或隐痛，并放射右腰背部，常有口苦，恶心，食欲不佳，或食后脘痞，每因进食油腻后诸证加重，舌淡，边尖多红，苔薄白或微黄，脉弦。可服消炎利胆片，肝胆炎片，利胆片等。此外，并发结石的患者可配合服用胆石通、利胆排石片等。 3．急性胆囊炎可用金胆片，口服6~10片，1日3次；胆舒胶囊，每次3粒，1日3次。","createTime":1547105873000,"diseaseCategoryId":198,"id":198,"pathology":"胆囊发炎并肿大的疾病叫胆囊炎。胆囊炎是胆结石堵塞了胆汁从胆囊正常流往肠内通道的结果。另外，胆囊炎也可能是由肠道感染逐渐向上扩散所致。患过胆中疾病的人发病率明显较高。","symptom":"发病前患者的腹部的右上方常会发生剧烈的疼痛，这种情况叫胆绞痛，随后才逐步形成胆囊炎，当胆囊发为的程度逐渐加重时，患者的体温会逐渐上升，并可能随即出现恶心及呕吐症状。病情严重者出现黄疸。如果胆囊因某种疾病肿胀得太厉害而破裂，就会形成严重的腹膜炎。","westernMedicineTreatment":" 1．可口服利胆醇（0.1克胶丸，饭后服）；胆酸钠（0.2克胶囊，总胆管阻塞时忌用）；去氢胆酸（0.25克片剂，胆道完全阻塞和严重肝、肾功能障碍者忌用）；硫酸镁（50%溶液每次10毫升，呕吐、便溏患者不宜服用，孕妇、妇女月经期、急腹症及有肠出血可能者均属禁忌）。 2．疼痛严重时选用普鲁本辛（15毫克片剂，青光眼中层得忌用）；颠茄（复方片。青光眼、前列腺肥大和急腹症诊断未明时慎用或忌用）；硝酸甘油（0.5毫克片剂，两面三刀下含服。可用于胆绞痛发作时。青光眼、低血压、脑出血、颅内压增高者忌用）。 3．消炎药。细菌感染时选用阿奇霉素、林可霉素等消炎药。此外，也可选用其他抗生素类如卡那霉素等肌肉注射，以尽快控制感染。 4．凡经药物等非手术治疗无效，且病情不断发展，影响生活和工作者，可考虑手术切除胆囊。 5．疼痛剧烈者，立即注射吗啡10毫克和阿托品0.5毫克，或者注射曲马多100毫克，此外口服颠茄酊；同时大剂量抗生素联合静滴，如青霉素、益萨林、庆大霉素、灭滴灵等药。"}
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
         * benefitTaboo :  1．急性胆囊炎病人，应卧床休息，轻者可吃半流质饮食，重者则应禁食并予以静脉输液。 2．如果你的胆囊炎病情特别严重，可考虑进医院动手术，但最好等发炎消退后再动手术。 3．注意卫生，吃生菜、水果时必须先洗干净，饭前便后必须洗手，以防止蛔虫感染，有蛔虫者应先驱虫。 4．如果你有胆囊疾病，应立即去看医生。 5．一切酒类及刺激性食物或浓烈的调味品均可能导致胆囊炎的急性发作，宜慎之。
         * chineseMedicineTreatment :  1．胸胁胀满，嗳气频作，恶心呕逆，口苦纳呆，大便不调，右上腹时有隐痛，每遇情志不遂则诸证加重。舌淡红苔薄白，脉弦。可服逍遥丸、四逆散、保和丸、木香顺气丸等。 2．右上腹间歇性闷痛或隐痛，并放射右腰背部，常有口苦，恶心，食欲不佳，或食后脘痞，每因进食油腻后诸证加重，舌淡，边尖多红，苔薄白或微黄，脉弦。可服消炎利胆片，肝胆炎片，利胆片等。此外，并发结石的患者可配合服用胆石通、利胆排石片等。 3．急性胆囊炎可用金胆片，口服6~10片，1日3次；胆舒胶囊，每次3粒，1日3次。
         * createTime : 1547105873000
         * diseaseCategoryId : 198
         * id : 198
         * pathology : 胆囊发炎并肿大的疾病叫胆囊炎。胆囊炎是胆结石堵塞了胆汁从胆囊正常流往肠内通道的结果。另外，胆囊炎也可能是由肠道感染逐渐向上扩散所致。患过胆中疾病的人发病率明显较高。
         * symptom : 发病前患者的腹部的右上方常会发生剧烈的疼痛，这种情况叫胆绞痛，随后才逐步形成胆囊炎，当胆囊发为的程度逐渐加重时，患者的体温会逐渐上升，并可能随即出现恶心及呕吐症状。病情严重者出现黄疸。如果胆囊因某种疾病肿胀得太厉害而破裂，就会形成严重的腹膜炎。
         * westernMedicineTreatment :  1．可口服利胆醇（0.1克胶丸，饭后服）；胆酸钠（0.2克胶囊，总胆管阻塞时忌用）；去氢胆酸（0.25克片剂，胆道完全阻塞和严重肝、肾功能障碍者忌用）；硫酸镁（50%溶液每次10毫升，呕吐、便溏患者不宜服用，孕妇、妇女月经期、急腹症及有肠出血可能者均属禁忌）。 2．疼痛严重时选用普鲁本辛（15毫克片剂，青光眼中层得忌用）；颠茄（复方片。青光眼、前列腺肥大和急腹症诊断未明时慎用或忌用）；硝酸甘油（0.5毫克片剂，两面三刀下含服。可用于胆绞痛发作时。青光眼、低血压、脑出血、颅内压增高者忌用）。 3．消炎药。细菌感染时选用阿奇霉素、林可霉素等消炎药。此外，也可选用其他抗生素类如卡那霉素等肌肉注射，以尽快控制感染。 4．凡经药物等非手术治疗无效，且病情不断发展，影响生活和工作者，可考虑手术切除胆囊。 5．疼痛剧烈者，立即注射吗啡10毫克和阿托品0.5毫克，或者注射曲马多100毫克，此外口服颠茄酊；同时大剂量抗生素联合静滴，如青霉素、益萨林、庆大霉素、灭滴灵等药。
         */

        private String benefitTaboo;
        private String chineseMedicineTreatment;
        private long createTime;
        private int diseaseCategoryId;
        private int id;
        private String pathology;
        private String symptom;
        private String westernMedicineTreatment;

        public String getBenefitTaboo() {
            return benefitTaboo;
        }

        public void setBenefitTaboo(String benefitTaboo) {
            this.benefitTaboo = benefitTaboo;
        }

        public String getChineseMedicineTreatment() {
            return chineseMedicineTreatment;
        }

        public void setChineseMedicineTreatment(String chineseMedicineTreatment) {
            this.chineseMedicineTreatment = chineseMedicineTreatment;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDiseaseCategoryId() {
            return diseaseCategoryId;
        }

        public void setDiseaseCategoryId(int diseaseCategoryId) {
            this.diseaseCategoryId = diseaseCategoryId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPathology() {
            return pathology;
        }

        public void setPathology(String pathology) {
            this.pathology = pathology;
        }

        public String getSymptom() {
            return symptom;
        }

        public void setSymptom(String symptom) {
            this.symptom = symptom;
        }

        public String getWesternMedicineTreatment() {
            return westernMedicineTreatment;
        }

        public void setWesternMedicineTreatment(String westernMedicineTreatment) {
            this.westernMedicineTreatment = westernMedicineTreatment;
        }
    }
}
