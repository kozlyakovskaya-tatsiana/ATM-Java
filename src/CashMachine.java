public class CashMachine {
    private int cash;
    public CashMachine(int cash){
        this.cash=cash;
    }
    public int getCash() {
        return cash;
    }
    public void setCash(int cash) {
        this.cash = cash;
    }
    public void withdrawCash(int sum ) {
        cash-=sum;
    }
    public void putMoney(int sum) throws Exception{
        if (sum> 1000000) throw new Exception("The sum must be less or equals 1000000");
        cash+=sum;
    }


}
