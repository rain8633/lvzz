#### 在做毕设之前 打算写一个旅游自助管理系统练练手  前端页面以及布局使用的scss文件参考之前的音乐播放器页面，在此基础上修改了一些css属性
#### 本网站分为用户端分和管理员端，用户端分为Web端和移动端，用户端完成注册登录，浏览景点信息，收藏，评分，发表评论，发表游记，查看旅游路线，购票；管理员端完成用户管理，游记管理，景点信息管理，购票管理以及收藏统计;
#### 基于springboot+vue的旅游自助管理平台
### 用户端
#### 登录注册

 注册时对密码进行加盐加密，登录时使用Shiro进行授权认证

[![pSWQdUA.png](https://s1.ax1x.com/2023/02/09/pSWQdUA.png)](https://imgse.com/i/pSWQdUA)

![](https://pic.imgdb.cn/item/63e4a8b14757feff3324cbdf.png)

![](https://pic.imgdb.cn/item/63e4a8b14757feff3324cbf4.png)

#### 客户端首页

![](https://pic.imgdb.cn/item/63e4acf44757feff332cb9e6.png)

#### 旅游景点页

##### 实现景区级别分类

![](https://pic.imgdb.cn/item/63e4acf44757feff332cba73.png)

##### 右上角搜索框可以实现模糊查询景点

![](https://pic.imgdb.cn/item/63e4e5a64757feff338ed605.png)

#### 景点详情页

实现景点收藏 景区评分 景区评价 前往购票功能，其中评论功能简单实现Redis缓存

![](https://pic.imgdb.cn/item/63e4acf44757feff332cba00.png)

#### 购票页面

前往支付时遇到一些困难  跳转到收银台界面出错（之前做课设的时候没问题的，把代码copy过来实现不了），看支付宝官网的介绍，采用弹窗弹出支付二维码的方式进行支付，二维码出不来，参考官网介绍，使用WebSocket技术进行通信，出来后支付失败，通过查资料发现需要进行内网穿透，使用NATAPP进行内网穿透

![](https://pic.imgdb.cn/item/63e4e6194757feff338f87e4.png)

![](https://pic.imgdb.cn/item/63e4e8a14757feff3393eb6a.png)

#### 旅游路线展示

集成百度地图API进行路线导航

![](https://pic.imgdb.cn/item/63e4acf44757feff332cbad8.png)

#### 旅游日记

集成Summernote富文本编辑器进行旅游日记的编写

##### 添加日记

![](https://pic.imgdb.cn/item/63e4acf44757feff332cbb15.png)

##### 查询全部日记以及模糊搜索

![](https://pic.imgdb.cn/item/63e4e5a64757feff338ed5c4.png)

##### 查看日记详情

![](https://pic.imgdb.cn/item/63e4e5a64757feff338ed5ca.png)



#### 我的收藏

![](https://pic.imgdb.cn/item/63e4e5a64757feff338ed5e8.png)

#### 发表评论

![](https://pic.imgdb.cn/item/63e4e6194757feff338f8801.png)

#### 个人信息修改

![](https://pic.imgdb.cn/item/63e4e6194757feff338f87f5.png)



#### 查看订单列表

![](https://pic.imgdb.cn/item/63e4e6194757feff338f87c8.png)

### 移动端

移动端使用Vue+Vant框架开发

![](https://pic.imgdb.cn/item/63e5c7244757feff33ab7b17.png)

![](https://pic.imgdb.cn/item/63e5c7244757feff33ab7b25.png)

![](https://pic.imgdb.cn/item/63e5c7244757feff33ab7b41.png)

![](https://pic.imgdb.cn/item/63e5c7244757feff33ab7b5b.png)

![](https://pic.imgdb.cn/item/63e5c7244757feff33ab7be2.png)

![](https://pic.imgdb.cn/item/63e5c76f4757feff33abdda7.png)

![](https://pic.imgdb.cn/item/63e5c76f4757feff33abddb6.png)

![](https://pic.imgdb.cn/item/63e5c76f4757feff33abddcc.png)

![](https://pic.imgdb.cn/item/63e5c7704757feff33abdeda.png)

![](https://pic.imgdb.cn/item/63e5c7704757feff33abdef1.png)

![](https://pic.imgdb.cn/item/63e5c7a34757feff33ac2d23.png)

### 管理员端

#### 收藏统计

使用Echarts图表对用户对景点的收藏进行统计

![](https://pic.imgdb.cn/item/63e4e6194757feff338f880a.png)

#### 日记管理

![](https://pic.imgdb.cn/item/63e4e8a14757feff3393eadb.png)

#### 景点管理

![](https://pic.imgdb.cn/item/63e4e8a14757feff3393eae6.png)

##### 景点信息编辑

![](https://pic.imgdb.cn/item/63e4e8a14757feff3393eb18.png)
