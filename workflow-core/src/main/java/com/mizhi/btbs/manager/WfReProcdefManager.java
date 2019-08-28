package com.mizhi.btbs.manager;

import com.mizhi.btbs.data.mapper.WfReProcdefMapper;
import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class WfReProcdefManager  {
    private static final Logger log = LoggerFactory.getLogger(WfReProcdefManager.class);

    @Autowired
    private WfReProcdefMapper wfReProcdefMapper;

    public WfReProcdef load(String id) {
        return wfReProcdefMapper.load(id);
    }

    public WfReProcdef singleResult(WfReProcdefQuery wfReProcdefQuery){
        List<WfReProcdef> wfReProcdefs = wfReProcdefMapper.queryList(wfReProcdefQuery);
        if (CollectionUtils.isEmpty(wfReProcdefs)) {
            return null;
        }
        return wfReProcdefs.get(0);
    }

    public WfReProcdef getByIdAndStep(String id, Integer stepCurrent) {
        WfReProcdefQuery wfReProcdefQuery = new WfReProcdefQuery();
        wfReProcdefQuery.setId(id);
        wfReProcdefQuery.setStepCurrent(stepCurrent);
        return singleResult(wfReProcdefQuery);
    }

    public List<WfReProcdef> queryList(WfReProcdefQuery wfReProcdefQuery){
        return wfReProcdefMapper.queryList(wfReProcdefQuery);
    }

    public void delete(String wfReProcdefId) {
        wfReProcdefMapper.delete(wfReProcdefId);
    }
}