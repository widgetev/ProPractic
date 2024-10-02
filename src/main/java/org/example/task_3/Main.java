package org.example.task_3;

import static java.lang.Thread.currentThread;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ThreadPool myPool =ThreadPool.of(4);
        int step1 = 12;
        for (int i = 0; i < step1; i++) {
            int finalI = i;
            myPool.execute(()-> {
                System.out.println("Task " + finalI + " run in " + currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, i);
        }
        myPool.shutdown();
        myPool.awaitTermination();
        //Thread.sleep(5000);

        for (int i = step1; i < step1*2; i++) {
            int finalI = i;
            myPool.execute(()-> {
                System.out.println("Task " + finalI + " run in " + currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, i);
        }

    }

}
