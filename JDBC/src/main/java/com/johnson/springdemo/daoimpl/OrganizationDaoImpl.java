package com.johnson.springdemo.daoimpl;

import com.johnson.springdemo.dao.OrganizationDao;
import com.johnson.springdemo.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("orgDao")
public class OrganizationDaoImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public boolean create(Organization organization) {
        return false;
    }

    public Organization getOrganization(Integer id) {
        return null;
    }

    public List<Organization> getAllOrganizations() {
        String sqlQuery = "SELECT * FROM organization";

        List<Organization> orgList = jdbcTemplate.query(sqlQuery, new OrganizationRowMapper());
        return orgList;
    }

    public boolean delete(Organization organization) {
        return false;
    }

    public boolean update(Organization organization) {
        return false;
    }

    public void cleanup() {

    }
}
