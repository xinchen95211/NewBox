package a.eve.newbox.Services;

import a.eve.newbox.pojo.Intermediate;
import a.eve.newbox.pojo.UserData;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserDataServices extends IService<UserData> {

    List<UserData> list_for_intermedia(List<Intermediate> list);
}
