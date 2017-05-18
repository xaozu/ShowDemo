package com.example.tj.showdemo.fun;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tj.showdemo.R;


/**
 * author: zhulunjun
 * time:   2017/5/16
 * about:
 */
public class FuncFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_func, container, false);
    }


    public Function mFunction;

    public void setFunction(Function mFunction) {
        this.mFunction = mFunction;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView textView= (TextView) view.findViewById(R.id.tv_fragment);
        view.findViewById(R.id.btn_function).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    mFunction.invateFunction(FuncFragment.class.getName());
                    String string=mFunction.invokeFunction(FuncFragment.class.getName(),"这是参数",String.class);
                    textView.setText(" "+FuncFragment.class.getName() +" 返回值 "+string);
                } catch (NotFuncException e) {
                    textView.setText(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TestFuncActivity){
            TestFuncActivity funcActivity= (TestFuncActivity) context;
            funcActivity.setFunction();
        }
    }
}
