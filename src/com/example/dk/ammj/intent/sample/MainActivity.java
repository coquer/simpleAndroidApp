package com.example.dk.ammj.intent.sample;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final int BROWSE_IMAGE_REQUEST_CODE = 0;
	
	/*
	 * Set event listeners.
	 * 
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button makeCall = (Button)findViewById(R.id.make_call);
		makeCall.setOnClickListener(makeCallLiter);
		
		Button makeDial = (Button)findViewById(R.id.make_dial);
		makeDial.setOnClickListener(makeDialLister);
		
		Button imagePicker = (Button)findViewById(R.id.image_picker);
		imagePicker.setOnClickListener(imagePickerLister);
		
		
		Button searchBtn = (Button)findViewById(R.id.search_btn);
		searchBtn.setOnClickListener(searchQueryLister);
		
		Button flashLight = (Button)findViewById(R.id.flash_light_btn);
		flashLight.setOnClickListener(flashLightLister);
		
		Button next = (Button)findViewById(R.id.send_msn_view);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	setContentView(R.layout.send_message_view);
            	Button back = (Button)findViewById(R.id.go_back);
            	back.setOnClickListener(gobackLister);
            	
            	Button sendMessage = (Button)findViewById(R.id.intent_send);
            	sendMessage.setOnClickListener(sendMessageInent);
            	
            	
            }

        });
		
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	/*
	 * Process image method
	 * */

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

	    switch(requestCode) { 
	    case BROWSE_IMAGE_REQUEST_CODE:
	        if(resultCode == RESULT_OK){  
	            Uri selectedImage = imageReturnedIntent.getData();
	            InputStream imageStream = null;
				try {
					imageStream = getContentResolver().openInputStream(selectedImage);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
	            System.out.println(yourSelectedImage.toString());
	        }
	    }
	}
	
	/*
	 * to do when a event is trigger..
	 * */
	
	private OnClickListener makeCallLiter = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			
			// TODO Auto-generated method stub
			Intent ad = new Intent("android.intent.action.DIAL");
			ad.setData(Uri.parse("tel:555-666-7777"));
			startActivity(ad);
		}
	};
	
	private OnClickListener makeDialLister = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent ac = new Intent("android.intent.action.CALL");
			ac.setData(Uri.parse("tel:555-555-5555"));
			startActivity(ac);
		}
	};
	
	private OnClickListener imagePickerLister = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent pickPhoto = new Intent(Intent.ACTION_PICK);
			pickPhoto.setType("image/*");
			startActivityForResult(pickPhoto, BROWSE_IMAGE_REQUEST_CODE);
		}
	};
	
	private OnClickListener searchQueryLister = new OnClickListener(){
		@Override
		public void onClick(View v) {			
			/*To show query to be search for....
			 * */
			EditText searchQuery;
			searchQuery = (EditText)findViewById(R.id.search_query);
			String searchQueryStr = searchQuery.getText().toString();
			Intent aws = new Intent("android.intent.action.WEB_SEARCH");
			aws.putExtra(SearchManager.QUERY, searchQueryStr);
			startActivity(aws);
		}
		
	};
	
	private OnClickListener flashLightLister = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Camera phoneCamara;
			phoneCamara = Camera.open();
			Camera.Parameters phoneCamaraParams = phoneCamara.getParameters();
            String stringFlashMode;
            stringFlashMode = phoneCamaraParams.getFlashMode();
            if (stringFlashMode.equals("torch")){
            	phoneCamaraParams.setFlashMode("on");
            }else{
            	phoneCamaraParams.setFlashMode("torch");
            	phoneCamara.setParameters(phoneCamaraParams);
            }
		}
		
	};
	
	private OnClickListener sendMessageInent = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			EditText txtSent;
			txtSent = (EditText)findViewById(R.id.text_tobe_send);
			String txtToBesend = txtSent.getText().toString();
			Intent ast = new Intent("android.intent.action.SEND");
			ast.setType("text/plain");
			ast.putExtra("android.intent.extra.TEXT", txtToBesend);
			startActivity(ast);
			
		}
		
	};
	
	private OnClickListener gobackLister = new OnClickListener(){

		@Override
		public void onClick(View v) {
			setContentView(R.layout.activity_main);
		}
		
	};
	
	
	
}
