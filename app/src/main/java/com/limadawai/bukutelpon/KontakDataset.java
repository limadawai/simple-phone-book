package com.limadawai.bukutelpon;

public class KontakDataset {

    int id;
    String image, nama, telpon, email;

    public KontakDataset() {
    }

    public KontakDataset(int id, String image, String nama, String telpon, String email) {
        this.id = id;
        this.image = image;
        this.nama = nama;
        this.telpon = telpon;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
