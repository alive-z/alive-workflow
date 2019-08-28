package com.mizhi.btbs.manager;

import com.mizhi.btbs.data.mapper.WfRuTaskMapper;
import com.mizhi.btbs.domain.model.WfRuTask;
import com.mizhi.btbs.domain.query.WfRuTaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class WfRuTaskManager  {
    private static final Logger log = LoggerFactory.getLogger(WfRuTaskManager.class);

    @Autowired
    private WfRuTaskMapper wfRuTaskMapper;

    public WfRuTask load(String wfRuTaskId) {
        return wfRuTaskMapper.load(wfRuTaskId);
    }

    public void insert(WfRuTask wfRuTask) {
        wfRuTaskMapper.insert(wfRuTask);
    }

    public void update(WfRuTask wfRuTask) {
        wfRuTaskMapper.insert(wfRuTask);
    }

    public List<WfRuTask> queryList(WfRuTaskQuery wfRuTaskQuery) {
        return wfRuTaskMapper.queryList(wfRuTaskQuery);
    }

    public WfRuTask singleResult(WfRuTaskQuery wfRuTaskQuery){
        List<WfRuTask> wfRuTasks = wfRuTaskMapper.queryList(wfRuTaskQuery);
        if (CollectionUtils.isEmpty(wfRuTasks)) {
            return null;
        }
        return wfRuTasks.get(0);
    }

    public void delete(String wfRuTaskId){
        wfRuTaskMapper.delete(wfRuTaskId);
    }

    public void delete(String wfType, String bizId){

        WfRuTaskQuery wfRuTaskQuery = new WfRuTaskQuery();
        wfRuTaskQuery.setWfType(wfType).setBizId(bizId);
        List<WfRuTask> wfRuTasks = queryList(wfRuTaskQuery);
        if(CollectionUtils.isEmpty(wfRuTasks)){
            return;
        }
        //delete old
        wfRuTasks.forEach(o -> delete(o.getId()));
    }
}