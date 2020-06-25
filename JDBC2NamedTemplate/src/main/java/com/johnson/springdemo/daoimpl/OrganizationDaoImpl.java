package com.johnson.springdemo.daoimpl;

import com.johnson.springdemo.dao.OrganizationDao;
import com.johnson.springdemo.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private NamedParameterJdbcTemplate NamedParamJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        NamedParamJdbcTemplate = new NamedParameterJdbcTemplate(ds);
    }

    public boolean create(Organization organization) {
        SqlParameterSource beanParams = new BeanPropertySqlParameterSource(organization);
        String sqlQuery = "INSERT INTO organization (company_name, year_of_incorporation, postal_code, employee_count, slogan)" +
                "VALUES(:companyName, :yearOfIncorporation, :postalCode, :employeeCount ,:slogan)";
        return NamedParamJdbcTemplate.update(sqlQuery, beanParams) == 1;
        //Object[] args = new Object[] {organization.getCompanyName(), organization.getYearOfIncorporation(), organization.getPostalCode(),
         //       organization.getEmployeeCount(), organization.getSlogan()};
        //return jdbcTemplate.update(sqlQuery, args) == 1;
    }

    public Organization getOrganization(Integer id) {
        SqlParameterSource params = new MapSqlParameterSource("ID", id);
        String sqlQuery = "SELECT * FROM organization WHERE id = :ID";

        //Object[] args = new Object[] {id};
        Organization org = NamedParamJdbcTemplate.queryForObject(sqlQuery, params, new OrganizationRowMapper());
        return org;
    }

    public List<Organization> getAllOrganizations() {
        String sqlQuery = "SELECT * FROM organization";

        List<Organization> orgList = NamedParamJdbcTemplate.query(sqlQuery, new OrganizationRowMapper());
        return orgList;
    }

    public boolean delete(Organization organization) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(organization);
        String sqlQuery = "DELETE FROM organization WHERE id = :id";
        //Object[] args = new Object[] {organization.getId()};

        return NamedParamJdbcTemplate.update(sqlQuery, params) == 1;
    }

    public boolean update(Organization organization) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(organization);
        String sqlQuery = "UPDATE organization SET slogan = :slogan WHERE id = :id";
        //Object[] args = new Object[] {organization.getSlogan(), organization.getId()};

        return NamedParamJdbcTemplate.update(sqlQuery, params) == 1;
    }

    public void cleanup() {
        String sqlQuery = "TRUNCATE TABLE organization ";
        NamedParamJdbcTemplate.getJdbcOperations().execute(sqlQuery);

    }
}
