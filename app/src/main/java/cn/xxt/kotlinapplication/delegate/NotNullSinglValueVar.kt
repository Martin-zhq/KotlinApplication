package cn.xxt.kotlinapplication.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

 class NotNullSinglValueVar<T>(): ReadWriteProperty<Any?, T> {
    private var value: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw  IllegalStateException("${property.name}" + "not initialized")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}