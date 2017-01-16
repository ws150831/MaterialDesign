package com.six.history.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20/020.
 */

public class ImageStatus
{


    /**
     * reason : success
     * result : [{"e_id":"236","title":"人民解放军副总参谋长杨勇逝世","content":"    在33年前的今天，1983年1月6日 (农历冬月廿三)，人民解放军副总参谋长杨勇逝世。\r\n    杨勇（1912-1983），1912年8月出生于湖南省浏阳县文家市一个农民的家庭。1930年2月加入中国共产党。同年参加中国工农红军。任红三军团第四师十团政治委员参加了中央根据地一、二、三、四、五次反\u201c围剿\u201d的斗争。长征途中，率部冲锋陷阵，英勇奋战，大战土城、娄山关、遵义、老鸦山，四渡赤水。抗日战争参加了平型关大战。1940年5月任第一一五师三四三旅旅长兼鲁西军区司令员。1945年任七纵司令员。1948年参加宛东战役、郑州战役、淮海战役。1949年2月任第二野战军第五兵团司令员。1950年1月，杨勇就任贵州省人民政府主席兼贵州军区司令员。1953年4月参加抗美援朝战争，先后任中国人民志愿军第十二兵团司令员、志愿军第三副司令员兼参谋长、志愿军司令员职务。1955年被授予中国人民解放军上将军衔，荣获一级八一勋章、一级独立自由勋章、一级解放勋章。1958年10月回国后，被任命为中国人民解放军北京军区司令员。次年10月任副总参谋长兼北京军区司令员。 \u201c文革\u201d中受诬陷和迫害。1972年5月杨勇任沈阳军区副司令员。1975年8月任新疆军区司令员、军区党委第二书记。1977年8月被选为中共十一届中央委员会委员。同年9月调任副总参谋长、总参谋部党委第三书记、中共中央军事委员会委员和列席常务委员。1982年9月在中共十二届全国代表大会上当选为十二届中央委员会委员和中央书记处书记。\r\n    于1983年1月6日在北京病逝，终年70岁。\r\n\r\n","picNo":"1","picUrl":[{"pic_title":"","id":1,"url":"http://images.juheapi.com/history/236_1.jpg"}]}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * e_id : 236
         * title : 人民解放军副总参谋长杨勇逝世
         * content :     在33年前的今天，1983年1月6日 (农历冬月廿三)，人民解放军副总参谋长杨勇逝世。
         杨勇（1912-1983），1912年8月出生于湖南省浏阳县文家市一个农民的家庭。1930年2月加入中国共产党。同年参加中国工农红军。任红三军团第四师十团政治委员参加了中央根据地一、二、三、四、五次反“围剿”的斗争。长征途中，率部冲锋陷阵，英勇奋战，大战土城、娄山关、遵义、老鸦山，四渡赤水。抗日战争参加了平型关大战。1940年5月任第一一五师三四三旅旅长兼鲁西军区司令员。1945年任七纵司令员。1948年参加宛东战役、郑州战役、淮海战役。1949年2月任第二野战军第五兵团司令员。1950年1月，杨勇就任贵州省人民政府主席兼贵州军区司令员。1953年4月参加抗美援朝战争，先后任中国人民志愿军第十二兵团司令员、志愿军第三副司令员兼参谋长、志愿军司令员职务。1955年被授予中国人民解放军上将军衔，荣获一级八一勋章、一级独立自由勋章、一级解放勋章。1958年10月回国后，被任命为中国人民解放军北京军区司令员。次年10月任副总参谋长兼北京军区司令员。 “文革”中受诬陷和迫害。1972年5月杨勇任沈阳军区副司令员。1975年8月任新疆军区司令员、军区党委第二书记。1977年8月被选为中共十一届中央委员会委员。同年9月调任副总参谋长、总参谋部党委第三书记、中共中央军事委员会委员和列席常务委员。1982年9月在中共十二届全国代表大会上当选为十二届中央委员会委员和中央书记处书记。
         于1983年1月6日在北京病逝，终年70岁。


         * picNo : 1
         * picUrl : [{"pic_title":"","id":1,"url":"http://images.juheapi.com/history/236_1.jpg"}]
         */

        private String e_id;
        private String title;
        private String content;
        private String picNo;
        private List<PicUrlBean> picUrl;

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicNo() {
            return picNo;
        }

        public void setPicNo(String picNo) {
            this.picNo = picNo;
        }

        public List<PicUrlBean> getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(List<PicUrlBean> picUrl) {
            this.picUrl = picUrl;
        }

        public static class PicUrlBean {
            /**
             * pic_title :
             * id : 1
             * url : http://images.juheapi.com/history/236_1.jpg
             */

            private String pic_title;
            private int id;
            private String url;

            public String getPic_title() {
                return pic_title;
            }

            public void setPic_title(String pic_title) {
                this.pic_title = pic_title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
