package com.wxp.utils.copy;

@FunctionalInterface
public interface IAction<T>  {
    void run(T param);
}
