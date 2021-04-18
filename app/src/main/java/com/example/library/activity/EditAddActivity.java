package com.example.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class EditAddActivity extends AppCompatActivity implements View.OnClickListener {

    //<editor-fold desc="Description">
    public static final String ID = "id";
    public static final String BOOK = "book";
    public static final String WRITER = "writer";
    public static final String DESC = "desc";
    public static final String URL = "url";
    public static final String PRICE = "price";
    public static final String PAGES = "pages";
    private EditText edtBookName, edtWriterName, edtDesc, edtPrice, edtUrl;
    private TextView txtEditAdd;
    private ImageView imgAddEdit;
    private NumberPicker numberPicker;
    private String book, writer, price, desc, url;
    private int pages;
    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add);
        findViews();
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(1000);
        imgAddEdit.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent.hasExtra(ID)) {
            txtEditAdd.setText("Update Book");
            imgAddEdit.setImageResource(R.drawable.ic_baseline_edit);
            edtBookName.setText(intent.getStringExtra("getBook"));
            edtWriterName.setText(intent.getStringExtra("getName"));
            edtDesc.setText(intent.getStringExtra("getDesc"));
            edtPrice.setText(intent.getStringExtra("getPrice"));
            edtUrl.setText(intent.getStringExtra("getUrl"));
            numberPicker.setValue(intent.getIntExtra("getPages", 1));
        } else {
            txtEditAdd.setText("Add Book");
            imgAddEdit.setImageResource(R.drawable.ic_baseline_add);
        }

    }

    private void saveLibrary() {
        book = edtBookName.getText().toString().trim();
        writer = edtWriterName.getText().toString().trim();
        desc = edtDesc.getText().toString().trim();
        price = edtPrice.getText().toString().trim();
        url = edtUrl.getText().toString().trim();
        pages = numberPicker.getValue();

        if (book.trim().isEmpty() || writer.trim().isEmpty() || desc.trim().isEmpty() || price.trim().isEmpty()) {
            Toast.makeText(this, "Please Insert book, writer, desc, price", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = getIntent();
        data.putExtra(BOOK, book);
        data.putExtra(WRITER, writer);
        data.putExtra(DESC, desc);
        data.putExtra(PRICE, price);
        data.putExtra(URL, url);
        data.putExtra(PAGES, pages);

        int id = getIntent().getIntExtra(ID, -1);
        if (id != -1) {
            data.putExtra(ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_add_edit:
                saveLibrary();
                break;
        }
    }

    private void findViews() {
        edtBookName = findViewById(R.id.edt_add_edit_book_name);
        edtWriterName = findViewById(R.id.edt_add_edit_writer_name);
        edtDesc = findViewById(R.id.edt_add_edit_desc);
        edtPrice = findViewById(R.id.edt_add_edit_price);
        edtUrl = findViewById(R.id.edt_add_edit_img_url);
        txtEditAdd = findViewById(R.id.txt_add_edit);
        numberPicker = findViewById(R.id.np_pages);
        imgAddEdit = findViewById(R.id.img_add_edit);
    }

}