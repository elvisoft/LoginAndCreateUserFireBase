package com.elvisoft.misdatosarg;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<CuentasBank> list;

    public CustomAdapter(Context context, List<CuentasBank> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imgCuenta;
        TextView txtNameCuenta;
        TextView txtNameBank;
        CuentasBank cb = list.get(i);
        return view;
    }
}
