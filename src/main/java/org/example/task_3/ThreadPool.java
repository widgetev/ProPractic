package org.example.task_3;

import java.util.*;

public class ThreadPool {

    private POOL_STATUS status;
    /**
     * Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
     */
    private Queue<Runnable> tasks = new LinkedList<>();

    private List<Thread> threads = new ArrayList<>();
    /**
     * При выполнении у пула потоков метода execute(Runnabler)
     * @param runnabler - указанная задача должна попасть в очередь исполнения,
     *      и как только появится свободный поток – должна быть выполнена
     */
    public void execute(Runnable runnabler, int i){
        if (this.status==POOL_STATUS.TERMINATED) {
            throw new IllegalStateException("Pool in TERMINATED state");
        }
        if (this.status==POOL_STATUS.ACTIVE) {
            System.out.println("Add new task " + i);
            synchronized (tasks) {
                this.tasks.offer(runnabler);
            }
        }
    };

    /**
     * Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются пулом
     * (при попытке добавить задачу можно бросать IllegalStateException), и все потоки для которых больше нет задач
     * завершают свою работу.
     * @param
     */
    public void shutdown(){
        this.status=POOL_STATUS.TERMINATED;
        System.out.println("pool shutdown");
    }

    /**
     * В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
     * @param capacity - емкость пула (количество рабочих потоков).
     */
    private ThreadPool(int capacity) {
        this.status=POOL_STATUS.ACTIVE;
        //Создать заданное кол-во потоков
        for (int i = 0; i < capacity ; i++) {
            Thread thread = new Thread(() -> {
                while (true) { //каждый будет крутиться "вечно"
                    Runnable task;
                    synchronized (tasks) {
                         task = tasks.poll();
                    }

                    if (task != null)
                        task.run();
                    else if (status==POOL_STATUS.TERMINATED) {
                        break;
                    }
                }
            }, "Thread_" + i);
            threads.add(thread);
            thread.start();
        }
    }

    public static ThreadPool of(int capacity) {
        ThreadPool pool = new ThreadPool(capacity);
        return pool;
    }
}
