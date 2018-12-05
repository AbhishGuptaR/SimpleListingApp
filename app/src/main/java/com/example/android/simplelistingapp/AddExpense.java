package com.example.android.simplelistingapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddExpense extends AppCompatActivity {

    Spinner spinner;
    RadioGroup radioGroup;
    ImageView imageView;
    String radioText,spinnerText,date;
    EditText entry,amount;
    int PICK_IMAGE_REQUEST =1;
    File finalFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("New Entry");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        entry = (EditText) findViewById(R.id.expenseentry);
        amount = (EditText) findViewById(R.id.expenseamount);

        Date curDate = Calendar.getInstance().getTime();
        date=curDate.toString();
        //Log.e("Date:",date);

        String[] group = {"My Expense","Office Expense"};

        spinner = (Spinner) findViewById(R.id.expensegroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,group);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerText = spinner.getSelectedItem().toString();
                //Log.e("spinnerText",spinnerText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        radioGroup = (RadioGroup) findViewById(R.id.expenseradiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedID);
                radioText = radioButton.getText().toString();
            }
        });


        imageView  = (ImageView) findViewById(R.id.addimage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), photo);
            // CALL THIS METHOD TO GET THE ACTUAL PATH
             finalFile = new File(getRealPathFromURI(tempUri));
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void addEntry(View view){

        String entryname = entry.getText().toString();
        String totamount = amount.getText().toString();

        MyDB myDB = new MyDB(this);
        SQLiteDatabase sqLiteDatabase = myDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Entry.COLUMN_NAME,entryname);
        values.put(Entry.COLUMN_AMOUNT,totamount);
        values.put(Entry.COLUMN_GROUP,spinnerText);
        values.put(Entry.COLUMN_TYPE,radioText);
        values.put(Entry.COLUMN_IMAGE,finalFile.toString());
        values.put(Entry.COLUMN_TIME,date);

        sqLiteDatabase.insert(Entry.TABLE_NAME,null,values);
        Toast.makeText(this,"Entry Added!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(AddExpense.this,AllExpensePage.class);
        startActivity(i);
        AddExpense.this.finish();

    }
}
