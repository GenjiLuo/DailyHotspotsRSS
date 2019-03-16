package org.gallon.rss.entity.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import kotlin.collections.ArrayList

@Document(collection = "rss")
class RSS {

  @Id
  var id: String? = null

  // 存库时间戳 13位
  var create_time: Long = System.currentTimeMillis()

  // 百度 网易 新浪(极速api) ...
  var src = ""

  // 科技 金融 娱乐 体育 汽车
  var category = ""

  // 0可用 1不可用 2丢弃 3测试
  var usage = 3

  var list: ArrayList<Article> = ArrayList()

}