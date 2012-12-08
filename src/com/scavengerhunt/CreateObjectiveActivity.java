package com.scavengerhunt;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;


public class CreateObjectiveActivity extends Activity {
	private ImageView imageView;
	private static final int RESULT_LOAD_IMAGE = 1;
	private static final int CAMERA_CAPTURE_IMAGE = 2;
	private static final int PIC_CROP = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_objective);
		//get the image view.
		imageView = (ImageView) findViewById(R.id.createObjectiveImageView);
		
		//set listener to get an image from the gallery.
		findViewById(R.id.btnAddPhotoGallery).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Intent i = new Intent(Intent.ACTION_PICK, 
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);						 
				startActivityForResult(i, RESULT_LOAD_IMAGE);
				
			}
		}); 
		
		//set listener to get an image from the camera.
		findViewById(R.id.btnAddPhotoCamera).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Intent i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					//we will handle the returned data in onActivityResult
					startActivityForResult(i, CAMERA_CAPTURE_IMAGE);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(CreateObjectiveActivity.this, 
							"Camera not found!", Toast.LENGTH_LONG).show();
				}
			}
			
		});
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

		            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		            
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
						Toast.makeText(CreateObjectiveActivity.this, 
								"Can't crop the image!", Toast.LENGTH_LONG).show();
					}
			     } else if(requestCode == PIC_CROP) {
			    	 Bundle extras = data.getExtras();
			    	 Bitmap img = extras.getParcelable("data");
			    	 imageView.setImageBitmap(img);
			     }
		    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_objective, menu);
		return true;
	}

}
