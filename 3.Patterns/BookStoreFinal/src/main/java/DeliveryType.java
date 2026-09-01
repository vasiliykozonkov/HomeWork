public enum DeliveryType {
    STANDARD("Стандартная"),
    EXPRESS("Экспресс");

    private final String displayName;

    DeliveryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}