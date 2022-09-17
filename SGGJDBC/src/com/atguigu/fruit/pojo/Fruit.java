package com.atguigu.fruit.pojo;

public class Fruit {
    private Integer fid;
    private String fname;
    private Integer price;
    private Integer fcount;
    private String remark;

    public Fruit() {
    }

    public Fruit(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        this.fid = fid;
        this.fname = fname;
        this.price = price;
        this.fcount = fcount;
        this.remark = remark;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFcount() {
        return fcount;
    }

    public void setFcount(Integer fcount) {
        this.fcount = fcount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof com.atguigu.fruit.jdbc.Fruit)) return false;

        com.atguigu.fruit.jdbc.Fruit fruit = (com.atguigu.fruit.jdbc.Fruit) o;

        if (getFid() != null ? !getFid().equals(fruit.getFid()) : fruit.getFid() != null) return false;
        if (getFname() != null ? !getFname().equals(fruit.getFname()) : fruit.getFname() != null) return false;
        if (getPrice() != null ? !getPrice().equals(fruit.getPrice()) : fruit.getPrice() != null) return false;
        if (getFcount() != null ? !getFcount().equals(fruit.getFcount()) : fruit.getFcount() != null) return false;
        return getRemark() != null ? getRemark().equals(fruit.getRemark()) : fruit.getRemark() == null;
    }

    @Override
    public int hashCode() {
        int result = getFid() != null ? getFid().hashCode() : 0;
        result = 31 * result + (getFname() != null ? getFname().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getFcount() != null ? getFcount().hashCode() : 0);
        result = 31 * result + (getRemark() != null ? getRemark().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return fid + "\t\t" +
               fname + "\t" +
               price + "\t\t" +
               fcount + "\t\t" +
               remark;
    }
}
