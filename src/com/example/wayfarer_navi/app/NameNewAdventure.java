package com.example.wayfarer_navi.app;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;


/**
 * class NameNewAdventure
 * @author Mu 
 */

public class NameNewAdventure extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.name_new_adventure);
	}

	// set listener for button
	// when click, start activity AddDestination
	// And get edittext as the adventure title, shown on the top
	public void initialAdventureName(View target)
	{
		Intent intent = new Intent(this, AddDestination.class);
		EditText name_adventure_edittext = (EditText) findViewById(R.id.name_adventure_edittext);
		String message = name_adventure_edittext.getText().toString();
		intent.putExtra("name_adventure_edittext", message);
		startActivity(intent);
	}
	
	// set click listener for each button on top menu
	public void ClickToBackMain(View target)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void ClickToMyItineraries(View target)
	{
		Intent intent = new Intent(this, MyItineraries.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
}