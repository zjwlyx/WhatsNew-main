package com.laioffer.tinnews.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class News  implements Serializable {
    public String ctime;
    public String title;
    public String description;
    public String picUrl;
    @NonNull
    @PrimaryKey
    public String url;
    public String source;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(ctime, news.ctime) &&
                Objects.equals(title, news.title) &&
                Objects.equals(description, news.description) &&
                Objects.equals(picUrl, news.picUrl) &&
                url.equals(news.url) &&
                Objects.equals(source, news.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ctime, title, description, picUrl, url, source);
    }

    @Override
    public String toString() {
        return "News{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
