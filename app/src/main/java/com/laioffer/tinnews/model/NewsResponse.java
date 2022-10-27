package com.laioffer.tinnews.model;

import java.util.List;
import java.util.Objects;

public class NewsResponse {
    public String code;
    public String msg;
    public List<News> newslist;

    @Override
    public String toString() {
        return "NewsResponse{" +
                "code='" + code + '\'' +
                ", message='" + msg + '\'' +
                ", news=" + newslist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsResponse that = (NewsResponse) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(newslist, that.newslist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, newslist);
    }
}
