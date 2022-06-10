package test_data;

import pages.ProductPage;

public class Product extends ProductPage {
    private String id;
    private String goodsName;
    private String goodsBrand;
    private String seller;
    private String price;
    private String picture;

    public Product() {
    }

    public Product(String id, String goodsName, String goodsBrand, String seller, String price, String picture) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsBrand = goodsBrand;
        this.seller = seller;
        this.price = price;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public String getSeller() {
        return seller;
    }

    public String getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }
}
