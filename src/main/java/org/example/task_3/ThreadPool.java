package org.example.task_3;

import jdk.jfr.StackTrace;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadPool {

    private final AtomicBoolean isActive;
    /**
     * Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
     */
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final List<Thread> threads = new ArrayList<>();
    private final CountDownLatch countDownLatch;

    public void awaitTermination() throws InterruptedException {
        System.out.println("awaiting");
        countDownLatch.await();
    }

    /**
     * При выполнении у пула потоков метода execute(Runnabler)
     * @param runnabler - указанная задача должна попасть в очередь исполнения,
     *      и как только появится свободный поток – должна быть выполнена
     */
    public void execute(Runnable runnabler, int i){

            if (!this.isActive.get()) {
                throw new IllegalStateException("Pool in TERMINATED state");
            }
        System.out.println("Add new task " + i);
        synchronized (tasks) {
            this.tasks.offer(runnabler);
            //System.out.println("notify");
            tasks.notifyAll(); //вино-водочный прислал заявки!
        }

    };

    /**
     * Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются пулом
     * (при попытке добавить задачу можно бросать IllegalStateException), и все потоки для которых больше нет задач
     * завершают свою работу.
     * @param
     */
    public void shutdown(){
        this.isActive.set(false); //без всяких compareAndSet. Гасим в любом случае
        System.out.println("pool shutdown");
    }

    /**
     * В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
     * @param capacity - емкость пула (количество рабочих потоков).
     */
    private ThreadPool(int capacity) {
        this.isActive = new AtomicBoolean(true);
        countDownLatch = new CountDownLatch(capacity);
        //Создать заданное кол-во потоков
        for (int i = 0; i < capacity ; i++) {
            Thread thread = new Thread(() -> {
                while (true) { //каждый будет крутиться "вечно"
                    Runnable task;
                    synchronized (tasks) {
                         task = tasks.poll();
                    }

                    if (task != null) { //если задача есть - работаем
                        //System.out.println(countDownLatch.getCount());
                        task.run();
                    }
                    else if (!this.isActive.get()) { //если задачи нет и TERMINATED - уволен
                        countDownLatch.countDown();
                        break;
                    } else { // иначе - перекур
                        synchronized (tasks) {
                            try {
                                tasks.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, "Thread_" + i);
            threads.add(thread);
            thread.start();
        }
    }

    public static @NotNull ThreadPool of(int capacity) {
        return new ThreadPool(capacity);
    }
}
