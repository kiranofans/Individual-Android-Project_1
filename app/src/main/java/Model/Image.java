package Model;

public class Image {
    private String imgName,extension,size,num;

    public Image(String imgName, String extension, String size, String num) {
        this.imgName = imgName;
        this.extension = extension;
        this.size = size;
        this.num = num;
    }

    //Getters and Setters
    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
