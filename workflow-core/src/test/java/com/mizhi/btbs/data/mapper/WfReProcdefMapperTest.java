package com.mizhi.btbs.data.mapper;

import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;
import com.mizhi.btbs.support.JdbcTestCaseSupport;
import com.mizhi.btbs.util.IDUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WfReProcdefMapperTest extends JdbcTestCaseSupport {
	private static final Logger log = LoggerFactory.getLogger(WfReProcdefMapper.class);

	@Autowired
	private WfReProcdefMapper wfReProcdefMapper;

	@Test
	public void testLoad() {
		WfReProcdef wfReProcdef = wfReProcdefMapper.load("72dcc323-c88b-43eb-a80e-56ab15953a11");
		log.info("=======>> testLoad " + wfReProcdef);
	}

	@Test
	public void testInsert() {
		WfReProcdef wfReProcdef = new WfReProcdef();
		wfReProcdef.setId(IDUtil.generate());
		wfReProcdef.setReDeployId("feb52c5b-8694-43e6-a2a3-ddfecd90d592");
		wfReProcdef.setWfType("order");
		wfReProcdef.setGroupId("333");
		wfReProcdef.setStepCurrent(0);
		wfReProcdef.setExpressType(1);
		wfReProcdef.setExpressKeys("5555");
		wfReProcdef.setOperateType(1);

		wfReProcdefMapper.insert(wfReProcdef);
        log.info("=======>> testInsert " + wfReProcdef);
	}

	@Test
	public void testUpdate() {
		WfReProcdef wfReProcdef = new WfReProcdef();

		wfReProcdef.setId("5986bdc9-726b-46d2-8edb-f970096537f9");
		wfReProcdef.setGroupId("4444");
		wfReProcdef.setWfType("contract");
		wfReProcdef.setStepCurrent(1);
		wfReProcdef.setExpressType(2);
		wfReProcdef.setExpressKeys("66666");
		wfReProcdef.setOperateType(2);

		wfReProcdefMapper.update(wfReProcdef);
	}

	@Test
	public void testDelete() {
		wfReProcdefMapper.delete("5986bdc9-726b-46d2-8edb-f970096537f9");
	}

	@Test
	public void testQueryList() {
		WfReProcdefQuery query = new WfReProcdefQuery();

		query.setReDeployId("feb52c5b-8694-43e6-a2a3-ddfecd90d592");
		query.setGroupId("333");
		query.setWfType("order");

		List<WfReProcdef> list =  wfReProcdefMapper.queryList(query);

        log.info("=======>> testQueryList " + list);
	}

	@Test
	public void testQueryCount() {
		WfReProcdefQuery query = new WfReProcdefQuery();
		query.setReDeployId("feb52c5b-8694-43e6-a2a3-ddfecd90d592");
		int count = wfReProcdefMapper.queryCount(query);

        log.info("=======>> testQueryCount " + count);
	}
}