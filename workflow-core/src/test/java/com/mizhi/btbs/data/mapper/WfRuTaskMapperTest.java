package com.mizhi.btbs.data.mapper;

import com.mizhi.btbs.domain.model.WfRuTask;
import com.mizhi.btbs.domain.query.WfRuTaskQuery;
import com.mizhi.btbs.support.JdbcTestCaseSupport;
import com.mizhi.btbs.util.IDUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WfRuTaskMapperTest extends JdbcTestCaseSupport {
	private static final Logger log = LoggerFactory.getLogger(WfRuTaskMapper.class);

	@Autowired
	private WfRuTaskMapper wfRuTaskMapper;

	@Test
	public void testLoad() {
		WfRuTask wfRuTask = wfRuTaskMapper.load("076535ab-12b7-4d9e-a49d-7ad59cb04dcb");
		log.info("=======>> testLoad " + wfRuTask);
	}

	@Test
	public void testInsert() {
		WfRuTask wfRuTask = new WfRuTask();
		wfRuTask.setId(IDUtil.generate());
		wfRuTask.setGroupId("4444");
		wfRuTask.setReDeployId("feb52c5b-8694-43e6-a2a3-ddfecd90d592");
		wfRuTask.setReProcdefId("72dcc323-c88b-43eb-a80e-56ab15953a11");
		wfRuTask.setCurrentStep(4);
		wfRuTask.setBizId("4444");
		wfRuTask.setWfType("contract");

		wfRuTaskMapper.insert(wfRuTask);
        log.info("=======>> testInsert " + wfRuTask);
	}

	@Test
	public void testUpdate() {
		WfRuTask wfRuTask = new WfRuTask();

		wfRuTask.setId("076535ab-12b7-4d9e-a49d-7ad59cb04dcb");
		wfRuTask.setGroupId("5555");
		wfRuTask.setCurrentStep(2);
		wfRuTask.setWfType("order");

		wfRuTaskMapper.update(wfRuTask);
	}

	@Test
	public void testDelete() {
		wfRuTaskMapper.delete("076535ab-12b7-4d9e-a49d-7ad59cb04dcb");
	}

	@Test
	public void testQueryList() {
		WfRuTaskQuery query = new WfRuTaskQuery();
		query.setReDeployId("feb52c5b-8694-43e6-a2a3-ddfecd90d592");
		query.setReProcdefId("72dcc323-c88b-43eb-a80e-56ab15953a11");

		List<WfRuTask> list =  wfRuTaskMapper.queryList(query);

        log.info("=======>> testQueryList " + list);
	}

	@Test
	public void testQueryCount() {
		WfRuTaskQuery query = new WfRuTaskQuery();
		query.setReDeployId("feb52c5b-8694-43e6-a2a3-ddfecd90d592");
		query.setReProcdefId("72dcc323-c88b-43eb-a80e-56ab15953a11");

		int count = wfRuTaskMapper.queryCount(query);

        log.info("=======>> testQueryCount " + count);
	}
}