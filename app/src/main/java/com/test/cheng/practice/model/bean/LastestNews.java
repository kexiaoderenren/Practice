package com.test.cheng.practice.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kexiaoderenren on 2017/1/3.
 */
public class LastestNews implements Serializable{


    /**
     * date : 20170103
     * stories : [{"images":["http://pic2.zhimg.com/c4f654d5eb619cb71ceb1962281e72c9.jpg"],"type":0,"id":9095657,"ga_prefix":"010322","title":"小事 · 被逼疯了"},{"images":["http://pic1.zhimg.com/b089433691f7f2176c33d23aa721275c.jpg"],"type":0,"id":9118595,"ga_prefix":"010321","title":"「我到这种年纪了，还没有说过爱过谁比海更深」"},{"images":["http://pic1.zhimg.com/37ede1bea3158124f743f598c5a614a8.jpg"],"type":0,"id":9118184,"ga_prefix":"010320","title":"也许你注意到了颤抖的双手，却没有意识到背后的痛苦"},{"title":"喜欢极简和原木的装修风格，家具陈设该如何选择？","ga_prefix":"010319","images":["http://pic1.zhimg.com/185f1c2c2077c7f51e0165f40a946f0c.jpg"],"multipic":true,"type":0,"id":9119197},{"images":["http://pic2.zhimg.com/42a8e908b456aa29197c0f8b1fd86edd.jpg"],"type":0,"id":9117452,"ga_prefix":"010318","title":"都说投行工作很辛苦，为什么不多招点人？"},{"images":["http://pic2.zhimg.com/2bac46074820ba4e8ede4e849109ea69.jpg"],"type":0,"id":9119126,"ga_prefix":"010317","title":"知乎好问题 · 为什么接纳自己是一件很重要的事？"},{"images":["http://pic1.zhimg.com/0ba35a6d270668cf1584d45fe2bdab9c.jpg"],"type":0,"id":9118136,"ga_prefix":"010316","title":"身为一个山西人，看到意面居然有种谜之亲切"},{"images":["http://pic2.zhimg.com/3af86488fe6769b6bb2667748f1e82e1.jpg"],"type":0,"id":9118079,"ga_prefix":"010315","title":"雾与霾不离不弃，人类遭殃了"},{"images":["http://pic1.zhimg.com/4af89bca7cf5bca7740f1e2ae69f2180.jpg"],"type":0,"id":9117414,"ga_prefix":"010314","title":"双方签订合同也可以交易，收购在经济学上有什么意义？"},{"images":["http://pic4.zhimg.com/26559413c8267d5077ba1a75985c1f8b.jpg"],"type":0,"id":9118033,"ga_prefix":"010313","title":"为什么任意门应该做成球形？"},{"images":["http://pic1.zhimg.com/3222f39add29ec446da0a23b20c72200.jpg"],"type":0,"id":9116405,"ga_prefix":"010312","title":"大误 · 抽 SSR 是玄学"},{"images":["http://pic3.zhimg.com/88dfefc31c598621126947d6aaed416a.jpg"],"type":0,"id":9115115,"ga_prefix":"010311","title":"孩子自尊心最脆弱的部分，别让最爱他们的家人伤害"},{"images":["http://pic1.zhimg.com/827de756a51a5fddc15f1d5727822e98.jpg"],"type":0,"id":9115328,"ga_prefix":"010310","title":"手机支付的未来，是 NFC 还是二维码？"},{"images":["http://pic3.zhimg.com/9ac068d081db9a1fabb3c715d28afd82.jpg"],"type":0,"id":9115215,"ga_prefix":"010309","title":"如果有一个「快乐按钮」，一摁就会有无与伦比的满足感"},{"images":["http://pic2.zhimg.com/569beaf58b2170a20a2106d0b4689e21.jpg"],"type":0,"id":9117233,"ga_prefix":"010308","title":"企业文化到底是什么呢？"},{"title":"新的一年，试试这些简单营养的西式早餐","ga_prefix":"010307","images":["http://pic2.zhimg.com/ab771f4069b58780abfbe2d3a3fd28c5.jpg"],"multipic":true,"type":0,"id":9112602},{"images":["http://pic1.zhimg.com/14883948fc305c7413dc877da9ff5dbc.jpg"],"type":0,"id":9117424,"ga_prefix":"010307","title":"先别急着掏钱，看起来神奇的洗脸刷不一定适合你"},{"images":["http://pic3.zhimg.com/ecce3489d8f8ea124948e15033f20822.jpg"],"type":0,"id":9117444,"ga_prefix":"010307","title":"如何在无领导小组面试中脱颖而出？"},{"images":["http://pic2.zhimg.com/3f95089e27191e0535bd03921e0e0df1.jpg"],"type":0,"id":9108463,"ga_prefix":"010306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/82c77ad7d28511b97d6681fa926aad40.jpg","type":0,"id":9119126,"ga_prefix":"010317","title":"知乎好问题 · 为什么接纳自己是一件很重要的事？"},{"image":"http://pic1.zhimg.com/1759ea113069b4788369f7cbd4f7283c.jpg","type":0,"id":9118079,"ga_prefix":"010315","title":"雾与霾不离不弃，人类遭殃了"},{"image":"http://pic2.zhimg.com/07823621d12a90c16acb51caa291f3b9.jpg","type":0,"id":9117444,"ga_prefix":"010307","title":"如何在无领导小组面试中脱颖而出？"},{"image":"http://pic1.zhimg.com/ab88987a9e0af72a768f9c68bbfdb3c4.jpg","type":0,"id":9115328,"ga_prefix":"010310","title":"手机支付的未来，是 NFC 还是二维码？"},{"image":"http://pic1.zhimg.com/a9eb2560fd255dc4e848131ebf037da4.jpg","type":0,"id":9112602,"ga_prefix":"010307","title":"新的一年，试试这些简单营养的西式早餐"}]
     */

    private String date;
    private List<StoriesEntity> stories;
    private List<TopStoriesEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesEntity implements Serializable{
        /**
         * images : ["http://pic2.zhimg.com/c4f654d5eb619cb71ceb1962281e72c9.jpg"]
         * type : 0
         * id : 9095657
         * ga_prefix : 010322
         * title : 小事 · 被逼疯了
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesEntity implements Serializable{
        /**
         * image : http://pic1.zhimg.com/82c77ad7d28511b97d6681fa926aad40.jpg
         * type : 0
         * id : 9119126
         * ga_prefix : 010317
         * title : 知乎好问题 · 为什么接纳自己是一件很重要的事？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Override
    public String toString() {
        return "LastestNews{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
