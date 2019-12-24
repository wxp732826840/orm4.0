package com.wxp.utils.tools.copy;

@FunctionalInterface
public interface IAction<T>  {
    void run(T param);
}
