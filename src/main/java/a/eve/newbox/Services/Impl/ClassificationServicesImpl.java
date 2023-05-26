package a.eve.newbox.Services.Impl;

import a.eve.newbox.Mapper.ClassificationMapper;
import a.eve.newbox.Services.ClassificationServices;
import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.Intermediate;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassificationServicesImpl extends ServiceImpl<ClassificationMapper,Classification> implements ClassificationServices {

    @Override
    public List<Classification> list_for_intermedia(List<Intermediate> list) {
//        List<Classification> alist = new ArrayList<>();
//        for (Intermediate intermediate : list) {
//            Classification byId = getById(intermediate.getClassificationID());
//            alist.add(byId);
//        }
//        return alist;
        return list.stream()
                .map(intermediate -> getById(intermediate.getClassificationID()))
                .collect(Collectors.toList());
    }
}
