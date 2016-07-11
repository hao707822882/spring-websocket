package com.other;

/**
 * Created by Administrator on 2016/3/13.
 */
public class Greeting {
    private String content;

    private Integer area;

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
