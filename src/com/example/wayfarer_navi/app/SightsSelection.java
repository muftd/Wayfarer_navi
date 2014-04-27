package com.example.wayfarer_navi.app;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher.ViewFactory;
import android.app.Activity;
import android.content.Intent;



/**
 * class AddDestination
 * @author Mu 
 */

public class SightsSelection extends Activity implements OnGestureListener
{
	String message_adventure = null;
	String message_destination = null;
	String message_sightname = null;

	private ViewFlipper viewFlipper;
	private TextSwitcher nSwitcher;
	private TextSwitcher dSwitcher;
	private GestureDetector detector;

	Animation leftInAnimation;
	Animation leftOutAnimation;
	Animation rightInAnimation;
	Animation rightOutAnimation;

	String[] strsName = new String[]
	{
		"Forbidden City",
		"MSRA"
	};
	String[] strsDescription = new String[]
	{
		"This is Forbidden City!",
		"This is MSRA!"
	};
	private int mCounter = 0;

	private static final String TAG = "SightsSelection";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sights_selection);
		
		detector = new GestureDetector(this, this);

		// set text for top content
		// 1. adventure name
		// 2. destination name
		TextView adventure_name = (TextView) findViewById(R.id.adventure_name);
		TextView destination_name = (TextView) findViewById(R.id.destination_name);

		Intent intent = getIntent();
		message_adventure = intent.getStringExtra(intent.EXTRA_TEXT);
		adventure_name.setText(message_adventure);
		message_destination = intent.getStringExtra("name_destination_edittext");
		destination_name.setText(message_destination);

		// add View to viewFlipper
		viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		
		viewFlipper.addView(getImageView(R.drawable.forbiddencity));
		viewFlipper.addView(getImageView(R.drawable.msralogo));

		// TextSwitcher "sight_name"config
		nSwitcher = (TextSwitcher) findViewById(R.id.sight_name);
		nSwitcher.setFactory(new ViewFactory()
		{
			public View makeView()
			{
				TextView tv = new TextView(SightsSelection.this);
				tv.setGravity(Gravity.START);
				tv.setTextSize(18);
				return tv;
			}
		});
		nSwitcher.setCurrentText(strsName[0]);
		
		// TextSwitcher "sight_description"config
		dSwitcher = (TextSwitcher) findViewById(R.id.sight_description);
		dSwitcher.setFactory(new ViewFactory()
		{
			public View makeView()
			{
				TextView tv = new TextView(SightsSelection.this);
				tv.setGravity(Gravity.START);
				tv.setTextSize(18);
				return tv;
			}
		});
		dSwitcher.setCurrentText(strsDescription[0]);

		// define animation
		leftInAnimation = AnimationUtils.loadAnimation(this, R.anim.left_in);
		leftOutAnimation = AnimationUtils.loadAnimation(this, R.anim.left_out);
		rightInAnimation = AnimationUtils.loadAnimation(this, R.anim.right_in);
		rightOutAnimation = AnimationUtils.loadAnimation(this, R.anim.right_out);
		
	}
		// define method to get image for viewFlipper
		private ImageView getImageView(int id)
		{
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(id);
			imageView.setScaleType(ImageView.ScaleType.CENTER);
			return imageView;
		}


		// deal touch event based on gesture
		@Override
		public boolean onTouchEvent(MotionEvent event)
		{
			return this.detector.onTouchEvent(event);
		}
		
		@Override
		public boolean onDown(MotionEvent e)
		{
			return false;
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
		{
			Log.i(TAG, "e1 = " + e1.getX() + ", e2 = " + e2.getX() + ", e1-e2 = " + (e1.getX()-e2.getX()));

			if (e1.getX() - e2.getX() > 120)
			{
				viewFlipper.setInAnimation(leftInAnimation);
				viewFlipper.setOutAnimation(leftOutAnimation);
				viewFlipper.showPrevious();
				
				if (mCounter == 0)
					mCounter += strsName.length;
			
				mCounter--;
				nSwitcher.setInAnimation(leftInAnimation);
				nSwitcher.setOutAnimation(leftOutAnimation);
				nSwitcher.setText(strsName[mCounter % strsName.length]);
				
				dSwitcher.setInAnimation(leftInAnimation);
				dSwitcher.setOutAnimation(leftOutAnimation);
				dSwitcher.setText(strsDescription[mCounter % strsDescription.length]);
				return true;
			}
			else if (e1.getX() - e2.getX() < -120)
			{
				viewFlipper.setInAnimation(rightInAnimation);
				viewFlipper.setOutAnimation(rightOutAnimation);
				viewFlipper.showNext();

				mCounter++;
				nSwitcher.setInAnimation(rightInAnimation);
				nSwitcher.setOutAnimation(rightOutAnimation);
				nSwitcher.setText(strsName[mCounter % strsName.length]);
				
				dSwitcher.setInAnimation(rightInAnimation);
				dSwitcher.setOutAnimation(rightOutAnimation);
				dSwitcher.setText(strsDescription[mCounter % strsDescription.length]);
				return true;
			}
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e)
		{

		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
		{
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e)
		{

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e)
		{
			return false;
		}

	public void ClickToAddSights(View target)
	{
		Intent intent = new Intent(this, MyItineraries.class);
		message_sightname = strsName[mCounter % strsName.length];
		intent.putExtra("sight_name", message_sightname);
		intent.putExtra(intent.EXTRA_TEXT, message_adventure);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
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