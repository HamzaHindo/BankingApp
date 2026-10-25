package account;

import java.time.Instant;
import java.util.UUID;

public class Account {
    private UUID account_number;
    private Instant createdAt;
    private UUID userId;
    private double balance;

    public Account(UUID userId, Instant createdAt, double balance) {
        this.userId = userId;
        this.account_number = UUID.randomUUID();
        this.createdAt = createdAt;
        this.balance = balance;
    }

    public UUID getAccountNumber(){
        return this.account_number;
    }
    public double getBalance(){
        return this.balance;
    }
    public UUID getUserId(){
        return this.userId;
    }
    public Instant getCreatedAt(){
        return this.createdAt;
    }
}
