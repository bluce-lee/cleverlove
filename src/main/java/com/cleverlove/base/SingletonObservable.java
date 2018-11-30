package com.cleverlove.base;

import java.util.Observable;

public class SingletonObservable extends Observable {

    private static volatile SingletonObservable observable;

    private SingletonObservable() {
    }

    public static synchronized SingletonObservable getSingletonObservable() {
        if (observable == null) {
            observable = new SingletonObservable();
        }
        return observable;
    }

//    public static SingletonObservable getBaseObservable() {
//        if (observable == null) {
//            synchronized (SingletonObservable.class) {
//                if (observable == null) {
//                    observable = new SingletonObservable();
//                }
//            }
//        }
//        return observable;
//    }

    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
        this.setChanged();
    }
}
