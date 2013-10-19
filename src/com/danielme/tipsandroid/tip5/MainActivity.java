package com.danielme.tipsandroid.tip5;

import com.danielme.tipsandroid.tip5.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.widget.TextView;



/**
 * 
 * @author Daniel Medina (danielme.com)
 *
 */
@SuppressLint("NewApi")
public class MainActivity extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
		
		TextView textViewStatate = (TextView) findViewById(R.id.textViewExternalStorageState);
		textViewStatate.setText(Environment.getExternalStorageState()); 
		
		TextView textViewPath = (TextView) findViewById(R.id.textViewExternalStorageDirectory);
		textViewPath.setText(Environment.getExternalStorageDirectory().getAbsolutePath());
		
		TextView textViewRemovable = (TextView) findViewById(R.id.textViewIsExternalStorageRemovable);
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD)
		{
			textViewRemovable.setText(getString(R.string.gingerbread));
		}
		else
		{
			textViewRemovable.setText(Boolean.toString(Environment.isExternalStorageRemovable()));
		}
		
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
		long available = ((long) stat.getAvailableBlocks() * (long) stat.getBlockSize()); 
		long total = ((long) stat.getBlockCount() * (long) stat.getBlockSize()); 

		float totalMB = (float)total/1024/1024;
		float availableMB = (float)available/1024/1024;

		TextView textViewTotal = (TextView) findViewById(R.id.textViewTotalSpace);
		textViewTotal.setText(Float.toString(totalMB));
		
		TextView textViewFree = (TextView) findViewById(R.id.textViewFreeSpace);
		String free = null;
		if (total > 0)
		{
			free = Float.toString(availableMB) + " (" + availableMB/totalMB * 100 + "%)";
		}
		else
		{
			free = "0.0";
		}
		textViewFree.setText(free);
		
		TextView textViewAppDir = (TextView) findViewById(R.id.textViewAppDir);
		if (this.getExternalFilesDir(null) != null)
		{
			textViewAppDir.setText(this.getExternalFilesDir(null).getAbsolutePath());
		}
		
		
	}
}