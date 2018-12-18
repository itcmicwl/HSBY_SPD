# spdherp_ui

> 国药器械spd内网系统

## Build Setup

``` bash
# install dependencies
1.安装node.js,vscode
2.安装webpack打包工具
3.打开前端项目根目录（此文件存在的目录）
4.目录结构及说明

├─build                          #打包设置
│      
├─config                        #运行及打包生成环境配置
│      
├─dist                          #打包文件目录
│              
├─src                           #项目源码目录
│  │  App.vue
│  │  main.js                   #入口文件
│  │  
│  ├─assets                     #静态资源
│  │  ├─images                  #图片
│  │  │      
│  │  └─styles                  #样式目录
│  │          
│  ├─common                     #公共库
│  │  │  directive.js           #全局指令
│  │  │  filter.js              #全局过滤器
│  │  │  
│  │  ├─js                      #
│  │  │      
│  │  └─third                   #第三方插件目录
│  │                      
│  ├─components                 #业务组件
│  │          
│  ├─config                     #项目配置
│  │      appConfigs.js
│  │      axiosConfig.js
│  │      baseRoutes.js         #读取并设置路由
│  │      menu.js               #读取并设置菜单
│  │      mockData.js         
│  │      refresh.js
│  │      theme.js
│  │      
│  ├─views                      #页面目录
│  │              
│  └─vuex                       #状态保持
│          
└─static
        .gitkeep
        


#安装依赖
npm install
#运行开发环境
npm run dev
#生产环境打包
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
(\w+) 
${batch}.$1 as s_b_$1