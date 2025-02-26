package TICKET;

public interface TicInter {
    void buyTicket(int userId, String departure, String destination);
    void cancelTicket(int userId, int ticketId);
    void showTicket(int ticketId);
}

