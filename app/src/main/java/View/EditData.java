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

public class EditData extends AppCompatActivity {
    EditText editname,editlname,editage,editdesc;
    Button button;
    DatabaseHalper databaseHalper;
    Data personInfo;
    int position;
    String str_positoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Bundle bundle=getIntent().getExtras();
        str_positoin=bundle.getString("position");
        position=Integer.valueOf(str_positoin);
        personInfo=databaseHalper.getData(position);
        databaseHalper=new DatabaseHalper(this);

        editname=findViewById(R.id.nameEdit);
        editlname=findViewById(R.id.lnameEdit);
        editage=findViewById(R.id.ageEdit);
        editdesc=findViewById(R.id.descEdit);
        button=findViewById(R.id.buttonEdit);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personInfo.setName(editname.getText().toString());
                personInfo.setLname(editlname.getText().toString());
                personInfo.setAge(editage.getText().toString());
                personInfo.setDescription(editdesc.getText().toString());

                databaseHalper.updateData(personInfo);
                MainActivity.notifyAdapter();
                Intent intent=new Intent(EditData.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}