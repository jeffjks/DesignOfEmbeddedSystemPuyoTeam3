cmd_/home/wlgh325/Workspace/driver_modules/led/led.mod.o := /home/wlgh325/Workspace/android_build/prebuilts/gcc/linux-x86/aarch64/aarch64-linux-android-4.9/bin/aarch64-linux-android-gcc -Wp,-MD,/home/wlgh325/Workspace/driver_modules/led/.led.mod.o.d  -nostdinc -isystem /home/wlgh325/Workspace/android_build/prebuilts/gcc/linux-x86/aarch64/aarch64-linux-android-4.9/bin/../lib/gcc/aarch64-linux-android/4.9.x/include -I/home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include -I./arch/arm64/include/generated/uapi -I./arch/arm64/include/generated  -I/home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include -I./include -I/home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi -I/home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi -I./include/generated/uapi -include /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kconfig.h -I/home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx//home/wlgh325/Workspace/driver_modules/led -I/home/wlgh325/Workspace/driver_modules/led -D__KERNEL__ -mlittle-endian -Wall -Wundef -Wstrict-prototypes -Wno-trigraphs -fno-strict-aliasing -fno-common -Werror-implicit-function-declaration -Wno-format-security -std=gnu89 -fno-PIE -mgeneral-regs-only -fno-asynchronous-unwind-tables -fno-pic -fno-delete-null-pointer-checks -O2 --param=allow-store-data-races=0 -DCC_HAVE_ASM_GOTO -Wframe-larger-than=2048 -fstack-protector-strong -Wno-unused-but-set-variable -fno-omit-frame-pointer -fno-optimize-sibling-calls -fno-var-tracking-assignments -Wdeclaration-after-statement -Wno-pointer-sign -fno-strict-overflow -fno-stack-check -fconserve-stack -Werror=implicit-int -Werror=strict-prototypes -Werror=date-time  -DKBUILD_BASENAME='"led.mod"'  -DKBUILD_MODNAME='"led"' -DMODULE -mcmodel=large  -c -o /home/wlgh325/Workspace/driver_modules/led/led.mod.o /home/wlgh325/Workspace/driver_modules/led/led.mod.c

source_/home/wlgh325/Workspace/driver_modules/led/led.mod.o := /home/wlgh325/Workspace/driver_modules/led/led.mod.c

deps_/home/wlgh325/Workspace/driver_modules/led/led.mod.o := \
    $(wildcard include/config/module/unload.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/module.h \
    $(wildcard include/config/modules.h) \
    $(wildcard include/config/sysfs.h) \
    $(wildcard include/config/modules/tree/lookup.h) \
    $(wildcard include/config/livepatch.h) \
    $(wildcard include/config/unused/symbols.h) \
    $(wildcard include/config/module/sig.h) \
    $(wildcard include/config/generic/bug.h) \
    $(wildcard include/config/kallsyms.h) \
    $(wildcard include/config/smp.h) \
    $(wildcard include/config/tracepoints.h) \
    $(wildcard include/config/tracing.h) \
    $(wildcard include/config/event/tracing.h) \
    $(wildcard include/config/ftrace/mcount/record.h) \
    $(wildcard include/config/constructors.h) \
    $(wildcard include/config/debug/set/module/ronx.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/list.h \
    $(wildcard include/config/debug/list.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/types.h \
    $(wildcard include/config/have/uid16.h) \
    $(wildcard include/config/uid16.h) \
    $(wildcard include/config/lbdaf.h) \
    $(wildcard include/config/arch/dma/addr/t/64bit.h) \
    $(wildcard include/config/phys/addr/t/64bit.h) \
    $(wildcard include/config/64bit.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/types.h \
  arch/arm64/include/generated/asm/types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/int-ll64.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/int-ll64.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/bitsperlong.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitsperlong.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/bitsperlong.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/posix_types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/stddef.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/stddef.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/compiler.h \
    $(wildcard include/config/sparse/rcu/pointer.h) \
    $(wildcard include/config/trace/branch/profiling.h) \
    $(wildcard include/config/profile/all/branches.h) \
    $(wildcard include/config/kasan.h) \
    $(wildcard include/config/enable/must/check.h) \
    $(wildcard include/config/enable/warn/deprecated.h) \
    $(wildcard include/config/kprobes.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/compiler-gcc.h \
    $(wildcard include/config/arch/supports/optimized/inlining.h) \
    $(wildcard include/config/optimize/inlining.h) \
    $(wildcard include/config/gcov/kernel.h) \
    $(wildcard include/config/arch/use/builtin/bswap.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/posix_types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/posix_types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/poison.h \
    $(wildcard include/config/illegal/pointer/value.h) \
    $(wildcard include/config/page/poisoning/zero.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/const.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kernel.h \
    $(wildcard include/config/preempt/voluntary.h) \
    $(wildcard include/config/debug/atomic/sleep.h) \
    $(wildcard include/config/mmu.h) \
    $(wildcard include/config/prove/locking.h) \
    $(wildcard include/config/panic/timeout.h) \
  /home/wlgh325/Workspace/android_build/prebuilts/gcc/linux-x86/aarch64/aarch64-linux-android-4.9/lib/gcc/aarch64-linux-android/4.9.x/include/stdarg.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/linkage.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/stringify.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/export.h \
    $(wildcard include/config/have/underscore/symbol/prefix.h) \
    $(wildcard include/config/modversions.h) \
    $(wildcard include/config/trim/unused/ksyms.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/linkage.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/bitops.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/bitops.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/barrier.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/barrier.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/builtin-__ffs.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/builtin-ffs.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/builtin-__fls.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/builtin-fls.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/ffz.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/fls64.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/find.h \
    $(wildcard include/config/generic/find/first/bit.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/sched.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/hweight.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/arch_hweight.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/const_hweight.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/lock.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/non-atomic.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bitops/le.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/byteorder.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/byteorder/little_endian.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/byteorder/little_endian.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/swab.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/swab.h \
  arch/arm64/include/generated/asm/swab.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/swab.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/byteorder/generic.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/log2.h \
    $(wildcard include/config/arch/has/ilog2/u32.h) \
    $(wildcard include/config/arch/has/ilog2/u64.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/typecheck.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/printk.h \
    $(wildcard include/config/message/loglevel/default.h) \
    $(wildcard include/config/early/printk.h) \
    $(wildcard include/config/printk/nmi.h) \
    $(wildcard include/config/printk.h) \
    $(wildcard include/config/dynamic/debug.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/init.h \
    $(wildcard include/config/debug/rodata.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kern_levels.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/cache.h \
    $(wildcard include/config/arch/has/cache/line/size.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/kernel.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/sysinfo.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/cache.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/cachetype.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/cputype.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/sysreg.h \
    $(wildcard include/config/arm64/4k/pages.h) \
    $(wildcard include/config/arm64/16k/pages.h) \
    $(wildcard include/config/arm64/64k/pages.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/opcodes.h \
    $(wildcard include/config/cpu/big/endian.h) \
    $(wildcard include/config/cpu/endian/be8.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/../../arm/include/asm/opcodes.h \
    $(wildcard include/config/cpu/endian/be32.h) \
    $(wildcard include/config/thumb2/kernel.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/stat.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/stat.h \
    $(wildcard include/config/compat.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/stat.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/stat.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/compat.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/sched.h \
    $(wildcard include/config/cpu/quiet.h) \
    $(wildcard include/config/no/hz/common.h) \
    $(wildcard include/config/sched/debug.h) \
    $(wildcard include/config/prove/rcu.h) \
    $(wildcard include/config/hotplug/cpu.h) \
    $(wildcard include/config/lockup/detector.h) \
    $(wildcard include/config/detect/hung/task.h) \
    $(wildcard include/config/core/dump/default/elf/headers.h) \
    $(wildcard include/config/virt/cpu/accounting/native.h) \
    $(wildcard include/config/no/hz/full.h) \
    $(wildcard include/config/sched/autogroup.h) \
    $(wildcard include/config/bsd/process/acct.h) \
    $(wildcard include/config/taskstats.h) \
    $(wildcard include/config/audit.h) \
    $(wildcard include/config/inotify/user.h) \
    $(wildcard include/config/fanotify.h) \
    $(wildcard include/config/epoll.h) \
    $(wildcard include/config/posix/mqueue.h) \
    $(wildcard include/config/keys.h) \
    $(wildcard include/config/perf/events.h) \
    $(wildcard include/config/bpf/syscall.h) \
    $(wildcard include/config/sched/info.h) \
    $(wildcard include/config/task/delay/acct.h) \
    $(wildcard include/config/schedstats.h) \
    $(wildcard include/config/sched/smt.h) \
    $(wildcard include/config/sched/mc.h) \
    $(wildcard include/config/numa.h) \
    $(wildcard include/config/sched/walt.h) \
    $(wildcard include/config/fair/group/sched.h) \
    $(wildcard include/config/rt/group/sched.h) \
    $(wildcard include/config/thread/info/in/task.h) \
    $(wildcard include/config/cgroup/sched.h) \
    $(wildcard include/config/preempt/notifiers.h) \
    $(wildcard include/config/blk/dev/io/trace.h) \
    $(wildcard include/config/preempt/rcu.h) \
    $(wildcard include/config/tasks/rcu.h) \
    $(wildcard include/config/memcg.h) \
    $(wildcard include/config/slob.h) \
    $(wildcard include/config/compat/brk.h) \
    $(wildcard include/config/cgroups.h) \
    $(wildcard include/config/cc/stackprotector.h) \
    $(wildcard include/config/virt/cpu/accounting/gen.h) \
    $(wildcard include/config/sysvipc.h) \
    $(wildcard include/config/auditsyscall.h) \
    $(wildcard include/config/rt/mutexes.h) \
    $(wildcard include/config/debug/mutexes.h) \
    $(wildcard include/config/trace/irqflags.h) \
    $(wildcard include/config/lockdep.h) \
    $(wildcard include/config/ubsan.h) \
    $(wildcard include/config/block.h) \
    $(wildcard include/config/task/xacct.h) \
    $(wildcard include/config/cpusets.h) \
    $(wildcard include/config/futex.h) \
    $(wildcard include/config/debug/preempt.h) \
    $(wildcard include/config/numa/balancing.h) \
    $(wildcard include/config/arch/want/batched/unmap/tlb/flush.h) \
    $(wildcard include/config/fault/injection.h) \
    $(wildcard include/config/latencytop.h) \
    $(wildcard include/config/function/graph/tracer.h) \
    $(wildcard include/config/kcov.h) \
    $(wildcard include/config/uprobes.h) \
    $(wildcard include/config/bcache.h) \
    $(wildcard include/config/vmap/stack.h) \
    $(wildcard include/config/arch/wants/dynamic/task/struct.h) \
    $(wildcard include/config/have/unstable/sched/clock.h) \
    $(wildcard include/config/irq/time/accounting.h) \
    $(wildcard include/config/proc/fs.h) \
    $(wildcard include/config/stack/growsup.h) \
    $(wildcard include/config/have/copy/thread/tls.h) \
    $(wildcard include/config/have/exit/thread.h) \
    $(wildcard include/config/debug/stack/usage.h) \
    $(wildcard include/config/preempt.h) \
    $(wildcard include/config/cpu/freq.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/sched.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/sched/prio.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/param.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/param.h \
    $(wildcard include/config/hz.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/param.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/capability.h \
    $(wildcard include/config/multiuser.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/capability.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/threads.h \
    $(wildcard include/config/nr/cpus.h) \
    $(wildcard include/config/base/small.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/timex.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/timex.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/time.h \
    $(wildcard include/config/arch/uses/gettimeoffset.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/seqlock.h \
    $(wildcard include/config/debug/lock/alloc.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/spinlock.h \
    $(wildcard include/config/debug/spinlock.h) \
    $(wildcard include/config/generic/lockbreak.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/preempt.h \
    $(wildcard include/config/preempt/count.h) \
    $(wildcard include/config/preempt/tracer.h) \
  arch/arm64/include/generated/asm/preempt.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/preempt.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/thread_info.h \
    $(wildcard include/config/have/arch/within/stack/frames.h) \
    $(wildcard include/config/hardened/usercopy.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/bug.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/bug.h \
    $(wildcard include/config/debug/bugverbose.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/brk-imm.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/bug.h \
    $(wildcard include/config/bug.h) \
    $(wildcard include/config/generic/bug/relative/pointers.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/thread_info.h \
    $(wildcard include/config/arm64/sw/ttbr0/pan.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/irqflags.h \
    $(wildcard include/config/irqsoff/tracer.h) \
    $(wildcard include/config/trace/irqflags/support.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/irqflags.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/ptrace.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/ptrace.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/hwcap.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/hwcap.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/ptrace.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/bottom_half.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/spinlock_types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/spinlock_types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/lockdep.h \
    $(wildcard include/config/lock/stat.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rwlock_types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/spinlock.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/lse.h \
    $(wildcard include/config/as/lse.h) \
    $(wildcard include/config/arm64/lse/atomics.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/processor.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/string.h \
    $(wildcard include/config/binary/printf.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/string.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/string.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/alternative.h \
    $(wildcard include/config/arm64/uao.h) \
    $(wildcard include/config/foo.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/cpucaps.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/insn.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/fpsimd.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/hw_breakpoint.h \
    $(wildcard include/config/have/hw/breakpoint.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/cpufeature.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/jump_label.h \
    $(wildcard include/config/jump/label.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/jump_label.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/virt.h \
    $(wildcard include/config/arm64/vhe.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/sections.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/sections.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/pgtable-hwdef.h \
    $(wildcard include/config/pgtable/levels.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rwlock.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/spinlock_api_smp.h \
    $(wildcard include/config/inline/spin/lock.h) \
    $(wildcard include/config/inline/spin/lock/bh.h) \
    $(wildcard include/config/inline/spin/lock/irq.h) \
    $(wildcard include/config/inline/spin/lock/irqsave.h) \
    $(wildcard include/config/inline/spin/trylock.h) \
    $(wildcard include/config/inline/spin/trylock/bh.h) \
    $(wildcard include/config/uninline/spin/unlock.h) \
    $(wildcard include/config/inline/spin/unlock/bh.h) \
    $(wildcard include/config/inline/spin/unlock/irq.h) \
    $(wildcard include/config/inline/spin/unlock/irqrestore.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rwlock_api_smp.h \
    $(wildcard include/config/inline/read/lock.h) \
    $(wildcard include/config/inline/write/lock.h) \
    $(wildcard include/config/inline/read/lock/bh.h) \
    $(wildcard include/config/inline/write/lock/bh.h) \
    $(wildcard include/config/inline/read/lock/irq.h) \
    $(wildcard include/config/inline/write/lock/irq.h) \
    $(wildcard include/config/inline/read/lock/irqsave.h) \
    $(wildcard include/config/inline/write/lock/irqsave.h) \
    $(wildcard include/config/inline/read/trylock.h) \
    $(wildcard include/config/inline/write/trylock.h) \
    $(wildcard include/config/inline/read/unlock.h) \
    $(wildcard include/config/inline/write/unlock.h) \
    $(wildcard include/config/inline/read/unlock/bh.h) \
    $(wildcard include/config/inline/write/unlock/bh.h) \
    $(wildcard include/config/inline/read/unlock/irq.h) \
    $(wildcard include/config/inline/write/unlock/irq.h) \
    $(wildcard include/config/inline/read/unlock/irqrestore.h) \
    $(wildcard include/config/inline/write/unlock/irqrestore.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/atomic.h \
    $(wildcard include/config/generic/atomic64.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/atomic.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/atomic_ll_sc.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/cmpxchg.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/atomic-long.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/math64.h \
    $(wildcard include/config/arch/supports/int128.h) \
  arch/arm64/include/generated/asm/div64.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/div64.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/time64.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/time.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/param.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/timex.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/arch_timer.h \
    $(wildcard include/config/fsl/erratum/a008585.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/clocksource/arm_arch_timer.h \
    $(wildcard include/config/arm/arch/timer.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/timecounter.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/timex.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/jiffies.h \
  include/generated/timeconst.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/plist.h \
    $(wildcard include/config/debug/pi/list.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rbtree.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rcupdate.h \
    $(wildcard include/config/tiny/rcu.h) \
    $(wildcard include/config/tree/rcu.h) \
    $(wildcard include/config/rcu/trace.h) \
    $(wildcard include/config/rcu/stall/common.h) \
    $(wildcard include/config/rcu/nocb/cpu.h) \
    $(wildcard include/config/debug/objects/rcu/head.h) \
    $(wildcard include/config/rcu/boost.h) \
    $(wildcard include/config/rcu/nocb/cpu/all.h) \
    $(wildcard include/config/no/hz/full/sysidle.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/cpumask.h \
    $(wildcard include/config/cpumask/offstack.h) \
    $(wildcard include/config/debug/per/cpu/maps.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/bitmap.h \
    $(wildcard include/config/s390.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/completion.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/wait.h \
  arch/arm64/include/generated/asm/current.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/current.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/wait.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/debugobjects.h \
    $(wildcard include/config/debug/objects.h) \
    $(wildcard include/config/debug/objects/free.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/ktime.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/timekeeping.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/errno.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/errno.h \
  arch/arm64/include/generated/asm/errno.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/errno.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/errno-base.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rcutree.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/nodemask.h \
    $(wildcard include/config/highmem.h) \
    $(wildcard include/config/movable/node.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/numa.h \
    $(wildcard include/config/nodes/shift.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/mm_types.h \
    $(wildcard include/config/split/ptlock/cpus.h) \
    $(wildcard include/config/arch/enable/split/pmd/ptlock.h) \
    $(wildcard include/config/have/cmpxchg/double.h) \
    $(wildcard include/config/have/aligned/struct/page.h) \
    $(wildcard include/config/transparent/hugepage.h) \
    $(wildcard include/config/kmemcheck.h) \
    $(wildcard include/config/userfaultfd.h) \
    $(wildcard include/config/aio.h) \
    $(wildcard include/config/mmu/notifier.h) \
    $(wildcard include/config/compaction.h) \
    $(wildcard include/config/x86/intel/mpx.h) \
    $(wildcard include/config/hugetlb/page.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/auxvec.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/auxvec.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/auxvec.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rwsem.h \
    $(wildcard include/config/rwsem/spin/on/owner.h) \
    $(wildcard include/config/rwsem/generic/spinlock.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/err.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/osq_lock.h \
  arch/arm64/include/generated/asm/rwsem.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/rwsem.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/uprobes.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/page-flags-layout.h \
    $(wildcard include/config/sparsemem.h) \
    $(wildcard include/config/sparsemem/vmemmap.h) \
  include/generated/bounds.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/sparsemem.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/workqueue.h \
    $(wildcard include/config/debug/objects/work.h) \
    $(wildcard include/config/freezer.h) \
    $(wildcard include/config/wq/watchdog.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/timer.h \
    $(wildcard include/config/timer/stats.h) \
    $(wildcard include/config/debug/objects/timers.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/sysctl.h \
    $(wildcard include/config/sysctl.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/uidgid.h \
    $(wildcard include/config/user/ns.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/highuid.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/sysctl.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/page.h \
    $(wildcard include/config/arm64/page/shift.h) \
    $(wildcard include/config/arm64/cont/shift.h) \
    $(wildcard include/config/have/arch/pfn/valid.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/personality.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/personality.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/pgtable-types.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/memory.h \
    $(wildcard include/config/arm64/va/bits.h) \
    $(wildcard include/config/blk/dev/initrd.h) \
  arch/arm64/include/generated/asm/sizes.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/sizes.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/sizes.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/mmdebug.h \
    $(wildcard include/config/debug/vm.h) \
    $(wildcard include/config/debug/virtual.h) \
    $(wildcard include/config/debug/vm/pgflags.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/memory_model.h \
    $(wildcard include/config/flatmem.h) \
    $(wildcard include/config/discontigmem.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/pfn.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/getorder.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/mmu.h \
    $(wildcard include/config/unmap/kernel/at/el0.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/cputime.h \
  arch/arm64/include/generated/asm/cputime.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/cputime.h \
    $(wildcard include/config/virt/cpu/accounting.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/cputime_jiffies.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/smp.h \
    $(wildcard include/config/up/late/init.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/llist.h \
    $(wildcard include/config/arch/have/nmi/safe/cmpxchg.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/smp.h \
    $(wildcard include/config/arm64/acpi/parking/protocol.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/sem.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/sem.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/ipc.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/ipc.h \
  arch/arm64/include/generated/asm/ipcbuf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/ipcbuf.h \
  arch/arm64/include/generated/asm/sembuf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/sembuf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/shm.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/shm.h \
  arch/arm64/include/generated/asm/shmbuf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/shmbuf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/shmparam.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/shmparam.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/signal.h \
    $(wildcard include/config/old/sigaction.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/signal.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/signal.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/signal.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/signal.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/signal-defs.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/sigcontext.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/siginfo.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/siginfo.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/siginfo.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/pid.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/percpu.h \
    $(wildcard include/config/need/per/cpu/embed/first/chunk.h) \
    $(wildcard include/config/need/per/cpu/page/first/chunk.h) \
    $(wildcard include/config/have/setup/per/cpu/area.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/percpu.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/percpu.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/percpu-defs.h \
    $(wildcard include/config/page/table/isolation.h) \
    $(wildcard include/config/debug/force/weak/per/cpu.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/topology.h \
    $(wildcard include/config/use/percpu/numa/node/id.h) \
    $(wildcard include/config/have/memoryless/nodes.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/mmzone.h \
    $(wildcard include/config/force/max/zoneorder.h) \
    $(wildcard include/config/cma.h) \
    $(wildcard include/config/memory/isolation.h) \
    $(wildcard include/config/zsmalloc.h) \
    $(wildcard include/config/zone/dma.h) \
    $(wildcard include/config/zone/dma32.h) \
    $(wildcard include/config/zone/device.h) \
    $(wildcard include/config/memory/hotplug.h) \
    $(wildcard include/config/flat/node/mem/map.h) \
    $(wildcard include/config/page/extension.h) \
    $(wildcard include/config/no/bootmem.h) \
    $(wildcard include/config/deferred/struct/page/init.h) \
    $(wildcard include/config/have/memory/present.h) \
    $(wildcard include/config/need/node/memmap/size.h) \
    $(wildcard include/config/have/memblock/node/map.h) \
    $(wildcard include/config/need/multiple/nodes.h) \
    $(wildcard include/config/have/arch/early/pfn/to/nid.h) \
    $(wildcard include/config/sparsemem/extreme.h) \
    $(wildcard include/config/holes/in/zone.h) \
    $(wildcard include/config/arch/has/holes/memorymodel.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/pageblock-flags.h \
    $(wildcard include/config/hugetlb/page/size/variable.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/memory_hotplug.h \
    $(wildcard include/config/memory/hotremove.h) \
    $(wildcard include/config/have/arch/nodedata/extension.h) \
    $(wildcard include/config/have/bootmem/info/node.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/notifier.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/mutex.h \
    $(wildcard include/config/mutex/spin/on/owner.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/srcu.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/topology.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/topology.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/seccomp.h \
    $(wildcard include/config/seccomp.h) \
    $(wildcard include/config/have/arch/seccomp/filter.h) \
    $(wildcard include/config/seccomp/filter.h) \
    $(wildcard include/config/checkpoint/restore.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/seccomp.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/seccomp.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/unistd.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/uapi/asm/unistd.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/unistd.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/unistd.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/seccomp.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/unistd.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rculist.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rtmutex.h \
    $(wildcard include/config/debug/rt/mutexes.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/resource.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/resource.h \
  arch/arm64/include/generated/asm/resource.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/resource.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/asm-generic/resource.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/hrtimer.h \
    $(wildcard include/config/high/res/timers.h) \
    $(wildcard include/config/time/low/res.h) \
    $(wildcard include/config/timerfd.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/timerqueue.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kcov.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/kcov.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/task_io_accounting.h \
    $(wildcard include/config/task/io/accounting.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/latencytop.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/cred.h \
    $(wildcard include/config/debug/credentials.h) \
    $(wildcard include/config/security.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/key.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/assoc_array.h \
    $(wildcard include/config/associative/array.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/selinux.h \
    $(wildcard include/config/security/selinux.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/gfp.h \
    $(wildcard include/config/pm/sleep.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/magic.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/cgroup-defs.h \
    $(wildcard include/config/sock/cgroup/data.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/limits.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/idr.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/percpu-refcount.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/percpu-rwsem.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rcu_sync.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/bpf-cgroup.h \
    $(wildcard include/config/cgroup/bpf.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/bpf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/bpf_common.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/cgroup_subsys.h \
    $(wildcard include/config/cgroup/cpuacct.h) \
    $(wildcard include/config/cgroup/schedtune.h) \
    $(wildcard include/config/blk/cgroup.h) \
    $(wildcard include/config/cgroup/device.h) \
    $(wildcard include/config/cgroup/freezer.h) \
    $(wildcard include/config/cgroup/net/classid.h) \
    $(wildcard include/config/cgroup/perf.h) \
    $(wildcard include/config/cgroup/net/prio.h) \
    $(wildcard include/config/cgroup/hugetlb.h) \
    $(wildcard include/config/cgroup/pids.h) \
    $(wildcard include/config/cgroup/debug.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/stat.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kmod.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/elf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/elf.h \
  arch/arm64/include/generated/asm/user.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/user.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/elf.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/uapi/linux/elf-em.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kobject.h \
    $(wildcard include/config/uevent/helper.h) \
    $(wildcard include/config/debug/kobject/release.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/sysfs.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kernfs.h \
    $(wildcard include/config/kernfs.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kobject_ns.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/kref.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/moduleparam.h \
    $(wildcard include/config/alpha.h) \
    $(wildcard include/config/ia64.h) \
    $(wildcard include/config/ppc64.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/extable.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/rbtree_latch.h \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/arch/arm64/include/asm/module.h \
    $(wildcard include/config/arm64/module/plts.h) \
    $(wildcard include/config/randomize/base.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/asm-generic/module.h \
    $(wildcard include/config/have/mod/arch/specific.h) \
    $(wildcard include/config/modules/use/elf/rel.h) \
    $(wildcard include/config/modules/use/elf/rela.h) \
  /home/wlgh325/Workspace/android_build/vendor/nxp-opensource/kernel_imx/include/linux/vermagic.h \
  include/generated/utsrelease.h \

/home/wlgh325/Workspace/driver_modules/led/led.mod.o: $(deps_/home/wlgh325/Workspace/driver_modules/led/led.mod.o)

$(deps_/home/wlgh325/Workspace/driver_modules/led/led.mod.o):
