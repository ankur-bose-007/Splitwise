public class ExpenseMetadata {
    private String name;
    private String imageUrl;
    private String notes;

    public ExpenseMetadata(String name, String imageUrl, String notes) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNotes() {
        return notes;
    }
}
