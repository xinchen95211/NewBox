package a.eve.newbox.Services.Impl;

import a.eve.newbox.Mapper.IntermediateMapper;
import a.eve.newbox.Services.IntermediateServices;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.Intermediate;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntermediateServicesImpl extends ServiceImpl<IntermediateMapper, Intermediate> implements IntermediateServices {
    @Override
    public List<Intermediate> get_for_userdataid(Long id) {
        LambdaQueryWrapper<Intermediate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Intermediate::getUserDataID,id);
        List<Intermediate> list = list(lambdaQueryWrapper);
        return list;
    }

    @Override
    public List<Intermediate> get_for_classifiID(Long id) {
        LambdaQueryWrapper<Intermediate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Intermediate::getClassificationID,id);
        List<Intermediate> list = list(lambdaQueryWrapper);
        return list;
    }

    @Override
    public boolean remove_for_classifiID(Long id) {
        LambdaQueryWrapper<Intermediate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Intermediate::getClassificationID,id);
        boolean remove = remove(lambdaQueryWrapper);
        return remove;
    }

    @Override
    public boolean remove_for_userdataID(Long id) {
        LambdaQueryWrapper<Intermediate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Intermediate::getUserDataID,id);
        boolean remove = remove(lambdaQueryWrapper);
        return remove;
    }
}
