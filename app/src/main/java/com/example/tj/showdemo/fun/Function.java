package com.example.tj.showdemo.fun;

import java.util.HashMap;

/**
 * author: zhulunjun
 * time:   2017/5/16
 * about: 封装回调，处理activity和fragment之间的通信
 *        优点：activity中不用实现多个接口来监听fragment的回调
 *        适用于一个activity对应多个fragment之间的通信
 */

public class Function {
    /**无参数无返回值*/
    private static HashMap<String,FunctionNotParamNotResult> mFunctionNPNRMap;
    /**无参数有返回值*/
    private static HashMap<String,FunctionNotParamWithResult> mFunctionNPWRMap;
    /**有参数无返回值*/
    private static HashMap<String,FunctionWithParamNotResult> mFunctionWPNRMap;
    /**有参数有返回值*/
    private static HashMap<String,FunctionWithParamWithResult> mFunctionWPWRMap;


    public static class Functions{
        public String name;//公有属性

        public Functions(String name) {
            this.name = name;
        }
    }

    /**
     * 无参数无返回值
     * no param
     * with result
     */
    public static abstract class FunctionNotParamNotResult extends Functions{
        public FunctionNotParamNotResult(String name) {
            super(name);
        }
        public abstract void function();

    }

    //生产FunctionNotParamNotResult
    public Function addFunction(FunctionNotParamNotResult function){
        if(null==function){
            return this;
        }
        if(null==mFunctionNPNRMap){
            mFunctionNPNRMap=new HashMap<>();
        }
        mFunctionNPNRMap.put(function.name, function);
        return this;
    }

    //消费FunctionNotParamNotResult
    public void invateFunction(String name) throws NotFuncException {
        if(null==mFunctionNPNRMap){
            return;
        }
        FunctionNotParamNotResult function=mFunctionNPNRMap.get(name);

        if(null!=function){
            function.function();
        }else{
            throw new NotFuncException("没有找到FunctionNotParamNotResult");
        }

    }

    /**
     * 无参数有返回值
     * no param
     * with result
     */
    public static abstract class FunctionNotParamWithResult<Result> extends Functions{
        public FunctionNotParamWithResult(String name) {
            super(name);
        }
        public abstract Result function();
    }

    //生产FunctionNotParamNotResult
    public Function addFunction(FunctionNotParamWithResult function){
        if(null==function){
            return this;
        }
        if(null==mFunctionNPWRMap){
            mFunctionNPWRMap=new HashMap<>();
        }
        mFunctionNPWRMap.put(function.name, function);
        return this;
    }
    //消费FunctionNotParamNotResult
    //Result是返回值泛型
    public <Result> Result invokeFunction(String name,Class<Result> result) throws NotFuncException {
        if(null==mFunctionNPWRMap){
            throw new NotFuncException("没有找到FunctionNotParamWithResultMap");
        }
        FunctionNotParamWithResult function=mFunctionNPWRMap.get(name);

        if(null!=function){
            if(null!=result){
                return result.cast(function.function());
            }else {
                return (Result) function.function();
            }
        }else{
            throw new NotFuncException("没有找到FunctionNotParamWhitResult");
        }

    }


    /**
     * 有参数无返回值
     * with param
     * no result
     */
    public static abstract class FunctionWithParamNotResult<Param> extends Functions{
        public FunctionWithParamNotResult(String name) {
            super(name);
        }
        public abstract void function(Param param);
    }

    //生产FunctionNotParamWithResult
    public Function addFunction(FunctionWithParamNotResult function){
        if(null==function){
            return this;
        }
        if(null==mFunctionWPNRMap){
            mFunctionWPNRMap=new HashMap<>();
        }
        mFunctionWPNRMap.put(function.name, function);
        return this;
    }

    //消费FunctionWithParamNotResult
    public <Param> void invokeFunction(String name,Param param) throws NotFuncException {
        if(null==mFunctionWPNRMap){
            throw new NotFuncException("没有找到FunctionWithParamNotResultMap");
        }
        FunctionWithParamNotResult function=mFunctionWPNRMap.get(name);

        if(null!=function){
            if(null!=param){
                function.function(param);
            }else{
                throw new NotFuncException("没有找到Param");
            }
        }else{
            throw new NotFuncException("没有找到FunctionWhitParamNotResult");
        }

    }

    /**
     * 有参数有返回值
     * with param
     * with result
     */
    public static abstract class FunctionWithParamWithResult<Param,Result> extends Functions{
        public FunctionWithParamWithResult(String name) {
            super(name);
        }
        public abstract Result function(Param param);
    }

    //生产FunctionWithParamWithResult
    public Function addFunction(FunctionWithParamWithResult function){
        if(null==function){
            return this;
        }
        if(null==mFunctionWPWRMap){
            mFunctionWPWRMap=new HashMap<>();
        }
        mFunctionWPWRMap.put(function.name, function);
        return this;
    }

    //消费FunctionWithParamNotResult
    public <Param,Result> Result invokeFunction(String name,Param param,Class<Result> result) throws NotFuncException {
        if(null==mFunctionWPWRMap){
            throw new NotFuncException("没有找到FunctionWithParamWithResultMap");
        }
        FunctionWithParamWithResult function=mFunctionWPWRMap.get(name);

        if(null!=function){
            if(null != param && null != result){
                return result.cast(function.function(param));
            }else{
                throw new NotFuncException("没有找到Param 或者 Result");
            }
        }else{
            throw new NotFuncException("没有找到FunctionWhitParamWithResult");
        }

    }

}
