package com.example.querycounter.counter;

public class Counter {

    private int count;
    private boolean flag;

    public Counter() {
    }

    public void startQueryCount() {
        this.flag = true;
    }

    public void countQuery() {
        this.count += 1;
    }

    public int getQueryCount() {
        return this.count;
    }

    public boolean getFlag() {
        return this.flag;
    }

    public void printQueryCount() {
        int cnt = this.count;
        clearCount();

        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println("Query Count : " + cnt + "번");
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
    }

    public void clearCount() {
        this.flag = false;
        this.count = 0;
    }

}
