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

            location：
                第五步：设计 LocatonTracker，使用的是 Google Play 提供的 GMS 功能，通过 Location Client 来得到当前的 Location

            第七步：在 data 层，具体实现 WeatherRepository 和 LocationTracker
                WeatherRepository -> WeatherRepositoryImpl -> getWeatherData(latitude, longititude) -> WeatherApi.getWeatherData(latitude, longititude) -> WeatherInfo
                LocationTracker -> DefaultLocationTracker -> getCurrentLocation() -> 检查权限 -> 检查 GPS -> FusedLocationProviderClent.lastLocation -> Location?

            第八步：提供依赖注入的单例实例 Module
                绑定 WeatherRepository 提供 WeatherRepositoryImpl 单例
                绑定 LocationTracker 提供 DefaultLocationTracker 单例
                提供 WeatherApi 和 FusedLocationProviderClient 单例

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
                    比如：0 -> 今天的天气信息，1 -> 明天的天气信息，2 -> 后天的天气信息，以此类推。

                第六步：设计 domain 层的接口，具体的实现，在 data 进行，这样如果更换数据源，只需要将 data 层的实现做好，其他都不用改，因为调用的是接口方法
                    WeatherRepository -> getWeatherData(latitude: Double, longititude: Double): Resouce<WeatherInfo>
                    LocationTracker -> getCurrentLocation(): Location?
                
        （3）Presentation （包括 UI，ViewModel，Composable 函数）
            第九步：确定 UI 的监听状态 State，包括三个需要监听的状态和信息：
                WeatherInfo 需要监听的信息
                isLoading 是否是正在加载状态
                error 错误信息

            第十步：构建 ViewModel：
                接收注入的 WeatherRepository 和 LocationTracker
                -> 通过 LocationTracker 得到当前的 Location
                -> 通过 Location 的 latutide 和 longititude，从 WeatherRepository 获取 WeatherInfo，并处理 isLoading 状态，或者是错误信息

            第十一步：用 @Composable 函数，来设计页面的 widget，构建监听式的 Compose UI

            第十二步：将 ViewModel 注入到 Activity 中
                -> 在 Activity 中进行初始化操作
                -> 开始 loadWeatherInfo()
                -> 将 ViewModel 中的 state 传递给 setContent() 中的 Compose 组件
                -> 当 state 中的 WeatherInfo，isLoading，error 发生变化时，Compose 发生重组，页面 UI 改变
