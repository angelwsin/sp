package com.ce;

/**
 * 
 * java5新特性 instrumentation的ClassFileTransformer类，Instrumentation 的最大作用，就是类定义动态改变和操作。
 * 在 Java SE 5 及其后续版本当中，开发者可以在一个普通 Java 程序（带有 main 函数的 Java 类）运行时，通过 –javaagent
 *  参数指定一个特定的 jar 文件（包含 Instrumentation 代理）来启动 Instrumentation 的代理程序。在类的字节码载入
 * jvm前会调用ClassFileTransformer的transform方法，从而实现修改原类方法的功能，实现aop,这个的好处是不会像动态代理或者cglib技术实现aop那样会产生一个新类，也不需要原类要有接口
 */
public class ClassFileTransformerTest {

}
