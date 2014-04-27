package com.example.wayfarer_navi.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


/**
 * class MyItineraries
 * description: enter into my itineraries
 * @author Mu 
 */
public class MyItineraries extends Activity {

	String message_adventure = null;
	String[] listText = null;
	
	private static final String TAG = "MyItineraries";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_itineraries);
		// Create list, and its element is map
		
		// Get intent string, then define it into listText
		// private String[] listText = new String[]
		// {"My Itineraries", "New Itineraries", "Browse", "Popular Itineraries", "Sign out"};
		
		TextView adventure_name = (TextView) findViewById(R.id.adventure_name);
		
		Intent intent = getIntent();
		message_adventure = intent.getStringExtra(intent.EXTRA_TEXT);
		adventure_name.setText(message_adventure);
		
		String new_sight = intent.getStringExtra("sight_name");
		listText = insert(listText, new_sight);
		System.out.println(listText);

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listText.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("option", listText[i]);
			listItems.add(listItem);
		}
		
		// Create a SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
			R.layout.my_itineraries_text,
			new String[]{"option"},
			new int[]{R.id.itineraries_text});	// itineraries_text is the id of TextView in "my_itineraries_text.xml"
		
		ListView list = (ListView) findViewById (R.id.my_itiernaries_list);	// my_itineraries_list is the id of ListView in "my_itineraries.xml"
		list.setAdapter(simpleAdapter);
	}
	
	// set click listener for each button on top menu
	public void ClickToBackMain(View target)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	public void ClickToMyItineraries(View target)
	{
		// do nothing
	}
	
	// funny foolish = =
	// concat seems not work, so I wrote "insert" method
	private static String[] insert(String[] arr, String str)
	{
		int size;
		if (arr == null)
			size = 0;
		else
			size = arr.length;
		String[] temp = new String[size + 1];
		if (arr != null)
			System.arraycopy(arr, 0, temp, 0, size);
		temp[size] = str;
		return temp;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

