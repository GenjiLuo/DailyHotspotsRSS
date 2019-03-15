package org.gallon.rss.entity.gson;

import java.util.List;

/**
 * https://www.jisuapi.com/debug/news/
 * 极速数据
 */
public class FastNews {

  /**
   * status : 0
   * msg : ok
   * result : {}
   */

  private String status;
  private String msg;
  private ResultBean result;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public ResultBean getResult() {
    return result;
  }

  public void setResult(ResultBean result) {
    this.result = result;
  }

  public static class ResultBean {
    /**
     * channel : 头条
     * num : 10
     * list : [{"title":"直击|传熊猫直播进入破产清算 副总裁庄明浩已离职","time":"2019-03-06 16:15:00","src":"新浪科技","category":"tech","pic":"http://n.sinaimg.cn/tech/transform/667/w400h267/20190306/EiUU-htwhfzs7091525.jpg","content":" 新浪科技讯 3月6日下午消息，据\u201c直播点吧\u201d微博爆料，熊猫直播已进入破产清算，并贴出一张群聊天记录截图。截图显示熊猫直播Hr在员工群\u201c潘达踢威\u201d中表示已经帮员工安排了多家公司的用人需求。<\/p>\n  新浪科技曾就此询问熊猫直播，但并未得到回复。此外，据媒体报道，熊猫直播副总裁庄明浩已确认离职。<\/p>\n  根据企查查显示，熊猫直播主体上海熊猫互娱文化有限公司的大股东为珺娱（湖州）文化发展中心，持股40.07%，而该公司则由王思聪100%持股，王思聪同时也是熊猫互娱董事长。此外，北京奇虎科技有限公司持股19.35%。<\/p>\n  此前，熊猫直播都多次传出欠薪、资金链断裂等传闻，2018年10月，其COO张菊元在接受媒体采访表示：公司还活着，且盈利在望，2019年将从巨头手中拿到融资，估值将超50亿元。<\/p>\n  \"\"<\/span><\/div>\n 如今，熊猫直播再度登上新闻，却是\u201c破产清算\u201d。在微博上，有熊猫直播主播在今日下午发文感慨，\u201c错过了你最辉煌时期，却见证了你的低谷\u201d，或侧面证实破产传言。(雪梅)<\/p>  <\/div>","url":"http://tech.sina.cn/i/gn/2019-03-06/detail-ihrfqzkc1689120.d.html?vt=4&pos=108","weburl":"https://tech.sina.com.cn/i/2019-03-06/doc-ihrfqzkc1689120.shtml"}]
     */

    private String channel;
    private String num;
    private List<ListBean> list;

    public String getChannel() {
      return channel;
    }

    public void setChannel(String channel) {
      this.channel = channel;
    }

    public String getNum() {
      return num;
    }

    public void setNum(String num) {
      this.num = num;
    }

    public List<ListBean> getList() {
      return list;
    }

    public void setList(List<ListBean> list) {
      this.list = list;
    }

    public static class ListBean {
      /**
       * title : 直击|传熊猫直播进入破产清算 副总裁庄明浩已离职
       * time : 2019-03-06 16:15:00
       * src : 新浪科技
       * category : tech
       * pic : http://n.sinaimg.cn/tech/transform/667/w400h267/20190306/EiUU-htwhfzs7091525.jpg
       * content :  新浪科技讯 3月6日下午消息，据“直播点吧”微博爆料，熊猫直播已进入破产清算，并贴出一张群聊天记录截图。截图显示熊猫直播Hr在员工群“潘达踢威”中表示已经帮员工安排了多家公司的用人需求。</p>
       新浪科技曾就此询问熊猫直播，但并未得到回复。此外，据媒体报道，熊猫直播副总裁庄明浩已确认离职。</p>
       根据企查查显示，熊猫直播主体上海熊猫互娱文化有限公司的大股东为珺娱（湖州）文化发展中心，持股40.07%，而该公司则由王思聪100%持股，王思聪同时也是熊猫互娱董事长。此外，北京奇虎科技有限公司持股19.35%。</p>
       此前，熊猫直播都多次传出欠薪、资金链断裂等传闻，2018年10月，其COO张菊元在接受媒体采访表示：公司还活着，且盈利在望，2019年将从巨头手中拿到融资，估值将超50亿元。</p>
       ""</span></div>
       如今，熊猫直播再度登上新闻，却是“破产清算”。在微博上，有熊猫直播主播在今日下午发文感慨，“错过了你最辉煌时期，却见证了你的低谷”，或侧面证实破产传言。(雪梅)</p>  </div>
       * url : http://tech.sina.cn/i/gn/2019-03-06/detail-ihrfqzkc1689120.d.html?vt=4&pos=108
       * weburl : https://tech.sina.com.cn/i/2019-03-06/doc-ihrfqzkc1689120.shtml
       */

      private String title;
      private String time;
      private String src;
      private String category;
      private String pic;
      private String content;
      private String url;
      private String weburl;

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public String getTime() {
        return time;
      }

      public void setTime(String time) {
        this.time = time;
      }

      public String getSrc() {
        return src;
      }

      public void setSrc(String src) {
        this.src = src;
      }

      public String getCategory() {
        return category;
      }

      public void setCategory(String category) {
        this.category = category;
      }

      public String getPic() {
        return pic;
      }

      public void setPic(String pic) {
        this.pic = pic;
      }

      public String getContent() {
        return content;
      }

      public void setContent(String content) {
        this.content = content;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public String getWeburl() {
        return weburl;
      }

      public void setWeburl(String weburl) {
        this.weburl = weburl;
      }

      @Override
      public String toString() {
        return "ListBean{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", src='" + src + '\'' +
                ", category='" + category + '\'' +
                ", pic='" + pic + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", weburl='" + weburl + '\'' +
                '}';
      }
    }
  }
}
