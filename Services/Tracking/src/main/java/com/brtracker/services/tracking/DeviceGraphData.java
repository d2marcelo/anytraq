package com.brtracker.services.tracking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.brtracker.shared.payload.tracking.AggregatedTotal;


public abstract class DeviceGraphData {

	private Map<String,Map<String,String>> totals = new HashMap<String,Map<String,String>>();
	
	public List<AggregatedTotal> getTotals () {
		List<AggregatedTotal> total = new ArrayList<AggregatedTotal>();
		for (Map.Entry<String, Map<String,String>> m : totals.entrySet()){
			Map<String,String> inner = m.getValue();
			AggregatedTotal agg = new AggregatedTotal();
			agg.setKey(m.getKey());
			agg.setValue(inner.get("value"));
			agg.setUnitType(inner.get("unit"));
			total.add(agg);		
		}
		return total;
	}
	
	public void addAggreagatedTotal (String key, String value, String unit){
		Map<String,String> v = new HashMap<String,String>();
		if (totals.containsKey(key)){
			v = totals.get(key);
		}
		v.put("value", value);
		v.put("unit", unit);
		totals.put(key, v);
	}
	
	public String getAggregatedTotal (String key){
		if (totals.containsKey(key))
		{
			return totals.get(key).get("value");
		}else 
			return null;
	}
	public abstract String processX (String x);
	public abstract String processY (String y);
	public abstract void add (String x, String y, String latitude, String longitude, Date date);
	public abstract String getXunit ();
	public abstract String getYunit ();
		
}
