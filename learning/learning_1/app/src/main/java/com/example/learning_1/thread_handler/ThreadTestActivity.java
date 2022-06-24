package com.example.learning_1.thread_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learning_1.R;
import com.example.learning_1.main.MainActivity;

import java.lang.ref.WeakReference;

public class ThreadTestActivity extends AppCompatActivity {
    private static final String TAG = "ThreadTestActivity";
    private static final int MSG_SHOW_TOAST = 1;

    private final Handler mHandler = new HandlerClass(this);
    private boolean mThreadRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);
        initViews();
    }

    private void initViews() {
        Button btnStart = findViewById(R.id.btn_thread_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int second = 0;
                        mThreadRun = true;
                        try {
                            while (mThreadRun) {
                                second ++;
                                Thread.sleep(1000);

                                Message msg = new Message();
                                msg.what = MSG_SHOW_TOAST;
                                msg.arg1 = second;
                                mHandler.sendMessage(msg);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

        Button btnStop = findViewById(R.id.btn_thread_stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadRun = false;
            }
        });
    }

    // memory leak 방지를 위해 static 클래스로 선언
    private static class HandlerClass extends Handler {
        // 약한참조, GC 동작 시, 무조건 정리된다.
        private final WeakReference<ThreadTestActivity> weakReference;

        private HandlerClass(ThreadTestActivity activity) {
            /* WeakReference<>(activity) 형태로 WeakReference에 인자로 activity를 전달했기때문에
            약한 참조이자, new 로 생성했기 때문에 강한참조이다.
            이후 사용할 때, ThreadTestActivity activity = weakReference.get(); 와 같이 사용했다면
            activity = null; 처럼 참조를 제거해 GC에 의해 정리되도록 해주어야한다.
             */
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.e(TAG, "a");
            ThreadTestActivity activity = weakReference.get();
            if (msg.what == MSG_SHOW_TOAST) {
                Toast.makeText(activity.getApplicationContext(),
                        "TEST", Toast.LENGTH_SHORT).show();

                TextView tv = activity.findViewById(R.id.tv_number);
                tv.setText(String.valueOf(msg.arg1));
                activity = null;
            }
            super.handleMessage(msg);
        }
    };
}