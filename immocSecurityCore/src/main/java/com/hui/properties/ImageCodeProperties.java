package com.hui.properties;

/**
 * @Author: CarlChen
 * @Despriction: 图形验证码配置
 * @Date: Create in 21:39 2019\2\10 0010
 */
public class ImageCodeProperties {

    private int width = 90;// 定义图片的width
    private int height = 20;// 定义图片的height
    private int codeCount = 4;// 定义图片上显示验证码的个数
    private int xx = 15;
    private int fontHeight = 18;
    private int codeY = 16;
    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    private String urls = "/user,/hello";

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(int codeCount) {
        this.codeCount = codeCount;
    }

    public int getXx() {
        return xx;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public int getFontHeight() {
        return fontHeight;
    }

    public void setFontHeight(int fontHeight) {
        this.fontHeight = fontHeight;
    }

    public int getCodeY() {
        return codeY;
    }

    public void setCodeY(int codeY) {
        this.codeY = codeY;
    }

    public char[] getCodeSequence() {
        return codeSequence;
    }

    public void setCodeSequence(char[] codeSequence) {
        this.codeSequence = codeSequence;
    }
}
