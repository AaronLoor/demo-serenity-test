package com.demotest.utils.daos;

public class DaoUtil {
    public void waitTime(double secondsTime) {
        try {
            Thread.sleep((long) (secondsTime * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
