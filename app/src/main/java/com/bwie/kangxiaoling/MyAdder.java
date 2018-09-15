package com.bwie.kangxiaoling;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ll on 2018/8/24.
 */

public class MyAdder extends LinearLayout {

    private Button jia;
    private EditText jia_ed;
    private Button jian;
    private int num = 1;
    private NumLisener numLisener;

    public MyAdder(Context context) {
        this(context, null);
    }

    public MyAdder(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAdder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.adderlayout, this, true);
        jia = (Button) view.findViewById(R.id.adder_jia);
        jia_ed = (EditText) view.findViewById(R.id.adder_tv);
        jian = (Button) view.findViewById(R.id.adder_jian);
        jia_ed.setText(num + "");
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                jia_ed.setText(num + "");
                if (numLisener != null) {
                    numLisener.getNum(num);
                }
            }
        });
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num <= 0) {
                    Toast.makeText(getContext(), "数量不能为0", Toast.LENGTH_SHORT).show();
                    num = 1;
                }
                jia_ed.setText(num + "");
                if (numLisener != null) {
                    numLisener.getNum(num);
                }
            }
        });
    }

    public void setNumLisener(NumLisener numLisener) {
        this.numLisener = numLisener;
    }

    public void getnumed(int ednum) {
        jia_ed.setText(ednum+"");
        num = Integer.parseInt(jia_ed.getText().toString());
    }

    public interface NumLisener {
        void getNum(int num);
    }
}
