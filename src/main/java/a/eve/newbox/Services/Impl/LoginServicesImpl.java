package a.eve.newbox.Services.Impl;

import a.eve.newbox.Mapper.LoginMapper;
import a.eve.newbox.Services.LoginServices;
import a.eve.newbox.pojo.Login;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LoginServicesImpl extends ServiceImpl<LoginMapper, Login> implements LoginServices {
}
