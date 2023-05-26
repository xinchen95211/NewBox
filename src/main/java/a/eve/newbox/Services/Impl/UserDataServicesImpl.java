package a.eve.newbox.Services.Impl;

import a.eve.newbox.Mapper.UserDataMapper;
import a.eve.newbox.Services.UserDataServices;
import a.eve.newbox.pojo.Intermediate;
import a.eve.newbox.pojo.UserData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDataServicesImpl extends ServiceImpl<UserDataMapper, UserData> implements UserDataServices {
    @Override
    public List<UserData> list_for_intermedia(List<Intermediate> list) {
        return list.stream()
                .map(intermediate -> getById(intermediate.getUserDataID()))
                .collect(Collectors.toList());
    }
}
