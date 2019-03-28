package com.lvlivejp.mybatisdemo.dynamicproxy;

public class MyFirstInterfaceImpl implements MyFirstInterface {
    @Override
    public String dosomething(String par) {
        return par+"_Impl";
    }
}
