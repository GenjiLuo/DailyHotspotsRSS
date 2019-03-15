package org.gallon.rss.entity.common

import org.gallon.rss.util.Const

class JsonResult(status: Int = 0, msg: String = Const.REQUEST_SUCCESS, data: Any?) {

    var status = status

    var msg = msg

    var data = data
}