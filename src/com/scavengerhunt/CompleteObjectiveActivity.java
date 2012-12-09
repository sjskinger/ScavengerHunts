package com.scavengerhunt;

import lib.ObjectHandler;
import model.Objective;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CompleteObjectiveActivity extends Activity implements LocationListener{
	private static final int RESULT_LOAD_IMAGE = 1;
	private static final int CAMERA_CAPTURE_IMAGE = 2;
	private static final int PIC_CROP = 3;
	private ImageView img;
	private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complete_objective);
		Intent i = getIntent();
		int id = i.getIntExtra("objectiveId", -1);
		ObjectHandler objHandler = (ObjectHandler) i.getSerializableExtra("objectHandler");
		if(id == -1) {
			Toast.makeText(CompleteObjectiveActivity.this, "No objective data found!",Toast.LENGTH_LONG).show();
			finish();
		}
		Objective o = (Objective) objHandler.readObject(ObjectHandler.DATATYPE_OBJECTIVE, id);
		TextView desc = (TextView) findViewById(R.id.completingObjectiveDescription);
		TextView name = (TextView) findViewById(R.id.completingObjectiveName);
		name.setText(o.getName());
		desc.setText(o.getDescription());
		img = (ImageView) findViewById(R.id.completingObjectiveImageView);
		//show the image here...
		
		//set listener to get an image from the gallery.
		findViewById(R.id.btnCompletingObjAddPhotoGallery).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent i = new Intent(Intent.ACTION_PICK, 
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);						 
				startActivityForResult(i, RESULT_LOAD_IMAGE);
				
			}
		}); 
		
		//set listener to get an image from the camera.
		findViewById(R.id.btnCompletingObjAddPhotoCamera).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					//we will handle the returned data in onActivityResult
					startActivityForResult(i, CAMERA_CAPTURE_IMAGE);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(CompleteObjectiveActivity.this, 
							"Camera not found!", Toast.LENGTH_LONG).show();
				}
			}
			
		});
		
		//////////////////////Getting the user coordinates////////////////
		latituteField = (TextView) findViewById(R.id.completingObjectiveLatitude);
	    longitudeField = (TextView) findViewById(R.id.completingObjectiveLongitude);

	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the location provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);

	    // Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " has been selected.");
	      onLocationChanged(location);
	    } else {
	      latituteField.setText("Location not available");
	      longitudeField.setText("Location not available");
	    }
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	    if(resultCode == RESULT_OK && data != null) {
		     if (requestCode == RESULT_LOAD_IMAGE) {
		         Uri selectedImage = data.getData();
		         String[] filePathColumn = { MediaStore.Images.Media.DATA };
		 
		         Cursor cursor = getContentResolver().query(selectedImage,
		                 filePathColumn, null, null, null);
		         cursor.moveToFirst();
		 
		         int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		         // String picturePath contains the path of selected Image
		         String picturePath = cursor.getString(columnIndex);
		         cursor.close();

	            img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	            
		     } else if(requestCode == CAMERA_CAPTURE_IMAGE) {
		    	 Uri selectedImage = data.getData();
		    	 //add cropping to that image..
		    	 try {
					//call the standard crop action intent (the user device may not support it)
					 Intent cropIntent = new Intent("com.android.camera.action.CROP");
					 //indicate image type and Uri
					 cropIntent.setDataAndType(selectedImage, "image/*");
					 //set crop properties
					 cropIntent.putExtra("crop", "true");
					 //indicate aspect of desired crop
					 cropIntent.putExtra("aspectX", 1);
					 cropIntent.putExtra("aspectY", 1);
					 //indicate output X and Y
					 cropIntent.putExtra("outputX", 256);
					 cropIntent.putExtra("outputY", 256);
					 //retrieve data on return
					 cropIntent.putExtra("return-data", true);
					 //start the activity - we handle returning in onActivityResult
					 startActivityForResult(cropIntent, PIC_CROP);
				} catch (Exception e) {
					Toast.makeText(CompleteObjectiveActivity.this, 
							"Can't crop the image!", Toast.LENGTH_LONG).show();
				}
		     } else if(requestCode == PIC_CROP) {
		    	 Bundle extras = data.getExtras();
		    	 Bitmap image = extras.getParcelable("data");
		    	 img.setImageBitmap(image);
		     }
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_complete_objective, menu);
		return true;
	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the location listener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}
	  
	@Override
	public void onLocationChanged(Location location) {
		 latituteField.setText(String.valueOf((int) (location.getLatitude())));
	     longitudeField.setText(String.valueOf((int) (location.getLongitude())));
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// Auto-generated method stub		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// Auto-generated method stub
		
	}

}
