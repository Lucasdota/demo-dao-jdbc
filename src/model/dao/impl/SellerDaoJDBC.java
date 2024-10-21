package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	// returns a seller by id
	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			// tests if a result came
			if (rs.next()) {
				/* time to get the ResultSet table that came
				   and instanciate it as objects in the memory
				   that's mandatory when working with OOP
				 */		
				// creates Department
				Department dep = instantiateDepartment(rs);	
				// now creates Seller to link with its own Department
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			// if a result didn't come, returns null
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			// close resources used
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
			/* doesn't need to close connection
			 * because you are going to use the connection again
			 * when using the other methods
			 */
		}	
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		// links Seller with Department
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) 
			// only propagates exception because findById is already treating it
			throws SQLException {
		Department dep = new Department();
		// gets the departmentId column value of the table
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
