package com.lvlivejp.mybatisdemo.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyFirstInterfaceProxy implements InvocationHandler {

    private Object target;

    public MyFirstInterfaceProxy() {
    }

    public MyFirstInterfaceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行代理对象前"+method.getName());
        Object o;
        if(target == null){
            o = "lv"+args[0];
        }else {
            o =method.invoke(target,args);
        }
        System.out.println("执行代理对象后");
        return o;
    }

    public static void main(String[] args){
        // 纯接口动态代理
        Class[] classes = {MyFirstInterface.class};
        MyFirstInterface myFirstInterface = (MyFirstInterface) Proxy.newProxyInstance(MyFirstInterface.class.getClassLoader(), classes,
                new MyFirstInterfaceProxy());
        System.out.println(myFirstInterface.dosomething("1111"));

        //接口实现类动态代理
        MyFirstInterface myFirstInterfaceImpl = new MyFirstInterfaceImpl();
//        classes = new Class[]{myFirstInterfaceImpl.getClass()};
        myFirstInterface = (MyFirstInterface) Proxy.newProxyInstance(myFirstInterfaceImpl.getClass().getClassLoader(), classes,
                new MyFirstInterfaceProxy(myFirstInterfaceImpl));
        System.out.println(myFirstInterface.dosomething("1111"));

        //简单java类不能实现代理，且Class<?>[] interfaces参数必须是接口，不能是实现类
        MyFirstClass myFirstClass = new MyFirstClass();
        myFirstClass = (MyFirstClass) Proxy.newProxyInstance(MyFirstClass.class.getClassLoader(), new Class[]{MyFirstClass.class},
                new MyFirstInterfaceProxy(myFirstClass));
        System.out.println(myFirstClass.dosomething("1111"));
    }
}
