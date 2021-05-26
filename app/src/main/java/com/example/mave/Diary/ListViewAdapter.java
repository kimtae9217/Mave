package com.example.mave.Diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mave.R;

import java.util.ArrayList;

    public class ListViewAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<Answer_Item> listItems = new ArrayList<Answer_Item>();

        public ListViewAdapter(Context context){
            this.mContext = context;
        }
        @Override
        public int getCount() {
            return listItems.size();
        }
        @Override
        public Object getItem(int i) {
            return listItems.get(i);
        }
        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // 레이아웃을 inflate해서 참조획득
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.answer_add_list, parent, false);
            }

            // 참조 획득
            TextView txt_title = (TextView)convertView.findViewById(R.id.txt_title);
            Answer_Item listItem = listItems.get(position);
            // 가져온 데이터를 텍스트뷰에 입력
            txt_title.setText(listItem.getAnswer());
            return convertView;
        }

        public void addItem(String answer){
            Answer_Item listItem = new Answer_Item();
            listItem.setAnswer(answer);
            listItems.add(listItem);
        }
    }

