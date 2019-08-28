package com.mizhi.btbs.data.mapper;

import com.mizhi.btbs.constant.Constant;
import com.mizhi.btbs.domain.model.WfReDeployment;
import com.mizhi.btbs.domain.query.WfReDeploymentQuery;
import com.mizhi.btbs.support.JdbcTestCaseSupport;
import com.mizhi.btbs.util.IDUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WfReDeploymentMapperTest extends JdbcTestCaseSupport {
	private static final Logger log = LoggerFactory.getLogger(WfReDeploymentMapper.class);

	@Autowired
	private WfReDeploymentMapper wfReDeploymentMapper;

	@Test
	public void testLoad() {
		WfReDeployment wfReDeployment = wfReDeploymentMapper.load("9760372c-fb82-47f1-b0d7-cecaaebd24a1");
		log.info("=======>> testLoad " + wfReDeployment);
	}

	@Test
	public void testInsert() {
		WfReDeployment wfReDeployment = new WfReDeployment();
		wfReDeployment.setId(IDUtil.generate());
		wfReDeployment.setGroupId("333" + Constant.SPLIT + "444");
		wfReDeployment.setStepNum(4);
		wfReDeployment.setWfType("order");
		wfReDeploymentMapper.insert(wfReDeployment);
        log.info("=======>> testInsert " + wfReDeployment);
	}

	@Test
	public void testUpdate() {
		WfReDeployment wfReDeployment = new WfReDeployment();

		wfReDeployment.setId("9760372c-fb82-47f1-b0d7-cecaaebd24a1");
		wfReDeployment.setWfType("contract");
		wfReDeployment.setStepNum(5);
		wfReDeployment.setGroupId("444" + Constant.SPLIT + "333");

		wfReDeploymentMapper.update(wfReDeployment);
	}

	@Test
	public void testDelete() {
		wfReDeploymentMapper.delete("9760372c-fb82-47f1-b0d7-cecaaebd24a1");
	}

	@Test
	public void testQueryList() {
		WfReDeploymentQuery query = new WfReDeploymentQuery();
		query.setGroupId("444");
		query.setWfType("order");

		List<WfReDeployment> list =  wfReDeploymentMapper.queryList(query);

        log.info("=======>> testQueryList " + list);
	}

	@Test
	public void testQueryCount() {
		WfReDeploymentQuery query = new WfReDeploymentQuery();
		query.setGroupId("444");
		query.setWfType("order");
		int count = wfReDeploymentMapper.queryCount(query);

        log.info("=======>> testQueryCount " + count);
	}
}