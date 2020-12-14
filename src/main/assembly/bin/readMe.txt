部署到linux服务器

服务启动失败常见问题：

1、文件格式
1-1、打开文件vi 文件名
1-2、查看文件格式:set ff，若结果为fileformat=dos，则更改文件格式:set ff=unix
1-3、:wq! 强制保存

2、系统报Permission denied（当前无权限），需要设置文件权限
chmod -R 777 文件名