package com.example.wayfarer_navi.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


/**
 * class MainActivity
 * description: display the main page
 * @author Mu 
 */
public class MainActivity extends Activity {

	private String[] listText = new String[]
	{"My Itineraries", "New Itineraries", "Browse", "Popular Itineraries", "Sign out"};


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Create list, and its element is map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < listText.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("option", listText[i]);
			listItems.add(listItem);
		}
		// Create a SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
			R.layout.mainpage_option_text,
			new String[]{"option"},
			new int[]{R.id.option_text});	// option_text is the id of TextView in "mainpage_option_text"
		ListView list = (ListView) findViewById (R.id.mainpage_option_list);	// mainpage_option_list is the id of ListView in "activity_main"
		list.setAdapter(simpleAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Intent intent = null;
				switch (position)
				{
					case 0: intent = new Intent(parent.getContext(), MyItineraries.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
							startActivity(intent);
							break;
					case 1: intent = new Intent(parent.getContext(), NameNewAdventure.class);
						 	startActivity(intent);	// Q: If put startActivity outside the switch, program will crash. I don't know why. Is the reason Intent may be discarded?
						 	break;
					default:
							System.out.println(position+" click");
						    break;
				
				}
				
			}
		});
	}
	
	// set click listener for each button on top menu
	public void ClickToBackMain(View target)
	{
		System.out.println("click back to main");
	}

	public void ClickToMyItineraries(View target)
	{
		Intent intent = new Intent(this, MyItineraries.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

