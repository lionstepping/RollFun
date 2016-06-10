package com.onepunch.rollfun;

import java.util.ArrayList;

import com.onepunch.rollfun.database.FunDBManager;
import com.onepunch.rollfun.entity.FunnyFood;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class AddActivity extends Activity {
	 private FunDBManager fdbm;
	 
	 private LinearLayout custom_layout;
	 private LinearLayout type_layout;
     private EditText nameText1;
     private EditText nameText2;
     private EditText typeText;
    
     private Spinner typeSpinner;
     private ArrayAdapter<String> sAdapter;
     private ArrayList<String> foodName;
     private ArrayList<String> types;
     private Button addBtn;
     private RadioGroup selectRgm;
     private RadioButton type_rbm;
     private RadioButton custom_rbm;
     
     
     private FunnyFood funFood;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);
		
		//初始化组件
		typeSpinner =(Spinner) findViewById(R.id.spinner_type);
		types.addAll(fdbm.findType());
		sAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, types);
		sAdapter.setDropDownViewResource(R.layout.spinner_item_lay);
		typeSpinner.setAdapter(sAdapter);
		
		custom_layout = (LinearLayout)findViewById(R.id.custom_layout);
		type_layout = (LinearLayout)findViewById(R.id.type_layout);
		nameText1 = (EditText)findViewById(R.id.name_et1);
		nameText2 = (EditText)findViewById(R.id.name_et2);
		typeText = (EditText)findViewById(R.id.type_et);
		
		
		//初始化对象
		funFood = new FunnyFood();
	}
	
	
	
	
	
}
