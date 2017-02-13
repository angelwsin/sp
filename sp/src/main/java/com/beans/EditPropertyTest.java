package com.beans;

/**
 *  java.beans.PropertyEditor(属性编辑器)
 *  java.beans.PropertyEditor的从字义来看是一个属性编辑器，但总觉得它的作用更像一个转换器－－从字符串转换为类对象的属性。

java.beans.PropertyEditor接口定义的方法有好几个，但是最重要为下面两个：

 void setValue(Object value)

 void setAsText(String text) throws java.lang.IllegalArgumentException;

一般地，我们要使用PropertyEditor时，并不直接实现此接口，而是通过继承实现此接口的java.beans.PropertyEditorSupport来简化我们的工作，
在子类覆盖setAsText方法就可以了，setValue方法一般不直接使用，在setAsText方法中将字符串进行转换并产生目标对象以后，由调setAsText调用setValue来把目标对象注入到编辑器中。
当然，你可用覆盖更多的方法来满足你的特殊要求。JavaBean的类和接口，被大部分spring包使用，可以从spring中学习更成熟的JavaBean使用方法。
 * 
 */

public class EditPropertyTest {

}
