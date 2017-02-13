package com.thread;


public class DefThread extends Thread{
    
    @Override
    public void run() {
        
        System.out.println("");
    }
    
    //线程创建时的初始化动作
    /**
     * parent 父线程  为当前线程currentThread()
     * group  线程组 默认同父线程
     * isDaemon 是否守护线程  默认同父线程
     * priority 默认同父线程
     * contextClassLoader 默认同父线程
     * inheritableThreadLocals   是否存在InheritableThreadLocal 默认同父线程
     */
    public static void main(String[] args) {
        //main线程的线程组            线程组成树形结构
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        DefThread thread = new DefThread();
        thread.start();
    }

    
   
}
