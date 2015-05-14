package edu.uark.csce.mobile.intentexample;
import android.os.Parcel;
import android.os.Parcelable;

public class myData implements Parcelable {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static final Parcelable.Creator<myData> CREATOR = new Creator<myData>() {  
		 public myData createFromParcel(Parcel source) {  
			 myData data = new myData();  
		     data.username = source.readString();  
		     data.password = source.readString();  
		     return data;  
		 }

		@Override
		public myData[] newArray(int size) {
			return new myData[size];
		} 
	};
		 
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(username);  
		dest.writeString(password); 
	}
};
