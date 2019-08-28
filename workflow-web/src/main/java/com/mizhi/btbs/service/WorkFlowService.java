package com.mizhi.btbs.service;

import com.mizhi.btbs.data.mapper.WfReDeploymentMapper;
import com.mizhi.btbs.data.mapper.WfReProcdefMapper;
import com.mizhi.btbs.domain.dto.WfReDeploymentDTO;
import com.mizhi.btbs.domain.model.WfReDeployment;
import com.mizhi.btbs.domain.model.WfReProcdef;
import com.mizhi.btbs.domain.query.WfReProcdefQuery;
import com.mizhi.btbs.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class WorkFlowService {

    @Autowired
    private WfReDeploymentMapper wfReDeploymentMapper;
    @Autowired
    private WfReProcdefMapper wfReProcdefMapper;

    public void load(String id) {
        Assert.hasLength(id,"id is not null");
        WfReDeployment wfReDeployment = wfReDeploymentMapper.load(id);
        if(wfReDeployment == null){
            return;
        }

        WfReProcdefQuery wfReProcdefQuery = new WfReProcdefQuery();
        wfReProcdefQuery.setReDeployId(id);
        List<WfReProcdef> wfReProcdefs = wfReProcdefMapper.queryList(wfReProcdefQuery);
        //TODO:wait
    }

    @Transactional
    public void edit(WfReDeploymentDTO wfReDeploymentDTO){
        validate(wfReDeploymentDTO);

        String reDeployId = wfReDeploymentDTO.getId();

        WfReDeployment wfReDeployment = new WfReDeployment(reDeployId);
        wfReDeployment.setGroupId(wfReDeployment.getGroupId());
        wfReDeployment.setWfType(wfReDeploymentDTO.getWfType());
        wfReDeployment.setStepNum(wfReDeploymentDTO.getStepNum());

        if(StringUtils.isEmpty(reDeployId)){
            wfReDeploymentMapper.insert(wfReDeployment.setId(IDUtil.generate()));
        } else {
            wfReDeploymentMapper.update(wfReDeployment);
        }

        wfReDeploymentDTO.getProcdefs().forEach(o->{
            String procdefId = o.getId();

            WfReProcdef wfReProcdef = new WfReProcdef(procdefId);
            wfReProcdef.setGroupId(o.getCurrentGroupId());
            wfReProcdef.setWfType(wfReDeploymentDTO.getWfType());
            wfReProcdef.setExpressKeys(o.getExpressKeys());
            wfReProcdef.setExpressType(o.getExpressType());
            wfReProcdef.setOperateType(o.getOptType());

            if (StringUtils.isEmpty(procdefId)) {
                wfReProcdefMapper.insert(wfReProcdef.setId(IDUtil.generate()));
            } else {
                wfReProcdefMapper.update(wfReProcdef);
            }
        });
    }

    @Transactional
    public void delete(String id) {
        Assert.hasLength(id,"id is not null");

        WfReProcdefQuery wfReProcdefQuery = new WfReProcdefQuery();
        wfReProcdefQuery.setReDeployId(id);
        List<WfReProcdef> wfReProcdefs = wfReProcdefMapper.queryList(wfReProcdefQuery);
        if(!CollectionUtils.isEmpty(wfReProcdefs)){
            wfReProcdefs.forEach(o -> wfReProcdefMapper.delete(o.getId()));
        }
        wfReDeploymentMapper.delete(id);
    }

    private void validate(WfReDeploymentDTO wfReDeploymentDTO){

        Assert.hasLength(wfReDeploymentDTO.getGroupId(),"groupId is not null");
        Assert.hasLength(wfReDeploymentDTO.getWfType(),"wfType is not null");
        Assert.notEmpty(wfReDeploymentDTO.getProcdefs(),"procdef is not empty");
        Assert.notNull(wfReDeploymentDTO.getStepNum(),"step num is not null");

        wfReDeploymentDTO.getProcdefs().forEach(o->{
            Assert.hasLength(o.getCurrentGroupId(),"current groupId is not null");
            Assert.hasLength(o.getExpressKeys(),"express keys is not null");
            Assert.notNull(o.getExpressType(),"express type is not empty");
            Assert.notNull(o.getStep(),"step is not empty");
        });
    }
}
