package com.onepunch.rollfun.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.onepunch.rollfun.entity.FunnyFood;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class FunDBManager {
	SQLiteDatabase sdb; 

	public static final String DB_NAME = "rollfun.db3"; //保存的数据库文件名
	public static final String PACKAGE_NAME = "com.onepunch.rollfun";	
	public static final String DATABASES_PATH = "/data"+ Environment.getDataDirectory().getAbsolutePath() + "/"+ PACKAGE_NAME+"/databases/";
	public static final String DB_PATH = DATABASES_PATH +DB_NAME;
	
	public FunDBManager(Context context){	

		InputStream isDb = null;
		OutputStream osDb =null;
		
		File fileDB =new File(DB_PATH);
	
		if (!fileDB.exists()) {
			File dbPath = new File(DATABASES_PATH);
			dbPath.mkdirs();
			AssetManager assetm = context.getAssets();
			try {
				isDb = assetm.open("rollfun.db3");
				osDb = new FileOutputStream(DB_PATH);
				byte []buffer = new byte[1024];
				int read = 0;
//				isDb.read(buffer);
				while ((read = isDb.read(buffer))!=-1) {
					osDb.write(buffer, 0, read);	
				}
//				osDb.write(buffer, 0, read);
//				osDb.flush();
				isDb.close();
				isDb=null;				
				osDb.close();
				osDb=null;
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	}
	
	public boolean addItem(FunnyFood ff){
		
		sdb = SQLiteDatabase.openDatabase(DB_PATH, null,SQLiteDatabase.OPEN_READWRITE);
		
		String sql_select = "select * from RollFun where type =? and type =?";
		String selects[] = new String[2]; 
		selects[0]=ff.getType();
		selects[1]=ff.getName();
		Cursor cursor = sdb.rawQuery(sql_select, selects);
		if (cursor.isNull(0)) {
			String sql = "insert into RollFun (type,name) values (?,?);";
			Object bindArgs[] = new Object[2]; 
			bindArgs[0]=ff.getType();
			bindArgs[1]=ff.getName();
			sdb.execSQL(sql, bindArgs);
			sdb.close();
		}else{
			
			return false;
		}
		
		
		return true;
	}
	
	public FunnyFood findById(Integer i){
		sdb = SQLiteDatabase.openDatabase(DB_PATH, null,SQLiteDatabase.OPEN_READWRITE);
		String sql = "select * from RollFun where id = "+i; 		
		Cursor cursor = sdb.rawQuery(sql,null);
		FunnyFood ff = new FunnyFood();
		ff.setType(cursor.getString(cursor.getColumnIndex("type")));
		ff.setName(cursor.getString(cursor.getColumnIndex("name")));
		cursor.close();
		sdb.close();
		return ff;
	}
	
	
	public ArrayList<String> findType(){
		ArrayList<String>types = new ArrayList<String>();
		sdb = SQLiteDatabase.openDatabase(DB_PATH, null,SQLiteDatabase.OPEN_READWRITE);
		String sql = "select DISTINCT type from RollFun"; 
		Cursor cursor = sdb.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			types.add(cursor.getString(cursor.getColumnIndex("type")));
		}
		cursor.close();
		sdb.close();
		return types;
	}
	
	public List<String> findByType(String type){
		ArrayList<String> foodNames = new ArrayList<String>();
		sdb = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
		String sql = "select name from RollFun where type = "+"'"+type+"'";
		Cursor cursor = sdb.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			foodNames.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
		sdb.close();
		return foodNames;
	}
	
	public List<String> findAll(){
		List<String> allFood = new ArrayList<String>();
		sdb = SQLiteDatabase.openDatabase(DB_PATH, null,SQLiteDatabase.OPEN_READWRITE);
		String sql = "select name from RollFun"; 
		Cursor cursor = sdb.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));			
			allFood.add(name);		
		}
		cursor.close();
		sdb.close();
		return allFood;
	}
}
