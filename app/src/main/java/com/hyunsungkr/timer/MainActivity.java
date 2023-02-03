package com.hyunsungkr.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtTimer;
    TextView txtCount;
    Button button;

    CountDownTimer timer;

    boolean isFinished = false;

    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTimer = findViewById(R.id.txtTimer);
        txtCount = findViewById(R.id.txtCount);
        button = findViewById(R.id.button);


        timer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                // 위에서 정한 인터벌이 될때마다 실행되는 함수
                // 인터벌마다 무엇인가 실행하고 싶으면, 여기에 코드 작성

                // 위의 L은 밀리 세컨즈이므로, 초로 표시하려면
                // L 을 1000으로 나눠준다.
                long remain = l / 1000;

                // 화면에 남은 시간을 보여준다.
                txtTimer.setText(""+remain + "초");

                Log.i("TimerAPP Main", ""+remain );



            }
            private void showAlertDialog(){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("타이머 종료!");
                builder.setMessage("다시 도전하시겠습니까?");
                builder.setNegativeButton("Cancel",null);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        count=0;
                        isFinished=false;
                        txtCount.setText(count+"");
                        timer.start();
                    }
                });

                builder.show();}

            @Override
            public void onFinish() {
                // 타이머가 종료될 때 실행되는 함수.
                // 종료될 때 하고싶은 코드를 여기에 작성.
                Log.i("TimerAPP Main", "타이머 종료");

                isFinished = true;
                showAlertDialog();






            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 타이머가 종료되었는지 확인!
                if(isFinished == true){
                    return;
                }

                count = count+1;
                txtCount.setText(""+count);

            }
        });



        timer.start();



    }
}