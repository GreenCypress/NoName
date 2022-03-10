# 校友邦自动打卡

依赖 MySQL  Redis（由于是单机应用 可以自行替换为Map的形式存储）

数据库在项目下  xyb.sql

swagger地址 http://host:port/doc.html


无客户端，只有接口，可以通过swagger文档进行操作

操作步骤：
1. 注册程序账号
2. 添加校友邦账号
3. 添加自动打卡任务


ps：
如果提示校友邦账号重新登录 则执行 重新登录校友邦账号 接口即可。

支持邮件通知，发送者邮箱账号可以自行修改 在application.yml下
