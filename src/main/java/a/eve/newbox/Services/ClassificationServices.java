package a.eve.newbox.Services;


import a.eve.newbox.pojo.Classification;
import a.eve.newbox.pojo.Intermediate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ClassificationServices extends IService<Classification>{
     List<Classification> list_for_intermedia(List<Intermediate> list);
}
