package me.yung.poly.jo;

import me.yung.poly.SharedStorage;

public class Task {
    private static final SharedStorage sharedStorage = SharedStorage.getInstance();
    String a_addr;
    String x_addr;

    public Task(String a_addr, String x_addr) {
        this.a_addr = a_addr;
        this.x_addr = x_addr;
    }

    @Override
    public String toString() {
        return "Task{" +
                "a_addr='" + a_addr + '\'' +
                ", x_addr='" + x_addr + '\'' +
                '}';
    }

    public String getA_addr() {
        return a_addr;
    }

    public String getX_addr() {
        return x_addr;
    }
}
