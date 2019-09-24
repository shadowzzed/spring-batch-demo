# spring-batch-demo
学习并且尝试使用spring-batch分析日志写入数据库
# mess-process分支
* 批量处理数据不再是一条一条处理
* 现有的chunk为先获取全部文件列表（在chunk外） 
* 逐个文件进入 比如先进入第一个 获取全部行数 每行都存在一个string数组的单位中
* 把string[]数组传入处理器processor，一条解析出一个对象，存储在对象数组中
* 对象数组发送给writer然后逐个发送至DB