一：项目架构
   1.latte_annotations 项目java lib 注解组件
   2.latte_compiler 项目java lib 编译组件
   3.latte_core 项目android lib 核心组件
   4.latte_ec 项目 android lib 电商业务组件


二：依赖关系
    1.主工程---> latte_compiler   latte_ec
    2.latte_ec ---> latte_core
    3.latte_core ---> latte_annotations