package Model;

public class Novels {
    private String bookTitle,author,type,category,contracted;
    private Boolean status;//finished or in progress

    public Novels(String bookTitle, String author, String type,
                  String category, String contracted,Boolean status) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.type = type;
        this.category = category;
        this.contracted= contracted;
        this.status = status;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getContracted() {
        return contracted;
    }

    public void setContracted(String contracted) {
        this.contracted = contracted;
    }
}

