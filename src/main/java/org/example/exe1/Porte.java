package org.example.exe1;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Porte {
     Lock lock = new ReentrantLock();
     Condition porteLibre = lock.newCondition();
    boolean estOccupee = false;
    public void entrer(String nom) throws InterruptedException{
        lock.lock();
        try {
            // Tant que la porte est occupee, on attend
            while (estOccupee) {
                System.out.println(nom + " attend que la porte soit libre...");
                porteLibre.await();
            }
            // La porte est maintenant occupee
            estOccupee = true;
            System.out.println(nom + " entre dans la porte.");
        }
        finally {
            lock.unlock();
        }
    }
    // Appelee par un thread apres avoir passe la porte

    public void sortir(String nom) {
        lock.lock();
        try {
            // Le thread libere la porte
            estOccupee = false;
            System.out.println(nom + " sort de la porte.");
            // On reveille un autre thread qui attend
            porteLibre.signal();
        } finally {
            lock.unlock();
        }
    }

}
