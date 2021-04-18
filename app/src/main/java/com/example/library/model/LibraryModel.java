package com.example.library.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.library.R;

@Entity(tableName = "Library")
public class LibraryModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String book;

    private String writer;

    private String desc;

    private String url;

    private int pages;

    private int price;

    public LibraryModel(String book, String writer, String desc, String url, int pages, int price) {
        this.book = book;
        this.writer = writer;
        this.desc = desc;
        this.url = url;
        this.pages = pages;
        this.price = price;
    }

    @BindingAdapter({"android:ImageLibrary"})
    public static void library(ImageView imageView, String home) {
        try {
            imageView.setAlpha(0f);
            Glide
                    .with(imageView.getContext())
                    .load(home)
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            imageView.animate().alpha(1f).setDuration(200).start();
                            return false;
                        }
                    })
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
