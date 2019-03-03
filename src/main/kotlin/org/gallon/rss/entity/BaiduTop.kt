package org.gallon.rss.entity

class BaiduTop(num: Int = 0, keywords: String = "", url: String = "", rise: Int = 0) {

    var num = num

    var keywords = keywords

    var url = url

    var rise = rise

    override fun toString(): String {
        return "BaiduTop(num=$num, keywords='$keywords', url='$url', rise=$rise)"
    }

}