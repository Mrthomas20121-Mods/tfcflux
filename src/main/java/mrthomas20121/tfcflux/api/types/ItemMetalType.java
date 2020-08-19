package mrthomas20121.tfcflux.api.types;

public enum ItemMetalType {
    SMALL_DUST(50),
    PLATE(100),
    DOUBLE_PLATE(200);

    private int smeltAmount;
    ItemMetalType(int smelt)
    {
        this.smeltAmount=smelt;
    }

    public int getSmeltAmount() {
        return smeltAmount;
    }

    public void setSmeltAmount(int smeltAmount) {
        this.smeltAmount = smeltAmount;
    }
}
