package cn.xxt.kotlinapplication.domain.command

public interface Command<T> {
    fun execute(): T
}