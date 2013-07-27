package com.brtracker.services.controller.dao;

import java.util.List;
import com.brtracker.shared.payload.controller.LoginRequest;
import com.brtracker.shared.utils.spring.HibernateHelperException;

public interface UserDAO {

		public void add (Object object) throws HibernateHelperException;
		public Object get (Long id) throws HibernateHelperException;
		public Object getUser (Long accountId, String username);
		public Object getUser (Long id);
		public Object login (LoginRequest login) throws HibernateHelperException;
		public boolean isLastAdmin (Long id);
		public long listTotalUserCount(Object userRequest);
		public List<?> listUser(Object userRequest);
		public void update(Object user)throws HibernateHelperException;
		public void delete(Object user)throws HibernateHelperException;
		
	
}
