package com.yongjia.weatherapp.domain.util

// 统一类型来表示不同状态下的结果，方便后续代码中进行统一处理
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    // 操作成功，data 为操作结果的实际数据
    class Success<T>(data: T?): Resource<T>(data)

    // 操作失败，data 为空，message 为操作失败的错误信息
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}