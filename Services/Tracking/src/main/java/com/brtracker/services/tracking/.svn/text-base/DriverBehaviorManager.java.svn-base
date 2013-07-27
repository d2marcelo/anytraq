package com.brtracker.services.tracking;

import java.util.List;
import java.util.Map;
import com.brtracker.services.tracking.dao.DriverBehaviorDAOImpl;
import com.brtracker.shared.payload.tracking.DriverBehaviorResponse;
import com.brtracker.shared.payload.tracking.data.DriverBehavior;
import com.brtracker.shared.utils.DateUtil;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ServiceResponseException;

public class DriverBehaviorManager extends DriverBehaviorDAOImpl {

	public String list(Long driverId, int duration) {
		DriverBehaviorResponse driverBehaviorResponse = new DriverBehaviorResponse();
		try {
			List<DriverBehavior> driverBehaviors = super.getDriverBehavior(driverId, DateUtil.getDiffInHours(duration));
			for (DriverBehavior db: driverBehaviors){
				Map<String,String> map = (Map<String, String>) JSONMapper.toObject(db.getStats(), Map.class);
				String lat = db.getLatitude();
				String lon = db.getLongitude();
				for (Map.Entry<String, String> m : map.entrySet()){
				String key = m.getKey();
				driverBehaviorResponse.addDriverBehavior(key, lat, lon);
				driverBehaviorResponse.addAggregatedTotal(key);
				}
			}
			return JSONMapper.toString(driverBehaviorResponse);
		
		}catch (JSONMapperException e){
			return ServiceResponseException.generateResponse(e);
		}
	}
		
}
