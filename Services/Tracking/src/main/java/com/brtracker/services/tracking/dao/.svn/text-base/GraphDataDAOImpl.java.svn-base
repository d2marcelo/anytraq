package com.brtracker.services.tracking.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.brtracker.shared.payload.tracking.data.GraphData;
import com.brtracker.shared.utils.logging.MyLogger;

public class GraphDataDAOImpl extends TrackingDAOImpl  {
	
	private MyLogger logger = new MyLogger(GraphDataDAOImpl.class);
	@SuppressWarnings("unchecked")
	
	public List<GraphData> getGraphData (Long driverId, int dataType) {
		return (List<GraphData>) find("from GraphData where driverId="+driverId +" and graphDataType="+dataType);
	}

	
	public List<GraphData> getGraphData (Long driverId, int dataType, Date date) {
		logger.logInfo("DriverId:"+ driverId+" dataType: "+dataType+" date:"+date);
		Criteria criteria = createCriteria(GraphData.class);
		criteria.add(Restrictions.eq("driverId", driverId));
		criteria.add(Restrictions.eq("graphDataType", dataType));
		criteria.add(Restrictions.gt("dataCreated", date));
		List<GraphData> list = criteria.list();
		logger.logInfo("Return size:"+ list.size());
		return list;
	}
	
	
	
}
