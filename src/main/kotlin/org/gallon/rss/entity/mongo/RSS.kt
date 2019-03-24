package org.gallon.rss.entity.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Document(collection = "rss")
class RSS {

  companion object {
      val USAGE_TEST = 0
      val USAGE_DAILY = 1
      val USAGE_OTHER = 2
      val USAGE_DELETE = 3
      val SRC_BAIDUTOP = "baidutop"
      val SRC_WANGYI = "wangyi"
      val SRC_XINLANG = "xinlang" //极速api
  }

  @Id
  var id: String? = null

  // 存库时间戳 13位
  var create_time: Long = System.currentTimeMillis()

  // 存库日期
  var create_date = SimpleDateFormat("yyyyMMdd").format(Date())

  // 百度 网易 新浪(极速api) ...
  var src = ""

  // 科技 金融 娱乐 体育 汽车
  var category = ""

  // 0测试 1每日 2其他 3删除
  var usage = USAGE_TEST

  var list: ArrayList<Article> = ArrayList()

}