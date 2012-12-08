package lib;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class ObjectHandler {
	
	private boolean mExternalStorageAvailable = false;
	private boolean mExternalStorageWriteable = false;
	private File root;
	
	public ObjectHandler(Context ctx) {
		//create directory structure if it doesn't exist..

		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    // Something else is wrong. It may be one of many other states, but all we need
		    //  to know is we can neither read nor write
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		    return;
		}
		Log.d("a", "************************************");
		File home = ctx.getExternalFilesDir(null);
		root = new File(home, "myData");
		//File structure already exists..
		if(root.exists()) {
			return;
		}
		//Make the directory structure..
		
		root.mkdir();
		new File(root, "user").mkdir();
		new File(root, "hunt").mkdir();
		new File(root, "objective").mkdir();
		
	}
	
	public Object readObject(String type, int id) {
		//get the serialized object from the appropriate file and deserialize it.
		return null;
	}
	
	public void writeObject(String type, int id, Object o) {
		//serialize and write object to appropriate file.
	}
}
