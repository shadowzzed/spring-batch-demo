
# hbase版本代码迁移至另一个代码库spring-batch-hbase
后续更新将会在那个库，这个库就不用了

# spring-batch-demo
学习并且尝试使用spring-batch分析日志写入数据库

# job1
* demo
* 将文字变为大写后输出

# job2
* work
* 处理日志然后用正则表达式分析后逐条输出

# 分支说明
* master分支练习了如何去使用spring-batch
* mess-process 将job2的chunk重新构筑了一次，每次读取一个文本的全部信息存到数组中然后批处理
* final分支 完善job2，比mess-process分支更加成熟
* hbase分支 删除jdbc插入,使用hbase,在66W条数据时报错,存在版本问题
