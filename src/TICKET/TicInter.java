package TICKET;

public interface TicInter {
    void buyTicket(Ticket ticket); // Modified to take a `Ticket` object
    void cancelTicket(int userId, int ticketId);
}
