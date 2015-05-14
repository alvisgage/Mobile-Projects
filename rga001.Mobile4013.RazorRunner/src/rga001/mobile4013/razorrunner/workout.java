package rga001.mobile4013.razorrunner;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

public class workout implements Parcelable{
	private String name;
	private int pos;
	private double distance;
	private Date datetime;
	private long duration;
	private int steps;
	private double caloriesburnt;
	private String distanceunit;
	public workout(){
		distance = 1.1;
		distanceunit = "miles";
		Calendar cal = Calendar.getInstance();
		datetime = cal.getTime();
		duration = cal.getTimeInMillis();
		steps = 100;
		caloriesburnt = 0;
		pos = 0;
		name = "Workout_" + DateFormat.format("yyyy_mm_dd", datetime);
	}
	public int getSteps(){
		return this.steps;
	}
	public int getPosition(){
		return this.pos;
	}
	public void setPosition(int p){
		this.pos = p;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String n){
		if (n.equals("")){
			this.name = "Workout_" + DateFormat.format("yyyy_mm_dd", this.datetime);
		}else{
			this.name = n;
		}
	}
	public String getDate(){
		return (String) DateFormat.format("MMM d, yyyy", this.datetime);
	}

	public String getDuration(){		
		return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(this.duration),
	            TimeUnit.MILLISECONDS.toMinutes(this.duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(this.duration)),
	            TimeUnit.MILLISECONDS.toSeconds(this.duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(this.duration)));
	}
	public double getDistance(){
		return this.distance;
	}
	public String getUnit(){
		return this.distanceunit;
	}
	public double getCaloriesburned(){
		return this.caloriesburnt;
	}
	public void setDuration(long d){
		this.duration = d;
	}
	public void setCaloriesBurned(double c){
		this.caloriesburnt = c;
	}
	public static final Parcelable.Creator<workout> CREATOR = new Creator<workout>() {
		public workout createFromParcel(Parcel source){
			workout data = new workout();
			data.name = source.readString();
			data.pos = source.readInt();
			data.distance = source.readDouble();
			data.datetime = (Date) source.readSerializable();
			data.duration = source.readLong();
			data.steps = source.readInt();
			data.caloriesburnt = source.readDouble();
			return data;
		}
		@Override
		public workout[] newArray(int size){
			return new workout[size];
		}
	};
	
	@Override
	public String toString(){
		return name;
	}
	@Override
	public int describeContents(){
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags){
		dest.writeString(name);
		dest.writeInt(pos);
		dest.writeDouble(distance);
		dest.writeSerializable(datetime);
		dest.writeLong(duration);
		dest.writeInt(steps);
		dest.writeDouble(caloriesburnt);
	}
	
}
