import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SessionManager {
    // Creating a thread-safe Set for active sessions
    private final Set<String> activeSessions = Collections.synchronizedSet(new HashSet<>());

    // User logs in
    public void login(String sessionId) {
        synchronized (activeSessions) {
            activeSessions.add(sessionId);
        }
        System.out.println(sessionId + " logged in.");
    }

    // User logs out
    public void logout(String sessionId) {
        synchronized (activeSessions) {
            activeSessions.remove(sessionId);
        }
        System.out.println(sessionId + " logged out.");
    }

    // Check if a session is active
    public boolean isActive(String sessionId) {
        synchronized (activeSessions) {
            return activeSessions.contains(sessionId);
        }
    }

    // Broadcast a message to all active sessions
    public void broadcast(String message) {
        synchronized (activeSessions) {
            for (String sessionId : activeSessions) {
                // Check if the session is still active before sending the message
                if (isActive(sessionId)) {
                    // Simulate sending a message to the session
                    System.out.println("Sending message to " + sessionId + ": " + message);
                }
            }
        }
    }

    public static void main(String[] args) {
        SessionManager sessionManager = new SessionManager();
        Runnable userSessionTask = getRunnable(sessionManager);

        // Create and start multiple threads
        int numberOfUsers = 50;
        Thread[] threads = new Thread[numberOfUsers];
        for (int i = 0; i < numberOfUsers; i++) {
            threads[i] = new Thread(userSessionTask, "User-" + (i + 1));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All users have logged out.");
    }

    private static Runnable getRunnable(SessionManager sessionManager) {
        // Simulate multiple users logging in, broadcasting, and logging out concurrently
        return () -> {
            String threadName = Thread.currentThread().getName();
            sessionManager.login(threadName);

            // Simulate some work
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Broadcast a message to all active sessions
            sessionManager.broadcast("Hello from " + threadName);

            // Simulate more work
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Logout the user
            sessionManager.logout(threadName);
        };
    }
}