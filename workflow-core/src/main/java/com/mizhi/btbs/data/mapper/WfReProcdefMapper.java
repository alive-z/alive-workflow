package com.mizhi.btbs.data.mapper;

import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;

import java.util.List;

public interface WfReProcdefMapper  {

	WfReProcdef load(String id);

	void insert(WfReProcdef wfReProcdef);

	void update(WfReProcdef wfReProcdef);

	void delete(String id);

	List<WfReProcdef> queryList(WfReProcdefQuery wfReProcdefQuery);

	int queryCount(WfReProcdefQuery wfReProcdefQuery);
}