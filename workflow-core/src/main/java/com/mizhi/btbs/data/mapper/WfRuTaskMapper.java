package com.mizhi.btbs.data.mapper;

import com.mizhi.btbs.domain.model.WfRuTask;
import com.mizhi.btbs.domain.query.WfRuTaskQuery;

import java.util.List;

public interface WfRuTaskMapper  {

	WfRuTask load(String id);

	void insert(WfRuTask wfRuTask);

	void update(WfRuTask wfRuTask);

	void delete(String id);

	List<WfRuTask> queryList(WfRuTaskQuery wfRuTaskQuery);

	int queryCount(WfRuTaskQuery wfRuTaskQuery);
}