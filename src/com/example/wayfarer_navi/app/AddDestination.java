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
 * class AddDestination
 * @author Mu 
 */

public class AddDestination extends Activity
{
	String message_adventure = null; // this string corresponding to name_adventure_edittext, content of sub-topmenu 
	String message_destination = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_destination);

		TextView adventure_name = (TextView) findViewById(R.id.adventure_name);

		Intent intent = getIntent();
		message_adventure = intent.getStringExtra("name_adventure_edittext");
		adventure_name.setText(message_adventure);		
		// adventure_name.setText(intent.getStringExtra("name_adventure_edittext"));
	}
	
	// set click listener for name_destination_confirm button
	// Enter into activity SightsSelection after desination's initialization
	public void initialDestination(View target)
	{
		Intent intent = new Intent(this, SightsSelection.class);
		EditText name_destination_edittext = (EditText) findViewById(R.id.name_destination_edittext);
		message_destination = name_destination_edittext.getText().toString();
		intent.putExtra("name_destination_edittext", message_destination);
		intent.putExtra(intent.EXTRA_TEXT, message_adventure);
		startActivity(intent);
		// There should add something to deal with the edittext this part
		// For instance, use the content in edittext to lock on the sight user want to go
		// Then provide rich source of information about the sight, just like some small spots
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