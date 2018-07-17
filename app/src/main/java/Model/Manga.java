package Model;

import Utility.Constants;

public class Manga implements Base_Items_Model{
    private String mangaTitle,author,type,category,contracted;

    public Manga(String mangaTitle, String author, String type,
                 String category,String contract) {
        this.mangaTitle = mangaTitle;
        this.author = author;
        this.type = type;
        this.contracted=contract;
        this.category = category;
    }

    public String getContracted() {
        return contracted;
    }

    public void setContracted(String contracted) {
        this.contracted = contracted;
    }

    public String getMangaTitle() {
        return mangaTitle;
    }

    public void setMangaTitle(String mangaTitle) {
        this.mangaTitle = mangaTitle;
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

    @Override
    public int getViewType() {
        return Constants.ViewTypes.MANGAS;
    }
}
