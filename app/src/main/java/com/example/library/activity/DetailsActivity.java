package com.example.library.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.library.R;
import com.example.library.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    //<editor-fold desc="--Declaration--">
    private ActivityDetailsBinding detailsBinding;
    private TextView txtBookName, txtWriterName, txtPages, txtPrice;
    private ImageView imageView, imgBack;
    private ReadMoreTextView readMoreTextView;
    private String bookName, writerName, desc, img;
    private int pages, price;
    //</editor-fold>
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        bindViews(detailsBinding);
        imgBack.setOnClickListener(this);
        receiveData();
    }

    private void receiveData() {
        Intent intent = getIntent();
        bookName = intent.getStringExtra("bookName");
        writerName = intent.getStringExtra("writerName");
        pages = intent.getIntExtra("pages", 0);
        price = intent.getIntExtra("price", 0);
        desc = intent.getStringExtra("desc");
        img = intent.getStringExtra("img");
        setData();
    }

    private void setData() {
        txtBookName.setText(bookName);
        txtWriterName.setText(writerName);
        txtPages.setText(pages + "");
        txtPrice.setText(price + " Dolores");
        readMoreTextView.setText(desc);
        readMoreTextView.setTrimCollapsedText(" More ");
        readMoreTextView.setTrimExpandedText(" Less ");
        try {
            imageView.setAlpha(0f);
            Glide
                    .with(this)
                    .load(img)
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    private void bindViews(ActivityDetailsBinding detailsBinding) {
        txtBookName = detailsBinding.txtBookNameDetails;
        txtWriterName = detailsBinding.txtWriterNameDetails;
        txtPages = detailsBinding.txtPagesDetails;
        txtPrice = detailsBinding.price;
        imageView = detailsBinding.imgDetails;
        imgBack = detailsBinding.imgBack;
        readMoreTextView = detailsBinding.txtDescDetails;
    }

}