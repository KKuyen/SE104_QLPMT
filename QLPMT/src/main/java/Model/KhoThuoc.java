package Model;

import javafx.scene.image.ImageView;

public class KhoThuoc {
    private Integer stt;
    private String tenthuoc;
    private String donvi;
    private Integer soluong;
    private  String dongia;




    public KhoThuoc(Integer stt, String tenthuoc, String donvi, Integer soluong, String dongia ) {
        this.tenthuoc = tenthuoc;
        this.donvi = donvi;
        this.soluong = soluong;
        this.dongia=dongia;
        this.stt=stt;

    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getDongia() {
        return dongia;
    }

    public String getTenthuoc() {
        return tenthuoc;
    }

    public void setTenthuoc(String tenthuoc) {
        this.tenthuoc = tenthuoc;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
