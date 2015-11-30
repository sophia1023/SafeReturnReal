package com.jh.safereturn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by HUNNY on 2015-11-30.
 */
public class FindPolice extends Activity{
    EditText EditText2;
    Button findButton;
    TextView TextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpolice);
        Parse.initialize(this, "GMPoXbwsPM7sNnBQYUUFYnkMkC4LiMxzOYaHcXgh", "1UfwfA5whNUf85Jwl1xbYgEjtRFCEixmKmjZOs44");
        ParseObject testObject = new ParseObject("PoliceDB");

        EditText2 = (EditText) findViewById(R.id.slocal);
        findButton = (Button) findViewById(R.id.find);
        TextView2 = (TextView) findViewById(R.id.mdata);

        findButton.setOnClickListener(new View.OnClickListener() {

            public String slocal;

            @Override
            public void onClick(View view) {

                slocal = EditText2.getText().toString();

                try {
                    ArrayList<ParseObject> datas = new ArrayList<ParseObject>(); // parse.com에서 읽어온 object들을 저장할 List

                    ParseQuery<ParseObject> query = ParseQuery.getQuery("PoliceDB"); // 서버에 mydatas class 데이터 요청

                    query.whereEqualTo("slocal", slocal); // my_type이 1인 object만 읽어옴. 해당 함수 호출하지 않으면 class의 모든 데이터를 읽어옴.

                    datas.addAll(query.find()); // 읽어온 데이터를 List에 저장

                    // 읽어온 데이터를 화면에 보여주기 위한 처리

                    StringBuffer str = new StringBuffer();

                    for (ParseObject object : datas) {

                        str.append(" ★ ");
                        str.append("ObjectId: ");
                        str.append(object.getObjectId());
                        str.append(" => ");

                        str.append("구분: ");
                        str.append(object.get("type"));
                        str.append(" // ");

                        str.append("지역: ");
                        str.append(object.get("local"));
                        str.append(" // ");

                        str.append("시/구/군: ");
                        str.append(object.get("slocal"));
                        str.append(" // ");

                        str.append("도로명: ");
                        str.append(object.get("road"));
                        str.append(" // ");

                        str.append("경찰서 이름: ");
                        str.append(object.get("pname"));
                        str.append(" // ");

                        str.append("전화번호: ");
                        str.append(object.get("phonenumber"));
                        str.append("\n\n");
                    }

                    TextView2.setText(str.toString()); // TextView에 데이터를 넣어준다.

                    datas.clear();

                } catch (ParseException e) {

                    e.printStackTrace();

                }

            }
        });

    }
}