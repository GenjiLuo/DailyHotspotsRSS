package org.gallon.rss.entity.mongo

class Article {

  // 搜索关键字(如果有)
  var keyword = ""

  // 百度 网易 新浪 ...
  var src = ""

  // 科技 金融 娱乐 体育 汽车
  var category = ""

  // 图片地址
  var pic = ""

  // 文章标题
  var title = ""

  // 文章预览内容
  var preview = ""

  // 文章内容
  var content = ""

  // 文章内容 html版
  var html = ""

  // 新闻时间
  var news_time: String = ""

  // 移动端链接文章链接
  var url = ""

  // PC端文章链接
  var weburl = ""

  // 热度(如果有)
  var rise = 0

  // 0可用 1不可用 2丢弃 3测试
  var usage = 0

  override fun toString(): String {
    return "Article(keyword='$keyword', src='$src', category='$category', pic='$pic', title='$title', preview='$preview', content='$content', html='$html', news_time='$news_time', url='$url', weburl='$weburl', rise=$rise, usage=$usage)"
  }

}