package com.example.mave.Diary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mave.R;
import com.example.mave.repository.GroupRepository;

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
            TextView txt_userId = (TextView)convertView.findViewById(R.id.txt_name);
            Answer_Item listItem = listItems.get(position);
            // 가져온 데이터를 텍스트뷰에 입력
            txt_title.setText(listItem.getAnswer());
            txt_userId.setText(listItem.getUserId());

            LinearLayout  question_linear = (LinearLayout)convertView.findViewById(R.id.question_linear);
            question_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("1123", "클릭된다");
                }
            });
            return convertView;
        }

        public void addItem(String userId, String answer){
            Answer_Item listItem = new Answer_Item();
            listItem.setAnswer(answer);
            listItem.setUserId(userId);
            listItems.add(listItem);
        }
    }

