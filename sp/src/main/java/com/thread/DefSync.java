package com.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class DefSync extends AbstractQueuedSynchronizer{

    
    
       public DefSync(int stat) {
           setState(stat);
    }
       
       @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }
}
