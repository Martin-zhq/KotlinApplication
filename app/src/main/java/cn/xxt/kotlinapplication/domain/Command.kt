package cn.xxt.kotlinapplication.domain

public interface Command<T> {
    fun execute(): T
}