package View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibo.sqliterecyclerviewmy.MainActivity;
import com.ibo.sqliterecyclerviewmy.R;

import Controller.DatabaseHalper;
import Model.Data;

public class AddData extends AppCompatActivity {
    EditText editname,editlname,editage,editdesc;
    Button button;
    DatabaseHalper databaseHalper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        databaseHalper=new DatabaseHalper(this);

        editname=findViewById(R.id.nameAdd);
        editlname=findViewById(R.id.lnameAdd);
        editage=findViewById(R.id.ageAdd);
        editdesc=findViewById(R.id.descAdd);
        button=findViewById(R.id.buttonSave);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editname.getText().toString();
                String lname=editlname.getText().toString();
                String age=editage.getText().toString();
                String desc=editdesc.getText().toString();
                databaseHalper.insertData(new Data(name,lname,age,desc));
                Intent intent=new Intent(AddData.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}