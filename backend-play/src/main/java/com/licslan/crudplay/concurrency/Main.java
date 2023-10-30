package com.licslan.crudplay.concurrency;

class Main {
    private static final Object lock = new Object();
    static int i = 0;

    public static void main(String[] args) {
        for (int j = 0; j < 1000; j++) {
            new Thread(Main::addOne).start();
        }


    }

    static void addOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock) {
            i++;
            System.out.println(i);
        }
    }
}