package com.wxp.utils.tools;

@FunctionalInterface
public interface IAction<T>  {
    public abstract  void run(T param);
}
