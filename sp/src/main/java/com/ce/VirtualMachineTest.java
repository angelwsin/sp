package com.ce;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;



/**
 * 
 * 
 *VirtualMachine
 * @version $Id: VirtualMachineTest.java, 
 */
public class VirtualMachineTest {
    
    
    public static void main(String[] args) {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(0).displayName());
        }
        
        /*System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(VirtualMachineTest.class.getClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader());*/
    }

}
