<h1>探索高并发场景下系统的高可用，高性能，最终一致性实践</h1>
<p>项目场景：家庭或企业用电，年度，季度，月度定期采集耗电量进行计费，邮件通知用户缴费，用户再进行缴费</p>
<p>项目入口：149.28.152.91:7080</p>
  
<h1>并发场景：</h1>
<h3>1.大量不同终端，同一短时间内(分钟级别内，qps2000+）访问系统上传读数</h3>
<p>Kafka主题发布订阅异步，解耦，削峰限流，RedisBitMap，缓存，Mysql，事务，线程池，高可用，高性能，最终一致性</p>
<h3>2.同一用户账号，同一时间内，通过不同终端访问系统进行缴费，防止重复支付</h3>
<p>Redisson分布式锁RLock，RedissBitMap，缓存，事务，Nginx，邮件应用，日志，打包部署，Ubuntu</p>

<h1>技术栈与机器：</h1>
<h1>Springboot3.2.5，Docker，Kafka，Redis，Redisson，Mysql8，Mybatis，Nginx，Jmeter，IntelijIDEA2023.3.4，jdk_21.0.2，Windows11，Ubuntu23.10 x64(2G，2核，vultr)</h1>
<h3>项目已给出Mysql创建文件，一定要jdk_21.0.2配置好相关环境(我是用jdk_21.0.2开发的，jdk_21.0.3上部署有问题，大佬赐教)，项目即可运行</h3>

<p>辅佐：Everything，Bandizip，Xterminal，SublineText，MysqlWorkbench，Git(Github)，PostmanAgent，DeepL，MicrosoftEdge，抖音TikTok，BiliBili，Google ,Clash...，阿里云，腾讯云，华为云，百度云，知乎CSDN博客园等各种论坛...酷狗，网易云，Pornhub等各种视频网站...英雄联盟，Epic...</p>
