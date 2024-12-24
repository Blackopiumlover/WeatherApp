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
                第一步：设计 WeatherApi，从服务器获取 Raw 数据，以 Json response 的内容，来设计 Data Transfer Object
                第二步：得到 WeatherDto
                第四步：设计 Mapper，将 WeatherDto 转化为 WeatherInfo，Mapper 应该和 Raw 数据一起放在数据层，
                这样可以保证 A 数据通过 A Mapper 转变为 Domain 层中的 WeatherInfo,
                B 数据通过 B Mapper 转变为 Domain 层中的 WeatherInfo，
                而数据层之上的 Domain 层完全不用关心 Raw 数据到底是怎么获得的，有哪些细节，只要用 Mapper 转变后得到的结果，即 WeatherInfo 继续后面的操作即可

        （2）Domain （Business Logic，完全不用关心数据源是什么，是从哪里来的）
                第三步：设计 WeatherInfo，来对应 data 层的 WeatherDto，其完全不用关注 WeatherDto 到底是是什么，到底是从哪里来的等等细节，只在意其能够被转换成 WeatherInfo 以供上层使用
                    WeatherInfo 包括两个信息：
                        当前的天气状况 currentWeatherData
                        一次性可以获取的，未来七天的天气状况 weatherDataPerDay
                    WeatherData 包括：
                        日期 time
                        温度（摄氏度）temperatureCelsius
                        气压：pressure
                        风速：windSpeed
                        湿度：humidity
                        天气概况：WeatherType
                    这样，WeatherInfo 就包括了当日天气信息，以及未来七天的天气信息
                    比如：0 -> 今天的天气信息，1 -> 明天的天气信息，2 -> 后天的天气信息，一次类推
        （3）Presentation （包括 UI，ViewModel，Composable 函数）