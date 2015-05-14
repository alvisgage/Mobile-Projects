package rga001.mobile4013.razorrunner;

import java.sql.Time;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class profile implements Parcelable{
	private String name;
	private int age;
	private String gender;
	private double weight;
	
	public profile(){
	}
	public profile(String n, int a, String g, double w){
		this.name = n;
		this.age = a;
		this.gender = g;
		this.weight = w;
	}
	public String getName(){
		return this.name;
	}
	public int getAge(){
		return this.age;
	}
	public String getGender(){
		return this.gender;
	}
	public double getWeight(){
		return this.weight;
	}
	public void setName(String n){
		this.name = n;
	}
	public void setAge(int a){
		this.age = a;
	}
	public void setWeight(double w){
		this.weight = w;
	}
	public void setGender(String s){
		this.gender = s;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeString(gender);
		dest.writeDouble(weight);
	}
	
	public static final Parcelable.Creator<profile> CREATOR = new Creator<profile>() {
		public profile createFromParcel(Parcel source){
			profile data = new profile();
			data.name = source.readString();
			data.age = source.readInt();
			data.gender = source.readString();
			data.weight = source.readDouble();
			return data;
		}
		@Override
		public profile[] newArray(int size){
			return new profile[size];
		}
	};
	
	@Override
	public String toString(){
		return this.name;
	}

}
