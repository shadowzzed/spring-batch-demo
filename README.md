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
* final分支 完善job2，比job2更加成熟
