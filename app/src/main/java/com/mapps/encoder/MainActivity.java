package com.mapps.encoder;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText Input;
    TextView EncodedText;
    Button EncodeButton;
    MyDatabase myDatabase;
    ContentValues contentValues;
    String finalEncoded = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentValues = new ContentValues();
        contentValues.put("Name","Milind");
        myDatabase = new MyDatabase(this);
        myDatabase.getWritableDatabase().insert("User",null,contentValues);

        Input = (EditText) findViewById(R.id.Text);
        EncodedText = (TextView) findViewById(R.id.EncodedText);
        EncodeButton = (Button) findViewById(R.id.EncodeButton);

        /*Cursor cursor = myDatabase.getReadableDatabase().query("User",new String[]{"Name"},null,null,null,null,null);

        cursor.moveToFirst();
        String name = cursor.getString(0);*/

        EncodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalEncoded = " ";
                String input = Input.getText().toString().toLowerCase();
                Cursor cursor = myDatabase.getReadableDatabase().query("User", new String[]{"Name"}, null, null, null, null, null);

                cursor.moveToFirst();
                String name = cursor.getString(0).toLowerCase();

                int length = input.length();
                int i = 0;
                int min = 26;
                String code = new String();

                for (int j = 0; j < length; j++) {

                    while (i < name.length()) {
                            min = getMin(name, input, i, j, min);
                            code = "" + name.charAt(i) + min;
                            i++;
                        }

                        finalEncoded = finalEncoded.concat(code);
                    }
                    EncodedText.setText(finalEncoded);
                }
        });
    }

    public int modulus(int x) {
        if(x > 0) {
            return x;
        }
        else if(x == 0){
            return x;
        }
        else {
            return (x * (-1));
        }
    }

    public int getMin(String name, String input, int i, int j, int min) {
        if (modulus(name.charAt(i) - input.charAt(j)) < min) {
            min = modulus(name.charAt(i) - input.charAt(j));
        }
        return min;
    }
}
