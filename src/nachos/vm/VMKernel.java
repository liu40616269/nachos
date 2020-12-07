package nachos.vm;

import nachos.machine.Machine;
import nachos.machine.OpenFile;
import nachos.machine.TranslationEntry;
import nachos.userprog.UserKernel;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * A kernel that can support multiple demand-paging user processes.
 */
public class VMKernel extends UserKernel {

    private String vmFileName;

    /**
     * Allocate a new VM kernel.
     */
    public VMKernel() {
        super();
    }

    /**
     * Initialize this kernel.
     */
    public void initialize(String[] args) {
        super.initialize(args);
        vmFileName = "." + Machine.networkLink().getLinkAddress() + ".vm";
        vmFile = Machine.stubFileSystem().open(vmFileName, true);
    }

    /**
     * Test this kernel.
     */
    public void selfTest() {
        super.selfTest();
    }

    /**
     * Start running user programs.
     */
    public void run() {
        super.run();
    }

    /**
     * Terminate this kernel. Never returns.
     */
    public void terminate() {
        vmFile.close();
        Machine.stubFileSystem().remove(vmFileName);
        super.terminate();
    }

    // dummy variables to make javac smarter
    private static VMProcess dummy1 = null;

    private static final char dbgVM = 'v';

    // 虚拟内存文件
    public static OpenFile vmFile;
    // 虚拟内存的回收块
    public static final LinkedList<Integer> freeVMBlock = new LinkedList<>();
    // 虚拟内存内部块总量
    public static int vmBlockLength = 0;
    // 进程虚拟页对应的虚拟内存块
    public static final Hashtable<PVPN, Integer> vmMap = new Hashtable<>();

    public static final Hashtable<PVPN, TranslationEntry> invPage = new Hashtable<>();

    public static class PVPN {
        public int pid;
        public int vpn;

        // generated by idea
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PVPN pvpn = (PVPN) o;

            if (pid != pvpn.pid) return false;
            return vpn == pvpn.vpn;
        }

        // generated by idea
        @Override
        public int hashCode() {
            int result = pid;
            result = 31 * result + vpn;
            return result;
        }
    }
}
