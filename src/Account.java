public class Account {
    private String cardNumber;
    private String pin;
    private int balance;
    private boolean active;
    private int attemptsForPin;
    public Account(){

    }
    public Account(String cardNumber, String pin, int ballance, boolean active, int attemtsForPin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = ballance;
        this.active = active;
        this.attemptsForPin = attemtsForPin;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getPin() {
        return pin;
    }
    public int getBalance() {
        return balance;
    }
    public int getAttemptsForPin() {
        return attemptsForPin;
    }
    public void setAttemptsForPin(int attemptsForPin) {
        this.attemptsForPin = attemptsForPin;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public boolean isActive() {
        return active;
    }
    public void withdraw (int sum) throws Exception{
        if (sum> balance) throw new Exception("There is not enough money on your account");
        balance -=sum;
    }
    public void putMoney (int sum) {
        balance+=sum;
    }
    @Override
    public String toString() {
        String activisation = active ? "active" : "blocked";
        return String.format("%s %s %d %s %d ",
                cardNumber, pin, balance, activisation, attemptsForPin);
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Account)) return false;
        Account account = (Account) obj;
        return (account.cardNumber.equals(cardNumber) && account.pin.equals(pin) && account.balance == balance &&
                account.active == active && account.attemptsForPin == attemptsForPin) ? true : false;
    }
}
