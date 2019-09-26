# spring-batch-demo
学习并且尝试使用spring-batch分析日志写入数据库
# mess-process分支
* 批量处理数据不再是一条一条处理
* 现有的chunk为先获取全部文件列表（在chunk外） 
* 逐个文件进入 比如先进入第一个 获取全部行数 每行都存在一个string数组的单位中
* 把string[]数组传入处理器processor，一条解析出一个对象，存储在对象数组中
* 对象数组发送给writer然后逐个发送至DB

# final分支
* 此分支解决的是从log日志中读取文件然后用正则表达式逐行解析成对象（一行信息就是一个对象）最后插入数据库
* 800MB的log本地用时2min，产生100W的数据量

# notes
* 作为简易的spring-batch程序，只需要配置chunk中的三个模块，reader，writer和processor
* reader即读取器，也就是整个批处理chunk的开头，需要从这个部分导入文件（当然也可以不导入，批处理其他类型的问题）**我的理解是这个部分就是批处理流程的开头，也就是spring-batch给你定义的第一步**，想要把自己的reader加到chunk中需要实现`ItemWriter<T>`.
* processor 处理器，是这个chunk的第二步，你从第一步获取的数据在这里进行加工,也需要实现一个接口`ItemProcessor<T1,T2>`,其中T1表示从reader获取的数据类型，T2表示处理过后的数据类型，可以不一致。我的加工内容是用正则表达式提取对象。
* writer 写入器，chunk的第三步，同样也需要实现一个接口`ItemWriter<T>`，由于这个程序是处理大数据量的程序，所以在这里我使用了Spring-JdbcTemplate的batchupdate来写入。

# 可能会踩到的坑
* `spring.batch.initialize-schema=ALWAYS`这个需要加上否则spring-batch程序无法运行（当然如果你自己在数据库中创建了这些表也可以）
* 对于reader、writer的理解有偏差，网上很多教程都是读取和输出是同一类型，导致你不知道如何自己去写一个reader和writer
* 在spring-batch中的默认配置是开机就自动执行，也就是类会直接被加载进spring容器，这时候会导致自动装配==Autowired==注入为null，此处我的解决策略为自动装配在构造器上，详细的可以参照此分支的writer类
* spring-jdbctemplate的batchupdate性能差，需要在数据库连接的参数上加`rewriteBatchedStatements=true`这样才是真正开启了批处理模式
