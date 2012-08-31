package org.exitsoft.showcase.vcsadmin.unit;

import javax.sql.DataSource;

import org.exitsoft.common.unit.Fixtures;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 业务单元测试基类
 * 
 * @author vincent
 *
 */
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class ManagerTestCaseSuper extends AbstractTransactionalJUnit4SpringContextTests{
	
	private static DataSource dataSourceHandler;
	
	protected DataSource dataSource;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		super.setDataSource(this.dataSource);
	}
	
	@Before
	public void reloadSampleData() throws Exception {
		if (dataSourceHandler == null) {
			Fixtures.loadData(dataSource, "/sample-data.xml");
			dataSourceHandler = dataSource;
		}
		
	}
	
	@AfterClass
	public static void cleanData() throws Exception {
		Fixtures.deleteData(dataSourceHandler, "/sample-data.xml");
		dataSourceHandler = null;
	}
}