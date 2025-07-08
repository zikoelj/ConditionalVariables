package org.example.exe2;

public class Main {
    public static void main(String[] args) {
        Guichet guichet = new Guichet();

        // Thread guichetier
        Thread guichetier = new Thread(() -> {
            int nombreClientsTraites = 0;
            try {
                while (nombreClientsTraites < 5) {
                    guichet.servirClient();
                    Thread.sleep(1000); // simule traitement
                    nombreClientsTraites++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[Guichetier] Tous les clients ont été servis.");
        });
        guichetier.start();

        // Les clients arrivent un à un avec un délai entre chaque
        for (int i = 1; i <= 5; i++) {
            final int clientId = i;
            new Thread(() -> guichet.arriverClient("Client-" + clientId)).start();

            try {
                Thread.sleep(1500); //Pause de 1.5s entre chaque client
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

