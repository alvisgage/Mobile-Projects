package edu.uark.csce.mobile4013_sensor_1;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements SensorEventListener, OnClickListener {

	private Button btnStart, btnStop;
	private LinearLayout layout;

	private boolean isStarted = false;
	private List<AcceleData> sensorData;
	private int sensorDataSize = 0;
	private int updates = 0;
	private int updateInterval = 50;
	private int maxSize = 500;
	private int maxDelay = 10000;
	private View mChart;

	private SensorManager sensorManager;

	private Handler myHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnStart = (Button) findViewById(R.id.start_button);
		btnStop = (Button) findViewById(R.id.stop_button);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);

		layout = (LinearLayout) findViewById(R.id.chart_layout);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorData = new ArrayList<AcceleData>();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isStarted == true) {
			sensorManager.unregisterListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_button:
			// start reading
			btnStart.setEnabled(false);
			btnStop.setEnabled(true);
			//sensorData = new ArrayList<AcceleData>();
			isStarted = true;
			Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
			Log.v("YTX", "register sensor");
			break;
		case R.id.stop_button:
			// stop reading and show data in chart
			btnStart.setEnabled(true);
			btnStop.setEnabled(false);
			isStarted = false;
			sensorManager.unregisterListener(this);
			Log.v("YTX", "unregister sensor");
			//layout.removeAllViews();
			//openChart();
			break;
		default:
			break;
		}

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && isStarted) {
			final double x = event.values[0];
			final double y = event.values[1];
			final double z = event.values[2];
			final long timestamp = System.currentTimeMillis();
			
			myHandler.post(new Runnable() {
				public void run() {
					AcceleData data = new AcceleData(timestamp, x, y, z);
					if(sensorData.size() > 0){
						if(data.getTimestamp() - sensorData.get(sensorData.size()-1).getTimestamp() 
								> maxDelay) {
							Log.d("YTX", "TS = " + (data.getTimestamp() - sensorData.get(sensorData.size()-1).getTimestamp()));
							sensorData = new ArrayList<AcceleData>();
						}
					}
					sensorData.add(data);
					sensorDataSize ++;
					if (sensorData.size() > maxSize) {
						sensorData.remove(0);
					}
					if(sensorDataSize >= updates * updateInterval) {
						updates ++;
						layout.removeAllViews();
						openChart();
					}
				}
			});
		}
	}

	private void openChart() {
		if (sensorData != null && sensorData.size() > 0) {
			AcceleData base = (AcceleData) sensorData.get(0);
			long t = base.getTimestamp();
			XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

			XYSeries xSeries = new XYSeries("X");
			XYSeries ySeries = new XYSeries("Y");
			XYSeries zSeries = new XYSeries("Z");

			for (int i = 0; i < sensorData.size(); i++) {
				AcceleData di = (AcceleData) sensorData.get(i);
				xSeries.add(di.getTimestamp() - t, di.getX());
				ySeries.add(di.getTimestamp() - t, di.getY());
				zSeries.add(di.getTimestamp() - t, di.getZ());
			}

			dataset.addSeries(xSeries);
			dataset.addSeries(ySeries);
			dataset.addSeries(zSeries);

			XYSeriesRenderer xRenderer = new XYSeriesRenderer();
			xRenderer.setColor(Color.RED);
			xRenderer.setPointStyle(PointStyle.CIRCLE);
			xRenderer.setFillPoints(true);
			xRenderer.setLineWidth(1);
			xRenderer.setDisplayChartValues(false);

			XYSeriesRenderer yRenderer = new XYSeriesRenderer();
			yRenderer.setColor(Color.GREEN);
			yRenderer.setPointStyle(PointStyle.CIRCLE);
			yRenderer.setFillPoints(true);
			yRenderer.setLineWidth(1);
			yRenderer.setDisplayChartValues(false);

			XYSeriesRenderer zRenderer = new XYSeriesRenderer();
			zRenderer.setColor(Color.BLUE);
			zRenderer.setPointStyle(PointStyle.CIRCLE);
			zRenderer.setFillPoints(true);
			zRenderer.setLineWidth(1);
			zRenderer.setDisplayChartValues(false);

			XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
			multiRenderer.setXLabels(0);
			multiRenderer.setLabelsColor(Color.RED);
			multiRenderer.setChartTitle("t vs (x,y,z)");
			multiRenderer.setXTitle("Sensor Data");
			multiRenderer.setYTitle("Values of Acceleration");
			multiRenderer.setZoomButtonsVisible(true);
			for (int i = 0; i < sensorData.size(); i++) {
				AcceleData di = (AcceleData) sensorData.get(i);
				multiRenderer
						.addXTextLabel(i + 1, "" + (di.getTimestamp() - t));
			}

			multiRenderer.addSeriesRenderer(xRenderer);
			multiRenderer.addSeriesRenderer(yRenderer);
			multiRenderer.addSeriesRenderer(zRenderer);

			// Creating a Line Chart
			mChart = ChartFactory.getLineChartView(getBaseContext(), dataset,
					multiRenderer);

			// Adding the Line Chart to the LinearLayout
			layout.addView(mChart);

		}
	}
}
