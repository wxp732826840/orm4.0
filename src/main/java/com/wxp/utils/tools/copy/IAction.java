package com.wxp.utils.tools.copy;

@FunctionalInterface
public interface IAction<T>  {
    public abstract  void run(T param);
}
