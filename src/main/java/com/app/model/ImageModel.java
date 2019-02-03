package com.app.model;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "image")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "image")
    private byte[] image;

    public ImageModel(String fileName, byte[] image) {
        this.fileName = fileName;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageModel imageModel1 = (ImageModel) o;
        return Objects.equals(id, imageModel1.id) &&
                Objects.equals(fileName, imageModel1.fileName) &&
                Arrays.equals(image, imageModel1.image);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, fileName);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
