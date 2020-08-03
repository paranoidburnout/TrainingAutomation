package enums;

public enum ValuesEnum {
    GOOGLE_URL("https://www.google.ru"),
    GOOGLE_TITLE("Google"),
    SEARCH_REQUEST("Biological neuroscience, cognitive neuroscience"),
    RESULTS_TITLE("Biological neuroscience, cognitive neuroscience - Поиск в Google"),
    TOOLTIP_TEXT("Поиск"),
    TITLE("title");

    public String text;

    ValuesEnum(String text) {
        this.text = text;
    }
}
