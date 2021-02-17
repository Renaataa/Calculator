package example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void run(View view){
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, gatherData());
        intent.setAction("example.project5");
        intent.setType("text/plain");
        startActivityForResult(intent, REQUEST_CODE);
    }

    private  int[] gatherData(){
        TextView arg1 = findViewById(R.id.arg1);
        TextView arg2 = findViewById(R.id.arg2);
        return new int[]{
            getNumber(arg1),
        getNumber(arg2)
        };
    }

    private int getNumber(TextView tv){
        return getNumber(tv.getText().toString());
    }

    private int getNumber(String txt){
        return Integer.parseInt(nullCheck(txt));
    }

    private  String nullCheck(String txt){
        return txt.equals("") ? "0" : txt;
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            handleResult(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private  void handleResult(Intent result){
        TextView operationText = findViewById(R.id.operationText);
        TextView resultText =findViewById(R.id.resultText);

        operationText.setText(result.getStringExtra(Intent.EXTRA_TEXT));
        resultText.setText(String.valueOf(result.getIntExtra("result", 0)));
    }
}