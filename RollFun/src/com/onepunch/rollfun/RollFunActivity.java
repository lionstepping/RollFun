package com.onepunch.rollfun;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.onepunch.rollfun.R;
import com.onepunch.rollfun.database.FunDBManager;
import com.onepunch.rollfun.entity.FunnyFood;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class RollFunActivity extends Activity {
	     private FunDBManager fdbm;
	     private TextView fanText;
	     private ImageView imAc;
	     private Spinner typeSpinner;
	     private ArrayAdapter<String> sAdapter;
	     private ArrayList<String> foodName;
	     private ArrayList<String> types;
	     private Button rollBtn;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			setContentView(R.layout.activity_roll_fun);
			super.onCreate(savedInstanceState);
			//初始化组件
			typeSpinner=(Spinner)findViewById(R.id.spinner1);
			rollBtn = (Button)findViewById(R.id.button1);
			fanText = (TextView)findViewById(R.id.fanText);
			imAc = (ImageView)findViewById(R.id.imageViewAC);
			
			fdbm = new FunDBManager(this);
			types =  new ArrayList<String>();
			types.add("全部");	
			types.addAll(fdbm.findType());
			sAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, types);
			sAdapter.setDropDownViewResource(R.layout.spinner_item_lay);
			typeSpinner.setAdapter(sAdapter);
			foodName= new ArrayList<String>();
			foodName = (ArrayList<String>) fdbm.findAll();
			typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if (arg2!=0) {
						foodName = (ArrayList<String>) fdbm.findByType(types.get(arg2));
					}
								
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			
			rollBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					int i = new Random().nextInt(foodName.size());
					fanText.setText(foodName.get(i));
					int imgno = new Random().nextInt(54)+ 0x7f020000;
					imAc.setImageResource(imgno);
				}
			});
			
		}
	
}
