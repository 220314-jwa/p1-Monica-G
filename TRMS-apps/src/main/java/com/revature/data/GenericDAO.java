package com.revature.data;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	public int create (T newObj) throws SQLException;
	public T getById(int id);
	public List<T> getAll();
	public void update(T updatedObj)throws SQLException;;
	public void delete(T objToDelete)throws SQLException;;

}
