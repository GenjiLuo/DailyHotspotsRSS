package org.gallon.rss.entity

class BaiduTop(var num: Int = 0, var keywords: String = "", var url: String = "", var rise: Int = 0) {

    override fun toString(): String {
        return "BaiduTop(num=$num, keywords='$keywords', url='$url', rise=$rise)"
    }

}