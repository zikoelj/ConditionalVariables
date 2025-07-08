package org.example.exe2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Guichet {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private final Queue<String> clients = new LinkedList<>();

    public void arriverClient(String nom) {
        lock.lock();
        try{
            clients.add(nom);
            System.out.println("il y a un client à traiter");
            condition.signal();
        }
        finally {
         lock.unlock();
        }
    }
    public void  servirClient() throws InterruptedException {
        lock.lock();
        try {
            while (clients.isEmpty()) {
                System.out.println("Guichet vide. Le guichetier attend un client...");
                condition.await();
            }
            String client = clients.poll();
            System.out.println("Le guichetier sert " + client);
        }
        finally {
            lock.unlock();
        }
    }
}
