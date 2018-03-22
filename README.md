

# 移动商城项目总结 #


移动商城项目是我第二个做得比较大的项目,该项目系统来源于传智Java168期，十天的视频课程(想要视频的同学关注我的公众号就可以直接获取了)



通过这次的项目又再次开阔了我的视野，自己练习完我将项目的代码放到了GitHub中：[https://github.com/ZhongFuCheng3y/Mobile-commerce-](https://github.com/ZhongFuCheng3y/Mobile-commerce-)，同时在练习的过程中也用博文记录下来了，一共7篇。


- [移动商城第一篇【搭建项目环境+数据模型】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=2&sn=ba2724d8c7e9f36d0865cd6fc7cc5923&chksm=ebd74395dca0ca83aeed913a0ed3360193b31149f72e1c3c3f0aaac2b87e212337bb61f563a4#rd)
- [移动商城第二篇（品牌管理模块）【文件上传、数据校验、CRUD】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=3&sn=ce83a2f7bbfb4241f5c671bf4f07e69e&chksm=ebd74395dca0ca831c8b692ae9a0914d7eaa992632d8e503456b500ae6e22b6d1f8515057155#rd)
- [移动商城第三篇（商品管理）【查询商品、添加商品】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=4&sn=7a6d3fc199f3698147fd079ef93e842b&chksm=ebd74395dca0ca8350274062a800bf5c9db67066e2cf8e35f1e353782d23940b9e005734dcac#rd)
- [移动商城第四篇（商品管理）【添加商品续篇、商品审核和上下架、前台展示、后台筛选】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=5&sn=b66b3d58b761666051675c6a8b85de2b&chksm=ebd74395dca0ca837fd6af5284e65c6ac4bc54301c8ddeb718168e2aa01e6c924f016e5c60b7#rd)
- [移动商城第五篇（用户模块）【用户登陆、回显用户、拦截器、收货地址】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=6&sn=fb1b4041392fd3d3745dfe8b53ad31b0&chksm=ebd74395dca0ca83cc3136cfe48dc9ec4e43126d10421a23758a64989956d3352ff129f55955#rd)
- [移动商城第六篇【单品查询、静态化页面】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=7&sn=4fd357a5d656a01edaa962185bfb0001&chksm=ebd74395dca0ca83c3ddf0a32c5aef874a35663305bad05d6eecee44bfdec0a558061f694bc0#rd)
- [移动商城第七篇【购物车增删改查、提交订单】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=8&sn=6dfbdaa7ddfe9fc2feffadc5d7336b7f&chksm=ebd74395dca0ca832e6bedf0c360e484657370fd8b326758d742112fb65140834f946e599573#rd)

该项目涉及到的知识：

- SpringMVC(上传图片到服务器上，后台校验，拦截器)
- Spring
- Mybatis(逆向工程，动态SQL)
- JSP，FreeMarker
- JS组件（富文本编辑器）
- Maven
- AJAX
- Oracle
- WebService
- 对象模型设计理解



写这篇博文的目的也是为了总结一下在这次项目中学到了什么东西，除了上面这些知识点大纲，还有**很多实用的小技巧**：

- 项目可以分成后台模块、前台模块(没必要写在一起)，如果前台模块崩了，不会影响到后台模块。有必要建一个资源服务器(CDN)存放静态资源。为了做到**一次构建**、还使用了一个**parent模块把它们管理起来**
- 使用`Jersey`调用API可以很方便将图片上传到服务器上，上传服务器的路径可能会经常用到，可以在配置文件写上，全站使用
- **很有必要做前台和后台校验**，前台校验为了更好的用户体验，后台校验防止恶意提交
- 设置`readonly`和`disabled`属性都能够使前台无法修改，`readonly`能在后台获取具体的值，`disabled`属性在后台获取不了值
- 当前台页面的要提交数据种类太多时(单选框、多选框、下拉框)，由于我们页面上的数据都是通过SQL查询出来然后给页面展示的，那么我们可以换个思路：获取它们的id，判断它的录入类型(数据库字段)。**遍历集合(从数据库查询出来的数据),如果id相同，说明在页面上被选中了**
- 使用jquery复制完一段样式时，`name`属性是一样的，如果该样式中需要提交数据，那么可以**加入一个变量来进行区分**，复制完之后使用**正则表达式**将不要的东西去掉就行了。
- **使用AJAX来获取用户是否登陆是比较通用的做法(纯JS代码)**
- jquery的`trigger`方法能够用**程序的方式来响应我们的事件**
- **使用静态化页面能够减少对数据库的访问，浏览速度会大大加快**
- 两台机器调用服务的问题，应该要想到WebService来进行解决。
- 一些用户级别的数据(轻量)可以考虑存储在Cookie中。**Cookie存储的是JSON值**，可以通过JsonArray来对JSON和Java对象之间的转换
- **如果我们的数据是在后台传过去或者转换而成的，在前台上并没有做什么改变的话。那么我们可以直接使用后台的数据来对其进行操作**



如果SSM基础比较薄弱的同学可以先看我系列文章：


**Mybatis：**

- [Mybatis【入门】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483937&idx=2&sn=28c7827639bb6ac0296746c4c4343c59&chksm=ebd74320dca0ca36b763b3975665fc38a7e921f9ecaef1aaea3a7c757063a29222cd00b3d3b6#rd)
- [Mybatis【配置文件】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483937&idx=3&sn=977f8e1eeb0d4e46bab6d6140e856c83&chksm=ebd74320dca0ca3648e351f2f3d5196842e64d2e8ba14722ec2548da46df7e88832765e67f87#rd)
- [Mybatis【关联映射】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483937&idx=4&sn=ef076c8a0eb26ae19a969a2c0238d850&chksm=ebd74320dca0ca36d49663e30d7d065340268a3b8d49e54949fef07b041fe7836c5689febdf3#rd)
- [Mybatis【缓存、代理、逆向工程】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483937&idx=5&sn=4a049d7461b67c4135183db09ec97bcb&chksm=ebd74320dca0ca3691081597ac9db2447d51250d7aa819009231760977dd932b43a116fe44ba#rd)
- [Mybatis【与Spring整合】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483937&idx=6&sn=13a9c31fe0e43d0986d4e2186b442202&chksm=ebd74320dca0ca3614eefd6dab6c4a0b965262e72f352ec48e5e19992a6c7a1d9cf7ca802103#rd)
- [Mybatis面试题](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483937&idx=1&sn=85727e94ad3d1af1ef99cfefa420520c&chksm=ebd74320dca0ca367f869427202eb029bf3cf8dd4dc71dae26d925aaaabc2c8ea704735b3b87#rd)

**Spring：**

- [Spring入门这一篇就够了](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483942&idx=1&sn=f71e1adeeaea3430dd989ef47cf9a0b3&chksm=ebd74327dca0ca3141c8636e95d41629843d2623d82be799cf72701fb02a665763140b480aec#rd)
- [Spring【依赖注入】就是这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483946&idx=1&sn=bb21dfd83cf51214b2789c9ae214410f&chksm=ebd7432bdca0ca3ded6ad9b50128d29267f1204bf5722e5a0501a1d38af995c1ee8e37ae27e7#rd)
- [Spring【AOP模块】就这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483954&idx=1&sn=b34e385ed716edf6f58998ec329f9867&chksm=ebd74333dca0ca257a77c02ab458300ef982adff3cf37eb6d8d2f985f11df5cc07ef17f659d4#rd)
- [Spring【DAO模块】知识要点](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483965&idx=1&sn=2cd6c1530e3f81ca5ad35335755ed287&chksm=ebd7433cdca0ca2a70cb8419306eb9b3ccaa45b524ddc5ea549bf88cf017d6e5c63c45f62c6e#rd)
- [SpringMVC入门就这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483973&idx=1&sn=dda2252f37e5eb6db90db636a65c40bf&chksm=ebd74344dca0ca52d671fc0fa072bcc80892bfb5801ceaab6a4754036d246f5bef960c1840bd#rd)
- [SpringMVC【开发Controller】详解](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483973&idx=2&sn=483265ffa9087ca956ec2d637119a5f8&chksm=ebd74344dca0ca5298b894fbb706c26ee942a423e858e27679f06df4b83899e1a97cc9d5eb97#rd)
- [SpringMVC【参数绑定、数据回显、文件上传】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484008&idx=2&sn=44e15b795eda5e1f112bf663cc146bf7&chksm=ebd74369dca0ca7fedadb2835d80896df76fa5279a9db38abccceb2b25c9ee95d549cc9010ed#rd)
- [SpringMVC【校验器、统一处理异常、RESTful、拦截器】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484008&idx=3&sn=5719448dab8d9c3d7e8c91261db4c1a2&chksm=ebd74369dca0ca7f51637c13b09579572d3e2960b5105decda4ecf7878f7b10346669f26e221#rd)
- [SpringBoot就是这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484031&idx=2&sn=c586cd21312c720a4a45435ea18dc30a&chksm=ebd7437edca0ca68a8bdf98b962474b53e68372adc9e059964b55de60a0056aa204348f6206b#rd)
- [SpringData JPA就是这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484031&idx=3&sn=c8b5a241b97cb502f93d2e216048fc8f&chksm=ebd7437edca0ca68d882ce50836ec87cb9cb4f337760fac5f6bab2368692952049c59902c135#rd)


**SSM整合与阅读项目：**

- [SSM【史上最详细整合】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483954&idx=3&sn=d4dd684f759320438c6a0c11866f1c6c&chksm=ebd74333dca0ca2550b0db08e48987aec52d5d85a83ff29068cd2b52d9d6fe470dd0fbbda688#rd)
- [Java高并发秒杀系统【观后总结】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483965&idx=2&sn=3714c57815d6a25f1a3060d592bcf7a8&chksm=ebd7433cdca0ca2acb3c46454908d078f13fe80752e82e63fc4641100839fe56833752887bea#rd)
- [阅读SSM项目之scm](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247483965&idx=3&sn=55435bb94e6493fd838414d18167dfbb&chksm=ebd7433cdca0ca2a01a5ae989938521397c507046e8b268398ba4db7472c19c3118e6b3d523e#rd)



> 如果文章有错的地方欢迎指正，大家互相交流。习惯在微信看技术文章，想要获取更多的Java资源的同学，可以**关注微信公众号:Java3y**
