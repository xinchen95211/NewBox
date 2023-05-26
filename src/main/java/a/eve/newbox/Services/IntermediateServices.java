package a.eve.newbox.Services;

import a.eve.newbox.pojo.Intermediate;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IntermediateServices extends IService<Intermediate> {
    List<Intermediate> get_for_userdataid(Long id);
    List<Intermediate> get_for_classifiID(Long id);

    boolean remove_for_classifiID(Long id);
    boolean remove_for_userdataID(Long id);
}
