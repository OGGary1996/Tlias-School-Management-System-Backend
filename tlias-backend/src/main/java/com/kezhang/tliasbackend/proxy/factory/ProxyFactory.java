//package com.kezhang.tliasbackend.proxy.factory;
//
//import com.kezhang.tliasbackend.proxy.invocationHandler.TimeInvocationHandler;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Proxy;
//
//@Component
//public class ProxyFactory {
//    /*
//    * 流程：
//    *  1. 获取目标对象的类加载器
//    *  2. 获取目标对象的接口
//    *  3. 如果目标对象没有实现任何接口，抛出异常
//    *  4. 使用Proxy.newProxyInstance创建代理对象
//    *  5. 进行类型转换，由Object转换为目标对象的类型
//    *
//    * 注意：Proxy.newProxyInstance方法
//    *  1. 第一个参数是类加载器，应该使用目标对象的接口的类加载器，而不是目标对象的类加载器
//    *  2. 第二个参数是目标对象的接口的数组，类型是Class<?>[]
//    *
//    * 原始写法：target为接口多态，类型为接口，但是实际上是实现类的实例，
//    *  return (T) Proxy.newProxyInstance(
//    *         target.getClass().getClassLoader(), // 实际上使用接口类型获取类加载器
//    *         new Class<?>[]{target.class}, // 实际上使用接口类型的数组数组
//    *         new TimeInvocationHandler(target)); // 传递的是实现类的实例
//    *  但是在Spring中，如果在调用工厂方法时，直接传递接 口类型的实现类，会引起Spring的循环依赖问题（创建接口的代理的同时也在传递接口的实现类，注入混乱）
//    *  所以，在调用工厂方法时，传递实现类的实例；
//    *  由于又需要用到接口的类型的类加载器和接口类型的接口数组，所以额外传递了接口类型和实现类的实例
//    * */
//
//    public <T,S extends T> T createTimeProxy (Class<T> interfaceType,S target) {
//        ClassLoader classLoader = interfaceType.getClassLoader();
//        Class<?>[] interfaces = new Class<?>[]{interfaceType};
//
//        Object proxy = Proxy.newProxyInstance(
//                classLoader,
//                interfaces,
//                new TimeInvocationHandler(target)
//        );
//
//        // 使用接口类型进行安全的类型转换
//        return interfaceType.cast(proxy);
//    }
//}
