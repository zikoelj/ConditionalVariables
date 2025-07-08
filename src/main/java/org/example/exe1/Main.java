package org.example.exe1;

public class Main {
    public static void main(String[] args) {
        Porte porte = new Porte();

        Runnable personne = () -> {
            String nom = Thread.currentThread().getName();
            try {
                porte.entrer(nom);
                Thread.sleep(1000); // temps passe dans la porte
                porte.sortir(nom);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // Creer plusieurs threads
        for (int i = 1; i <= 5; i++) {
            new Thread(personne, "Personne-" + i).start();
        }
    }
}