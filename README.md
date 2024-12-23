# Weather Application

1. MVI 架构 + Jetpack Compose UI 
2. 全 Kotlin

设计思路：

    1. 编写 WeatherType，描绘有哪些具体的天气类型

    2. 编写工具类 Resource，用来统一处理各种操作的结果，构造器中传入 data 与 message
        （1） Success -> 操作成功 其中的 data 为操作成功得到的数据，message 为空
        （2） Error -> 操作失败，其中 data 为空，message 为操作失败的失败信息

    3. 程序设计分为三层
        （1）Data（数据源，Local DataBase，Api）
            remote：
            local：
        （2）Domain （Business Logic，完全不用关心数据源是什么，是从哪里来的）
        （3）Presentation （包括 UI，ViewModel，Composable 函数）